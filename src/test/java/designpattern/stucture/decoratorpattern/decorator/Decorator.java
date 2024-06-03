package designpattern.stucture.decoratorpattern.decorator;

import designpattern.stucture.decoratorpattern.component.Component;

public abstract class Decorator implements Component {
    protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
}
