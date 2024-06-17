package concurrency.conn.simplethread;

import org.junit.jupiter.api.Test;

class SimpleThreadMain {
    public static void main(String[] args) {
        SimpleThread thread1 = new SimpleThread();
        SimpleThread thread2 = new SimpleThread();
        SimpleThread thread3 = new SimpleThread();

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
