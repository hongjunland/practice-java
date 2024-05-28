package designpattern.creation.factorymethodpattern.creator;

import designpattern.creation.factorymethodpattern.product.ConcreteProductB;
import designpattern.creation.factorymethodpattern.product.Product;

public class ConcreteCreatorB extends Creator{
    @Override
    public Product factoryMethod() {
        return new ConcreteProductB();
    }
}
