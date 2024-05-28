package designpattern.creation.factorymethodpattern.product;

public class ConcreteProductA implements Product{
    @Override
    public void use() {
        System.out.println("Using Product A");
    }
}
