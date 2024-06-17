package concurrency.conn.synchronizedthread;

public class Counter implements Countable{
    private int count = 0;

    @Override
    public void increment() {
        count++;
    }

    @Override
    public int getCount() {
        return count;
    }
}
