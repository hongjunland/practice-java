package designpattern.behavior.chainofresponsibilitypattern.handler;

public class HandlerImplA implements Handler{
    private Handler nextHandler;
    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(String request) {
        if(request.contains(" ")){
            System.out.println("Handler A: 해당 요청에 공백이 들어있습니다.");
        }else if(nextHandler != null){
            nextHandler.handleRequest(request);
        }
    }
}
