package com.kamiljan.mobilecalculator.calculator;

import com.kamiljan.mobilecalculator.calculator.exceptions.InvalidOperationException;
import com.kamiljan.mobilecalculator.calculator.exceptions.ParseException;
import com.kamiljan.mobilecalculator.calculator.expressions.Expression;
import com.kamiljan.mobilecalculator.calculator.parser.Parser;
import com.kamiljan.mobilecalculator.calculator.tokenizer.Tokenizer;
import com.kamiljan.mobilecalculator.calculator.tokens.Token;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Calculator {
    private final Tokenizer tokenizer;
    private final Parser parser;

    public Calculator(Tokenizer tokenizer, Parser parser) {
        this.tokenizer = tokenizer;
        this.parser = parser;
    }

    public BigDecimal evaluate(String expression) throws InvalidOperationException {
        if (expression.length() == 0) {
            throw new ParseException("Expression was null!");
        }

        ArrayList<Token> tokens = tokenize(expression.toCharArray());
        Expression root = parse(tokens);
        return root.evaluate();
    }

    public Expression parse(ArrayList<Token> tokens) {
        return parser.parse(tokens);
    }

    public ArrayList<Token> tokenize(char[] array) {
        return tokenizer.tokenize(array);
    }
}
