package designpattern.behavior.chainofresponsibilitypattern;

import designpattern.behavior.chainofresponsibilitypattern.handler.Handler;
import designpattern.behavior.chainofresponsibilitypattern.handler.HandlerImplA;
import designpattern.behavior.chainofresponsibilitypattern.handler.HandlerImplB;
import designpattern.behavior.chainofresponsibilitypattern.handler.HandlerImplC;

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
