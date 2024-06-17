package concurrency.conn.lockandcondition;

class Producer implements Runnable{
    private final MessageQueue messageQueue;

    public Producer(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        int value = 0;
        while (true){
            try {
                messageQueue.put(value);
                value++;
                Thread.sleep(500);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
