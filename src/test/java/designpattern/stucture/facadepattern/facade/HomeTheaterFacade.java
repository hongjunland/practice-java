package designpattern.stucture.facadepattern.facade;

import designpattern.stucture.facadepattern.subsystemclasses.Amplifier;
import designpattern.stucture.facadepattern.subsystemclasses.DVDPlayer;
import designpattern.stucture.facadepattern.subsystemclasses.Projector;
import designpattern.stucture.facadepattern.subsystemclasses.Tuner;

public class HomeTheaterFacade {
    private Amplifier amp;
    private Tuner tuner;
    private DVDPlayer dvd;
    private Projector projector;

    public HomeTheaterFacade(Amplifier amp, Tuner tuner, DVDPlayer dvd, Projector projector) {
        this.amp = amp;
        this.tuner = tuner;
        this.dvd = dvd;
        this.projector = projector;
    }

    public void watchMovie(String movie){
        System.out.println("Get ready to watch a movie...");
        amp.on();
        amp.setVolume(5);
        dvd.on();
        dvd.play(movie);
        projector.on();
    }

    public void endMovie(){
        System.out.println("Shutting movie theater down...");
        amp.off();
        dvd.off();
        projector.off();
    }
}
