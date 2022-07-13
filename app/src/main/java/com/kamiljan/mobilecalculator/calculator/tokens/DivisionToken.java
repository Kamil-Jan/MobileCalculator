package com.kamiljan.mobilecalculator.calculator.tokens;

import com.kamiljan.mobilecalculator.calculator.expressions.DivisionExpression;
import com.kamiljan.mobilecalculator.calculator.expressions.Expression;

public class DivisionToken extends OperationToken {
    public DivisionToken(String value) {
        super(value);
        precedence = 3;
        associativity = Associativity.LEFT;
    }


    @Override
    public Expression toExpression(Expression left, Expression right) {
        return new DivisionExpression(left, right);
    }
}

