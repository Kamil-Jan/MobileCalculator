package com.kamiljan.mobilecalculator.calculator.tokens;

import com.kamiljan.mobilecalculator.calculator.expressions.Expression;
import com.kamiljan.mobilecalculator.calculator.expressions.MultiplicationExpression;
import com.kamiljan.mobilecalculator.calculator.expressions.ValueExpression;

public class UnaryMinusToken extends OperationToken implements UnaryOperationToken {
    public UnaryMinusToken(String value) {
        super(value);
        this.precedence = 4;
        this.associativity = Associativity.RIGHT;
    }

    @Override
    public Expression toExpression(Expression expression) {
        return toExpression(new ValueExpression(-1), expression);
    }

    @Override
    public Expression toExpression(Expression left, Expression right) {
        return new MultiplicationExpression(left, right);
    }
}
