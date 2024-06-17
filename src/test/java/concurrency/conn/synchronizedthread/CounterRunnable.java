package concurrency.conn.synchronizedthread;

public class CounterRunnable implements Runnable{
    private final Countable counter;

    public CounterRunnable(Countable counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0 ; i < 1000; i++){
            counter.increment();
        }
    }
}
