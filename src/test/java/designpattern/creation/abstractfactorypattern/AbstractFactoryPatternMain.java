package designpattern.creation.abstractfactorypattern;

import designpattern.creation.abstractfactorypattern.client.Application;
import designpattern.creation.abstractfactorypattern.factory.GUIFactory;
import designpattern.creation.abstractfactorypattern.factory.MacFactory;
import designpattern.creation.abstractfactorypattern.factory.WindowsFactory;

public class AbstractFactoryPatternMain {
    public static void main(String[] args) {
        Application application;
        GUIFactory factory;
        System.out.println(System.getProperty("os.name"));
        String osName = System.getProperty("os.name").toLowerCase();
        if(osName.contains("win")){
            factory = new WindowsFactory();
        }else{
            factory = new MacFactory();
        }

        application = new Application(factory);
        application.paint();
    }
}


