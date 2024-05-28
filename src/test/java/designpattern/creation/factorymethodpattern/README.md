# Factory Method Pattern
객체 생성에 대한 인터페이스를 정의하고, 하위 클래스가 인스턴스를 결정하게 하는 패턴입니다. 
이 패턴은 객체 생성 코드를 클라이언트 코드에서 분리하여, 객체 생성의 유연성을 높이는 데 중점을 둡니다.

## 구성 요소
1. Product: 생성될 객체의 인터페이스
2. ConcreteProduct: Product 인터페이스의 구현체
3. Creator: Product 객체를 생성후 반환하는 팩토리 메서드
4. ConcreteCreator: 팩토리 메서드를 재정의하여 ConcreteProduct 객체를 생성

## 장점
* 객체 생성의 캡슐화: 객체 생성 코드를 클라이언트 코드에 분리하여, 클라이언트가 구체적인 클래스를 알 필요 없이 객체를 생성할 수 있습니다.
* 확장성: 새로운 제품 클래스를 추가할 때, 기존 코드를 수정할 필요 없이 새로운 서브클래스를 추가하면 됩니다.
* 유연성: 객체 생성 방식을 변경할 때 클라이언트 코드에 영향을 주지 않습니다.

## 사례
* GUI 라이브러리: 다양한 컴포넌트들을 생성할때 팩토리 메서드 패턴을 사용합니다.
* 로깅 라이브러리: 다양한 로깅 방식(파일,콘솔,원격 서버 등)을 지원하기 위해 팩토리 메서드 패턴을 사용하여 Logger 객체를 생성합니다.
* 데이터베이스 연결: 다양한 데이터베이스에 연결하기 위해 팩토리 메서드 패턴을 사용하여 커넥터 객체를 생성합니다.

## 예시 코드
* Product: Product Interface
    ```java
    public interface Product {
        void use();
    }
    ```
  
* ConcreteProduct: Concrete Product
    ```java
    public class ConcreteProductA implements Product{
        @Override
        public void use() {
            System.out.println("Using Product A");
        }
    }
    
    public class ConcreteProductB implements Product{
        @Override
        public void use() {
            System.out.println("Using Product B");
        }
    }
    ```
  
* Creator: Abstract Creator 
    ```java
    public abstract class Creator {
        public abstract Product factoryMethod();
    
        public void someOperation(){
            Product product = factoryMethod();
            product.use();
        }
    }
    ```
  
* ConcreteCreator: Concrete Creator
    ```java
    public class ConcreteCreatorA extends Creator{
        @Override
        public Product factoryMethod() {
            return new ConcreteProductA();
        }
    }
    public class ConcreteCreatorB extends Creator{
        @Override
        public Product factoryMethod() {
            return new ConcreteProductB();
        }
    }
    ```
  
* Main
    ```java
    public class FactoryMethodPatternMain {
        public static void main(String[] args) {
            Creator creatorA = new ConcreteCreatorA();
            creatorA.someOperation();
    
            Creator creatorB = new ConcreteCreatorB();
            creatorB.someOperation();
        }
    }
    ```
  
* Output
    ```shell
    Using Product A
    Using Product B
    ```
