package designpattern.behavior.strategypattern;

import designpattern.behavior.strategypattern.context.PrinterContext;
import designpattern.behavior.strategypattern.strategy.ConsolePrintStrategy;
import designpattern.behavior.strategypattern.strategy.LowerCasePrintStrategy;
import designpattern.behavior.strategypattern.strategy.UpperCasePrintStrategy;

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
