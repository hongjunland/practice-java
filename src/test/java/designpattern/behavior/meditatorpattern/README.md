## Mediator Pattern
객체간의 복잡한 통신을 중재 객체를 통해 단순화하여 각 객체가 서로 직접 통신하는 대신 중재자를 통해 통신하도록 하는 것입니다.
이 패턴은 객체 간의 결합도를 낮추어 시스템의 유연성과 확장성을 향상시키는 데 도움을 줍니다.

## 구성 요소
1. Mediator: 다양한 객체(Colleague) 간의 통신 로직을 캡슐화 하는 인터페이스를 정의합니다.
2. ConcreteMediator: 실제 구현된 로직을 담당합니다.
3. Colleague: 중재자와 통신하는 객체들로, 다른 객체와 직접 통신하지 않고 중재자를 통해 통신합니다.

## 장점
* 객체 간 결합도 감소: 객체간의 직접적인 통신을 하지 않기 때문에 결합도가 감소합니다.
* 시스템 유연성 향상: 객체 간의 통신 로직이 중재자에 집중되어 있기 때문에, 시스템의 다양한 부분을 독립적으로 변경하거나 확장하기 쉽습니다.
* 중재 로직의 재사용:  중재자 클래스를 통해 중재 로직을 한 곳에 모아두면, 다른 시스템이나 애플리케이션에서도 이 로직을 재사용할 수 있습니다.
* 복잡한 통신 프로세스 단순화: 여러 객체 간의 복잡한 통신 프로세스를 중재자가 관리함으로써, 각 객체는 비즈니스 로직에만 집중할 수 있으며 통신 프로세스를 이해하기 쉽습니다.
* 동적인 상호작용 관리: 중재자 패턴은 실행 시간에 통신하는 객체들의 상호작용을 변경할 수 있게 해주어, 다양한 상황에서의 동적 요구사항을 충족시킬 수 있습니다.

## 사용 사례
* GUI
* 채팅 시스템
* 시스템 구성요소간의 통신관리
* MVC 패턴의 Controller
* IoT

## 예시 코드
* ChatMediator: Mediator
    ```java
    public interface ChatMediator {
        void sendMessage(String message, User user);
        void addUser(User user);
    }
    ```
  
* ChatMediatorImpl: ConcreteMediator
    ```java
    public class ChatMediatorImpl implements ChatMediator{
        private List<User> userList;
    
        public ChatMediatorImpl() {
            this.userList = new ArrayList<>();
        }
    
        @Override
        public void sendMessage(String message, User user) {
            for (User u: userList){
                if(u != user){
                    u.receive(message);
                }
            }
        }
    
        @Override
        public void addUser(User user) {
            this.userList.add(user);
        }
    }
    ```
* User: Colleague(abstract)
    ```java
    public abstract class User {
        protected ChatMediator mediator;
        protected String name;
    
        public User(ChatMediator mediator, String name) {
            this.mediator = mediator;
            this.name = name;
        }
    
        public abstract void send(String message);
        public abstract void receive(String message);
    }
    ```
  
* UserImpl: Concrete Colleague
  ```java
  public class UserImpl extends User{
  
      public UserImpl(ChatMediator mediator, String name) {
          super(mediator, name);
      }
  
      @Override
      public void send(String message) {
          System.out.println(this.name + ": Sending Message:" + message);
          mediator.sendMessage(message, this);
      }
  
      @Override
      public void receive(String message) {
          System.out.println(this.name + ": Receive Message:"+ message);
      }
  }
  ```
  
* Main
  ```java
  public class MediatorPatternMain {
      public static void main(String[] args) {
          ChatMediator mediator = new ChatMediatorImpl();
          User user1 = new UserImpl(mediator, "Tom");
          User user2 = new UserImpl(mediator, "Kate");
          User user3 = new UserImpl(mediator, "Jack");
          User user4 = new UserImpl(mediator, "Jenny");
          mediator.addUser(user1);
          mediator.addUser(user2);
          mediator.addUser(user3);
          mediator.addUser(user4);
  
          user1.send("Hi, I am Tom");
          System.out.println("--------");
          user3.send("Nice to meet you");
      }
  }
  ```
* Output
  ```shell
  Tom: Sending Message:Hi, I am Tom
  Kate: Receive Message:Hi, I am Tom
  Jack: Receive Message:Hi, I am Tom
  Jenny: Receive Message:Hi, I am Tom
  --------
  Jack: Sending Message:Nice to meet you
  Tom: Receive Message:Nice to meet you
  Kate: Receive Message:Nice to meet you
  Jenny: Receive Message:Nice to meet you
  ```