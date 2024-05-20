package designpattern.observerpattern;

import designpattern.observerpattern.observer.ConcreteObserver;
import designpattern.observerpattern.observer.Observer;
import designpattern.observerpattern.subject.ConcreteSubject;

public class ObserverPatternMain {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();

        Observer observer1 = new ConcreteObserver("Observer 1", subject);
        Observer observer2 = new ConcreteObserver("Observer 2", subject);
        Observer observer3 = new ConcreteObserver("Observer 3", subject);

        subject.attach(observer1);
        subject.attach(observer2);
        subject.attach(observer3);

        System.out.println("상태변경");
        subject.setState(10);
        System.out.println("상태변경");
        subject.setState(20);
        System.out.println("상태변경");
        subject.setState(15);
    }
}
