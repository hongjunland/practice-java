package designpattern.behavior.meditatorpattern.mediator;

import designpattern.behavior.meditatorpattern.colleague.User;

public interface ChatMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}
