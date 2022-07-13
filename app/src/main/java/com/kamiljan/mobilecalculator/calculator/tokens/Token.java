package com.kamiljan.mobilecalculator.calculator.tokens;

public class Token {
    protected final String value;

    public Token(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
