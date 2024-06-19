# Thread Pool
스레드 풀을 사용ㅎ는 이유는 주로 성능 최적화, 자원 관리, 응답 시간 단축, 그리고 시스템 안정성을 확보하기 위해서입니다.

## 장점

* 재사용 가능한 스레드: 미리 생성된 스레드를 재사용하여, 스레드 생성 및 종료에 따른 오버헤드를 줄입니다.
* 작업 큐 관리: 대기 중인 작업을 큐에 저장하고, 스레드가 사용 가능해지면 큐에서 작업을 가져와 실행합니다.
* 성능 최적화
* 동시성 제어: 동시에 실행되는 스레드 수를 제한함으로써, 과도한 동시성으로 인한 리소스 부족 문제를 발생합니다.
* 관리 용이성: 생명주기가 스레드 풀로 관리 되어서, 시스템 리소스를 효율적으로 할당할 수 있습니다.

## 고정 크기 
* Main
    ```java
    class FixedThreadPoolMain {
        public static void main(String[] args) {
            ExecutorService executorService = Executors.newFixedThreadPool(3);
            for(int i = 0 ; i < 10; i++){
                int taskNumber = i;
                executorService.submit(() -> {
                    System.out.println("Executing Task " + taskNumber + " by " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                    }
                });
            }
            executorService.shutdown();
        }
    }
    ```

## 가변 크기
* Main
    ```java
    class CachedThreadPoolMain {
        public static void main(String[] args) {
            ExecutorService executorService = Executors.newCachedThreadPool();
    
            for(int i = 0 ; i < 100 ; i++){
                int taskNumber = i;
                executorService.submit(()->{
                    System.out.println("Executing Task " + taskNumber + " by " + Thread.currentThread().getName());
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                    }
                });
            }
    
            executorService.shutdown();
        }
    }
    ```
