package designpattern.creation.abstractfactorypattern.product;

public class MacCheckBox implements Checkbox{
    @Override
    public void paint() {
        System.out.println("You have created MacCheckbox.");
    }
}
