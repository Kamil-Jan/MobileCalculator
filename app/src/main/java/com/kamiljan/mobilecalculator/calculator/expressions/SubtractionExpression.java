package com.kamiljan.mobilecalculator.calculator.expressions;

import java.math.BigDecimal;

public class SubtractionExpression extends BinaryExpression {
    public SubtractionExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public BigDecimal evaluate() {
        return left.evaluate().subtract(right.evaluate());
    }
}
