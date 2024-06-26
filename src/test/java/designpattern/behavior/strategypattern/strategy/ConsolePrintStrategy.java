package designpattern.behavior.strategypattern.strategy;

public class ConsolePrintStrategy implements PrintStrategy {
    @Override
    public void print(String text) {
        System.out.println("Console: " + text);
    }
}
