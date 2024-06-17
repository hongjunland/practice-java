package concurrency.conn.lockandcondition;

class LockAndConditionMain {
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();
        Thread producerThread = new Thread(new Producer(messageQueue));
        Thread consumerThread = new Thread(new Consumer(messageQueue));

        producerThread.start();
        consumerThread.start();
    }
}
