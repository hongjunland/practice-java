package designpattern.strategy;

public class Printer {
    private PrintStrategy printStrategy;

    public void setPrintStrategy(PrintStrategy printStrategy){
        this.printStrategy = printStrategy;
    }
    public void print(String text){
        printStrategy.print(text);
    }
}
