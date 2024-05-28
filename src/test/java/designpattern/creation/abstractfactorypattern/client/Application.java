package designpattern.creation.abstractfactorypattern.client;

import designpattern.creation.abstractfactorypattern.factory.GUIFactory;
import designpattern.creation.abstractfactorypattern.product.Button;
import designpattern.creation.abstractfactorypattern.product.Checkbox;

public class Application{
    private Button button;
    private Checkbox checkbox;

    public Application(GUIFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
    }
    public void paint(){
        button.paint();
        checkbox.paint();
    }
}
