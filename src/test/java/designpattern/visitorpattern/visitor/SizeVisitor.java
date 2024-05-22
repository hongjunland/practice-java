package designpattern.visitorpattern.visitor;

import designpattern.visitorpattern.element.DirectoryElement;
import designpattern.visitorpattern.element.Element;
import designpattern.visitorpattern.element.FileElement;

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
