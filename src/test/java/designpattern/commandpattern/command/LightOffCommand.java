package designpattern.commandpattern.command;

import designpattern.commandpattern.receiver.SwitchLight;

public class LightOffCommand implements Command{
    private SwitchLight switchLight;
    public LightOffCommand(SwitchLight switchLight){
        this.switchLight = switchLight;
    }
    @Override
    public void execute() {
        switchLight.turnOff();
    }
}
