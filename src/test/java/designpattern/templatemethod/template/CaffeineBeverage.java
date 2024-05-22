package designpattern.templatemethod.template;

public abstract class CaffeineBeverage {
    public final void prepareRecipe(){
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    protected abstract void brew();
    protected abstract void addCondiments();

    private void pourInCup() {
        System.out.println("Pouring into cup");
    }
    protected void boilWater(){
        System.out.println("Boiling water");

    }


}
