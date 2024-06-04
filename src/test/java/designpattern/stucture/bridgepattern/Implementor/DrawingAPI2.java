package designpattern.stucture.bridgepattern.Implementor;

public class DrawingAPI2 implements DrawingAPI{
    @Override
    public void drawCircle(double x, double y, double radius) {
        System.out.println("API2.circle at (" + x + ", " + y + ") with radius " + radius);
    }
}
