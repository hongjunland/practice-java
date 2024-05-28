# Prototype Pattern
기존 객체를 복제하여 새로운 객체를 생성하는 패턴입니다. 이 패턴은 주로 객체 생성 비용이 크거나, 객체 생성 과정이 복잡할 때 유용하게 사용됩니다.
프로토타입 패턴을 통해 객체를 생성할 때 복제를 사용하므로, 객체 생성의 유연성을 높이고 성능일 최적화 할 수 있습니다.

## 구성 요소
1. Prototype: `clone` 메서드를 선언하는 인터페이스 또는 추상 클래스입니다.
2. ConcretePrototype: `clone` 메서드를 구현하는 클래스입니다.

## 장점
* 객체 생성 비용 감소: 객체를 복제하여 생성함으로써, 각체 생성 비용을 줄일 수 있습니다.   
* 객체 생성의 유연성 증가: 복잡한 객체 생성 과정을 단순화할 수 있습니다.
* 상태 초기화 간소화: 객체를 복제함으로써, 상태 초기화 과정을 간소화할 수 있습니다.

## 사용사례
* GUI 프레임워크: 복잡한 GUI Element들을 복제하여 새로운 Element를 생성
* 게임 개발: 다양한 게임 객체를 복제
* 문서 생성 시스템: 복잡한 문서 객체를 복제하여 새로운 문서를 생성할 때 사용됩니다.

## 예시 코드
* Shape: Prototype Interface
    ```java
    // Cloneable은 기존 라이브러리
    public interface Shape extends Cloneable{
        Shape clone();
        void draw();
    }
    ```
* Circle, Rectangle: Concrete Prototype
    ```java
    public class Circle implements Shape{
        private int radius;
    
        public Circle(int radius) {
            this.radius = radius;
        }
    
        @Override
        public Shape clone() {
            return new Circle(this.radius);
        }
    
        @Override
        public void draw() {
            System.out.println("Drawing a Circle with radius " + radius);
        }
    }
    ```
    ```java
    public class Rectangle implements Shape{
        private int width;
        private int height;
    
        public Rectangle(int width, int height) {
            this.width = width;
            this.height = height;
        }
    
        @Override
        public Shape clone() {
            return new Rectangle(this.width, this.height);
        }
    
        @Override
        public void draw() {
            System.out.println("Drawing a Rectangle with width " + width + " and height " + height);
        }
    }
    ```
  
* Main
    ```java
    public class PrototypeMain {
        public static void main(String[] args) {
            Shape originCircle = new Circle(10);
            Shape originRectangle = new Rectangle(20, 30);
    
            Shape clonedCircle = originCircle.clone();
            Shape clonedRectangle = originRectangle.clone();
    
            System.out.println("Original Objects:");
            originCircle.draw();
            originRectangle.draw();
    
            System.out.println("Cloned Objects:");
            clonedCircle.draw();
            clonedRectangle.draw();
        }
    }
    ```
  
* Output
    ```shell
    Original Objects:
    Drawing a Circle with radius 10
    Drawing a Rectangle with width 20 and height 30
    Cloned Objects:
    Drawing a Circle with radius 10
    Drawing a Rectangle with width 20 and height 30
    ```