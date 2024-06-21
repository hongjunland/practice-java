# Asynchronous Programming
비동기 프로그래밍은 동시성 문제를 해결하고, 비동기 작업을 효율적으로 처리하는 방법입니다. 비동기 프로그래밍에서는 작업이 완료될 때까지 기다리지 않고, 다른 작업을 수행할 수 있도록 합니다. 

## Callback
`Callback`은 비동기 작업이 완료된 후에 실행되는 함수입니다. 작업이 완료되었을 때 특정한 동작을 수행할 수 있습니다.

```java
class CallbackMain {

    interface Callback {
        void onComplete(String result);
    }

    public static void asyncOperation(Callback callback) {
        new Thread(() -> {
            try {
                // 비동기 작업 시뮬레이션
                Thread.sleep(2000);
                callback.onComplete("Operation completed");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    public static void main(String[] args) {
        System.out.println("Starting async operation...");
        asyncOperation(result -> System.out.println("Callback received: " + result));
        System.out.println("Async operation started");
    }
}
```

## CompletableFuture 
`CompletableFutre`는 Java 8에 도입된 비동기 프로그래밍을 위한 클래스입니다. 비동기 작업을 쉽게 관리하고, 조합할 수 있도록 다양한 메서드를 제공합니다.

### Method
* Run
  * runAsync: return void
  * supplyAsync: return callable
* Callback
  * thenApply: Callable, 반환 값을 받아 처리 
  * thenAccept: Void, 반환 값을 받아 처리
  * thenRun: 반환 값을 받지 않고 다른 작업을 실행함
* Compose tasks
  * thenCompose: 두 작업이 이어서 실행하도록 조합하여, 앞선 작업의 결과를 받아서 사용할 수 있음.
  * thenCombine: 두 작업을 독립적으로 실행하고, 둘 다 완료되었을 때 콜백을 실행
  * allOf: 여러 작업들을 동시에 실행하고, 모든 작업 결과에 콜백을 실행
  * anyOf: 가장 빨리 끝난 하나의 결과에 콜백을 실행
* Handling
  * exceptionally: 발생한 에러를 받아서 예외를 처리함
  * handle, handleAsync: 결과값 or 에러를 반환받아 발생한 경우와 아닌 경우 모두 처리할 수 있음

```java
class CompletableFutureMain {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // 비동기 작업 수행
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello, World!";
        });

        // 결과 출력
        future.thenAccept(result -> {
            System.out.println(result);
        });

        // 메인 스레드가 즉시 종료되지 않도록 대기
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

## Asynchronous Streams
비동기 스트림은 비동기 작업의 결과를 스트림 형태로 처리할 수 있게 합니다. Java에서는 `Flow` API가 이를 지원합니다. 
`Flow` API는 `Publisher`, `Subscriber`, `Subscription`, `Processor` 인터페이스로 구성되어 있으며, `Reactive Streams` 표준을 따릅니다.

```java
class AsynchronousStreamsMain {
    public static void main(String[] args) throws InterruptedException {
        // Publisher 생성
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        // Subscriber 생성
        Flow.Subscriber<String> subscriber = new Flow.Subscriber<>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                subscription.request(1); // 첫 번째 항목 요청
            }

            @Override
            public void onNext(String item) {
                System.out.println("Received: " + item);
                subscription.request(1); // 다음 항목 요청
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Done");
            }
        };

        // Subscriber를 Publisher에 등록
        publisher.subscribe(subscriber);
        
        // 항목 발행
        String[] items = {"item1", "item2", "item3"};
        for(String item : items){
            publisher.submit(item);
            System.out.println("Published: " + item);
            Thread.sleep(500);
        }
        
        // 발행 완료
        publisher.close();
        TimeUnit.SECONDS.sleep(1);
    }
}
```