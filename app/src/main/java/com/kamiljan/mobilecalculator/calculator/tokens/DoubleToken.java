package com.kamiljan.mobilecalculator.calculator.tokens;

import com.kamiljan.mobilecalculator.calculator.expressions.Expression;
import com.kamiljan.mobilecalculator.calculator.expressions.ValueExpression;

public class DoubleToken extends NumberToken<Double> {
    private final double doubleValue;

    public DoubleToken(String value) {
        super(value);
        try {
            doubleValue = Double.parseDouble(value);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException(
                    "Expected double but found: " + value
            );
        }
    }

    @Override
    public Double getNumberValue() {
        return doubleValue;
    }

    @Override
    public Expression toExpression() {
        return new ValueExpression(doubleValue);
    }
}
