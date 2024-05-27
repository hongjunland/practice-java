package designpattern.behavior.iteratorpattern.itrerator;

import java.util.List;

public class ConcreteIterator<T> implements Iterator{
    private List<T> collections;
    private int position = 0;

    public ConcreteIterator(List<T> collections) {
        this.collections = collections;
    }

    @Override
    public boolean hasNext() {
        return position < collections.size();
    }

    @Override
    public T next() {
        if(this.hasNext()){
            return collections.get(position++);
        }
        return null;
    }
}
