# Thread 실습
Java에서 Thread의 가장 기본적인 형태 실습

## 예시 코드
* SimpleThread
    ```java
    class SimpleThread extends Thread{
        public void run(){
            for(int i = 0 ; i < 10 ; i++){
                System.out.println(Thread.currentThread().getName() + " is running: "+ i);
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    System.out.println(Thread.currentThread().getName() + " was interrupted.");
                }
            }
        }
    }
    ```
  
* SimpleThreadMain
    ```java
    class SimpleThreadMain {
        public static void main(String[] args) {
            SimpleThread thread1 = new SimpleThread();
            SimpleThread thread2 = new SimpleThread();
            SimpleThread thread3 = new SimpleThread();
    
            thread1.start();
            thread2.start();
            thread3.start();
        }
    }
    ```
  
* output
    ```bash
    Thread-0 is running: 0
    Thread-2 is running: 0
    Thread-1 is running: 0
    Thread-2 is running: 1
    Thread-1 is running: 1
    Thread-0 is running: 1
    Thread-2 is running: 2
    Thread-1 is running: 2
    Thread-0 is running: 2
    Thread-2 is running: 3
    Thread-1 is running: 3
    Thread-0 is running: 3
    Thread-2 is running: 4
    Thread-1 is running: 4
    Thread-0 is running: 4
    Thread-2 is running: 5
    Thread-1 is running: 5
    Thread-0 is running: 5
    Thread-2 is running: 6
    Thread-1 is running: 6
    Thread-0 is running: 6
    Thread-2 is running: 7
    Thread-1 is running: 7
    Thread-0 is running: 7
    Thread-2 is running: 8
    Thread-1 is running: 8
    Thread-0 is running: 8
    Thread-2 is running: 9
    Thread-1 is running: 9
    Thread-0 is running: 9
    ```
