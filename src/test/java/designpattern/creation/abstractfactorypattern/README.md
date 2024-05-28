# Abstract Factory Pattern
관련 객체의 군을 생성하기 위한 인터페이스를 제공하는 디자인 패턴입니다. 이 패턴은 구체적인 클래스를 지정하지 않고도 관련 객체들을 생성할 수 있는 방법을 제공합니다.
주로 시스템이 독립적이고 교체 가능한 여러 제품군 중 하나를 선택할 수 있어야 할 때 유용합니다.

## 구성 요소
1. AbstractFactory: 관련 객체들을 생성하기 위한 인터페이스를 선언
2. ConcreteFactory: AbstractFactory 인터페이스의 구현체
3. AbstractProduct: 제품 객체들이 구현해야 할 인터페이스 정의
4. ConcreteProduct: AbstractProduct 인터페이스를 구현하는 구체적인 객체
5. Client: AbstractFactory 및 AbstractProduct 인터페이스를 사용하여 객체들을 생성하고 사용

## 장점
* 서로 관련된 객체들의 일관성 보장: 동일한 팩토리에서 생성된 객체들은 서로 호환됩니다.
* 서브 클래스에 의존하지 않음: 클라이언트 코드에서 서브 클래스를 지정하지 않고 객체를 생성할 수 있습니다.
* 확장성: 새로운 제품군이 추가도리 떄, 기존 코드를 수정할 필요 없이 새로운 팩토리와 제품 클래스를 추가하면 됩니다.

## 사용 사례
* GUI 툴킷: 서로 다른 OS에서 동작하는 GUI 요소들을 생성할 때 사용
* 데이터베이스 연결: 서로 다른 DB에 연결하기 위한 커넥터 객체 생성
* 문서 생성 시스템: 서로 다른 파일형식의 문서를 생성하는 객체를 생성할 때 사용

## 예시 

* GUIFactory: AbstractFactory
    ```java
    public interface GUIFactory {
        Button createButton();
        Checkbox createCheckbox();
    }
    ```
  
* MacFactory, WindowsFactory: Concrete Factory
    ```java
    public class MacFactory implements GUIFactory{
        @Override
        public Button createButton() {
            return new MacButton();
        }
    
        @Override
        public Checkbox createCheckbox() {
            return new MacCheckBox();
        }
    }
    public class WindowsFactory implements GUIFactory{
        @Override
        public Button createButton() {
            return new WindowsButton();
        }
    
        @Override
        public Checkbox createCheckbox() {
            return new WindowsCheckbox();
        }
    }
    ```
  
* Button, Checkbox: AbstractProduct 
    ```java
    public interface Button {
        void paint();
    }
    public interface Checkbox {
        void paint();
    }
    ```

* MacButton, MacCheckbox, WindowsButton, WindowsCheckbox: ConcreteProduct
    ```java
    public class MacButton implements Button{
        @Override
        public void paint() {
            System.out.println("You have created MacButton.");
        }
    }
    public class MacCheckBox implements Checkbox{
        @Override
        public void paint() {
            System.out.println("You have created MacCheckbox.");
        }
    }
    public class WindowsButton implements Button{
        @Override
        public void paint() {
            System.out.println("You have created WindowsButton.");
        }
    }
    public class WindowsCheckbox implements Checkbox{
        @Override
        public void paint() {
            System.out.println("You have created WindowsCheckbox.");
        }
    }
    ```
  
* Application: Client
    ```java
    public class Application{
        private Button button;
        private Checkbox checkbox;
    
        public Application(GUIFactory factory) {
            this.button = factory.createButton();
            this.checkbox = factory.createCheckbox();
        }
        public void paint(){
            button.paint();
            checkbox.paint();
        }
    }
    ```

* Main
    ```java
    public class AbstractFactoryPatternMain {
        public static void main(String[] args) {
            Application application;
            GUIFactory factory;
            System.out.println(System.getProperty("os.name"));
            String osName = System.getProperty("os.name").toLowerCase();
            if(osName.contains("win")){
                factory = new WindowsFactory();
            }else{
                factory = new MacFactory();
            }
    
            application = new Application(factory);
            application.paint();
        }
    }
    ```
  
* Output
    ```shell
    You have created WindowsButton.
    You have created WindowsCheckbox.
    ```
