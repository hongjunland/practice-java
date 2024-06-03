# Adapter Pattern
어댑터 패턴은 소프트웨어 설계에서 구조적 디자인 패턴 중 하나로, 클래스의 인터페이스를 사용자가 기대하는 다른 인터페이스로 변환하는 데 사용됩니다.
이 패턴은 호환되지 않는 인터페이스를 가진 두 객체가 함께 동작할 수 있도록 도와줍니다.

## 구성 요소
1. Target Interface: 클라이언트가 사용하려는 인터페이스
2. Adapter: 타겟 인터페이스를 구현하고, 어탭터가 변환할 기존 클래스(Adaptee)의 참조를 유지합니다. Target Interface의 메서드를 호출하면 Adaptee의 메서드를 호출하도록 합니다.
3. Adaptee: 기존 클래스입니다. 이 클래스는 클라이언트가 호환되지 않는 인터페이스를 가지고 있습니다.

## 장점
* 호환성 향상: 서로 다른 인터페이스를 가진 클래스들이 함께 동작할 수 있도록 합니다.
* 재사용성 증가: 기존 클래스를 수정하지 않고도 재사용할 수 있습니다.
* 유연성 향상: 새로운 어댑터를 추가함으로써 다른 인터페이스를 가진 클래스들도 쉽게 통합할 수 있습니다.

## 사용 사례
* 래거시 코드 통합
* Third-Party 라이브러리 통합
* API 변환
* InputStreamReader
* Spring MVC에 있는 HandlerAdapter

## 예시 코드
* Target: Target Interface
    ```java
    public interface Target {
        String request();
    }
    ```
  
* Adaptee: Adaptee class
    ```java
    public class Adaptee {
        public String specificRequest(){
            return ".eetpadA eht fo roivaheb laicepS";
        }
    }
    ```
  
* Adapter: Adapter class
    ```java
    public class Adapter implements Target {
        private Adaptee adaptee;
    
        public Adapter(Adaptee adaptee) {
            this.adaptee = adaptee;
        }
    
        @Override
        public String request() {
            return "Adapter: (TRANSLATED) " + new StringBuilder(adaptee.specificRequest()).reverse().toString();
        }
    }
    ```
  
* Output
  ```shell
  Client: I can work just fine with the Target objects:
  Target: The default target's behavior.
  
  Client: The Adaptee class has a weird interface. See, I don't understand it:
  Adaptee: .eetpadA eht fo roivaheb laicepS
  
  Client: But I can work with it via the Adapter:
  Adapter: (TRANSLATED) Special behavior of the Adaptee.
  ```