# Single Responsibility Principle
클래스는 단 하나의 책임만 가져야 하며, 클래스가 변경되어야 하는 이유는 단 하나뿐이어야 한다.

## 중요성
* 가독성 향상: 클래스가 하나의 책임만 가지므로, 클래스의 목적이 명확해지고, 코드를 읽고 이해하기 쉬워집니다.
* 유지보수성 향상: 클래스에 변경이 필요할 때, 변경의 범위가 한정되므로 다른 코드에 영향을 주지 않고 수정할 수 있습니다.
* 재사용성 향상: 하나의 책임만 가지는 클래스는 다른 프로그램에서도 쉽게 재사용할 수 있습니다.
* 테스트 용이성: 단일 책임을 가지는 클래스는 더 작은 단위 테스트가 가능하므로 테스트가 쉽고 빠릅니다.

## 기존 코드
* Book
    ```java
    public class Book {
        private String title;
        private String author;
    
        public Book(String title, String author) {
            this.title = title;
            this.author = author;
        }
    
        public void print(){
            System.out.println("Title: "+ title + ", Author: "+ author);
        }
        public void saveToFile(){
            //.....
        }
    }
    ```
    해당 `Book` 클래스는 책의 정보뿐만 아니라 정보, 출력, 저장을 모두 책임지고 있습니다.

## 개선 코드
* Book
    ```java
    public class Book {
        private String title;
        private String author;
    
        public Book(String title, String author) {
            this.title = title;
            this.author = author;
        }
    
        public String getTitle() {
            return title;
        }
    
        public void setTitle(String title) {
            this.title = title;
        }
    }
    ```
* BookPrinter
    ```java
    public class BookPrinter {
        public void print(Book book){
            System.out.println("Title: " + book.getTitle()+ ", Author: " + book.getTitle());
        }
    }
    ```
* BookRepository
    ```java
    public class BookRepository {
        public void saveToFile(){
            // 저장 로직
        }
    }
    ```