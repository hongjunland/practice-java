package designpattern.stucture.facadepattern.subsystemclasses;

public class DVDPlayer {
    public void on() {
        System.out.println("DVDPlayer on");
    }
    public void off() {
        System.out.println("DVDPlayer off");
    }
    public void play(String movie) {
        System.out.println("DVDPlayer playing " + movie);
    }
}
