package concurrency.conn.waitandnotify;

class Producer implements Runnable {
    private final MessageQueue messageQueue;

    public Producer(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        int value = 0;
        while (true) {
            synchronized (messageQueue.lock) {
                while (messageQueue.queue.size() == messageQueue.MAX_SIZE) {
                    try {
                        messageQueue.lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                messageQueue.queue.offer(value);
                System.out.println("Produced: " + value);
                value++;
                messageQueue.lock.notifyAll();
                try {
                    Thread.sleep(500); // 생산 속도 조절을 위해 잠시 대기
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}