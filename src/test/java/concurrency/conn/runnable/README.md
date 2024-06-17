# Thread 실습
Java에서 Thread의 가장 기본적인 형태 실습

## Thread와 Runnable 의 차이
1. Thread
   * 설명
       * `Thread` 클래스는 `Java`에서 직접 스레드를 생성하고 관리하는 방법을 제공합니다.
       * `Runnable` 인터페이스의 구현체
   * 장점
     * 그대로 사용하면 되기 때문에 구현 및 실행 코드가 간단합니다.
   * 단점
     * 클래스 확장의 제한: Java는 다중상속이 허용되지 않기 때문에 다른 클래스를 상속받을 수 없습니다.
     * 코드 재사용성의 제한: 스레드가 직접 실행 코드를 포함하므로, 코드 재사용성이 떨어집니다.
2. Runnable
   * 설명
     * `Runnable` 인터페이스는 스레드에서 실행할 코드를 제공하는 인터페이스입니다. 스레드 자체는 `Thread` 클래스가 관리합니다.
   * 장점
     * 코드 재사용성
     * 유연성
   * 단점
     * 복잡성 증가: `Thread` 객체를 별도로 생성해야 하므로 약간 더 복잡해 질 수 있습니다.
## 예시 코드
* SimpleRunnable
    ```java
    class SimpleRunnable implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " is running: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " was interrupted.");
                }
            }
        }
    }
    ```
  
* Main
    ```java
    class SimpleRunnableMain {
        public static void main(String[] args) {
            Thread thread1 = new Thread(new SimpleRunnable());
            Thread thread2 = new Thread(new SimpleRunnable());
    
            thread1.start();
            thread2.start();
        }
    }
    ```
  
* Output
    ```shell
    Thread-0 is running: 0
    Thread-1 is running: 0
    Thread-0 is running: 1
    Thread-1 is running: 1
    Thread-0 is running: 2
    Thread-1 is running: 2
    Thread-1 is running: 3
    Thread-0 is running: 3
    Thread-1 is running: 4
    Thread-0 is running: 4
    ```