package designpattern.visitorpattern.visitor;

import designpattern.visitorpattern.element.DirectoryElement;
import designpattern.visitorpattern.element.FileElement;

public interface Visitor {
    void visit(FileElement file);
    void visit(DirectoryElement directory);
}
