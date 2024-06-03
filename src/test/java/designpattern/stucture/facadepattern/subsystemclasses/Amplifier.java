package designpattern.stucture.facadepattern.subsystemclasses;

public class Amplifier {
    public void on() {
        System.out.println("Amplifier on");
    }
    public void off() {
        System.out.println("Amplifier off");
    }
    public void setVolume(int volume) {
        System.out.println("Amplifier setting volume to " + volume);
    }
}
