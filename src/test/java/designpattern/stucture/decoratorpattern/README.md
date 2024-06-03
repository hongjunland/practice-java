# Decorator Pattern
Decorator 패턴은 객체에 추가적인 기능을 동적으로 추가할 수 있게 해주는 디자인 패턴입니다. 
객체를 감싸서 추가 기능을 제공하는 방식으로, 상속이 아닌 구성을 통한 기능 확장을 가능하게 합니다.
이는 객체의 기능을 확장하거나 변경하는 데 유연성을 제공합니다.

## 구성 요소
1. Component: 기본 인터페이스 또는 추상 클래서. 데코레이터와 실제 객체가 구현할 공통 인터페이스입니다.
2. ConcreteComponent: 기본 기능을 구현하는 실제 객체입니다.
3. Decorator: `Component` 인터페이스를 구현하는 추상 클래스입니다. 여기서는 추가적인 가능을 정의하고, `Component` 객체를 감쌉니다.
4. ConcreteDecorator: `Decorator` 클래스의 구체적인 구현체로, 추가적인 기능을 실제로 구현합니다.

## 장점
* 동적 기능 추가 가능: 객체에 새로운 기능을 동적으로 추가할 수 있습니다. 상속을 사용하지 않고도 객체의 기능을 확장할 수 있습니다.
* 유연성: 다양한 데코레이터를 조합하여 객체에 필요한 기능을 유연하게 추가할 수 있습니다. 이를 통해 객체의 동작을 런타임에 변경할 수 있습니다.
* 단일 책임 원칙 준수: 각 데코레이터는 하나의 구체적인 기능만 추가하므로, 단일 책임 원칙을 준수 할 수 있습니다.
* 개방-폐쇄 원칙 준수: 기존 코드를 수정하지 않고도 새로운 기능을 추가할 수 있으므로, 개방-폐쇄 원칙을 준수할 수 있습니다.

## 사용 사례
* I/O Stream: `BufferedInputStream`, `DataInputStream` 등이 `InputStream`을 감싸서 추가 기능을 제공합니다.
* GUI: 스크롤 바, 테두리 등을 동적으로 추가하는 데 사용됩니다.
* 로킹, 보안, 트랜잭션 처리: 메서드 호출에 대한 로깅, 보안 검증, 트랜잭션 관리 등의 cross-cutting 관심사를 객체에 동적으로 추가할 때 사용됩니다.
* 형식 변환기: 문자열이나 데이터의 형식을 동적으로 변환하는 데코레이터를 사용하여 다양한 출력 형식을 지원할 수 있습니다.

## 예시 코드
* Component: Component Interface
    ```java
    public interface Component {
        void operation();
    }
    ```
  
* ConcreteComponent: ConcreteComponent
    ```java
    public class ConcreteComponent implements Component{
        @Override
        public void operation() {
            System.out.println("ConcreteComponent: 기본 작업 수행");
        }
    }
    ```
  
* Decorator: Decorator Interface
    ```java
    public abstract class Decorator implements Component {
        protected Component component;
    
        public Decorator(Component component) {
            this.component = component;
        }
    
        @Override
        public void operation() {
            component.operation();
        }
    }
    ```
  
* ConcreteDecoratorA, ConcreteDecoratorB  : ConcreteDecorator 
    ```java
    public class ConcreteDecoratorA extends Decorator {
        public ConcreteDecoratorA(Component component) {
            super(component);
        }
    
        @Override
        public void operation() {
            super.operation();
            addedBehavior();
        }
    
        private void addedBehavior() {
            System.out.println("ConcreteDecoratorA: 추가 기능 A");
        }
    }
    
    public class ConcreteDecoratorB extends Decorator {
        public ConcreteDecoratorB(Component component) {
            super(component);
        }
    
        @Override
        public void operation() {
            super.operation();
            addedBehavior();
        }
    
        private void addedBehavior() {
            System.out.println("ConcreteDecoratorB: 추가 기능 B");
        }
    }
    ```
  
* Main
    ```java
    public class DecoratorPatternMain {
        public static void main(String[] args) {
            Component component = new ConcreteComponent();
            Component decoratorA = new ConcreteDecoratorA(component);
            Component decoratorB = new ConcreteDecoratorB(decoratorA);
    
            System.out.println("Client: 기본 컴포넌트 실행");
            component.operation();
    
            System.out.println("\nClient: 데코레이터 A를 적용한 컴포넌트 실행");
            decoratorA.operation();
    
            System.out.println("\nClient: 데코레이터 A와 B를 적용한 컴포넌트 실행");
            decoratorB.operation();
        }
    }
    ```
  
* Output
    ```shell
    Client: 기본 컴포넌트 실행
    ConcreteComponent: 기본 작업 수행
    
    Client: 데코레이터 A를 적용한 컴포넌트 실행
    ConcreteComponent: 기본 작업 수행
    ConcreteDecoratorA: 추가 기능 A
    
    Client: 데코레이터 A와 B를 적용한 컴포넌트 실행
    ConcreteComponent: 기본 작업 수행
    ConcreteDecoratorA: 추가 기능 A
    ConcreteDecoratorB: 추가 기능 B
    ```