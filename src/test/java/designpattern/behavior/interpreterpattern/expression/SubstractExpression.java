package designpattern.behavior.interpreterpattern.expression;

public class SubstractExpression implements Expression{
    private Expression leftExpression;
    private Expression rightExpression;

    public SubstractExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }
    @Override
    public int interpret() {
        return leftExpression.interpret()- rightExpression.interpret();
    }
}
