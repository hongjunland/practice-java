package designpattern.behavior.visitorpattern.visitor;

import designpattern.behavior.visitorpattern.element.DirectoryElement;
import designpattern.behavior.visitorpattern.element.Element;
import designpattern.behavior.visitorpattern.element.FileElement;

public class NameVisitor implements Visitor{
    @Override
    public void visit(FileElement file) {
        System.out.println("File: "+ file.getTitle());
    }

    @Override
    public void visit(DirectoryElement directory) {
        System.out.println("Directory: "+ directory.getTitle());
        for(Element element : directory.getElements()){
            element.accept(this);
        }
    }
}
