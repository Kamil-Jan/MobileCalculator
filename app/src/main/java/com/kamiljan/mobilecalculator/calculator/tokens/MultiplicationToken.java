package com.kamiljan.mobilecalculator.calculator.tokens;

import com.kamiljan.mobilecalculator.calculator.expressions.Expression;
import com.kamiljan.mobilecalculator.calculator.expressions.MultiplicationExpression;

public class MultiplicationToken extends OperationToken {
    public MultiplicationToken(String value) {
        super(value);
        precedence = 3;
        associativity = Associativity.LEFT;
    }


    @Override
    public Expression toExpression(Expression left, Expression right) {
        return new MultiplicationExpression(left, right);
    }
}
