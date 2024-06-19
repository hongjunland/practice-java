
# Volatile
`volatile`키워드는 변수의 값을 메인 메모리에 직접 저장하고, 읽을 때도 메인 메모리에서 직접 읽도록 보장합니다.
이는 변수의 값이 여러 스레드에 의해 일관되게 읽히고 쓰이도록 합니다. 하지만 `volitile`은 원자성을 보장하지 않으므로, 복잡한 연산에는 적합하지 않습니다.

### 장점
* 간단한 사용법: 변수 앞에 `volatile` 키워드만 추가해도 됩니다.
* 변수 가시성 보장: 여러 스레드 간의 변수의 값을 일관되게 유지합니다.
* 성능: `Memory barrier`를 사용하여 성능을 저하시키지 않고 가시성을 보장

### 단점
* 원자성 미보장: 복잡한 연산에 적합하지 않습니다.
* 제한적인 사용: 단순한 상태 플래그나 카운터와 같은 용도로만 적합합니다.

```java
class VolatileMain {
    private static volatile boolean flag = true;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (flag) {
                // Do something
            }
            System.out.println("Thread 1 finished");
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            flag = false;
            System.out.println("Thread 2 set flag to false");
        });

        t1.start();
        t2.start();
    }
}
```