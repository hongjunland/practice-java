package designpattern.creation.prototypepattern.prototype;

public interface Shape extends Cloneable{
    Shape clone();
    void draw();
}
