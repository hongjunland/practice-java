package designpattern.stucture.adapterpattern;

import designpattern.stucture.adapterpattern.adaptee.Adaptee;
import designpattern.stucture.adapterpattern.adapter.Adapter;
import designpattern.stucture.adapterpattern.target.Target;

public class AdapterPatternMain {
    public static void main(String[] args) {
        System.out.println("Client: I can work just fine with the Target objects:");
        Target target = new Target() {
            @Override
            public String request() {
                return "Target: The default target's behavior.";
            }
        };
        System.out.println(target.request());

        System.out.println("\nClient: The Adaptee class has a weird interface. See, I don't understand it:");
        Adaptee adaptee = new Adaptee();
        System.out.println("Adaptee: " + adaptee.specificRequest());

        System.out.println("\nClient: But I can work with it via the Adapter:");
        Target adapter = new Adapter(adaptee);
        System.out.println(adapter.request());
    }
}
