package designpattern.chainofresponsibility;

import designpattern.chainofresponsibility.handler.Handler;
import designpattern.chainofresponsibility.handler.HandlerImplA;
import designpattern.chainofresponsibility.handler.HandlerImplB;
import designpattern.chainofresponsibility.handler.HandlerImplC;

public class ChainOfResponsibilityMain {
    public static void main(String[] args) {
        Handler handlerA = new HandlerImplA();
        Handler handlerB = new HandlerImplB();
        Handler handlerC = new HandlerImplC();
        handlerA.setNextHandler(handlerB);
        handlerB.setNextHandler(handlerC);

        handlerA.handleRequest("dsdsd #@ddsdsd");
        handlerA.handleRequest("dsdsd#ddsdsd");
        handlerA.handleRequest("dsdsd@dsdsd");
    }
}
