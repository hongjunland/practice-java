# Open/Closed Principle
소프트웨어 엔티티(클래스, 모듈, 함수 등)는 확장에 열려있어야 하지만, 변경에는 닫혀 있어야 한다는 원칙입니다.
이 원칙을 따르면 기존 코드를 변경하지 않고 새로운 기능을 추가할 수 있어, 시스템의 유연성과 확장성을 높일 수 있습니다.

## 장점
* 유연성 향상: 새로운 기능을 추가할 때 기존 코드를 변경하지 않아도 되므로, 시스템의 유연성이 높아집니다.
* 안정성 보장: 기존 코드를 변경하지 않으므로, 변경으로 인한 사이드 이펙트를 방지할 수 있습니다.
* 유지보수성 향상: 새로운 요구사항이 생겼을 때 기존 코드를 건드리지 않고 확장할 수 있어 유지보수가 용이합니다.

## 기존 코드
```java
class Shape {
    public int type;
}

class Rectangle extends Shape {
    public int length;
    public int width;
}

class Circle extends Shape {
    public int radius;
}

class AreaCalculator {
    public double calculate(Shape shape) {
        if (shape.type == 1) { // Rectangle
            Rectangle rectangle = (Rectangle) shape;
            return rectangle.length * rectangle.width;
        } else if (shape.type == 2) { // Circle
            Circle circle = (Circle) shape;
            return Math.PI * circle.radius * circle.radius;
        }
        return 0;
    }
}
```
`Shape` 클래스와 `AreaCalculator` 클래스는 새로운 도형이 추가될 때마다 `AreaCalculator` 클래스가 변경되어야 합니다. 이는 개방-폐쇄 원칙을 위반하는 예입니다.

## 개선된 코드
```java
abstract class Shape {
    public abstract double calculateArea();
}

class Rectangle extends Shape {
    private int length;
    private int width;

    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double calculateArea() {
        return length * width;
    }
}

class Circle extends Shape {
    private int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class AreaCalculator {
    public double calculate(Shape shape) {
        return shape.calculateArea();
    }
}
```
개방-폐쇄 원칙을 준수하기 위해 `Shape` 클래스를 추상화하고, 각 도형별로 `Shape` 클래스를 확장하여 구현합니다. 이제 새로운 도형을 추가할 때 `AreaCalculator` 클래스를 변경할 필요가 없습니다.