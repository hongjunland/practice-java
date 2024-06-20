package concurrency.conn.asyncprogramming;

import java.util.concurrent.CompletableFuture;

class CompletableFutureMain {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // 비동기 작업 수행
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello, World!";
        });

        // 결과 출력
        future.thenAccept(result -> {
            System.out.println(result);
        });

        // 메인 스레드가 즉시 종료되지 않도록 대기
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
