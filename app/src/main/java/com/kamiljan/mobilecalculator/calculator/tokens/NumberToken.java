package com.kamiljan.mobilecalculator.calculator.tokens;

import com.kamiljan.mobilecalculator.calculator.expressions.Expression;

public abstract class NumberToken<T extends Number> extends Token {
    public NumberToken(String value) {
        super(value);
    }

    public abstract T getNumberValue();

    public abstract Expression toExpression();
}
