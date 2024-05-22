package designpattern.templatemethod;

import designpattern.templatemethod.template.CaffeineBeverage;
import designpattern.templatemethod.template.Coffee;
import designpattern.templatemethod.template.Tea;

public class TemplateMethodMain {
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
