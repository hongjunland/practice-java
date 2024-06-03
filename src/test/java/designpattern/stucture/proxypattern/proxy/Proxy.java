package designpattern.stucture.proxypattern.proxy;

import designpattern.stucture.proxypattern.realsubject.RealSubject;
import designpattern.stucture.proxypattern.subject.Subject;

public class Proxy implements Subject {
    private RealSubject realSubject;
    @Override
    public void request() {
        if(realSubject == null){
            realSubject = new RealSubject();
        }
        System.out.println("Proxy: Logging the request.");
        realSubject.request();
    }
}
