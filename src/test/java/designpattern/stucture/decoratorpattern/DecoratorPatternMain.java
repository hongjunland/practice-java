package designpattern.stucture.decoratorpattern;

import designpattern.stucture.decoratorpattern.component.Component;
import designpattern.stucture.decoratorpattern.component.ConcreteComponent;
import designpattern.stucture.decoratorpattern.decorator.ConcreteDecoratorA;
import designpattern.stucture.decoratorpattern.decorator.ConcreteDecoratorB;

public class DecoratorPatternMain {
    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        Component decoratorA = new ConcreteDecoratorA(component);
        Component decoratorB = new ConcreteDecoratorB(decoratorA);

        System.out.println("Client: 기본 컴포넌트 실행");
        component.operation();

        System.out.println("\nClient: 데코레이터 A를 적용한 컴포넌트 실행");
        decoratorA.operation();

        System.out.println("\nClient: 데코레이터 A와 B를 적용한 컴포넌트 실행");
        decoratorB.operation();
    }
}
