package designpattern.stucture.bridgepattern.Implementor;

public class DrawingAPI1 implements DrawingAPI{
    @Override
    public void drawCircle(double x, double y, double radius) {
        System.out.println("API1.circle at (" + x + ", " + y + ") with radius " + radius);
    }
}
