package concurrency.conn.concurrentcollections.copyonwritearraylist;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class CopyOnWriteArrayListMain {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();

        // 여러 스레드가 리스트에 데이터 삽입
        for (int i = 0; i < 3; i++) {
            final int threadId = i;
            Thread writer = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    String value = "Thread " + threadId + " - Element " + j;
                    list.add(value);
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

        // 여러 스레드가 리스트에서 데이터 읽기
        for (int i = 0; i < 3; i++) {
            Thread reader = new Thread(() -> {
                while (true) {
                    list.forEach(value -> {
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
