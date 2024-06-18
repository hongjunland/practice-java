package concurrency.conn.concurrentcollections.concurrentskiplistmap;

import java.util.concurrent.ConcurrentSkipListMap;

class ConcurrentSkipListMapMain {
    public static void main(String[] args) {
        ConcurrentSkipListMap<Integer, String> map = new ConcurrentSkipListMap<>();

        // 여러 스레드가 맵에 데이터 삽입
        for (int i = 0; i < 3; i++) {
            final int threadId = i;
            Thread writer = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    int key = threadId * 10 + j;
                    String value = "Value " + key;
                    map.put(key, value);
                    System.out.println("Inserted: " + key + " = " + value);
                    try {
                        Thread.sleep((int) (Math.random() * 100));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            writer.start();
        }

        // 여러 스레드가 맵에서 데이터 읽기
        for (int i = 0; i < 3; i++) {
            Thread reader = new Thread(() -> {
                while (true) {
                    map.forEach((key, value) -> {
                        System.out.println("Read: " + key + " = " + value);
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
