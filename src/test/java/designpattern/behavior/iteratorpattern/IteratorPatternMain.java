package designpattern.behavior.iteratorpattern;

import designpattern.behavior.iteratorpattern.aggregate.ConcreteAggregate;
import designpattern.behavior.iteratorpattern.itrerator.Iterator;

public class IteratorPatternMain {
    public static void main(String[] args) {
        ConcreteAggregate<String> aggregate = new ConcreteAggregate<>();
        aggregate.addItem("Item 1");
        aggregate.addItem("Item 2");
        aggregate.addItem("Item 3");
        Iterator<String> iterator = aggregate.createIterator();
        while(iterator.hasNext()){
            String item = iterator.next();
            System.out.println("Item: " + item);
        }
    }
}
