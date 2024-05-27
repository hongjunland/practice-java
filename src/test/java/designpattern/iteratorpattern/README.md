# Iterator Pattern
이터레이터 패턴은 컬렉션의 요소들에 접근하는 방법을 제공하는 디자인 패턴입니다. 이 패턴을 사용하면 컬렉션의 내부 구조를 노출하지 않고도 순차적으로 요소들에 접근할 수 있습니다.

## 구성 요소
1. Iterator: 컬렉션의 요소들을 순차적으로 접근하는 인터페이스
2. Concrete Iterator: Iterator의 구현체로 실제 요소들의 순회 담당
3. Aggregate: 컬렉션의 인터페이스이며 이터페이터를 생성하는 메서드를 포함합니다.
4. Concrete Aggregate: Aggregate의 구현체로 컬렉션의 요소들을 관리하고, Concrete Iterator를 생성

## 장점
* 일관된 인터페이스 제공: 이터레이터 패턴은 다양한 유형의 컬렉션에 대해 일관된 방법으로 접근할 수 있는 인터페이스 제공
* 컬렉션의 내부 구조 은닉: 이터레이터 패턴을 사용하면 컬렉션의 내부 구조를 노출하지 않고도 요소들에 접근할 수 있습니다.
* 단일 책임 원칙 준수: 이터레이터 패턴을 사용하면 컬렉션과 그 순회를 별도의 클래스로 분리하여 단일 책임 원칙을 준수할 수 있습니다.

## 사용 사례
* Java Collections Framework: List, Set, Map 등은 `Iterator` 인터페이스를 통해 요소를 순회합니다.
* .Net의 IEnumable 인터페이스
* 데이터베이스 접근: 쿼리 결과를 순회할 때 커서 또는 이터레이터 패턴을 사용하여 결과 집합을 순회합니다.

## 예시 코드

* Iterator: Iterator Interface
    ```java
    public interface Iterator<T> {
        boolean hasNext();
        T next();
    }
    ```

* Concrete Iterator: Concrete Iterator
    ```java
    public class ConcreteIterator<T> implements Iterator{
        private List<T> collections;
        private int position = 0;
    
        public ConcreteIterator(List<T> collections) {
            this.collections = collections;
        }
    
        @Override
        public boolean hasNext() {
            return position < collections.size();
        }
    
        @Override
        public T next() {
            if(this.hasNext()){
                return collections.get(position++);
            }
            return null;
        }
    }
    ```

* Aggregate Interface: Aggregate
    ```java
    public interface Aggregate<T> {
        Iterator<T> createIterator();
    }
    ```

* ConcreteAggregate:Concrete Aggregate
    ```java
    public class ConcreteAggregate<T> implements Aggregate<T>{
        private List<T> items = new ArrayList<>();
        public void addItem(T item){
            items.add(item);
        }
        public List<T> getItems(){
            return items;
        }
        @Override
        public Iterator<T> createIterator() {
            return new ConcreteIterator<>(items);
        }
    }
    ```