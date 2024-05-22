package designpattern.meditatorpattern.mediator;

import designpattern.meditatorpattern.colleague.User;

import java.util.ArrayList;
import java.util.List;

public class ChatMediatorImpl implements ChatMediator{
    private List<User> userList;

    public ChatMediatorImpl() {
        this.userList = new ArrayList<>();
    }

    @Override
    public void sendMessage(String message, User user) {
        for (User u: userList){
            if(u != user){
                u.receive(message);
            }
        }
    }

    @Override
    public void addUser(User user) {
        this.userList.add(user);
    }
}
