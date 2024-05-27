package designpattern.behavior.observerpattern.subject;

import designpattern.behavior.observerpattern.observer.Observer;

public interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyAllObservers();
}
