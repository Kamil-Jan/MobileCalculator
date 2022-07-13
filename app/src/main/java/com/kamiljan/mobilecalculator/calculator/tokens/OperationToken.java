package com.kamiljan.mobilecalculator.calculator.tokens;

import com.kamiljan.mobilecalculator.calculator.expressions.Expression;

public abstract class OperationToken extends Token implements Comparable<OperationToken> {
    public enum Associativity {
        LEFT, RIGHT
    }

    protected int precedence;
    protected Associativity associativity;

    public OperationToken(String value) {
        super(value);
    }

    public int getPrecedence() {
        return precedence;
    }

    public Associativity getAssociativity() {
        return associativity;
    }

    public boolean isLeftAssociative() {
        return associativity.equals(Associativity.LEFT);
    }

    public boolean isRightAssociative() {
        return associativity.equals(Associativity.RIGHT);
    }

    public abstract Expression toExpression(Expression left, Expression right);

    @Override
    public int compareTo(OperationToken o) {
        return precedence - o.getPrecedence();
    }
}
