package designpattern.strategypattern;

import designpattern.strategypattern.context.PrinterContext;
import designpattern.strategypattern.strategy.ConsolePrintStrategy;
import designpattern.strategypattern.strategy.LowerCasePrintStrategy;
import designpattern.strategypattern.strategy.UpperCasePrintStrategy;

public class StrategyPatternMain {
    public static void main(String[] args) {
        PrinterContext printerContext = new PrinterContext();

        printerContext.setPrintStrategy(new ConsolePrintStrategy());
        printerContext.print("Hello World!!");

        printerContext.setPrintStrategy(new LowerCasePrintStrategy());
        printerContext.print("Hello World!!");

        printerContext.setPrintStrategy(new UpperCasePrintStrategy());
        printerContext.print("Hello World!!");
    }
}
