# Builder Pattern
빌더 패턴은 복잡한 객체의 생성 과정을 단순화하고, 객체 생성의 단계별 과정을 캡슐화하여 클라이언트 코드에 객체 생성을 유연하게 하는 디자인 패턴입니다.
이 패턴은 특히, 생성자 매개변수가 많거나 객체 초기화에 여러 단계를 거쳐야 하는 경우에 유용합니다.

## 구성 요소
* Product: 최종적으로 생성될 복잡한 객체
* Builder: Product 객체를 생성하는 데 필요한 인터페이스 정의
* ConcreteBuilder: Builder 인터페이스를 구현하여 Product 객체의 생성과 조립과정을 정의
* Director: Builder 객체를 사용하는 클래스

## 장점
* 복잡한 객체의 생성 단순화: 객체 생성 과정을 단계별로 분리하여 캡슐화합니다.
* 가독성 향상: 클라이언트 코드에서 가독성을 높이고, 객체 생성 과정을 명확히 합니다.
* 유연성 증가: 객체 생성 과정에서 불필요한 매개변수를 제거하거나, 선택적 매개변수를 사용할 수 있습니다.

## 사용 사례
* StringBuilder
* Lombok에서 사용하는 Builder
* BUI 라이브러리
* 문서 생성 시스템

## 예시 코드
* Computer: Product
    ```java
    public class Computer {
        // 필수 매개변수
        private final String CPU;
        private final String RAM;
    
        // 선택적 매개변수
        private final String storage;
        private final String graphicsCard;
        private final String OS;
    
        private Computer(ComputerBuilder builder) {
            this.CPU = builder.CPU;
            this.RAM = builder.RAM;
            this.storage = builder.storage;
            this.graphicsCard = builder.graphicsCard;
            this.OS = builder.OS;
        }
    
        // Getter 메서드
        public String getCPU() {
            return CPU;
        }
    
        public String getRAM() {
            return RAM;
        }
    
        public String getStorage() {
            return storage;
        }
    
        public String getGraphicsCard() {
            return graphicsCard;
        }
    
        public String getOS() {
            return OS;
        }
    
        // Builder 클래스
        public static class ComputerBuilder {
            // 필수 매개변수
            private final String CPU;
            private final String RAM;
    
            // 선택적 매개변수
            private String storage;
            private String graphicsCard;
            private String OS;
    
            public ComputerBuilder(String CPU, String RAM) {
                this.CPU = CPU;
                this.RAM = RAM;
            }
    
            public ComputerBuilder setStorage(String storage) {
                this.storage = storage;
                return this;
            }
    
            public ComputerBuilder setGraphicsCard(String graphicsCard) {
                this.graphicsCard = graphicsCard;
                return this;
            }
    
            public ComputerBuilder setOS(String OS) {
                this.OS = OS;
                return this;
            }
    
            public Computer build() {
                return new Computer(this);
            }
        }
    }
    ```

* Director: Director
    ```java
    public class Director {
        public Computer constructGamingComputer() {
            return new Computer.ComputerBuilder("Intel i9", "32GB")
                    .setStorage("1TB SSD")
                    .setGraphicsCard("NVIDIA RTX 3080")
                    .setOS("Windows 11")
                    .build();
        }
    
        public Computer constructOfficeComputer() {
            return new Computer.ComputerBuilder("Intel i5", "16GB")
                    .setStorage("512GB SSD")
                    .setGraphicsCard("Integrated")
                    .setOS("Windows 10")
                    .build();
        }
    }
    ```
  
* Main
    ```java
    public class BuilderPatternMain {
        public static void main(String[] args) {
            Director director = new Director();
    
            Computer gamingComputer = director.constructGamingComputer();
            System.out.println("Gaming Computer:");
            System.out.println("CPU: " + gamingComputer.getCPU());
            System.out.println("RAM: " + gamingComputer.getRAM());
            System.out.println("Storage: " + gamingComputer.getStorage());
            System.out.println("Graphics Card: " + gamingComputer.getGraphicsCard());
            System.out.println("OS: " + gamingComputer.getOS());
    
            Computer officeComputer = director.constructOfficeComputer();
            System.out.println("\nOffice Computer:");
            System.out.println("CPU: " + officeComputer.getCPU());
            System.out.println("RAM: " + officeComputer.getRAM());
            System.out.println("Storage: " + officeComputer.getStorage());
            System.out.println("Graphics Card: " + officeComputer.getGraphicsCard());
            System.out.println("OS: " + officeComputer.getOS());
        }
    }
    ```
  
* Output
    ```shell
    CPU: Intel i9
    RAM: 32GB
    Storage: 1TB SSD
    Graphics Card: NVIDIA RTX 3080
    OS: Windows 11
    
    Office Computer:
    CPU: Intel i5
    RAM: 16GB
    Storage: 512GB SSD
    Graphics Card: Integrated
    OS: Windows 10
    ```