package com.kamiljan.mobilecalculator.calculator.tokenizer;

import com.kamiljan.mobilecalculator.calculator.exceptions.ParseException;
import com.kamiljan.mobilecalculator.calculator.tokens.Token;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class RPNTokenizerTest {
    RPNTokenizer tokenizer;

    @Before
    public void setup() {
        tokenizer = new RPNTokenizer();
    }

    @Test
    public void testRPNProduction() {
        String expression = "3+4*2";
        List<Token> rpnList = tokenizer.tokenize(expression.toCharArray());
        Assert.assertEquals(rpnList.get(0).getValue(), "3");
        Assert.assertEquals(rpnList.get(1).getValue(), "4");
        Assert.assertEquals(rpnList.get(2).getValue(), "2");
        Assert.assertEquals(rpnList.get(3).getValue(), "*");
        Assert.assertEquals(rpnList.get(4).getValue(), "+");
    }

    @Test
    public void testRPNProductionWithParenthesis() {
        String expression = "3+4*2/(1-5)^2^3";
        List<Token> rpnList = tokenizer.tokenize(expression.toCharArray());
        Assert.assertEquals(rpnList.get(0).getValue(), "3");
        Assert.assertEquals(rpnList.get(1).getValue(), "4");
        Assert.assertEquals(rpnList.get(2).getValue(), "2");
        Assert.assertEquals(rpnList.get(3).getValue(), "*");
        Assert.assertEquals(rpnList.get(4).getValue(), "1");
        Assert.assertEquals(rpnList.get(5).getValue(), "5");
        Assert.assertEquals(rpnList.get(6).getValue(), "-");
        Assert.assertEquals(rpnList.get(7).getValue(), "2");
        Assert.assertEquals(rpnList.get(8).getValue(), "3");
        Assert.assertEquals(rpnList.get(9).getValue(), "^");
        Assert.assertEquals(rpnList.get(10).getValue(), "^");
        Assert.assertEquals(rpnList.get(11).getValue(), "/");
        Assert.assertEquals(rpnList.get(12).getValue(), "+");
    }

    @Test
    public void testRPNProductionThreeOperatorWithNoParenthesis() {
        String expression = "5-2+1";
        List<Token> rpnList = tokenizer.tokenize(expression.toCharArray());
        Assert.assertEquals(rpnList.get(0).getValue(), "5");
        Assert.assertEquals(rpnList.get(1).getValue(), "2");
        Assert.assertEquals(rpnList.get(2).getValue(), "-");
        Assert.assertEquals(rpnList.get(3).getValue(), "1");
        Assert.assertEquals(rpnList.get(4).getValue(), "+");
    }

    @Test(expected = ParseException.class)
    public void testRPNMissingParenthesis() {
        String expression = "4+((3+4)-5";
        tokenizer.tokenize(expression.toCharArray());
    }

    @Test(expected = ParseException.class)
    public void testRPNMissingParenthesis2() {
        String expression = "4+(3+4)-5)";
        tokenizer.tokenize(expression.toCharArray());
    }

    @Test(expected = ParseException.class)
    public void testRPNMissingParenthesis3() {
        String expression = "4+(((3+4)-5)";
        tokenizer.tokenize(expression.toCharArray());
    }
}
