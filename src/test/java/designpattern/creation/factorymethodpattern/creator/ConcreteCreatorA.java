package designpattern.creation.factorymethodpattern.creator;

import designpattern.creation.factorymethodpattern.product.ConcreteProductA;
import designpattern.creation.factorymethodpattern.product.Product;

public class ConcreteCreatorA extends Creator{
    @Override
    public Product factoryMethod() {
        return new ConcreteProductA();
    }
}
