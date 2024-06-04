# Flyweight Pattern
많은 수의 객체를 효율적으로 관리하기 위해 객체를 가능한 한 많이 공유하여 메모리 사용을 최소화 하는 패턴입니다.
이 패턴은 대규모 객체를 다룰 때 메모리 사용을 줄이고 성능을 향상시키는 데 유용합니다.

## 구성 요소
1. Flyweight: 공유 가능한 객체입니다. 불변성을 보장해야하며, 상태를 변경할 수 없습니다.
2. Intrinsic State: 공유 가능한 상태로, Flyweight 객체 내부에 저장됩니다. 모든 객체가 공유할 수 있는 상태입니다.
3. Extrinsic State: 공유되지 않는 상태로, 각 객체의 고유 상태입니다. 외재 상태는 Flyweight 객체 외부에서 관리됩니다.
4. Flyweight Factory: Flyweight 객체를 생성하고 관리하는 역할을 합니다. 이미 생성된 Flyweight 객체를 재사용하여 메모리 사용을 최소화 합니다.

## 장점
* 메모리 절약: 동일한 객체를 동유하여 메모리 사용을 줄일 수 있습니다.
* 성능 향상: 객체 생성 비용을 줄이고, 시스템의 성능을 향상시킬 수 있습니다.
* 단순화된 객체 관리: Flyweight 객체의 수를 줄여 객체 관리를 단순화할 수 있습니다.

## 사용 사례
* 텍스트 편집기: 대량의 문자를 처리할 때, 각 문자 객체를 공유하여 메모리 사용을 줄입니다. 예를 들어, 각 문자는 Flyweight 객체로 관리되고, 위치나 스타일과 같은 외재 상태는 외부에서 관리됩니다.
* 그래픽 시스템: 대량의 그래픽 객체(ex: 점, 선, 사각형)를 처리할 때 Flyweight 패턴을 사용하여 메모리 사용을 줄입니다.
* 게임 개발: 대량의 적 캐릭터나 아이템을 관리할 때 메모리 사용을 최적화합니다.

## 예시 코드
* Flyweight : Flyweight
    ```java
    public interface Flyweight {
        void operation(String extrinsicState);
    }
    ```
* ConcreteFlyweight : Concrete Flyweight
    ```java
    public class ConcreteFlyweight implements Flyweight{
        private final String intrinsicState;
    
        public ConcreteFlyweight(String intrinsicState) {
            this.intrinsicState = intrinsicState;
        }
    
        @Override
        public void operation(String extrinsicState) {
            System.out.println("Intrinsic State: " + intrinsicState + ", Extrinsic State: " + extrinsicState);
        }
    }
    ```
* FlyweightFactory: Flyweight Factory
    ```java
    public class FlyweightFactory {
        private final Map<String, Flyweight> flyweights = new HashMap<>();
    
        public Flyweight getFlyweight(String key){
            if(!flyweights.containsKey(key)){
                flyweights.put(key, new ConcreteFlyweight(key));
            }
            return flyweights.get(key);
        }
    }
    ```
  
* Main
    ```java
    public class FlyweightPatternMain {
        public static void main(String[] args) {
            FlyweightFactory factory = new FlyweightFactory();
            Flyweight flyweight1 = factory.getFlyweight("A");
            Flyweight flyweight2 = factory.getFlyweight("B");
            Flyweight flyweight3 = factory.getFlyweight("A");
    
            flyweight1.operation("First Call");
            flyweight2.operation("Second Call");
            flyweight3.operation("Third Call");
        }
    }
    ```

* Output
    ```shell
    Intrinsic State: A, Extrinsic State: First Call
    Intrinsic State: B, Extrinsic State: Second Call
    Intrinsic State: A, Extrinsic State: Third Call
    ```