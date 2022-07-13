package com.kamiljan.mobilecalculator.calculator;

import com.kamiljan.mobilecalculator.calculator.exceptions.InvalidOperationException;
import com.kamiljan.mobilecalculator.calculator.exceptions.ParseException;
import com.kamiljan.mobilecalculator.calculator.parser.Parser;
import com.kamiljan.mobilecalculator.calculator.parser.RPNParser;
import com.kamiljan.mobilecalculator.calculator.tokenizer.RPNTokenizer;
import com.kamiljan.mobilecalculator.calculator.tokenizer.Tokenizer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class CalculatorTest {

    Tokenizer tokenizer;
    Parser parser;
    Calculator calculator;

    @Before
    public void setup() {
        tokenizer = new RPNTokenizer();
        parser = new RPNParser();
        calculator = new Calculator(tokenizer, parser);
    }

    @Test
    public void evaluateZeroOperators() {
        BigDecimal result = calculator.evaluate("1");
        Assert.assertEquals(1, result.intValue());
    }

    @Test
    public void evaluateOneOperator() {
        BigDecimal result = calculator.evaluate("1+1");
        Assert.assertEquals(2, result.intValue());
    }

    @Test
    public void evaluateThreeOperatorWithNoParenthesis() {
        BigDecimal result = calculator.evaluate("5-2+1");
        Assert.assertEquals(4, result.intValue());
    }

    @Test
    public void evaluateOneOperatorWithParenthesis() {
        BigDecimal result = calculator.evaluate("(1+1)");
        Assert.assertEquals(2, result.intValue());
    }

    @Test
    public void evaluateTwoOperatorWithParenthesis() {
        BigDecimal result = calculator.evaluate("((1-0)+(1-1))");
        Assert.assertEquals(1, result.intValue());
    }

    @Test
    public void evaluateThreeOperatorWithInnerParenthesis() {
        BigDecimal result = calculator.evaluate("(1-1)+(1+1)");
        Assert.assertEquals(2, result.intValue());
    }

    @Test
    public void evaluateMultiplicationAndDivision() {
        BigDecimal result = calculator.evaluate("3+4*2/(1-5)");
        Assert.assertEquals(1, result.intValue());
    }

    @Test
    public void evaluatePowOperation() {
        BigDecimal result = calculator.evaluate("8/(0-4)^2");
        Assert.assertEquals(0.5, result.doubleValue(), 0);
    }

    @Test
    public void evaluateComplexOperation() {
        BigDecimal result = calculator.evaluate("3+4*2/(1-5)^2^3");
        Assert.assertEquals(3.00012, result.doubleValue(), 5);
    }

    @Test
    public void evaluateUnaryMinusAtTheBeginning() {
        BigDecimal result = calculator.evaluate("-1*2");
        Assert.assertEquals(-2, result.intValue());
    }

    @Test
    public void evaluateUnaryMinusAfterOperator() {
        BigDecimal result = calculator.evaluate("1*-2");
        Assert.assertEquals(-2, result.intValue());
    }

    @Test
    public void evaluateUnaryMinusAfterLeftParenthesis() {
        BigDecimal result = calculator.evaluate("1*(-2/4)");
        Assert.assertEquals(-0.5, result.doubleValue(), 1);
    }

    @Test
    public void evaluateUnaryMinusBeforePow() {
        BigDecimal result = calculator.evaluate("-2^2");
        Assert.assertEquals(-4, result.intValue());
    }

    @Test
    public void evaluateUnaryMinusWithPow() {
        BigDecimal result = calculator.evaluate("2^-2");
        Assert.assertEquals(0.25, result.doubleValue(), 2);
    }

    @Test
    public void evaluateDoubleUnaryMinus() {
        BigDecimal result = calculator.evaluate("5--2");
        Assert.assertEquals(7, result.intValue());
    }

    @Test
    public void evaluateTripleUnaryMinus() {
        BigDecimal result = calculator.evaluate("5---2");
        Assert.assertEquals(3, result.intValue());
    }

    @Test
    public void evaluateDoubleAddition() {
        BigDecimal result = calculator.evaluate("0.5+1.5");
        Assert.assertEquals(2, result.intValue());
    }

    @Test
    public void evaluateWithDoubleSubtraction() {
        BigDecimal result = calculator.evaluate("0.5-2.5");
        Assert.assertEquals(-2, result.intValue());
    }

    @Test
    public void evaluateWithDoubleMultiplication() {
        BigDecimal result = calculator.evaluate("97.54*334.5");
        Assert.assertEquals(32627.13, result.doubleValue(), 2);
    }

    @Test
    public void evaluateWithDoubleDivision() {
        BigDecimal result = calculator.evaluate("97.54/334.5");
        Assert.assertEquals(0.29160, result.doubleValue(), 5);
    }

    @Test
    public void evaluateWithDoublePow() {
        BigDecimal result = calculator.evaluate("48^2.5");
        Assert.assertEquals(15962.58024, result.doubleValue(), 5);
    }

    @Test
    public void evaluateWithMinusDoublePow() {
        BigDecimal result = calculator.evaluate("10^-1.5");
        Assert.assertEquals(0.03162, result.doubleValue(), 5);
    }

    @Test
    public void evaluatePowPrecision() {
        BigDecimal result = calculator.evaluate("1.2^3");
        Assert.assertEquals(1.728, result.doubleValue(), 3);
    }

    @Test(expected = InvalidOperationException.class)
    public void evaluateZeroDivision() {
        calculator.evaluate("1+2/0");
    }

    @Test(expected = InvalidOperationException.class)
    public void evaluateNegativeNumberToDoublePower() {
        calculator.evaluate("(-2)^0.5");
    }

    @Test(expected = ParseException.class)
    public void evaluateEmptyString() {
        calculator.evaluate("");
    }

    @Test(expected = ParseException.class)
    public void evaluateWrongOperators() {
        calculator.evaluate("1+*2");
    }

    @Test(expected = ParseException.class)
    public void evaluateWrongOperators2() {
        calculator.evaluate("1+2*");
    }

    @Test(expected = ParseException.class)
    public void evaluateWrongOperators3() {
        calculator.evaluate("+12*");
    }

    @Test(expected = ParseException.class)
    public void evaluateMissingParenthesis() {
        calculator.evaluate("((1+1)");
    }

    @Test(expected = ParseException.class)
    public void evaluateMissingParenthesis2() {
        calculator.evaluate("(1+1))");
    }
}
