# Proxy Pattern
Proxy 패턴은 실제 객체에 대한 접근을 제어하기 위해 Proxy 객체를 제공하는 패턴입니다. Proxy는 실제 객체와 동일한 인터페이스를 제공하여 클라이언트가 실제 객체 대신 프록시 객체를 통해 작업을 수행하도록 합니다.

## 구성 요소
1. Proxy: 실제 객체에 대한 참조를 유지하면서 실제 객체와 동일한 인터페이스를 구현합니다. 클라이언트는 프록시를 통해 실제 객체에 접근합니다.
2. RealSubject: 실제 작업을 수행하는 객체입니다.
3. Subject: 실제 객체와 프록시 객체가 구현할 공통 인터페이스입니다.

## 장점
* 객체 생성 지연: 객체 생성을 지연시켜 자원을 절약할 수 있습니다. 필요할 때만 객체를 생성하여 메모리와 성능을 효율적으로 관리할 수 있습니다.
* 접근 제어: 특정 조건에 따라 객체에 대한 접근을 제어할 수 있습니다. 예를 들어, 사용자의 권한에 따라 접근을 제어하거나, 접근 로그를 남기는 등의 추가 기능을 구현할 수 있습니다.
* 원격 접근: 원격 객체에 대한 접근을 로컬 객체처럼 처리할 수 있게 합니다. 네트워크 통신을 숨기고, 로컬 호출처럼 사용할 수 있도록 합니다.
* 추가 기능 제공: 실제 객체에 대한 요청을 가로채어 로깅, 캐싱, 데이터 검증 등의 추가 기능을 쉽게 구현할 수 있습니다.
* 리소스 관리: 실제 객체의 사용 빈도가 낮거나, 리소스를 많이 소비하는 객체에 대해 프록시를 사용하여 효율적으로 리소스를 관리할 수 있습니다.

## 사용 사례
* 원격 프록시(Remote Proxy): 원격 서버의 객체를 로컬에서 접근할 수 있게 합니다. -> RMI
* 가상 프록시(Virtual Proxy): 객체의 생성 비용이 클 경우, 실제 객체의 생성을 지연시키고 필요할 때만 생성합니다. 
* 보호 프록시(Protection Proxy): 접근 제어를 수행하여 특정 조건에서만 실제 객체에 접근할 수 있게 합니다.

## 예시 코드
* Subject: Subject Interface
    ```java
    interface Subject {
        void request();
    }
    ```
* RealSubject: RealSubject
    ```java
    class RealSubject implements Subject {
        @Override
        public void request() {
            System.out.println("RealSubject: Handling request.");
        }
    }
    ```
* Proxy: Proxy
    ```java
    class Proxy implements Subject {
        private RealSubject realSubject;
    
        @Override
        public void request() {
            if (realSubject == null) {
                realSubject = new RealSubject();
            }
            System.out.println("Proxy: Logging the request.");
            realSubject.request();
        }
    }
    ```
  
* Main
    ```java
    public class ProxyPatternExample {
        public static void main(String[] args) {
            Subject proxy = new Proxy();
            proxy.request();  // 프록시를 통해 실제 객체에 접근
        }
    }
    ```

* Output
    ```shell
    Proxy: Logging the request.
    RealSubject: Handling request.
    ```