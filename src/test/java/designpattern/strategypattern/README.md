# Strategy Pattern

알고리즘 그룹을 정의하고, 각각을 캡술화하여 상호 교환하도록 만드는 패턴입니다. 이 패턴을 사용한다면 알고리즘을 클라이언트와 분리하여 독립적으로 변경할 수 있습니다. 이 패턴은 객체의 행동을 동적으로 바꾸고 싶을때 유용합니다.

## 구성 요소
1. Strategy Interface: 공통 메소드 정의
2. ConcreteStrategy classes: 구현체
3. Context class: Strategy 객체를 구성하여 사용합니다.

## 장점:
* 유연성 증가: 클라이언트 코드의 변경 없이 알고리즘을 쉽게 교체할 수 있습니다.
* 재사용성: 알고리즘을 독립적인 클래스로 정의하여 재사용할 수 있습니다.
* 유지 보수 용이: 알고리즘을 각각의 클래스에 캡슐화하여 유지 보수가 쉽습니다.

## 적합한 사용 사례
* 다양한 알고리즘을 사용하는 경우: 알고리즘을 런타임에 변경 가능
* 조건문 분기 제거: if-else, switch 같은 조건문들로 코드가 복잡해질때  

## 현업 사례
* 결제 처리 방식 
* UI 테마 변경
* 압축, 정렬 알고리즘 선택
* 파일 형식 선택

## 예시 코드
* PrintStrategy: Strategy interface
  ```java
    public interface PrintStrategy {
        void print(String text);
    }
    ```
* ConsolePrintStrategy: Strategy 구현체 1
  ```java
    public class ConsolePrintStrategy implements PrintStrategy{
        @Override
        public void print(String text) {
            System.out.println("Console: " + text);
        }
    }

    ```

* LowerCasePrintStrategy: Strategy 구현체 2
  ```java
  public class LowerCasePrintStrategy implements PrintStrategy{
    @Override
    public void print(String text) {
    System.out.println("UpperCase: " + text.toLowerCase());
    }
  }
    ```

* UpperCasePrintStrategy: Strategy 구현체 3
  ```java
  public class UpperCasePrintStrategy implements PrintStrategy{
    @Override
    public void print(String text) {
      System.out.println("UpperCase: " + text.toUpperCase());
    }
  }
    ```

* Printer: Context class
  ```java
  public class Printer {
      private PrintStrategy printStrategy;
  
      public void setPrintStrategy(PrintStrategy printStrategy){
          this.printStrategy = printStrategy;
      }
      public void print(String text){
          printStrategy.print(text);
      }
  }
  ```
  
* Main
    ```java
    public class StrategyPatternMain {
        public static void main(String[] args) {
            Printer printer = new Printer();
            printer.setPrintStrategy(new ConsolePrintStrategy());
            printer.print("Hello World!!");
    
            printer.setPrintStrategy(new LowerCasePrintStrategy());
            printer.print("Hello World!!");
    
            printer.setPrintStrategy(new UpperCasePrintStrategy());
            printer.print("Hello World!!");
        }
    }
    ```

* Output
  ```shell
  Console: Hello World!!
  LowerCase: hello world!!
  UpperCase: HELLO WORLD!!
  ```