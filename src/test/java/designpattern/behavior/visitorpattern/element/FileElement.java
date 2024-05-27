package designpattern.behavior.visitorpattern.element;

import designpattern.behavior.visitorpattern.visitor.Visitor;

public class FileElement implements Element{
    private String title;
    private int size;

    public FileElement(String title, int size) {
        this.title = title;
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
