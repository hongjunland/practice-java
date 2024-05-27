package designpattern.behavior.mementopattern.originator;

import designpattern.behavior.mementopattern.memento.Memento;

public class Originator {
    private String state;

    public void setState(String state) {
        System.out.println("Setting state to " + state);
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Memento saveStateToMemento(){
        System.out.println("Saving state to Memento");
        return new Memento(state);
    }

    public void getStateFromMemento(Memento memento){
        state = memento.getState();
        System.out.println("Restoring state from Memento: " + state);
    }
}
