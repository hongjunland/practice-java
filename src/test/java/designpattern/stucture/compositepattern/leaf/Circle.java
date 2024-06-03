package designpattern.stucture.compositepattern.leaf;

import designpattern.stucture.compositepattern.component.Graphic;

public class Circle implements Graphic {

    @Override
    public void draw() {
        System.out.println("Drawing a Circle");
    }
}
