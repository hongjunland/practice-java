package designpattern.stucture.proxypattern.realsubject;

import designpattern.stucture.proxypattern.subject.Subject;

public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("RealSubject: Handling request.");
    }
}
