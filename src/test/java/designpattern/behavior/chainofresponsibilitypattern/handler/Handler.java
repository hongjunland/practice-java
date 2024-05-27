package designpattern.behavior.chainofresponsibilitypattern.handler;

public interface Handler {
    void setNextHandler(Handler nextHandler);
    void handleRequest(String request);
}
