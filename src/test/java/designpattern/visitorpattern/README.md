# Visitor Pattern
객체의 구조와는 독립적으로 새로운 연산을 추가할 수 있는 디자인 패턴입니다.
이 패턴은 객체 구조 내에 각 요소를 방문하고, 그 요소에 대해 특정 작업을 수행하는 방식으로 동작합니다.
객체 구조의 변경 없이 새로운 연산을 추가할 수 있기 때문에, 개방/폐쇄 원칙을 준수하는 데 유용합니다.

## 구성 요소
1. Visitor Interface: 객체 구조의 각 요소를 방문하기 위한 메서드를 선언합니다.
2. Concrete Visitor: Visitor Interface의 구현하며, 각 요소에 대해 수행할 구체적인 작업을 정의합니다.
3. Element Interface: Visitor를 받아들이기 위한 'accept' 메서드를 정의합니다.
4. Concrete Element: Element Interface를 구현하며, 'accept' 메서드를 통해 Visitor를 받아들입니다.
5. ObjectStructure(Optional): Element들의 집합을 관리하며, 각 요소에 대해 Visitor를 받아들이는 역할을 합니다.

## 장점
* 새로운 연산의 추가 용이: 객체 구조를 변경하지 않고 새로운 연산을 추가할 수 있습니다.
* 객체 구조와 연산의 분리: 객체 구조와 연산을 분리하여, 코드의 응집도를 높이고 유지보수성을 향상시킵니다.
* 확장성: 새로운 Visitor를 추가하여 쉽게 확장할 수 있습니다.

## 사용 사례
* 컴파일러: 구문 분석 트리를 순회하며 구문 검증, 코드 생성등의 작업을 수행
* 객체 그래프의 다양한 처리: XML 파싱, File System, UI 구성 요소 순회

## 예시 코드
* Visitor: Visitor Interface
    ```java
    public interface Visitor {
        void visit(FileElement file);
        void visit(DirectoryElement directory);
    }
    ```

* SizeVisitor: Concrete Visitor
    ```java
    public class SizeVisitor implements Visitor{
        private int totalSize = 0;
    
        public int getTotalSize() {
            return totalSize;
        }
    
        @Override
        public void visit(FileElement file) {
            this.totalSize += file.getSize();
        }
    
        @Override
        public void visit(DirectoryElement directory) {
            for(Element element: directory.getElements()){
                element.accept(this);
            }
        }
    }
    ```

* NameVisitor: Concrete Visitor
    ```java
    public class NameVisitor implements Visitor{
        @Override
        public void visit(FileElement file) {
            System.out.println("File: "+ file.getTitle());
        }
    
        @Override
        public void visit(DirectoryElement directory) {
            System.out.println("Directory: "+ directory.getTitle());
            for(Element element : directory.getElements()){
                element.accept(this);
            }
        }
    }
    ```

* Element: Element Interface
    ```java
    public interface Element {
        void accept(Visitor visitor);
    }
    ```

* DirectoryElement: Concrete Element
    ```java
    public class DirectoryElement implements Element{
        private String title;
        private List<Element> elements = new ArrayList<>();
        public DirectoryElement(String title) {
            this.title = title;
        }
    
        public String getTitle() {
            return title;
        }
    
        public void addElement(Element element){
            elements.add(element);
        }
    
        public List<Element> getElements() {
            return elements;
        }
    
    
        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }
    ```

* FileElement: Concrete Element
    ```java
    public class FileElement implements Element{
        private String title;
        private int size;
    
        public FileElement(String title, int size) {
            this.title = title;
            this.size = size;
        }
    
        public String getTitle() {
            return title;
        }
    
        public int getSize() {
            return size;
        }
    
        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }
    ```
  
* Main
    ```java
    public class VisitorPatternMain {
        public static void main(String[] args) {
            DirectoryElement rootDir = new DirectoryElement("rootDir");
            FileElement file1 = new FileElement("file1", 10);
            FileElement file2 = new FileElement("file2", 20);
            FileElement file3 = new FileElement("file3", 15);
            FileElement file4 = new FileElement("file4", 13);
            DirectoryElement dir1 = new DirectoryElement("dir1");
    
            rootDir.addElement(file1);
            rootDir.addElement(file2);
            dir1.addElement(file3);
            rootDir.addElement(file4);
            rootDir.addElement(dir1);
    
            SizeVisitor sizeVisitor = new SizeVisitor();
            System.out.println("SizeVisitor 실행");
            sizeVisitor.visit(rootDir);
            System.out.println("Total size: " + sizeVisitor.getTotalSize());
            System.out.println("\nNameVisitor 실행");
            NameVisitor nameVisitor = new NameVisitor();
            rootDir.accept(nameVisitor);
        }
    }
    ```
  
* Output
    ```shell
    SizeVisitor 실행
    Total size: 58
    
    NameVisitor 실행
    Directory: rootDir
    File: file1
    File: file2
    File: file4
    Directory: dir1
    File: file3
    ```