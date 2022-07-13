package com.kamiljan.mobilecalculator.calculator.expressions;

import java.math.BigDecimal;

public abstract class Expression {
    public abstract BigDecimal evaluate();

    @Override
    public String toString() {
        return String.valueOf(evaluate());
    }
}
