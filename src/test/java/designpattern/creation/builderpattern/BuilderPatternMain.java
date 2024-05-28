package designpattern.creation.builderpattern;

import designpattern.creation.builderpattern.builder.Computer;
import designpattern.creation.builderpattern.builder.Director;

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
