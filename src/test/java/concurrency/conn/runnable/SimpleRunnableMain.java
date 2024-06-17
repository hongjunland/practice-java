package concurrency.conn.runnable;

class SimpleRunnableMain {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new SimpleRunnable());
        Thread thread2 = new Thread(new SimpleRunnable());

        thread1.start();
        thread2.start();
    }
}
