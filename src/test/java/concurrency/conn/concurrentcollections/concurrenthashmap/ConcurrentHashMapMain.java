package concurrency.conn.concurrentcollections.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;

class ConcurrentHashMapMain {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
//        HashMap<String, Integer> map = new HashMap<>(); -> 일반 HashMap을 사용시 ConcurrentModificationException 발생
        // 데이터 삽입
        map.put("Apple", 1);
        map.put("Banana", 2);
        // 병렬로 데이터 접근
        Thread readThread = new Thread(() -> {
            for(String key: map.keySet()){
                System.out.println(key + ": " + map.get(key));
            }
        });
        //
        Thread writeThread = new Thread(() -> {
            map.put("Orange", 3);
            map.put("Grapes", 4);
        });

        readThread.start();
        writeThread.start();

        try {
            readThread.join();
            writeThread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
