package concurrency.conn.concurrentcollections.copyonwritearrayset;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

class CopyOnWriteArraySetMain {
    public static void main(String[] args) {
        Set<String> set = new CopyOnWriteArraySet<>();

        // 여러 스레드가 세트에 데이터 삽입
        for (int i = 0; i < 3; i++) {
            final int threadId = i;
            Thread writer = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    String value = "Thread " + threadId + " - Element " + j;
                    set.add(value);
                    System.out.println("Inserted: " + value);
                    try {
                        Thread.sleep((int) (Math.random() * 100));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            writer.start();
        }

        // 여러 스레드가 세트에서 데이터 읽기
        for (int i = 0; i < 3; i++) {
            Thread reader = new Thread(() -> {
                while (true) {
                    set.forEach(value -> {
                        System.out.println("Read: " + value);
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
            reader.start();
        }
    }
}
