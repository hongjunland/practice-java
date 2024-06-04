package designpattern.stucture.flyweightpattern.flyweightfactory;

import designpattern.stucture.flyweightpattern.flyweight.ConcreteFlyweight;
import designpattern.stucture.flyweightpattern.flyweight.Flyweight;

import java.util.HashMap;
import java.util.Map;

public class FlyweightFactory {
    private final Map<String, Flyweight> flyweights = new HashMap<>();

    public Flyweight getFlyweight(String key){
        if(!flyweights.containsKey(key)){
            flyweights.put(key, new ConcreteFlyweight(key));
        }
        return flyweights.get(key);
    }
}
