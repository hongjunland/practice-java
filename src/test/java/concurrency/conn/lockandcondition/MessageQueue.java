package concurrency.conn.lockandcondition;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MessageQueue {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int MAX_SIZE = 5;
    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    public void put(int value) throws InterruptedException{
        lock.lock();
        try {
            while(queue.size() == MAX_SIZE){
                notFull.await();
            }
            queue.offer(value);
            System.out.println("Produced: " + value);
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    public int take() throws InterruptedException{
        lock.lock();
        try {
            while (queue.isEmpty()){
                notEmpty.await();
            }
            int value = queue.poll();
            System.out.println("Consumed: "+ value);
            notFull.signal();
            return value;
        }finally {
            lock.unlock();
        }
    }
}
