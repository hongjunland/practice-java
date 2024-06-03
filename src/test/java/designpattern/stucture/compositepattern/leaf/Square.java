package designpattern.stucture.compositepattern.leaf;

import designpattern.stucture.compositepattern.component.Graphic;

public class Square implements Graphic {

    @Override
    public void draw() {
        System.out.println("Drawing a Square");
    }
}
