package designpattern.stucture.facadepattern;

import designpattern.stucture.facadepattern.facade.HomeTheaterFacade;
import designpattern.stucture.facadepattern.subsystemclasses.Amplifier;
import designpattern.stucture.facadepattern.subsystemclasses.DVDPlayer;
import designpattern.stucture.facadepattern.subsystemclasses.Projector;
import designpattern.stucture.facadepattern.subsystemclasses.Tuner;

public class FacadePatternMain {
    public static void main(String[] args) {
        Amplifier amp = new Amplifier();
        Tuner tuner = new Tuner();
        DVDPlayer dvd = new DVDPlayer();
        Projector projector = new Projector();

        HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade(amp, tuner, dvd, projector);
        homeTheaterFacade.watchMovie("Parasite");
        System.out.println();
        homeTheaterFacade.endMovie();
    }
}
