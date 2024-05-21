## Command Pattern
요청을 객체로 캡술화하여 요청자와 수행자 간의 결합을 제거하는 디자인 패턴입니다.
이는 요청을 객체로 변환하여 다양한 작업을 추상화하고, 큐잉, 로깅, 취소 및 재실행 등의 작업을 수행할 수 있게 해줍니다.
주로 작업을 Queue에 넣거나, 실행 취소 기능을 구현할 때 유용합니다.

## 구성 요소
1. Command Interface: 실행할 명령을 정의하는 인터페이스
2. Concrete Command Classes: Command 인터페이스의 구현체
3. Receiver: ConcreteCommand가 실행할 실제 작업을 수행하는 클래스
4. Invoker: Command 객체를 실행하는 클래스
5. Client: ConcreteCommand 객체를 생성하고 설정하는 클래스

## 장점
* Command의 캡슐화: 요청을 객체로 캡슐화하여 클라이언트와 호출자의 결합도를 제거합니다.
* 유연성: 새로운 커맨드를 쉽게 추가할 수 있으며, 기존 코드를 수정하지 않고도 기능을 확장할 수 있습니다.
* 작업 큐잉 및 로깅: 작업을 큐에 넣어 순차적으로 실행하거나, 로깅하여 나중에 분석할 수 있습니다.
* 실행 취소 및 재실행 기능: 명령 객체에 상태 정보를 저장하여 실행 취소 및 재실행 기능을 구현할 수 있습니다.

## 사용 사례
* UI 이벤트 처리: 버튼 클릭 등의 이벤트를 처리할 때
* 매크로 기록 및 실행: 여러 작업을 기록하여 나중에 실행할 때
* 트랜잭션 관리: DBMS 트랜잭션을 관리하고 실행 취소 및 재실행 기능을 구현할 때
* 작업 큐: 작업을 큐에 넣어 순차적으로 실행하거나, 작업 실패 시 재실행할 때
* 원격 서비스 호출: 원격 서비스 호출을 캡슐화하여 호출자의 결합을 제거할 때

## 예시 코드
* Command: Command Interface
    ```java
    public interface Command {
        void execute();
        void undo();
    }
    ```
  
* WriteCommand: Concrete Command
    ```java
    public class WriteCommand implements Command{
        private TextEditor textEditor;
        private String textToAdd;
    
        public WriteCommand(TextEditor textEditor, String textToAdd) {
            this.textEditor = textEditor;
            this.textToAdd = textToAdd;
        }
    
    
        @Override
        public void execute() {
            textEditor.write(textToAdd);
        }
    
        @Override
        public void undo() {
            textEditor.erase(textToAdd.length());
        }
    }
    ```
  
* TextEditor: Receiver
    ```java
    public class TextEditor {
        private StringBuilder content = new StringBuilder();
        public void write(String textToAdd){
            content.append(textToAdd);
        }
        public void erase(int length){
            if(content.length() >= length){
                content.delete(content.length() - length, content.length());
            }
        }
        public String getContent(){
            return content.toString();
        }
    }
    ```

* CommandManager: Invoker
  ```java
  public class CommandManager {
      private Stack<Command> commandStack = new Stack<>();
      private Stack<Command> undoStack = new Stack<>();
  
      public void executeCommand(Command command){
          command.execute();
          commandStack.push(command);
          undoStack.clear();
      }
  
      public void undo(){
          if(!commandStack.isEmpty()){
              Command command = commandStack.pop();
              command.undo();
              undoStack.push(command);
          }
      }
  
      public void redo(){
          if(!undoStack.isEmpty()){
              Command command = undoStack.pop();
              command.execute();
              commandStack.push(command);
          }
      }
  }
  ```

* CommandPatternMain: Main
  ```java
  public class CommandPatternMain {
      public static void main(String[] args) {
          TextEditor textEditor = new TextEditor();
          CommandManager commandManager = new CommandManager();
  
          commandManager.executeCommand(new WriteCommand(textEditor, "Hello, "));
          commandManager.executeCommand(new WriteCommand(textEditor, "world!!"));
  
          System.out.println("Current text: " + textEditor.getContent());
  
          commandManager.undo();
          System.out.println("After undo: "+ textEditor.getContent());
  
          commandManager.redo();
          System.out.println("After redo: "+ textEditor.getContent());
      }
  }
  ```

* Output
  ```shell
  Current text: Hello, world!!
  After undo: Hello, 
  After redo: Hello, world!!
  ```