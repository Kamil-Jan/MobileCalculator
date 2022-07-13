package com.kamiljan.mobilecalculator.calculator.tokens;

import com.kamiljan.mobilecalculator.calculator.expressions.Expression;
import com.kamiljan.mobilecalculator.calculator.expressions.SubtractionExpression;

public class MinusToken extends OperationToken {
    public MinusToken(String value) {
        super(value);
        this.precedence = 2;
        this.associativity = Associativity.LEFT;
    }

    @Override
    public Expression toExpression(Expression left, Expression right) {
        return new SubtractionExpression(left, right);
    }
}
