package designpattern.behavior.templatemethodpattern;

import designpattern.behavior.templatemethodpattern.templatemethod.CaffeineBeverage;
import designpattern.behavior.templatemethodpattern.templatemethod.Coffee;
import designpattern.behavior.templatemethodpattern.templatemethod.Tea;

public class TemplateMethodMain{

    public static void main(String[] args) {
        CaffeineBeverage tea = new Tea();
        CaffeineBeverage coffee = new Coffee();
        System.out.println("Making tea...");
        tea.prepareRecipe();
        System.out.println();
        System.out.println("Making coffee...");
        coffee.prepareRecipe();
    }
}
