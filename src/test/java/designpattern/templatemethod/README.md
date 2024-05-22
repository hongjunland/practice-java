## Template Method Pattern
알고리즘의 구조를 저의하고, 하위 클래스가 알고리즘의 특정 단계를 재정의할 수 있도록 합니다. 
이 패턴은 알고리즘의 뼈대를 정의하며서, 세부적인 구현은 하위 클래스에 맡기는 방식으로 코드를 재사용하고 확장성을 높입니다.

## 구성 요소 
1. Abstract Class: 알고리즘의 뼈대를 정의하는 템플릿 메서드를 포함합니다. 여러 단계로 나누어져 있으며, 각 단계는 추상 메서드로 선언되어 하위 클래스에서 구현됩니다.
2. Concrete Class: 추상 클래스에서 정의된 추상 메서드를 구현하여 알고리즘의 각 단계를 구체화합니다.

## 장점
* 재사용성: 공통 구조를 상위클래스에 정의함으로써 코드 중복을 줄입니다.
* 유연성: 알고리즘의 세부 구현을 하위 클래스에서 자유롭게 변경할 수 있어 확장성이 높습니다.
* 유지보수성: 알고리즘의 공통 부분을 한 곳에서 관리할 수 있어 유지보수가 용이합니다.

## 사용사례
* JUnit 프레임워크
* Spring TransactionTemplate, HibernateTemplate
* Apache Hadoop의 Mapper 클래스

## 예시 코드
* CaffeineBeverage: Abstract Class
    ```java
    public abstract class CaffeineBeverage {
        public final void prepareRecipe(){
            boilWater();
            brew();
            pourInCup();
            addCondiments();
        }
    
        protected abstract void brew();
        protected abstract void addCondiments();
    
        private void pourInCup() {
            System.out.println("Pouring into cup");
        }
        protected void boilWater(){
            System.out.println("Boiling water");
    
        }
    
    
    }
    ```

* Coffee, Tea: Concrete Class
    ```java
    //A
    public class Coffee extends CaffeineBeverage{
    
    
        @Override
        protected void brew() {
            System.out.println("Dripping coffee through filter");
        }
    
        @Override
        protected void addCondiments() {
            System.out.println("Adding sugar and milk");
        }
    }
    
    //B
    public class Tea extends CaffeineBeverage{
        @Override
        protected void brew() {
            System.out.println("Steeping the tea");
        }
    
        @Override
        protected void addCondiments() {
            System.out.println("Adding lemon");
        }
    }
    ```
  
* Main
    ```java
    public class TemplateMethodMain {
        public static void main(String[] args) {
            CaffeineBeverage tea = new Tea();
            CaffeineBeverage coffee = new Coffee();
    
            System.out.println("Making tea...");
            tea.prepareRecipe();
            System.out.println();
            System.out.println("Making coffee...");
            coffee.prepareRecipe();
        }
    }
    ```
  
* Output
    ```shell
    Making tea...
    Boiling water
    Steeping the tea
    Pouring into cup
    Adding lemon
    
    Making coffee...
    Boiling water
    Dripping coffee through filter
    Pouring into cup
    Adding sugar and milk
    ```