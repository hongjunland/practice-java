package designpattern.meditatorpattern;

import designpattern.meditatorpattern.colleague.User;
import designpattern.meditatorpattern.colleague.UserImpl;
import designpattern.meditatorpattern.mediator.ChatMediator;
import designpattern.meditatorpattern.mediator.ChatMediatorImpl;

public class MediatorPatternMain {
    public static void main(String[] args) {
        ChatMediator mediator = new ChatMediatorImpl();
        User user1 = new UserImpl(mediator, "Tom");
        User user2 = new UserImpl(mediator, "Kate");
        User user3 = new UserImpl(mediator, "Jack");
        User user4 = new UserImpl(mediator, "Jenny");
        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);
        mediator.addUser(user4);

        user1.send("Hi, I am Tom");
        System.out.println("--------");
        user3.send("Nice to meet you");
    }
}
