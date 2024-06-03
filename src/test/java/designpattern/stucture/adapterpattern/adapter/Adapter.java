package designpattern.stucture.adapterpattern.adapter;

import designpattern.stucture.adapterpattern.adaptee.Adaptee;
import designpattern.stucture.adapterpattern.target.Target;

public class Adapter implements Target {
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public String request() {
        return "Adapter: (TRANSLATED) " + new StringBuilder(adaptee.specificRequest()).reverse().toString();
    }
}
