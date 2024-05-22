package designpattern.chainofresponsibility.handler;

public interface Handler {
    void setNextHandler(Handler nextHandler);
    void handleRequest(String request);
}
