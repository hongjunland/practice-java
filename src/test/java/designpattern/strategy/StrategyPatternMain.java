package designpattern.strategy;

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
