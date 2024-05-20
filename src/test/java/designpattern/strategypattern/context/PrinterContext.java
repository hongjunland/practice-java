package designpattern.strategypattern.context;

import designpattern.strategypattern.strategy.PrintStrategy;

public class PrinterContext {
    private PrintStrategy printStrategy;

    public void setPrintStrategy(PrintStrategy printStrategy){
        this.printStrategy = printStrategy;
    }
    public void print(String text){
        printStrategy.print(text);
    }
}
