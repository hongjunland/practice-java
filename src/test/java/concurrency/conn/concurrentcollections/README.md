# Concurrent Collections
멀티스레드 환경에서 안전하게 사용할 수 있도록 설계된 자료 구조입니다. `Java`는 `java.util.concurrent` 패키지에서 여러 동시성 컬렉션을 제공합니다. 이 컬렉션들은 내부적으로 동기화 메커니즘을 사용하여 `Thread-safe`을 보장합니다.

## 목록
1. ConcurrentHashMap
2. ConcurrentLinkedQueue
3. ConcurrentSkipListMap
4. ConcurrentSkipListSet
5. CopyOnWriteArrayList
6. CopyOnWriteArraySet

## ConcurrentHashMap

### 사용하는 이유
* Thread-safety
* 고성능
* 부분 Lock
### 라이브러리 사례
* Java Concurrency Utilities: Java의 동시성 유틸리티 라이브러리에서 널리 사용됩니다.
* Apache Spark: 내부에서 데이터 공유와 캐싱을 위해 사용됩니다.
* Spring Framework: `Bean` 관리 및 여러 동시성 작업에서 사용됩니다.

### 예시 코드
```java
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
```

## ConcurrentLinkedQueue

### 사용하는 이유
* Thread safety
* Non-Blocking
* FIFO

### 라이브러리 사례
* Java Concurrency Utilities: Java의 동시성 유틸리티 라이브러리에서 사용됩니다.
* Netty: 네트워크 애플리케이션에서 이벤트를 큐에 넣고 처리하는 데 사용됩니다.
* Akka: 액터 기반 애플리케이션에서 메시지 큐로 사용됩니다.

### 예시 코드
```java
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
```

## ConcurrentSkipListMap

### 사용 이유
* Thread-Safety
* 정렬된 맵: `key`의 자연적인 순서나 사용자 정의 비교자에 따라 정렬된 상태로 데이터를 유지합니다.
* 고성능 읽기: 읽기 연산이 매우 빠르며, 쓰기 연산도 고성능을 보입니다.

### 라이브러리 사례
* Apache Kafka: `ConcurrentSkipListMap`을 사용하여 `Message Queue`를 관리하고, 메시지를 정렬된 순서로 처리합니다.
* Elasticsearch: 내부에서 메타데이터 관리와 캐싱을 위해 사용합니다.

### 예시 코드
```java
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
```

## ConcurrentSkipListSet

### 사용하는 이유
- Thread-Safety
- Sorted Set
- 고성능 읽기
### 라이브러리 사례
- Spring Framework: `ConcurrentSkipListSet`을 사용하여 이벤트 리스너 관리와 같은 여러 스레드에서 접근 가능한 데이터를 정렬된 상태로 유지합니다.
- Cassandra: 데이터 정렬 및 중복 방지를 위해 사용됩니다.

### 예시 코드
```java
class ConcurrentSkipListSetMain {
    public static void main(String[] args) {
        ConcurrentSkipListSet<Integer> set = new ConcurrentSkipListSet<>();

        // 여러 스레드가 세트에 데이터 삽입
        for (int i = 0; i < 3; i++) {
            final int threadId = i;
            Thread writer = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    int value = threadId * 10 + j;
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
```

## CopyOnWriteArrayList

### 사용하는 이유
* 스레드 안전성: 쓰기 연산이 발생할 때마다 내부 배열의 복사본을 만듭니다. 이를 통해 읽기 연산과 쓰기 연산이 동시에 발생해도 안전합니다.
* 읽기 최적화: 읽기 연산이 매우 빠르며, 쓰기 빈도가 낮고 읽기 빈도가 높은 경우에 적합합니다.
* 일관성 유지: 여러 스레드가 데이터를 안전하게 읽고 쓸 수 있으며, 일관성을 유지합니다.

### 라이브러리 사례
* Java Swing: GUI 이벤트 리스너를 관리할 때 사용됩니다. 이벤트 리스너 목록은 주로 읽기 연산이 많고, 쓰기 연산이 적기 때문에 적합합니다.
* Netty: 비동기 이벤트 기반 네트워크 애플리케이션 프레임워크에서 채널 핸들러 목록을 관리할 때 사용됩니다.

### 예시 코드
```java
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
```
## CopyOnWriteArraySet

### 사용하는 이유
- Thread-safety
- 읽기 최적화: 읽기 연산이 매우 빠르며, 쓰기 빈도가 낮고 읽기 빈도가 높은 경우에 적합합니다.
- 중복 방지: 중복 요소를 허용하지 않습니다.

### 라이브러리 사례
- Java Concurrency Utilities: 다양한 동시성 유틸리티 클래스에서 사용됩니다. 주로 읽기 작업이 빈번하고, 쓰기 작업이 드물게 발생하는 경우에 적합합니다.
- Akka: 액터 기반 애플리케이션에서 메시지 핸들러 목록을 관리할 때 사용됩니다.

### 예시코드
```java
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
```

## 비교/요약
| 특성      | 	ConcurrentSkipListMap/Set             | 	CopyOnWriteArrayList/Set |
|---------|----------------------------------------|---------------------------|
| 동시성	    | 높은 동시성 지원	                             | 낮은 동시성 지원 (쓰기 작업 시 배열 복사) |
| 읽기 작업	  | 효율적 (O(log n))	                        | 매우 효율적 (O(1))             |
| 쓰기 작업	  | 효율적 (O(log n)), 다만 쓰기 빈도가 매우 높을 때는 비쌈	 | 비효율적 (쓰기 작업 시 배열 복사)      |
| 정렬	     | 자동 정렬 (Skip List 구조)	                  | 정렬되지 않음                   |
| 일관성 유지	 | Fine-grained Locking 사용                | 	배열 복사로 일관성 유지            |
| 사용 사례	  | 고빈도 읽기/쓰기, 정렬된 데이터 필요                  | 	고빈도 읽기/저빈도 쓰기, 불변 데이터    |