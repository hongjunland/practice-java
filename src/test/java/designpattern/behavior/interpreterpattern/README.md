# Interpreter Pattern
인터프리터 패턴은 주어진 언어 문법을 표현하고 해석하는 패턴입니다. 이 패턴은 주로 SQL Parser, 정규 표현식 엔진, 컴파일러와 인터프리터 등에 사용됩니다.
인터프리터 패턴을 사용하면 언어의 문법을 클래스 구조로 정의하고, 문장을 해석하고 평가하는 방법을 제공합니다.

## 구성 요소
1. AbstractExpression: 인터프리터의 인터페이스로, Interpret 작업을 수행하는 메서드를 정의합니다.
2. TerminalExpression: 표현식의 최종 요소로, 기본 해석 작업을 수행합니다. 문법의 말단 요소를 처리합니다.
3. NonTerminalExpression: 하나 이상의 다른 표현식을 포함하는 표현식으로, 복잡한 문법 규칙을 처리합니다.
4. Context: 해석에 필요한 전역 정보를 제공합니다. 변수를 저장하거나, 입력된 표현식을 저장합니다.

## 장점
* 문법의 유연한 정의: 언어의 문법을 쉽게 정의하고 확장할 수 있습니다.
* 표현식의 가독성: 언어의 규칙을 명확하게 클래스 구조로 표현하여 가독성을 높입니다.
* 유지보수성 향상: 언어의 각 규칙을 독립적으로 구현할 수 있어 유지보수성이 향상됩니다.

## 사용 사례
* SQL Parser
* 정규표현식 엔진
* 프로그래밍언어 Interpreter
* Rule Engine

## 예시 코드
* Abstract Expression
    ```java
    public interface Expression {
        int interpret();
    }
    ```

* Concrete Expression: Concrete Expression
    ```java
    public class NumberExpression implements Expression{
        private int number;
    
        public NumberExpression(int number) {
            this.number = number;
        }
    
        @Override
        public int interpret() {
            return number;
        }
    }
    
    public class AddExpression implements Expression{
        private Expression leftExpression;
        private Expression rightExpression;
    
        public AddExpression(Expression leftExpression, Expression rightExpression) {
            this.leftExpression = leftExpression;
            this.rightExpression = rightExpression;
        }
    
        @Override
        public int interpret() {
            return leftExpression.interpret() + rightExpression.interpret();
        }
    }
    
    public class SubstractExpression implements Expression{
        private Expression leftExpression;
        private Expression rightExpression;
    
        public SubstractExpression(Expression leftExpression, Expression rightExpression) {
            this.leftExpression = leftExpression;
            this.rightExpression = rightExpression;
        }
        @Override
        public int interpret() {
            return leftExpression.interpret()- rightExpression.interpret();
        }
    }
    
    ```

* Main
    ```java
    public class InterpreterPatternMain {
        public static void main(String[] args) {
            Expression number1 = new NumberExpression(1);
            Expression number2 = new NumberExpression(3);
            Expression addExpression = new AddExpression(number1, number2);
    
            System.out.println("1+ 2 = " + addExpression.interpret());
    
            Expression number3 = new NumberExpression(3);
            Expression substractExpression = new SubstractExpression(addExpression, number3);
    
            System.out.println("(1+2) - 3 = " + substractExpression.interpret());
        }
    }
    ```

* Output
    ```shell
    1+ 2 = 4
    (1+2) - 3 = 1
    ```