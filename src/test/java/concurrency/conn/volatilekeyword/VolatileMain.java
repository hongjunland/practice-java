package concurrency.conn.volatilekeyword;

class VolatileMain {
    private static volatile boolean flag = true;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (flag) {
                // Do something
            }
            System.out.println("Thread 1 finished");
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            flag = false;
            System.out.println("Thread 2 set flag to false");
        });

        t1.start();
        t2.start();
    }
}
