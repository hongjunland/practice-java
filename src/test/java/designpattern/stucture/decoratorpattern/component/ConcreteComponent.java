package designpattern.stucture.decoratorpattern.component;

public class ConcreteComponent implements Component{
    @Override
    public void operation() {
        System.out.println("ConcreteComponent: 기본 작업 수행");
    }
}
