package designpattern.meditatorpattern.mediator;

import designpattern.meditatorpattern.colleague.User;

public interface ChatMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}
