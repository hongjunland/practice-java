package designpattern.stucture.facadepattern.subsystemclasses;

public class Tuner {
    public void on() {
        System.out.println("Tuner on");
    }
    public void off() {
        System.out.println("Tuner off");
    }
    public void setFrequency(double frequency) {
        System.out.println("Tuner setting frequency to " + frequency);
    }
}
