# Wait & Notify 
`wait`와 `notify`를 사용해서 멀티스레딩 환경에서 Producer-Consumer 문제를 해결하는 방식입니다.
producer와 consumer가 협력하여 작업을 수행합니다.

## 예제 설명
* Producer: Queue가 가득 차지 않았을 때, 항목을 추가합니다. 가득 찬 경우 `wait` 합니다.
* Consumer: Queue가 비어있지 않은 경우, 항목을 제거합니다. 비어있는 경우 `wait` 합니다.
* 두 스레드는 `notify` 를 사용하여 `wait` 중인 다른 스레드를 깨웁니다.

## 예시 코드
* MessageQueue
    ```java
    class MessageQueue {
        final Queue<Integer> queue = new LinkedList<>();
        final int MAX_SIZE = 5;
        final Object lock = new Object();
    }
    ```

* Producer
    ```java
    class Producer implements Runnable {
        private final MessageQueue messageQueue;
    
        public Producer(MessageQueue messageQueue) {
            this.messageQueue = messageQueue;
        }
    
        @Override
        public void run() {
            int value = 0;
            while (true) {
                synchronized (messageQueue.lock) {
                    while (messageQueue.queue.size() == messageQueue.MAX_SIZE) {
                        try {
                            messageQueue.lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    messageQueue.queue.offer(value);
                    System.out.println("Produced: " + value);
                    value++;
                    messageQueue.lock.notifyAll();
                    try {
                        Thread.sleep(500); // 생산 속도 조절을 위해 잠시 대기
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
    ```

* Consumer
    ```java
    class Consumer implements Runnable {
        private final MessageQueue messageQueue;
    
        public Consumer(MessageQueue messageQueue) {
            this.messageQueue = messageQueue;
        }
    
        @Override
        public void run() {
            while (true) {
                synchronized (messageQueue.lock) {
                    while (messageQueue.queue.isEmpty()) {
                        try {
                            messageQueue.lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    int value = messageQueue.queue.poll();
                    System.out.println("Consumed: " + value);
                    messageQueue.lock.notifyAll();
                    try {
                        Thread.sleep(1000); // 소비 속도 조절을 위해 잠시 대기
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
    ```
  
* Main
    ```java
    public class WaitAndNotifyMain {
    
        public static void main(String[] args) {
            MessageQueue producerConsumer = new MessageQueue();
            Thread producerThread = new Thread(new Producer(producerConsumer));
            Thread consumerThread = new Thread(new Consumer(producerConsumer));
    
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
    Consumed: 2
    Produced: 3
    Consumed: 3
    Produced: 4
    Produced: 5
    Consumed: 4
    //... 무한 루프
    ```