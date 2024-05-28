package designpattern.creation.builderpattern.builder;

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

