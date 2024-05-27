package designpattern.behavior.interpreterpattern;

import designpattern.behavior.interpreterpattern.expression.AddExpression;
import designpattern.behavior.interpreterpattern.expression.Expression;
import designpattern.behavior.interpreterpattern.expression.NumberExpression;
import designpattern.behavior.interpreterpattern.expression.SubstractExpression;

public class InterpreterPatternMain {
    public static void main(String[] args) {
        Expression number1 = new NumberExpression(1);
        Expression number2 = new NumberExpression(3);
        Expression addExpression = new AddExpression(number1, number2);

        System.out.println("1+ 2 = " + addExpression.interpret());

        Expression number3 = new NumberExpression(3);
        Expression substractExpression = new SubstractExpression(addExpression, number3);

        System.out.println("(1+2) - 3 = " + substractExpression.interpret());
    }
}
