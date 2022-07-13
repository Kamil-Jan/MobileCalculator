package com.kamiljan.mobilecalculator.calculator.parser;

import com.kamiljan.mobilecalculator.calculator.exceptions.ParseException;
import com.kamiljan.mobilecalculator.calculator.expressions.Expression;
import com.kamiljan.mobilecalculator.calculator.expressions.ValueExpression;
import com.kamiljan.mobilecalculator.calculator.tokens.IntToken;
import com.kamiljan.mobilecalculator.calculator.tokens.OperationToken;
import com.kamiljan.mobilecalculator.calculator.tokens.Token;

import java.util.List;
import java.util.ListIterator;

public class BasicParser implements Parser {
    @Override
    public Expression parse(List<Token> tokens) {
        ListIterator<Token> tokenListIterator = tokens.listIterator();
        Expression expr = parseExpression(tokenListIterator);
        if (tokenListIterator.hasNext()) {
            throw new ParseException("Extra text after expression: " +
                    tokenListIterator.next().getValue() + "!");
        }
        return expr;
    }

    private Expression parseExpression(ListIterator<Token> tokenListIterator) {
        if (!tokenListIterator.hasNext()) {
            throw new ParseException("Premature end of expression!");
        }

        Expression expr = parseTerm(tokenListIterator);
        while (tokenListIterator.hasNext()) {
            Token operation = tokenListIterator.next();
            if (operation instanceof OperationToken) {
                expr = parseBinaryExpression(
                        expr,
                        (OperationToken) operation,
                        tokenListIterator
                );
            } else {
                tokenListIterator.previous();
                break;
            }
        }
        return expr;
    }

    private Expression parseBinaryExpression(Expression left,
                                             OperationToken operation,
                                             ListIterator<Token> tokenListIterator) {
        return operation.toExpression(left, parseTerm(tokenListIterator));
    }

    private Expression parseTerm(ListIterator<Token> tokenListIterator) {
        Token token = tokenListIterator.next();
        if (token.getValue().equals("("))
            return parseParenthesizedExpression(tokenListIterator);
        return parseValueExpression((IntToken) token);
    }

    private Expression parseValueExpression(IntToken token) {
        try {
            Number intVal = token.getNumberValue();
            return new ValueExpression(intVal);
        } catch (NumberFormatException numberFormatException) {
            throw new ParseException(
                    "Expected int but found: " + token.getValue() + "!"
            );
        }
    }

    private Expression parseParenthesizedExpression(ListIterator<Token> tokenListIterator) {
        Expression innerExpr = parseExpression(tokenListIterator);
        if (!tokenListIterator.hasNext() || !tokenListIterator.next().getValue().equals(")")) {
            throw new ParseException("Missing right parenthesis!");
        }
        return innerExpr;
    }
}
