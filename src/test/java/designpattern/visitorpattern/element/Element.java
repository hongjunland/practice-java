package designpattern.visitorpattern.element;

import designpattern.visitorpattern.visitor.Visitor;

public interface Element {
    void accept(Visitor visitor);
}
