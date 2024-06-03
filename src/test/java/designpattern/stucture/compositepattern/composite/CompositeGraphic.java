package designpattern.stucture.compositepattern.composite;

import designpattern.stucture.compositepattern.component.Graphic;

import java.util.ArrayList;
import java.util.List;

public class CompositeGraphic implements Graphic {
    private final List<Graphic> childGraphics = new ArrayList<>();
    public void add(Graphic graphic){
        childGraphics.add(graphic);
    }
    public void remove(Graphic graphic){
        childGraphics.remove(graphic);
    }
    @Override
    public void draw() {
        for (Graphic graphic: childGraphics){
            graphic.draw();
        }
    }
}
