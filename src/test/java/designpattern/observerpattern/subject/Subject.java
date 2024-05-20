package designpattern.observerpattern.subject;

import designpattern.observerpattern.observer.Observer;

public interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyAllObservers();
}
