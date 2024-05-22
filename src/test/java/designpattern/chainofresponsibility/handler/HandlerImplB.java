package designpattern.chainofresponsibility.handler;

public class HandlerImplB implements Handler{
    private Handler nextHandler;
    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(String request) {
        if(request.contains("@")){
            System.out.println("Handler B: 해당 요청에 @이 들어있습니다.");
        }else if(nextHandler != null){
            nextHandler.handleRequest(request);
        }
    }
}
