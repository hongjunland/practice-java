package concurrency.conn.asyncprogramming;

class CallbackMain {
    private interface Callback{
            void onComplete(String result);
    }
    public static void asyncOperation(Callback callback){
        new Thread(()->{
            try {
                Thread.sleep(2000);
                callback.onComplete("Operation completed");
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }).start();
    }
    public static void main(String[] args) {
        System.out.println("Starting async operation...");
        asyncOperation(result -> System.out.println("Callback received: " + result));
        System.out.println("Async operation started");
    }
}
