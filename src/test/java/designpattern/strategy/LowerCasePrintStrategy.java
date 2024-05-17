package designpattern.strategy;

public class LowerCasePrintStrategy implements PrintStrategy{
    @Override
    public void print(String text) {
        System.out.println("UpperCase: " + text.toLowerCase());
    }
}
