package com.kamiljan.mobilecalculator.calculator.parser;

import com.kamiljan.mobilecalculator.calculator.exceptions.ParseException;
import com.kamiljan.mobilecalculator.calculator.expressions.Expression;
import com.kamiljan.mobilecalculator.calculator.expressions.ValueExpression;
import com.kamiljan.mobilecalculator.calculator.tokens.NumberToken;
import com.kamiljan.mobilecalculator.calculator.tokens.OperationToken;
import com.kamiljan.mobilecalculator.calculator.tokens.Token;
import com.kamiljan.mobilecalculator.calculator.tokens.UnaryOperationToken;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class RPNParser implements Parser {
    @Override
    public Expression parse(List<Token> tokens) {
        Stack<Expression> expressionStack = new Stack<>();
        for (Token token : tokens) {
            if (token instanceof NumberToken<?>) {
                NumberToken<?> numToken = (NumberToken<?>) token;
                expressionStack.push(new ValueExpression((Number) numToken.getNumberValue()));
            } else if (token instanceof UnaryOperationToken) {
                UnaryOperationToken unaryOperationToken = (UnaryOperationToken) token;
                Expression expression = expressionStack.pop();
                expressionStack.push(unaryOperationToken.toExpression(expression));
            } else if (token instanceof OperationToken) {
                OperationToken operationToken = (OperationToken) token;
                try {
                    Expression right = expressionStack.pop();
                    Expression left = expressionStack.pop();
                    expressionStack.push(operationToken.toExpression(left, right));
                } catch (EmptyStackException emptyStackException) {
                    throw new ParseException("Invalid expression!");
                }
            }
        }
        return expressionStack.peek();
    }
}
