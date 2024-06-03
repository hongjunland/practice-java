package designpattern.creation.factorymethodpattern;

import designpattern.creation.factorymethodpattern.creator.ConcreteCreatorA;
import designpattern.creation.factorymethodpattern.creator.ConcreteCreatorB;
import designpattern.creation.factorymethodpattern.creator.Creator;

public class FactoryMethodPatternMain {
    public static void main(String[] args) {
        Creator creatorA = new ConcreteCreatorA();
        creatorA.someOperation();
        Creator creatorB = new ConcreteCreatorB();
        creatorB.someOperation();
    }
}
