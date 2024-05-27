package designpattern.behavior.visitorpattern.visitor;

import designpattern.behavior.visitorpattern.element.DirectoryElement;
import designpattern.behavior.visitorpattern.element.Element;
import designpattern.behavior.visitorpattern.element.FileElement;

public class SizeVisitor implements Visitor{
    private int totalSize = 0;

    public int getTotalSize() {
        return totalSize;
    }

    @Override
    public void visit(FileElement file) {
        this.totalSize += file.getSize();
    }

    @Override
    public void visit(DirectoryElement directory) {
        for(Element element: directory.getElements()){
            element.accept(this);
        }
    }
}
