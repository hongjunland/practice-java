package designpattern.stucture.flyweightpattern;

import designpattern.stucture.flyweightpattern.flyweight.Flyweight;
import designpattern.stucture.flyweightpattern.flyweightfactory.FlyweightFactory;

public class FlyweightPatternMain {
    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight flyweight1 = factory.getFlyweight("A");
        Flyweight flyweight2 = factory.getFlyweight("B");
        Flyweight flyweight3 = factory.getFlyweight("A");

        flyweight1.operation("First Call");
        flyweight2.operation("Second Call");
        flyweight3.operation("Third Call");
    }
}
