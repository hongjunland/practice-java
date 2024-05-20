## Observer Pattern
객체의 상태 변화를 관찰하고, 상태가 변경될 때 이를 자동으로 통지받아 반응하는 디자인 패턴입니다.
주체(Subject)와 옵저버(Observer)간의 1:n관계를 정의하여, 주체의 상태 변화가 발생하면 여러 옵저버에게 자동으로 통지됩니다.
이는 이벤트 기반 시스템에서 주로 사용됩니다.

## 구성 요소
1. Subject: 상태 변화를 알리는 객체입니다. 옵저버를 관리(CRUD)하며, 상태변화를 알리는 메서드를 정의합니다.
2. Observer: Subject의 상태 변화를 관찰하고, 상태가 변경될 때 알림을 받는 객체 입니다. Subject에서 통지를 받기 위한 메서드를 정의합니다.

## 장점
* 느슨한 결합: Subject와 Observer 간의 결합도가 낮아져서 서로 독립적으로 변경할 수 있습니다.
* 확장성: 새로운 Observer를 쉽게 추가할 수 있습니다.
* 재사용성: Subject와 Observer는 독립적으로 재사용할 수 있습니다.

## 사용 사례
* GUI: 버튼 클릭 등의 이벤트를 처리할 때
* MVC 패턴: 모델의 상태가 변경될 때 View를 업데이트하기 위해
* Event Driven Systems: 특정 이벤트 발생 시 여러 컴포넌트에 알림을 보낼때
* 실시간 데이터 스트리밍: 데이터 변경을 구독하고 반응해야 하는 경우

## 예시 코드

* Subject: Subject Interface
    ```java
    public interface Subject {
        void attach(Observer observer);
        void detach(Observer observer);
        void notifyAllObservers();
    }
    ```
  
* ConcreteSubject: Concreted Subject
    ```java
    public class ConcreteSubject implements Subject {
        private List<Observer> observerList = new ArrayList<>();
        private int state;
    
        public int getState() {
            return state;
        }
    
        public void setState(int state) {
            this.state = state;
            notifyAllObservers();
        }
    
        @Override
        public void attach(Observer observer) {
            observerList.add(observer);
        }
    
        @Override
        public void detach(Observer observer) {
            observerList.remove(observer);
        }
    
        @Override
        public void notifyAllObservers() {
            for (Observer observer: observerList){
                observer.update();
            }
        }
    }
    ```

* Observer: Observer Interface
    ```java
    public interface Observer {
        void update();
    }
    ```

* ConcreteObserver: Concreted Observer
    ```java
    public class ConcreteObserver implements Observer{
        private String name;
        private ConcreteSubject subject;
    
        public ConcreteObserver(String name, ConcreteSubject subject) {
            this.name = name;
            this.subject = subject;
        }
    
        @Override
        public void update() {
            System.out.println(name + " 업데이트 알림: 현재 상태는 " + subject.getState());
        }
    }
    ```
  
* ObserverPatternMain :Main class
    ```java
    public class ObserverPatternMain {
        public static void main(String[] args) {
            ConcreteSubject subject = new ConcreteSubject();
    
            Observer observer1 = new ConcreteObserver("Observer 1", subject);
            Observer observer2 = new ConcreteObserver("Observer 2", subject);
            Observer observer3 = new ConcreteObserver("Observer 3", subject);
    
            subject.attach(observer1);
            subject.attach(observer2);
            subject.attach(observer3);
    
            System.out.println("상태변경");
            subject.setState(10);
            System.out.println("상태변경");
            subject.setState(20);
            System.out.println("상태변경");
            subject.setState(15);
        }
    }
    ```
  
* Output
    ```shell
    상태변경
    Observer 1 업데이트 알림: 현재 상태는 10
    Observer 2 업데이트 알림: 현재 상태는 10
    Observer 3 업데이트 알림: 현재 상태는 10
    상태변경
    Observer 1 업데이트 알림: 현재 상태는 20
    Observer 2 업데이트 알림: 현재 상태는 20
    Observer 3 업데이트 알림: 현재 상태는 20
    상태변경
    Observer 1 업데이트 알림: 현재 상태는 15
    Observer 2 업데이트 알림: 현재 상태는 15
    Observer 3 업데이트 알림: 현재 상태는 15
    ```