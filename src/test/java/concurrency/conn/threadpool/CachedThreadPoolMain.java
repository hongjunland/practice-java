package concurrency.conn.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CachedThreadPoolMain {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i = 0 ; i < 100 ; i++){
            int taskNumber = i;
            executorService.submit(()->{
                System.out.println("Executing Task " + taskNumber + " by " + Thread.currentThread().getName());
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            });
        }

        executorService.shutdown();
    }
}
