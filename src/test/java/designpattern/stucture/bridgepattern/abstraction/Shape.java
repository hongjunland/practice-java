package designpattern.stucture.bridgepattern.abstraction;

import designpattern.stucture.bridgepattern.Implementor.DrawingAPI;

public abstract class Shape {
    protected DrawingAPI drawingAPI;
    protected Shape(DrawingAPI drawingAPI){
        this.drawingAPI = drawingAPI;
    }
    public abstract void draw();
    public abstract void resizeByPercentage(double pct);
}
