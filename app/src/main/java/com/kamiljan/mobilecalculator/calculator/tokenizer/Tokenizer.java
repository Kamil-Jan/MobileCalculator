package com.kamiljan.mobilecalculator.calculator.tokenizer;

import com.kamiljan.mobilecalculator.calculator.tokens.Token;

import java.util.ArrayList;

public interface Tokenizer {
    ArrayList<Token> tokenize(char[] array);
}
