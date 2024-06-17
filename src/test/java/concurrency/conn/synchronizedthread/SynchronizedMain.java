package concurrency.conn.synchronizedthread;

import java.util.List;

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
