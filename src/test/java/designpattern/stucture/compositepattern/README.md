# Composite Pattern
Composite Pattern은 객체들을 트리 구조로 구성하여 부분-전체 계층을 표현하는 패턴입니다. 이 패턴을 통해 클라이언트가 개별 객체와 복합 객체를 동일하게 처리할 수 있습니다.

## 구성 요소
1. Component: 모든 객체에 대한 공통 인터페이스를 정의합니다. 이는 개별 객체와 복합 객체 모두가 구현해야 하는 메서드를 포함합니다.
2. Leaf: 트리 구조의 말단 객체로, 더 이상 하위 객체를 포함하지 않는 객체를 의미합니다. `Component` 인터페이스를 구현합니다.
3. Composite: 복합 객체로, 하위 객체들을 포함할 수 있습니다. `Component` 인터페이스를 구현하며, 하위 객체에 대한 작업을 수행합니다.

## 장점
* 일관된 트리 구조: 클라이언트가 개별 객체와 복합 객체를 동일하게 처리할 수 있어 코드의 일관성을 유지할 수 있습니다.
* 유연성: 새로운 구성 요소를 추가할 때 기존 코드를 변경할 필요 없이 트리 구조를 확장할 수 있습니다.
* 재사용성: 공통 인터페이스를 통해 구성 요소들을 쉽게 재사용할 수 있습니다.

## 사용 사례
* GUI 구성 요소
* 파일 시스템
* 문서 편집기

## 예시 코드
* Graphic: Component
    ```java
    interface Graphic {
        void draw();
    }
    ```
* Circle, Square: Leaf
    ```java
    class Circle implements Graphic {
        @Override
        public void draw() {
            System.out.println("Drawing a Circle");
        }
    }
    
    class Square implements Graphic {
        @Override
        public void draw() {
            System.out.println("Drawing a Square");
        }
    }
    ```
* CompositeGraphic: Composite
    ```java
    import java.util.ArrayList;
    import java.util.List;
    
    class CompositeGraphic implements Graphic {
        private List<Graphic> childGraphics = new ArrayList<>();
    
        public void add(Graphic graphic) {
            childGraphics.add(graphic);
        }
    
        public void remove(Graphic graphic) {
            childGraphics.remove(graphic);
        }
    
        @Override
        public void draw() {
            for (Graphic graphic : childGraphics) {
                graphic.draw();
            }
        }
    }
    ```
  
* Main
    ```java
    public class CompositePatternMain {
        public static void main(String[] args) {
            // 개별 객체 생성
            Graphic circle = new Circle();
            Graphic square = new Square();
    
            // 복합 객체 생성
            CompositeGraphic composite1 = new CompositeGraphic();
            composite1.add(circle);
            composite1.add(square);
    
            CompositeGraphic composite2 = new CompositeGraphic();
            composite2.add(new Square());
            composite2.add(new Circle());
            composite2.add(composite1);
    
            // 트로 구조를 통해 전체 객체를 동일하게 처리
            composite2.draw();
        }
    }
    ```
* Output
    ```shell
    Drawing a Square
    Drawing a Circle
    Drawing a Circle
    Drawing a Square
    ```