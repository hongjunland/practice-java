package designpattern.stucture.decoratorpattern.decorator;

import designpattern.stucture.decoratorpattern.component.Component;



public class ConcreteDecoratorA extends Decorator{
    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        addedBehavior();
    }

    private void addedBehavior() {
        System.out.println("ConcreteDecoratorA: 추가 기능 A");
    }
}
