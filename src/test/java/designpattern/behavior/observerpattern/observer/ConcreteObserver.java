package designpattern.behavior.observerpattern.observer;

import designpattern.behavior.observerpattern.subject.ConcreteSubject;

public class ConcreteObserver implements Observer{
    private String name;
    private ConcreteSubject subject;

    public ConcreteObserver(String name, ConcreteSubject subject) {
        this.name = name;
        this.subject = subject;
    }

    @Override
    public void update() {
        System.out.println(name + " 업데이트 알림: 현재 상태는 " + subject.getState());
    }
}
