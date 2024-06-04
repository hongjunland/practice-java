# Liskov Substitution Principle 
하위 타입은 언제나 자신의 상위 타입으로 교체할 수 있어야 한다는 원칙입니다. 즉, 객체는 그 하위 타입의 인스턴스로 바꾸어도 프로그램의 동작이 변하지 않아야 합니다.

## 장점
* 유연한 설계: 부모 클래스와 자식클래스의 관계가 명확해지고, 객체를 대체할 수 있으므로 유연한 설계를 할 수 있습니다.
* 코드 재사용성: 부모 클래스 타입으로 작성된 코드가 자식 클래스에서도 문제없이 동작할 수 있어 코드의 재사용성이 높아집니다.
* 유지보수성: 클래스의 변경이 다른 클래스에 미치는 영향을 최소화할 수 있습니다.

## 기존 코드
```java
class Bird {
    public void fly() {
        System.out.println("Flying");
    }
}

class Sparrow extends Bird {
    // Sparrow는 fly 메서드를 제대로 사용할 수 있습니다.
}

class Ostrich extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Ostriches can't fly");
    }
}
```
해당 코드는 `Ostrich` 객체가 `Bird` 객체로 교체될 때 예외를 발생시켜 프로그램의 동작이 변하게 됩니다.

## 개선 코드
```java
class Bird {
    public void move() {
        System.out.println("Moving");
    }
}

class FlyingBird extends Bird {
    public void fly() {
        System.out.println("Flying");
    }
}

class Sparrow extends FlyingBird {
    // Sparrow는 FlyingBird의 기능을 사용할 수 있습니다.
}

class Ostrich extends Bird {
    @Override
    public void move() {
        System.out.println("Running");
    }
}
```
`Bird` 클래스를 추상화하고, `FlyingBird`와 `NonFlyingBird`로 분리합니다. 그래서 교체되더라도 예외를 발생시키지 않고 프로그램이 정상적으로 동작합니다. 