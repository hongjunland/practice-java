# Synchronized Thread
`synchronized` 키워드를 사용하여 스레드간의 동기화 문제를 해결합니다.

* Countable
    ```java
    public interface Countable {
        int getCount();
        void increment();
    }
    ```

* Counter
    ```java
    public class Counter implements Countable{
        private int count = 0;
    
        @Override
        public void increment() {
            count++;
        }
    
        @Override
        public int getCount() {
            return count;
        }
    }
    ```

* SynchronizedCounter
    ```java
    class SynchronizedCounter implements Countable{
        private int count = 0;
    
        @Override
        public synchronized void increment() {
            count++;
        }
    
        @Override
        public int getCount() {
            return count;
        }
    }
    ```
  
* CounterRunnable
    ```java
    public class CounterRunnable implements Runnable{
        private final Countable counter;
    
        public CounterRunnable(Countable counter) {
            this.counter = counter;
        }
    
        @Override
        public void run() {
            for (int i = 0 ; i < 1000; i++){
                counter.increment();
            }
        }
    }
    ```
  
* Main
    ```java
    public class SynchronizedMain {
        public static void main(String[] args) throws InterruptedException{
            Countable counter = new Counter();
            Countable synchronizedCounter = new SynchronizedCounter();
            List<Thread> threadList = List.of(new Thread(new CounterRunnable(counter)), new Thread(new CounterRunnable(counter)), new Thread(new CounterRunnable(counter)));
            List<Thread> syncronizedthreadList = List.of(new Thread(new CounterRunnable(synchronizedCounter)), new Thread(new CounterRunnable(synchronizedCounter)), new Thread(new CounterRunnable(synchronizedCounter)));
    
            for (Thread thread: threadList){
                thread.start();
            }
            for (Thread syncThread: syncronizedthreadList){
                syncThread.start();
            }
    
            for (Thread thread: threadList){
                thread.join();
            }
            for (Thread syncThread: syncronizedthreadList){
                syncThread.join();
            }
    
            System.out.println("count: " + counter.getCount());
            System.out.println("Synchronized count: " + synchronizedCounter.getCount());
        }
    }
    ```
  
* Output
    ```bash
    count: 2678
    Synchronized count: 3000
    ```