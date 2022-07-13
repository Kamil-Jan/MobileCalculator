package com.kamiljan.mobilecalculator.calculator.tokens;

import com.kamiljan.mobilecalculator.calculator.expressions.Expression;
import com.kamiljan.mobilecalculator.calculator.expressions.ValueExpression;

public class IntToken extends NumberToken<Integer> {
    private final int intValue;

    public IntToken(String value) {
        super(value);
        try {
            intValue = Integer.parseInt(value);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException(
                    "Expected int but found: " + value
            );
        }
    }

    @Override
    public Integer getNumberValue() {
        return intValue;
    }

    @Override
    public Expression toExpression() {
        return new ValueExpression(intValue);
    }
}
