package concurrency.conn.waitandnotify;


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