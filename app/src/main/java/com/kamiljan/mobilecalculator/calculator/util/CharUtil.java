package com.kamiljan.mobilecalculator.calculator.util;

import com.kamiljan.mobilecalculator.calculator.tokens.OperationToken;
import com.kamiljan.mobilecalculator.calculator.tokens.Token;

import java.util.ArrayList;

public class CharUtil {
    public static boolean isNumber(char c) {
        return '0' <= c && c <= '9';
    }

    public static boolean isUnaryMinus(char c, ArrayList<Token> prevTokens) {
        if (c != '-') return false;

        // The first character of an input
        if (prevTokens.size() == 0) return true;

        // Preceded by a left parenthesis or by another operator
        Token prevToken = prevTokens.get(prevTokens.size() - 1);
        return (prevToken.getValue().equals("(")) || (prevToken instanceof OperationToken);
    }

    public static boolean isOperator(char c) {
        if (c == '+') return true;
        if (c == '-') return true;
        if (c == '×' || c == '*') return true;
        if (c == '÷' || c == '/') return true;
        return (c == '^');
    }
}
