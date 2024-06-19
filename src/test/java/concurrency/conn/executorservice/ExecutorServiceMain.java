package concurrency.conn.executorservice;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

class ExecutorServiceMain {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Submit Runnable tasks
        Future<?> runnableFuture = executorService.submit(() -> {
            System.out.println("Executing Runnable Task by " + Thread.currentThread().getName());
        });

        // Submit Callable
        Future<Integer> callableFuture = executorService.submit(() -> {
            System.out.println("Executing Callable Task by " + Thread.currentThread().getName());
            return 42;
        });

        try {
            System.out.println("Runnable Task completed: " + runnableFuture.get());
            System.out.println("Callable Task result: " + callableFuture.get());

            // invokeAll
            List<Callable<Integer>> taskList = Arrays.asList(() -> 1, () -> 2, () -> 3);
            List<Future<Integer>> futures = executorService.invokeAll(taskList);
            for (Future<Integer> future : futures) {
                System.out.println("invokeAll result: " + future.get());
            }

            // invokeAny
            Integer result = executorService.invokeAny(taskList);
            System.out.println("invokeAny result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // 스레드 풀 종료
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}
