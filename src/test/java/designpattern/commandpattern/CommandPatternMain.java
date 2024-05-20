package designpattern.commandpattern;

import designpattern.commandpattern.command.Command;
import designpattern.commandpattern.command.LightOffCommand;
import designpattern.commandpattern.command.LightOnCommand;
import designpattern.commandpattern.invoker.RemoteControl;
import designpattern.commandpattern.receiver.SwitchLight;

public class CommandPatternMain {
    public static void main(String[] args) {
        SwitchLight switchLight = new SwitchLight();
        Command lightOn = new LightOnCommand(switchLight);
        Command lightOff = new LightOffCommand(switchLight);

        RemoteControl remoteControl = new RemoteControl();

        // Turn on the light
        remoteControl.setCommand(lightOn);
        remoteControl.pressButton();

        // Turn off the light
        remoteControl.setCommand(lightOff);
        remoteControl.pressButton();
    }
}
