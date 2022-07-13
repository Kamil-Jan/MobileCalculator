package com.kamiljan.mobilecalculator.calculator.expressions;

import java.math.BigDecimal;

public class MultiplicationExpression extends BinaryExpression {
    public MultiplicationExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public BigDecimal evaluate() {
        return left.evaluate().multiply(right.evaluate());
    }
}
