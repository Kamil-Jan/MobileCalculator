package com.kamiljan.mobilecalculator.calculator.tokenizer;

import com.kamiljan.mobilecalculator.calculator.tokens.DivisionToken;
import com.kamiljan.mobilecalculator.calculator.tokens.DoubleToken;
import com.kamiljan.mobilecalculator.calculator.tokens.IntToken;
import com.kamiljan.mobilecalculator.calculator.tokens.MinusToken;
import com.kamiljan.mobilecalculator.calculator.tokens.MultiplicationToken;
import com.kamiljan.mobilecalculator.calculator.tokens.PlusToken;
import com.kamiljan.mobilecalculator.calculator.tokens.PowToken;
import com.kamiljan.mobilecalculator.calculator.tokens.Token;
import com.kamiljan.mobilecalculator.calculator.tokens.UnaryMinusToken;
import com.kamiljan.mobilecalculator.calculator.util.CharUtil;

import java.util.ArrayList;

public class BasicTokenizer implements Tokenizer {

    @Override
    public ArrayList<Token> tokenize(char[] array) {
        ArrayList<Token> tokens = new ArrayList<>();
        StringBuilder numString = new StringBuilder();

        final char DECIMAL_POINT = '.';
        for (char ch : array) {
            if (CharUtil.isNumber(ch) || ch == DECIMAL_POINT) numString.append(ch);
            else {
                if (numString.length() > 0) {
                    if (numString.toString().contains(".")) {
                        tokens.add(new DoubleToken(numString.toString()));
                    } else {
                        tokens.add(new IntToken(numString.toString()));
                    }
                    numString = new StringBuilder();
                }

                if (CharUtil.isUnaryMinus(ch, tokens)) {
                    tokens.add(new UnaryMinusToken("~"));
                    continue;
                }
                if (ch == '(') tokens.add(new Token("("));
                if (ch == ')') tokens.add(new Token(")"));
                if (ch == '+') tokens.add(new PlusToken("+"));
                if (ch == '-') tokens.add(new MinusToken("-"));
                if (ch == '*') tokens.add(new MultiplicationToken("*"));
                if (ch == '/') tokens.add(new DivisionToken("/"));
                if (ch == '^') tokens.add(new PowToken("^"));
            }
        }

        if (numString.length() > 0) {
            if (numString.toString().contains(".")) {
                tokens.add(new DoubleToken(numString.toString()));
            } else {
                tokens.add(new IntToken(numString.toString()));
            }
        }

        return tokens;
    }
}
