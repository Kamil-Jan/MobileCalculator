package com.kamiljan.mobilecalculator.calculator.tokenizer;

import com.kamiljan.mobilecalculator.calculator.tokens.Token;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

public class BasicTokenizerTest {
    Tokenizer tokenizer;

    @Before
    public void setup() {
        tokenizer = new BasicTokenizer();
    }

    @Test
    public void tokenizeWithOneOperator() {
        String expression = "1+2";
        List<Token> tokens = tokenizer.tokenize(expression.toCharArray());
        Assert.assertEquals(tokens.size(), expression.length());
        for (int i = 0; i < expression.length(); i++) {
            Assert.assertEquals(tokens.get(i).getValue(),
                    String.valueOf(expression.charAt(i)));
        }
    }

    @Test
    public void tokenizeWithParenthesis() {
        String expression = "(1+2)-3";
        List<Token> tokens = tokenizer.tokenize(expression.toCharArray());
        Assert.assertEquals(tokens.size(), expression.length());
        for (int i = 0; i < expression.length(); i++) {
            Assert.assertEquals(tokens.get(i).getValue(),
                    String.valueOf(expression.charAt(i)));
        }
    }

    @Test
    public void tokenizeWithMultipleDigitNumbers() {
        String expression = "(12+332)-3";
        List<String> expectedResult = Arrays.asList(
                "(", "12", "+", "332", ")", "-", "3"
        );
        List<Token> tokens = tokenizer.tokenize(expression.toCharArray());
        Assert.assertEquals(tokens.size(), expectedResult.size());
        for (int i = 0; i < tokens.size(); i++) {
            Assert.assertEquals(tokens.get(i).getValue(),
                    expectedResult.get(i)
            );
        }
    }

    @Test
    public void tokenizeWithDoubleValues() {
        String expression = "0.5+3.7899+212";
        List<String> expectedResult = Arrays.asList(
                "0.5", "+", "3.7899", "+", "212"
        );
        List<Token> tokens = tokenizer.tokenize(expression.toCharArray());
        Assert.assertEquals(tokens.size(), expectedResult.size());
        for (int i = 0; i < tokens.size(); i++) {
            Assert.assertEquals(tokens.get(i).getValue(),
                    expectedResult.get(i)
            );
        }
    }
}
