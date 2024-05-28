package designpattern.creation.prototypepattern;

import designpattern.creation.prototypepattern.prototype.Circle;
import designpattern.creation.prototypepattern.prototype.Rectangle;
import designpattern.creation.prototypepattern.prototype.Shape;

import java.util.ArrayList;
import java.util.List;

public class PrototypeMain {
    public static void main(String[] args) {
        Shape originCircle = new Circle(10);
        Shape originRectangle = new Rectangle(20, 30);

        Shape clonedCircle = originCircle.clone();
        Shape clonedRectangle = originRectangle.clone();

        System.out.println("Original Objects:");
        originCircle.draw();
        originRectangle.draw();

        System.out.println("Cloned Objects:");
        clonedCircle.draw();
        clonedRectangle.draw();
    }
}
