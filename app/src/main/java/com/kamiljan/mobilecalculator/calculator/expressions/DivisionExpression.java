package com.kamiljan.mobilecalculator.calculator.expressions;

import com.kamiljan.mobilecalculator.calculator.exceptions.InvalidOperationException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DivisionExpression extends BinaryExpression {
    public DivisionExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public BigDecimal evaluate() {
        try {
            return left.evaluate().divide(right.evaluate(), 10, RoundingMode.HALF_UP);
        } catch (ArithmeticException arithmeticException) {
            throw new InvalidOperationException("Cannot divide by zero!");
        }
    }
}
