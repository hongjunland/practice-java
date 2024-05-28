package designpattern.creation.abstractfactorypattern.factory;

import designpattern.creation.abstractfactorypattern.product.Button;
import designpattern.creation.abstractfactorypattern.product.Checkbox;
import designpattern.creation.abstractfactorypattern.product.MacButton;
import designpattern.creation.abstractfactorypattern.product.MacCheckBox;

public class MacFactory implements GUIFactory{
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacCheckBox();
    }
}
