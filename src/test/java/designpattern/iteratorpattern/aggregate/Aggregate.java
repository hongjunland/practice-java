package designpattern.iteratorpattern.aggregate;

import designpattern.iteratorpattern.itrerator.Iterator;

public interface Aggregate<T> {
    Iterator<T> createIterator();
}
