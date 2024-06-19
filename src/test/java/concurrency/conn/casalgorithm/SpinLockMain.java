package concurrency.conn.casalgorithm;

import java.util.concurrent.atomic.AtomicBoolean;

class SpinLockMain {

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        Runnable task = () -> {
            spinLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " acquired the lock");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                System.out.println(Thread.currentThread().getName() + " released the lock");
                spinLock.unlock();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
    }
}

class SpinLock{
    private final AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {
        while (!lock.compareAndSet(false, true)) {
            // Spin-wait (busy-wait)
        }
    }

    public void unlock() {
        lock.set(false);
    }
}
