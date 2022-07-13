package com.kamiljan.mobilecalculator.calculator.parser;

import com.kamiljan.mobilecalculator.calculator.tokens.Token;
import com.kamiljan.mobilecalculator.calculator.expressions.Expression;

import java.util.List;

public interface Parser {
    Expression parse(List<Token> tokens);
}
