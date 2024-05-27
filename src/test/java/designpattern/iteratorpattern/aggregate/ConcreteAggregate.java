package designpattern.iteratorpattern.aggregate;

import designpattern.iteratorpattern.itrerator.ConcreteIterator;
import designpattern.iteratorpattern.itrerator.Iterator;

import java.util.ArrayList;
import java.util.List;

public class ConcreteAggregate<T> implements Aggregate<T>{
    private List<T> items = new ArrayList<>();
    public void addItem(T item){
        items.add(item);
    }
    public List<T> getItems(){
        return items;
    }
    @Override
    public Iterator<T> createIterator() {
        return new ConcreteIterator<>(items);
    }
}
