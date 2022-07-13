package com.kamiljan.mobilecalculator.calculator.expressions;

import com.kamiljan.mobilecalculator.calculator.exceptions.InvalidOperationException;

import java.math.BigDecimal;
import java.math.MathContext;

import ch.obermuhlner.math.big.BigDecimalMath;

public class PowExpression extends BinaryExpression {
    public PowExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public BigDecimal evaluate() {
        MathContext mc = new MathContext(10);
        try {
            return BigDecimalMath.pow(left.evaluate(), right.evaluate(), mc);
        } catch (ArithmeticException arithmeticException) {
            throw new InvalidOperationException("A negative number cannot be raised to a fractional power!");
        }
    }
}
