package designpattern.commandpattern.invoker;

import designpattern.commandpattern.command.Command;

public class RemoteControl {
    private Command command;
    public void setCommand(Command command){
        this.command = command;
    }
    public void pressButton(){
        command.execute();
    }
}
