package concurrency.conn.asyncprogramming;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

class AsynchronousStreamsMain {
    public static void main(String[] args) throws InterruptedException {
        // Publisher 생성
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        // Subscriber 생성
        Flow.Subscriber<String> subscriber = new Flow.Subscriber<>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                subscription.request(1); // 첫 번째 항목 요청
            }

            @Override
            public void onNext(String item) {
                System.out.println("Received: " + item);
                subscription.request(1); // 다음 항목 요청
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Done");
            }
        };

        // Subscriber를 Publisher에 등록
        publisher.subscribe(subscriber);
        
        // 항목 발행
        String[] items = {"item1", "item2", "item3"};
        for(String item : items){
            publisher.submit(item);
            System.out.println("Published: " + item);
            Thread.sleep(500);
        }
        
        // 발행 완료
        publisher.close();
        TimeUnit.SECONDS.sleep(1);
    }
}
