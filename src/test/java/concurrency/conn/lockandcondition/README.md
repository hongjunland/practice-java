# Lock & Condition
`Lock`과 `Condition`을 사용하여 동기화 메커니즘을 구현해보았습니다. 해당 기능들을 사용하는 이유는 기존의 `synchronized`와 `wait`, `notify`, `notifyAll` 메서드로는 제공되지 않는 몇 가지 유용한 기능을 제공하기 때문입니다. 이를 통해 더 세밀한 제어와 향상된 동시성 관리를 할수 있습니다.

## Lock
`Lock`은 동기화 접근을 제어하기 위해 사용되는 인터페이스입니다. `Java`에서 가장 많이 사용되는 `Lock` 구현체는 `ReentrantLock`입니다. `ReentrantLock`은 재진입 가능 `Lock`이며, 동일한 스레드가 이미 획득한 `Lock`을 여러 번 획득할 수 있습니다.
- 주요 메서드
    - `lock()`: `Lock`을 획득합니다. 다른 스레드가 이미 가지고 있으면, `Lock`을 획득할 때 까지 대기합니다.
    - `unlock()`: `Lock`을 해제합니다.
    - `tryLock()`: `Lock`을 시도하여 즉시 획득할 수 있는지 확인합니다. `Lock`을 획득하면 `true`를 반환하고, 그렇지 않으면 `false`를 반환합니다.
    - `lockInterruptibly()`: 인터럽트가 가능한 상태로 `Lock`을 획득합니다. 다른 스레드가 `Lock`을 가지고 있는 동안 인터럽트가 발생하면 예외를 던집니다.

## Condition
`Condition`은 `Object` 클래스의 `wait`, `notify`, `notifyAll`메서드와 유사한 동기화 메커니즘을 제공합니다.
`Condition`객체는 `Lock`객체와 함께 사용됩니다. 하나의 `Lock`객체는 여러개의 `Condition`객체를 가질 수 있어 보다 세밀한 제어가 가능합니다.

- 주요 메서드
  - `await()`: 조건이 출족될 때까지 대기합니다. `Object`클래스의 `wait()`와 유사합니다.
  - `signal()`: 대기 중인 스레드의 하나를 깨웁니다. `Object`클래스의 `notify()`와 유사합니다.
  - `signalAll()`: 대기 중인 모든 스레드를 깨웁니다. `Object`클래스의 `notifyAll()`와 유사합니다.

## 예시 코드
* MessageQueue
    ```java
    class MessageQueue {
        private final Queue<Integer> queue = new LinkedList<>();
        private final int MAX_SIZE = 5;
        private final Lock lock = new ReentrantLock();
        private final Condition notEmpty = lock.newCondition();
        private final Condition notFull = lock.newCondition();
    
        public void put(int value) throws InterruptedException{
            lock.lock();
            try {
                while(queue.size() == MAX_SIZE){
                    notFull.await();
                }
                queue.offer(value);
                System.out.println("Produced: " + value);
                notEmpty.signal();
            }finally {
                lock.unlock();
            }
        }
    
        public int take() throws InterruptedException{
            lock.lock();
            try {
                while (queue.isEmpty()){
                    notEmpty.await();
                }
                int value = queue.poll();
                System.out.println("Consumed: "+ value);
                notFull.signal();
                return value;
            }finally {
                lock.unlock();
            }
        }
    }
    ```
  
* Producer
    ```java
    class Producer implements Runnable{
        private final MessageQueue messageQueue;
    
        public Producer(MessageQueue messageQueue) {
            this.messageQueue = messageQueue;
        }
    
        @Override
        public void run() {
            int value = 0;
            while (true){
                try {
                    messageQueue.put(value);
                    value++;
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    ```
  
* Consumer
    ```java
    class Consumer implements Runnable{
        private final MessageQueue messageQueue;
    
        public Consumer(MessageQueue messageQueue) {
            this.messageQueue = messageQueue;
        }
    
        @Override
        public void run() {
            while (true){
                try {
                    messageQueue.take();
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    ```
  
* Main
```java
class LockAndConditionMain {
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();
        Thread producerThread = new Thread(new Producer(messageQueue));
        Thread consumerThread = new Thread(new Consumer(messageQueue));

        producerThread.start();
        consumerThread.start();
    }
}
```

* Output
    ```shell
    Produced: 0
    Consumed: 0
    Produced: 1
    Consumed: 1
    Produced: 2
    Produced: 3
    Consumed: 2
    Produced: 4
    Produced: 5
    Consumed: 3
    Produced: 6
    Produced: 7
    # 무한루프...
    ```