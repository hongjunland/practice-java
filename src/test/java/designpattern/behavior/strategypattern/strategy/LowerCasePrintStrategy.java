package designpattern.behavior.strategypattern.strategy;

public class LowerCasePrintStrategy implements PrintStrategy {
    @Override
    public void print(String text) {
        System.out.println("LowerCase: " + text.toLowerCase());
    }
}
