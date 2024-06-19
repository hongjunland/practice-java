package concurrency.conn.casalgorithm;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicIntegerMain {
    private static AtomicInteger atomicCounter = new AtomicInteger(0);
    public static void main(String[] args) {
        Runnable task = () -> {
            for(int i = 0 ; i < 1000; i++){
                atomicCounter.incrementAndGet();
            }
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        System.out.println("Final counter value: " + atomicCounter.get());
    }
}
