package concurrency.conn.synchronizedthread;

class SynchronizedCounter implements Countable{
    private int count = 0;

    @Override
    public synchronized void increment() {
        count++;
    }

    @Override
    public int getCount() {
        return count;
    }
}
