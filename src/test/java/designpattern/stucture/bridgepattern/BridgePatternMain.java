package designpattern.stucture.bridgepattern;

import designpattern.stucture.bridgepattern.Implementor.DrawingAPI1;
import designpattern.stucture.bridgepattern.Implementor.DrawingAPI2;
import designpattern.stucture.bridgepattern.abstraction.CircleShape;
import designpattern.stucture.bridgepattern.abstraction.Shape;

public class BridgePatternMain {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[]{
                new CircleShape(1, 2, 3, new DrawingAPI1()),
                new CircleShape(5, 7, 11, new DrawingAPI2())
        };
        for(Shape shape : shapes){
            shape.resizeByPercentage(2.5);
            shape.draw();
        }
    }
}
