package com.kamiljan.mobilecalculator.calculator.tokens;

import com.kamiljan.mobilecalculator.calculator.expressions.PowExpression;
import com.kamiljan.mobilecalculator.calculator.expressions.Expression;

public class PowToken extends OperationToken {
    public PowToken(String value) {
        super(value);
        precedence = 4;
        associativity = Associativity.RIGHT;
    }

    @Override
    public Expression toExpression(Expression left, Expression right) {
        return new PowExpression(left, right);
    }
}
