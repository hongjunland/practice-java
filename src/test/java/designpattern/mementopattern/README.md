# Memento Pattern
메멘토 패턴은 객체의 상태를 저장하고 복원할 수 있게 하는 디자인 패턴입니다.
이 패턴은 객체의 내부 상태를 캡슐화하여 저장하고, 나중에 필요할 때 복원할 수 있게 합니다. 
주로 Undo 기능에서 사용됩니다.

## 구성 요소
1. Memento: Originator 객체의 상태를 저장하는 객체입니다. Originator 객체의 내부 상태를 캡슐화하여 저장하고, 외부에서는 이 상태에 접근할 수 없습니다.
2. Originator(창조자): 상태를 저장하고 복원하는 객체입니다. 현재 상태를 Memento로 저장하고, 나중에 이 Memento를 사용하여 복원할 수 있습니다.
3. Caretaker(관리자): Memento를 관리하는 객체입니다. Memento를 저장하고 필요할 때 Memento를 사용하여 Originator 객체의 상태를 복원합니다.

## 장점
* Unde/Redo 기능: 객체의 상태를 저장하거나 복원할 수 있어서 Undo/Redo 기능을 쉽게 구현할 수 있습니다.
* 캡슐화 유지: 객체의 상태를 외부에 노출하지 않고 저장할 수 있어, 객체의 캡슐화 원칙을 유지합니다.
* 상태 복원 용이: 복잡한 객체의 상태를 쉽게 저장하고 복원할 수 있습니다.

## 사용 사례
* 편집기: 편집기에서 Undo/Redo 기능을 구현할 때 사용됩니다.
* 게임 개발: 게임에서 플래이어의 상태(위치, 점수, 레벨 등)를 저장하고 복원하는 기능을 구현할 때 사용됩니다.

## 예시 코드
* Memnto
    ```java
    public class Memento {
        private final String state;
    
        public Memento(String state) {
            this.state = state;
        }
    
        public String getState() {
            return state;
        }
    }
    ```
* Originator
    ```java
    public class Originator {
        private String state;
    
        public void setState(String state) {
            System.out.println("Setting state to " + state);
            this.state = state;
        }
    
        public String getState() {
            return state;
        }
    
        public Memento saveStateToMemento(){
            System.out.println("Saving state to Memento");
            return new Memento(state);
        }
    
        public void getStateFromMemento(Memento memento){
            state = memento.getState();
            System.out.println("Restoring state from Memento: " + state);
        }
    }
    ```

* Caretaker
    ```java
    public class Caretaker {
        private List<Memento> mementoList = new ArrayList<>();
    
        public void add(Memento state){
            mementoList.add(state);
        }
    
        public Memento get(int index){
            return mementoList.get(index);
        }
    }
    ```

* Main
    ```java
    public class MementoPatternMain {
        public static void main(String[] args) {
            Originator originator = new Originator();
            Caretaker caretaker = new Caretaker();
    
            originator.setState("State #1");
            originator.setState("State #2");
            caretaker.add(originator.saveStateToMemento());
    
            originator.setState("State #3");
            caretaker.add(originator.saveStateToMemento());
    
            originator.setState("State #4");
    
            System.out.println("Current state: " + originator.getState());
            originator.getStateFromMemento(caretaker.get(0));
            System.out.println("First saved state: " + originator.getState());
            originator.getStateFromMemento(caretaker.get(1));
            System.out.println("Second saved state: " + originator.getState());
        }
    }
    ```
  
* Output
    ```shell
    Setting state to State #1
    Setting state to State #2
    Saving state to Memento
    Setting state to State #3
    Saving state to Memento
    Setting state to State #4
    Current state: State #4
    Restoring state from Memento: State #2
    First saved state: State #2
    Restoring state from Memento: State #3
    Second saved state: State #3
    ```