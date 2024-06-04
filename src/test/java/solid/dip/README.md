# Dependency Inversion Principle
고수준의 모듈은 저수준 모듈에 의존해서는 안되며, 둘 다 추상화에 의존해야 한다는 것을 의미합니다.

## 장점
* 유지보수성 향상
* 테스트 용이성 향상
* 재사용성 향상

## 기존 코드
```java
class Warrior {
    private Sword sword;

    public Warrior(Sword sword) {
        this.sword = sword;
    }

    public void attack() {
        sword.swing();
    }
}

class Sword {
    public void swing() {
        System.out.println("칼로 공격합니다!");
    }
}
```
`Warrior`가 공격할 때 직접 `Sword` 객체를 생성하고 사용합니다. 이는 다음과 같은 문제점을 가지고 있습니다. 
* 결합도 증가: 다른 무기로 변경하기 어렵습니다.
* 테스트 어려움: 특정 객체에 의존하기 때문에, 테스트 코드 작성이 어렵습니다.

## 개선 코드
```java
interface Weapon {
    void attack();
}

class Warrior {
    private Weapon weapon;

    public Warrior(Weapon weapon) {
        this.weapon = weapon;
    }

    public void attack() {
        weapon.attack();
    }
}

class Sword implements Weapon {
    @Override
    public void attack() {
        System.out.println("칼로 공격합니다!");
    }
}

class Axe implements Weapon {
    @Override
    public void attack() {
        System.out.println("도끼로 공격합니다!");
    }
}
```
아래와 같은 결과를 조치했습니다.
* 추상화: `Weapon` 인터페이스를 도입하여 무기들의 공통적인 행동을 추상화했습니다.
* 유연성 향상: 새로운 무기는 `Weapon`을 구현하는 클래스를 통해 쉽게 추가할 수 있습니다.