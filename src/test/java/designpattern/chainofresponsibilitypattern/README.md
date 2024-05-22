## Chain of Responsibility Pattern
책임연쇄 패턴은 요청이 처리될때까지 객체가 잠재적 핸들러 체인을 따라 요청을 전달할 수 있도록 하는 디자인 패턴입니다.
이 패턴은 수신자와 송신자를 분리하여 송신자에 대한 정보를 알 필요 없이 여러 객체들에게 요청을 처리할 수 있도록 합니다.
마치 연결 리스트처럼 각 Handler 객체끼리 다음 포인터를 연결하는 구조입니다.

## 구성 요소
1. Handler Interface: 요청들을 핸들링할 인터페이스를 정의합니다. 그리고 다음 체인에 전달할 메서드도 정의합니다.
2. Concrete Handler: 핸들러 인터페이스를 구현하고 요청을 처리할 수 있는지 여부를 결정합니다. 처리할수 없는 경우, 다음 핸들러로 요청이 전달됩니다.
3. Client(Main)

## 장점
* 유연성: 요청을 처리하는 객체가 동적으로 변경될 수 있어 유연합니다.
* 객체 간 결합도 감소: 요청의 발신자와 수신자를 분리하여 객체 간의 결합도를 낮춰줍니다.
* 책임 분리: 각 객체는 단일 책임 원칙을 따르며, 자신이 처리할 수 있는 요청만 처리합니다.

## 사용 사례
* Spring Security: 요청에 대한 Filter chain 에서 사용됩니다.
* 로그 처리기: 각 로그 처리기가 다른 유형의 로그를 처리하거나 다음 처리기로 전달합니다.
* Event Handler: GUI에서 이벤트를 처리하는 핸들러 체인

## 예시 코드
* Handler : Handler Interface
    ```java
    public interface Handler {
        void setNextHandler(Handler nextHandler);
        void handleRequest(String request);
    }
    ```

* HandlerImplA: Concrete Handler
    ```java
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
    ```

* HandlerImplB: Concrete Handler
    ```java
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
    ```

* HandlerImplC: Concrete Handler
    ```java
    public class HandlerImplC implements Handler{
        private Handler nextHandler;
        @Override
        public void setNextHandler(Handler nextHandler) {
            this.nextHandler = nextHandler;
        }
    
        @Override
        public void handleRequest(String request) {
            if(request.contains("#")){
                System.out.println("Handler C: 해당 요청에 #이 들어있습니다.");
            }else if(nextHandler != null){
                nextHandler.handleRequest(request);
            }
        }
    }
    ```
* Main
    ```java
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
    ```

* Output
    ```shell
    Handler A: 해당 요청에 공백이 들어있습니다.
    Handler C: 해당 요청에 #이 들어있습니다.
    Handler B: 해당 요청에 @이 들어있습니다.
    ```
