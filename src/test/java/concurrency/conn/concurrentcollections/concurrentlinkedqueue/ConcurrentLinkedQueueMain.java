package concurrency.conn.concurrentcollections.concurrentlinkedqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

class ConcurrentLinkedQueueMain {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        System.out.println("ConcurrentLinkedQueue");
        // 생산자 스레드 생성
        Thread producer1 = new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                String element = "Producer 1 - Element " + i;
                queue.add(element);
                System.out.println("Produced: " + element);
                try {
                    Thread.sleep((int) (Math.random() * 100));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread producer2 = new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                String element = "Producer 2 - Element " + i;
                queue.add(element);
                System.out.println("Produced: " + element);
                try {
                    Thread.sleep((int) (Math.random() * 100));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // 소비자 스레드 생성
        Thread consumer1 = new Thread(() -> {
            while (true) {
                String element = queue.poll();
                if (element != null) {
                    System.out.println("Consumed by Consumer 1: " + element);
                } else {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
        });

        Thread consumer2 = new Thread(() -> {
            while (true) {
                String element = queue.poll();
                if (element != null) {
                    System.out.println("Consumed by Consumer 2: " + element);
                } else {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
        });

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();

        try {
            producer1.join();
            producer2.join();
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
