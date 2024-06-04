# Interface Segregation Principle
특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다는 원칙입니다. 즉, 인터페이스는 클라이언트가 필요로 하는 메서드들만 제공해야 하며, 불필요한 메서드가 포함되지 않도록 작고 구체적으로 설계해야 합니다.

## 장점
* 유연성 향상: 클라이언트가 자신이 사용하지 않는 메서드를 구현할 필요가 없어 유연성이 높아집니다.
* 응집도 향상: 인터페이스가 명확하고 특정 책임을 가지므로 응집도가 향상됩니다.
* 유지보수성 향상: 변경이 필요한 부분만 수정하면 되므로, 인터페이스의 변경으로 인한 최소화 됩니다.
* 재사용성 향상: 작고 구제적인 인터페이스는 다양한 컨텍스트에서 재사용할 수 있습니다.

## 기존 코드
```java
interface Worker {
    void work();
    void eat();
}

class HumanWorker implements Worker {
    @Override
    public void work() {
        System.out.println("Human working");
    }

    @Override
    public void eat() {
        System.out.println("Human eating");
    }
}

class RobotWorker implements Worker {
    @Override
    public void work() {
        System.out.println("Robot working");
    }

    @Override
    public void eat() {
        throw new UnsupportedOperationException("Robots don't eat");
    }
}
```
`Worker` 인터페이스는 작업과 식사 기능을 모두 포함하고 있습니다. 로봇은 식사 기능이 필요 없으므로 이 인터페이스를 구현하는 것이 부적절합니다.

## 개선 코드
```java
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class HumanWorker implements Workable, Eatable {
    @Override
    public void work() {
        System.out.println("Human working");
    }

    @Override
    public void eat() {
        System.out.println("Human eating");
    }
}

class RobotWorker implements Workable {
    @Override
    public void work() {
        System.out.println("Robot working");
    }
}
```
인터페이스 분리 원칙을 준수하기 위해 `Worker` 인터페이스를 `Workable`과 `Eatable`로 분리합니다.
이제 `RobotWorker`는 불필요한 `eat` 메서드를 구현하지 않아도 됩니다.