package solid.ocp.before;

public class AreaCalculator {
    public double calculate(Shape shape){
        if(shape.type == 1){
            Rectangle rectangle = (Rectangle) shape;
            return rectangle.length * rectangle.width;
        }else if (shape.type == 2){
            Circle circle = (Circle) shape;
            return Math.PI * circle.radius * circle.radius;
        }
        return 0;
    }
}
