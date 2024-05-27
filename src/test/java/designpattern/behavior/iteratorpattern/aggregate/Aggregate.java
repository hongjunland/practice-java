package designpattern.behavior.iteratorpattern.aggregate;

import designpattern.behavior.iteratorpattern.itrerator.Iterator;

public interface Aggregate<T> {
    Iterator<T> createIterator();
}
