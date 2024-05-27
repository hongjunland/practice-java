package designpattern.behavior.visitorpattern.element;

import designpattern.behavior.visitorpattern.visitor.Visitor;

public interface Element {
    void accept(Visitor visitor);
}
