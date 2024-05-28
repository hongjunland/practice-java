package designpattern.creation.factorymethodpattern.creator;

import designpattern.creation.factorymethodpattern.product.Product;

public abstract class Creator {
    public abstract Product factoryMethod();

    public void someOperation() {
        Product product = factoryMethod();
        product.use();
    }
}
