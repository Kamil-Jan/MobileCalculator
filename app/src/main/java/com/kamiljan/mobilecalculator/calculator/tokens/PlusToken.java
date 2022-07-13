package com.kamiljan.mobilecalculator.calculator.tokens;

import com.kamiljan.mobilecalculator.calculator.expressions.AdditionExpression;
import com.kamiljan.mobilecalculator.calculator.expressions.Expression;

public class PlusToken extends OperationToken {
    public PlusToken(String value) {
        super(value);
        precedence = 2;
        associativity = Associativity.LEFT;
    }

    @Override
    public Expression toExpression(Expression left, Expression right) {
        return new AdditionExpression(left, right);
    }
}
