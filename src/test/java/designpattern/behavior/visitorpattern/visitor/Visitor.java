package designpattern.behavior.visitorpattern.visitor;

import designpattern.behavior.visitorpattern.element.DirectoryElement;
import designpattern.behavior.visitorpattern.element.FileElement;

public interface Visitor {
    void visit(FileElement file);
    void visit(DirectoryElement directory);
}
