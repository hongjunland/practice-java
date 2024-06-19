# Compare-And-Swap 
`CAS`알고리즘은 동시성 제어 방법중에 하나이며, `lock`을 사용하지 않는 방법입니다.

## 사용한 사례
* Atomic Variable
* Non-Blocking
* Spin Lock

## Atomic Variable
`Atomic Variable`은 여러 스레드가 동시에 접근해도 안전하게 값을 읽고 쓸 수 있는 변수를 말합니다.
이 변수들은 동시성 문제를 피하기 위해 `Lock`을 사용하지 않고도 스레드의 `consistency`과 `atomicity`를 보장합니다.
`Java`에서는 `java.util.concurrent.atomic` 패키지 내에 다양한 `Atomic Variable`을 제공합니다.

### 주요 아토믹 클래스
1. AtomicInteger: `int` 값을 원자적으로 업데이트할 수 있는 클래스
2. AtomicLong: `long` 값을 원자적으로 업데이트할 수 있는 클래스
3. AtomicBoolean: `boolean`값을 원자적으로 업데이트할 수 있는 클래스
4. AtomicReference: `reference`값을 원자적으로 업데이트할 수 있는 클래스

### 특징
* Atomicity: `Atomic Variable`이 제공하는 모든 연산은 원자적으로 수행됩니다. 즉, 중간 상태가 없이 한 번에 수행되며, 다른 스레드가 해당 변수를 읽거나 쓰는 동안 연산이 중단되지 않습니다.
* CAS(Compare-And-Swap) 알고리즘: `Atomic Variable`은 `CAS`알고리즘을 사용하여 값을 업데이트 합니다. 예상되는 값과 현재 값을 비교하고, 예상되는 값과 현재 값이 일치하면 새로운 값으로 원자적으로 교체하는 방법입니다.
* 성능 최적화: `Lock`을 사용하지 않기 때문에, 성능 면에서 우수합니다.

```java
class AtomicIntegerMain {
    private static AtomicInteger atomicCounter = new AtomicInteger(0);
    public static void main(String[] args) {
        Runnable task = () -> {
            for(int i = 0 ; i < 1000; i++){
                atomicCounter.incrementAndGet();
            }
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        System.out.println("Final counter value: " + atomicCounter.get());
    }
}
```

## Non-Blocking Algorithms
`lock`을 사용하지 않는 `non-blocking`알고리즘은 대기 없이 동시성을 제어하는 방법입니다. 대표적인 예로는 Lock-Free 자료구조가 있습니다. 스레드가 `lock`을 기다리지 않고도 동시성을 제어할 수 있도록 설계되어 있습니다.

### 장점
* 높은 성능: `lock`을 사용하지 않기 때문에 컨텍스트 스위칭과 같은 오버헤드가 적습니다.
* DeadLock 방지: `lock`을 사용하지 않기 때문에 발생하지 않습니다.
* 높은 확장성: 멀티스레드 환경에서 성능이 잘 확장됩니다.

### 단점
* 복잡성: 구현이 간단하지 않습니다.
* Starving: 일부 스레드가 무한 대기중일 수도 있습니다.

```java
class NonBlockingMain {
    public static void main(String[] args) {
        LockFreeStack<Integer> stack = new LockFreeStack<>();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}

class LockFreeStack<T>{
    private AtomicReference<Node<T>> head = new AtomicReference<>();

    private static class Node<T> {
        final T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }

    public void push(T value) {
        Node<T> newHead = new Node<>(value);
        Node<T> oldHead;
        do {
            oldHead = head.get();
            newHead.next = oldHead;
        } while (!head.compareAndSet(oldHead, newHead));
    }

    public T pop() {
        Node<T> oldHead;
        Node<T> newHead;
        do {
            oldHead = head.get();
            if (oldHead == null) {
                return null;
            }
            newHead = oldHead.next;
        } while (!head.compareAndSet(oldHead, newHead));
        return oldHead.value;
    }
}
```

## Spin Lock
스레드가 `lock`을 얻을 때까지 반복적으로 확인하는 방법입니다. 이는 짧은 대기 시간 동안 효율적이지만, 긴 대기 시간 동안에는 CPU 자원을 낭비 할 수있습니다.

### 장점
* 짧은 대기 시간에 효율적
* 낮은 오버헤드: 컨텍스트 스위칭이 필요없어서 오버헤드가 적습니다.

### 단점
* CPU 소모: `lock`을 획득할 때까지 계속해서 확인하기 때문에 CPU 자원을 많이 소모합니다.
* 긴 대기 시간에 비효율적: 대기 시간이 길어질수록 성능이 저하됩니다.

```java
class SpinLockMain {

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        Runnable task = () -> {
            spinLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " acquired the lock");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                System.out.println(Thread.currentThread().getName() + " released the lock");
                spinLock.unlock();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
    }
}

class SpinLock{
    private final AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {
        while (!lock.compareAndSet(false, true)) {
            // Spin-wait (busy-wait)
        }
    }

    public void unlock() {
        lock.set(false);
    }
}
```
