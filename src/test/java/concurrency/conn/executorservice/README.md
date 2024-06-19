# Executors and Thread Pool
`ExecutorService`를 사용하면 스레드 풀을 생성하고 태스크를 효율적으로 관리할 수 있습니다.
스레드 풀은 스레드 생성을 제한하고, 재사용할 수 있는 스레드 집합을 관리하여 성능을 최적화 합니다.

## Executors
`Executors` 클래스는 `Executor` 및 `ExecutorService`를 생성하는 팩토리 메서드를 제공하는 Utility 클래스입니다. 주로 스레드 풀을 생성하는 데 사용됩니다.

### Method
- newFixedThreadPool(int nThreads)
    - 고정된 크기의 스레드 풀을 생성합니다.
    - `nThread` 만큼의 스레드를 생성하여, 태스크를 처리합니다.
- newCachedThreadPool()
  - 필요에 따라 새로운 스레드를 생성하며, 이전에 생성된 스레드를 재사용합니다
  - 스레드가 사용 가능한 재사용되며, 60초 이상 유휴 상태인 스레드는 제거됩니다.
- newSingleThreadExecutor()
  - 단일 스레드로 태스크를 처리하는 `ExecutorService`를 생성합니다.
  - 모든 태스크가 순차적으로 실행됩니다.
- newScheduledThreadPool(int corePoolSize)
  - 주기적으로 실행되는 태스크를 스케줄링할 수 있는 스레드 풀을 생성합니다.

## ExecutorService
`ExecutorService`는 비동기 작업을 실행하는 서비스입니다. 스레드 풀을 관리하고 태스크를 제출하고 실행하며, 결과를 처리하는 데 사용됩니다.

### Method
* submit(Runnable task): Future<?>
  * 주어진 `Runnable` 태스크를 실행합니다.
  * 태스크의 완료 상태를 추적할 수 있습니다.
* submit(Callable<T> task): Future<?>
  * 주어진 `Callable` 테스크를 실행합니다.
  * 태스크의 결과를 추적하고 가져올 수 있습니다.
* invokeAll(Collection<? extends Callable<T>> tasks): List<Future<T>>
  * 주어진 `Callable` 태스크 컬렉션을 모두 실행합니다.
  * 모든 태스크가 완료될 때까지 대기합니다.
* invokeAny(Collection<? extends Callable<T>> tasks)
    * 주어진 `Callable` 태스크 컬렉션을 모두 실행합니다.
    * 가장 먼저 완료된 태스크의 결과를 반환하며, 다른 태스크는 취소됩니다.
* shutdown()
  * 새로운 태스크 제출을 중지하고, 이미 제출된 태스크가 완료될 때까지 기다립니다.
* shutdownNow()
  * 실행 중인 태스크를 중지하고, 대기 중인 태스크를 모두 취소합니다.
* awaitTermination(long timeout, TimeUnit unit)
  * `shutdown()` 또는 `shutdownNow()` 가 호출된 후, 모든 태스크가 완료되거나 주어진 시간동안 대기합니다.

## 예시 코드
* Main
    ```java
    class ExecutorServiceMain {
        public static void main(String[] args) {
            ExecutorService executorService = Executors.newFixedThreadPool(3);
    
            // Submit Runnable tasks
            Future<?> runnableFuture = executorService.submit(() -> {
                System.out.println("Executing Runnable Task by " + Thread.currentThread().getName());
            });
    
            // Submit Callable
            Future<Integer> callableFuture = executorService.submit(() -> {
                System.out.println("Executing Callable Task by " + Thread.currentThread().getName());
                return 42;
            });
    
            try {
                System.out.println("Runnable Task completed: " + runnableFuture.get());
                System.out.println("Callable Task result: " + callableFuture.get());
    
                // invokeAll
                List<Callable<Integer>> taskList = Arrays.asList(() -> 1, () -> 2, () -> 3);
                List<Future<Integer>> futures = executorService.invokeAll(taskList);
                for (Future<Integer> future : futures) {
                    System.out.println("invokeAll result: " + future.get());
                }
    
                // invokeAny
                Integer result = executorService.invokeAny(taskList);
                System.out.println("invokeAny result: " + result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
    
            // 스레드 풀 종료
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
            }
        }
    }
    ```
* Output
    ```shell
    Executing Runnable Task by pool-1-thread-1
    Executing Callable Task by pool-1-thread-2
    Runnable Task completed: null
    Callable Task result: 42
    invokeAll result: 1
    invokeAll result: 2
    invokeAll result: 3
    invokeAny result: 1
    ```