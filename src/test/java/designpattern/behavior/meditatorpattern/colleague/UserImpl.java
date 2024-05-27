package designpattern.behavior.meditatorpattern.colleague;

import designpattern.behavior.meditatorpattern.mediator.ChatMediator;

public class UserImpl extends User{

    public UserImpl(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message) {
        System.out.println(this.name + ": Sending Message:" + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receive(String message) {
        System.out.println(this.name + ": Receive Message:"+ message);
    }
}
