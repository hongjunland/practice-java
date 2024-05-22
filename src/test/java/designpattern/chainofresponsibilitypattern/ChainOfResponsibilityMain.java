package designpattern.chainofresponsibilitypattern;

import designpattern.chainofresponsibilitypattern.handler.Handler;
import designpattern.chainofresponsibilitypattern.handler.HandlerImplA;
import designpattern.chainofresponsibilitypattern.handler.HandlerImplB;
import designpattern.chainofresponsibilitypattern.handler.HandlerImplC;

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
