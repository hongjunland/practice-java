# Singleton Pattern
싱글턴 패턴은 클래스의 인스턴스를 하나만 생성하고, 해당 인스턴스에 전역적으로 접근할 수 있도록 하는 디자인 패턴입니다.
이 패턴은 애플리케이션에서 공통으로 사용되는 객체를 하나만 생성하여 사용하고자 할 때 유용합니다.

## 장점
* 인스턴스의 고유성 보장
* 전역 접근 제공
* 메모리 중복 사용 절약

## 사용 사례
* 로그 관리: 로그를 관리하는 클래스는 애플리케이션 전역에서 사용되므로 싱글턴 패턴으로 구현됩니다.
* 설정 관리: 애플리케이션 설정을 관리하는 클래스는 하나의 인스턴스로 전역에서 사용됩니다.
* 데이터베이스 연결: 데이터베이스 연결 객체는 하나의 인스턴스로 전역에서 사용되어야 하므로 싱글턴 패턴으로 구현될 수 있습니다.

## 예시 코드
* Singleton: 기본형
    ```java
    public class Singleton {
        private static Singleton instance;
    
        private Singleton(){}
    
        public static Singleton getInstance(){
            if(instance == null){
                instance = new Singleton();
            }
            return instance;
        }
    }
    ```

* ThreadSafeSingleton: 쓰레드에 안전한 싱글턴(성능 감소)
    ```java
    public class ThreadSafeSingleton {
        private static ThreadSafeSingleton instance;
    
        private ThreadSafeSingleton(){}
    
        public static synchronized ThreadSafeSingleton getInstance(){
            if(instance == null){
                instance = new ThreadSafeSingleton();
            }
            return instance;
        }
    }
    ```

* EagerSingleton: 미리 생성된 싱글턴(메모리 낭비)
    ```java
    public class EagerSingleton {
        private static final EagerSingleton instance = new EagerSingleton();
        private EagerSingleton(){}
        public static EagerSingleton getInstance(){
            return instance;
        }
    }
    ```
* DoubleCheckedLockingSingleton: EagerSingleton에서 메모리 낭비를 보완
    ```java
    public class DoubleCheckedLockingSingleton {
        private static volatile DoubleCheckedLockingSingleton instance;
    
        private DoubleCheckedLockingSingleton(){}
    
        public static DoubleCheckedLockingSingleton getInstance(){
            if(instance == null){
                synchronized (DoubleCheckedLockingSingleton.class){
                    if(instance == null){
                        instance = new DoubleCheckedLockingSingleton();
                    }
                }
            }
            return instance;
        }
    }
    ```
* BillPughSingleton: 정적 내부 클래스를 사용해서 Lazy Initialization Thread-safe를 모두 챙김
    ```java
    public class BillPughSingleton {
        private BillPughSingleton(){}
    
        private static class SingletonHelper{
            private static final BillPughSingleton INSTANCE = new BillPughSingleton();
        }
    
        public static BillPughSingleton getInstance(){
            return SingletonHelper.INSTANCE;
        }
    }
    ```