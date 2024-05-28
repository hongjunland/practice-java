package designpattern.creation.abstractfactorypattern.factory;

import designpattern.creation.abstractfactorypattern.product.Button;
import designpattern.creation.abstractfactorypattern.product.Checkbox;

public interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}
