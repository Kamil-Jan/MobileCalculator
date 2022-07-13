package com.kamiljan.mobilecalculator.calculator.expressions;

import java.math.BigDecimal;

public class ValueExpression extends Expression {
    private final BigDecimal value;

    public ValueExpression(Number value) {
        this.value = new BigDecimal(value.toString());
    }

    @Override
    public BigDecimal evaluate() {
        return value;
    }
}
