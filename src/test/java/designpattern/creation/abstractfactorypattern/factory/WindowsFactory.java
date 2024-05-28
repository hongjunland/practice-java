package designpattern.creation.abstractfactorypattern.factory;

import designpattern.creation.abstractfactorypattern.product.Button;
import designpattern.creation.abstractfactorypattern.product.Checkbox;
import designpattern.creation.abstractfactorypattern.product.WindowsButton;
import designpattern.creation.abstractfactorypattern.product.WindowsCheckbox;

public class WindowsFactory implements GUIFactory{
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}
