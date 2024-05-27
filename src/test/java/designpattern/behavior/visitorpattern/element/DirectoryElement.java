package designpattern.behavior.visitorpattern.element;

import designpattern.behavior.visitorpattern.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class DirectoryElement implements Element{
    private String title;
    private List<Element> elements = new ArrayList<>();
    public DirectoryElement(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void addElement(Element element){
        elements.add(element);
    }

    public List<Element> getElements() {
        return elements;
    }


    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
