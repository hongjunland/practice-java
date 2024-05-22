package designpattern.templatemethodpattern;

import designpattern.templatemethodpattern.templatemethod.CaffeineBeverage;
import designpattern.templatemethodpattern.templatemethod.Coffee;
import designpattern.templatemethodpattern.templatemethod.Tea;

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
