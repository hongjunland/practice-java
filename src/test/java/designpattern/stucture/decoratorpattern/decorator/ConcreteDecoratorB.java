package designpattern.stucture.decoratorpattern.decorator;

import designpattern.stucture.decoratorpattern.component.Component;

public class ConcreteDecoratorB extends Decorator{
    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        addedBehavior();
    }

    private void addedBehavior() {
        System.out.println("ConcreteDecoratorB: 추가 기능 B");
    }
}
