package designpattern.stucture.compositepattern;

import designpattern.stucture.compositepattern.component.Graphic;
import designpattern.stucture.compositepattern.composite.CompositeGraphic;
import designpattern.stucture.compositepattern.leaf.Circle;
import designpattern.stucture.compositepattern.leaf.Square;

public class CompositePatternMain {
    public static void main(String[] args) {
        // 개별 객체 생성
        Graphic circle = new Circle();
        Graphic square = new Square();

        // 복합 객체 생성
        CompositeGraphic composite1 = new CompositeGraphic();
        composite1.add(circle);
        composite1.add(square);

        CompositeGraphic composite2 = new CompositeGraphic();
        composite2.add(new Square());
        composite2.add(new Circle());
        composite2.add(composite1);

        // 트로 구조를 통해 전체 객체를 동일하게 처리
        composite2.draw();
    }
}
