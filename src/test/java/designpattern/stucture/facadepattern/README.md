# Facade Pattern
`Facade Pattern`은 복잡한 서브시스템의 인터페이스를 단순화하여 클라이언트에게 제공하는 역할을 합니다.
이 패턴을 통해 클라이언트는 서비스시템의 내부 세부 사항을 알 필요 없이 단순화된 인터페이스를 통해 서브시스템의 기능을 사용할 수 있스빈다.

## 구성 요소
1. Facade: 클라이언트가 사용할 수 있는 단순한 인터페이스를 제공합니다.
2. Subsystem Classes: 복잡한 서브시스템의 클래스들로, `Facade`를 통해 간접적으로 접근됩니다.

## 장점
* 복잡성 숨기기: 복잡한 서브시스템의 세부 사항을 숨기고, 단순한 인터페이스를 통해 서브시스템의 기능을 사용할 수 있게 합니다.
* 단일 인터페이스 제공: 여러 서브시스템 클래스의 기능을 하나의 인터페이스로 묶어 제공함으로써 클라잉너트가 서브시스템을 더 쉽게 사용할 수 있게 합니다.

## 사용 사례
* JDBCTemplate
* Hibernate
* Java AWT/Swing
* Apache Common

## 예시 코드
* HomeTheaterFacade: Facade
    ```java
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
    ```
* Amplifier, DVDPlayer, Projector, Tuner : Subsystem Classes
    ```java
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
    
    public class Projector {
        public void on() {
            System.out.println("Projector on");
        }
    
        public void off() {
            System.out.println("Projector off");
        }
    }
    
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
    ```
* Main
    ```java
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
    ```
* Output
    ```shell
    Get ready to watch a movie...
    Amplifier on
    Amplifier setting volume to 5
    DVDPlayer on
    DVDPlayer playing Parasite
    Projector on
    
    Shutting movie theater down...
    Amplifier off
    DVDPlayer off
    Projector off
    ```

