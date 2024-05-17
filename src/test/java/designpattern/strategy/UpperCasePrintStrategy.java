package designpattern.strategy;

public class UpperCasePrintStrategy implements PrintStrategy{
    @Override
    public void print(String text) {
        System.out.println("UpperCase: " + text.toUpperCase());
    }
}
