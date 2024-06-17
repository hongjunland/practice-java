package concurrency.conn.waitandnotify;


public class WaitAndNotifyMain {

    public static void main(String[] args) {
        MessageQueue producerConsumer = new MessageQueue();
        Thread producerThread = new Thread(new Producer(producerConsumer));
        Thread consumerThread = new Thread(new Consumer(producerConsumer));

        producerThread.start();
        consumerThread.start();
    }
}
