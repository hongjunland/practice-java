package designpattern.commandpattern.command;

import designpattern.commandpattern.receiver.SwitchLight;

public class LightOnCommand implements Command{
    private SwitchLight switchLight;
    public LightOnCommand(SwitchLight switchLight){
        this.switchLight = switchLight;
    }
    @Override
    public void execute() {
        switchLight.turnOn();
    }
}
