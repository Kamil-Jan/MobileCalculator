package com.kamiljan.mobilecalculator.calculator.tokens;

import com.kamiljan.mobilecalculator.calculator.expressions.Expression;

public interface UnaryOperationToken {
    Expression toExpression(Expression expression);
}
