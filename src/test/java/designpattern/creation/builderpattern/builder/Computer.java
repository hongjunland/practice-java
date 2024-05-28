package designpattern.creation.builderpattern.builder;

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
