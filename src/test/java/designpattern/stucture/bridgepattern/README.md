# Bridge Pattern
Bridge 패턴은 구현부에서 추상층을 분리하여 각자가 독립적으로 변형될 수 있도록 하는 패턴입니다.
이 패턴을 통해 추상화 부분과 실제 구현 부분을 분리하여, 두 부분이 독립적으로 변형되고 확장될 수 있게 합니다.

## 구성 요소
1. Abstraction: 추상화의 최상위 계층을 정의합니다. 일반적으로 인터페이스 또는 추상 클래스로 구현됩니다.
2. RefinedAbstraction: `Abstraction`을 확장한 구체적인 추상화 클래스입니다.
3. Implementor: 구현부의 인터페이스를 정의합니다.
4. ConcreteImplementor: `Implementor` 인터페이스를 구현한 구체적인 클래스입니다.

## 장점
* 추상화와 구현 분리: 추상화와 구현을 분리하여 독립적으로 확장할 수 있습니다.
* 유연성 향상: 새로운 구현을 추가하더라도 추상화 부분을 변경할 필요가 없습니다.
* 클래스 수 감소: 계층 구조를 단순화하여 클래스 수를 줄일 수 있습니다.

## 사용 사례
* GUI 프레임워크
* Database Driver
* 멀티미디어 라이브러리

## 예시 코드

* Shape: Abstaction
    ```java
    public abstract class Shape {
        protected DrawingAPI drawingAPI;
        protected Shape(DrawingAPI drawingAPI){
            this.drawingAPI = drawingAPI;
        }
        public abstract void draw();
        public abstract void resizeByPercentage(double pct);
    }
    ```
  
* CicleShape: RefinedAbstraction
    ```java
    public class CircleShape extends Shape{
        private double x;
        private double y;
        private double radius;
    
        public CircleShape(double x, double y, double radius, DrawingAPI drawingAPI) {
            super(drawingAPI);
            this.x = x;
            this.y = y;
            this.radius = radius;
        }
    
        @Override
        public void draw() {
            drawingAPI.drawCircle(x, y, radius);
        }
    
        @Override
        public void resizeByPercentage(double pct) {
            radius *= (1.0 + pct / 100.0);
        }
    }
    ```
  
* DrawingAPI: Implementor
    ```java
    public interface DrawingAPI {
        void drawCircle(double x, double y, double radius);
    }
    ```
  
* DrawingAPI1, DrawingAPI2: ConcreteImplementor
    ```java
    public class DrawingAPI1 implements DrawingAPI{
        @Override
        public void drawCircle(double x, double y, double radius) {
            System.out.println("API1.circle at (" + x + ", " + y + ") with radius " + radius);
        }
    }
    public class DrawingAPI2 implements DrawingAPI{
        @Override
        public void drawCircle(double x, double y, double radius) {
            System.out.println("API2.circle at (" + x + ", " + y + ") with radius " + radius);
        }
    }
    ```
  
* Main
    ```java
    public class BridgePatternMain {
        public static void main(String[] args) {
            Shape[] shapes = new Shape[]{
                    new CircleShape(1, 2, 3, new DrawingAPI1()),
                    new CircleShape(5, 7, 11, new DrawingAPI2())
            };
            for(Shape shape : shapes){
                shape.resizeByPercentage(2.5);
                shape.draw();
            }
        }
    }
    ```
* Output
    ```shell
    API1.circle at (1.0, 2.0) with radius 3.0749999999999997
    API2.circle at (5.0, 7.0) with radius 11.274999999999999
    ```