package com.kamiljan.mobilecalculator.calculator.expressions;

import java.math.BigDecimal;

public class AdditionExpression extends BinaryExpression {
    public AdditionExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public BigDecimal evaluate() {
        return left.evaluate().add(right.evaluate());
    }
}
