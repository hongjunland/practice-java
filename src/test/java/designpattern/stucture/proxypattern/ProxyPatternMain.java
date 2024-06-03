package designpattern.stucture.proxypattern;

import designpattern.stucture.proxypattern.proxy.Proxy;
import designpattern.stucture.proxypattern.subject.Subject;

public class ProxyPatternMain {
    public static void main(String[] args) {
        Subject proxy = new Proxy();
        proxy.request();
    }
}
