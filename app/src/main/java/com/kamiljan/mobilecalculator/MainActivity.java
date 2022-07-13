package com.kamiljan.mobilecalculator;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.kamiljan.mobilecalculator.calculator.Calculator;
import com.kamiljan.mobilecalculator.calculator.exceptions.InvalidOperationException;
import com.kamiljan.mobilecalculator.calculator.exceptions.ParseException;
import com.kamiljan.mobilecalculator.calculator.parser.Parser;
import com.kamiljan.mobilecalculator.calculator.parser.RPNParser;
import com.kamiljan.mobilecalculator.calculator.tokenizer.RPNTokenizer;
import com.kamiljan.mobilecalculator.calculator.tokenizer.Tokenizer;
import com.kamiljan.mobilecalculator.calculator.util.CharUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private List<String> expressionList;
    Tokenizer tokenizer;
    Parser parser;
    Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // hide the title
        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the title bar
        setContentView(R.layout.activity_main);

        expressionList = new ArrayList<>();
        tokenizer = new RPNTokenizer();
        parser = new RPNParser();
        calculator = new Calculator(tokenizer, parser);
    }

    public String getExpression() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : expressionList) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    public void setExpression() {
        TextView expressionTextView = findViewById(R.id.expression_text_view);
        expressionTextView.setText(getExpression());
    }

    public String getNumberString(BigDecimal num) {
        double doubleValue = num.doubleValue();

        if ((int) doubleValue == doubleValue) {
            return String.valueOf((int) doubleValue);
        }
        return String.valueOf(doubleValue);
    }

    public void showError(Exception e) {
        clearExpression();
        addToExpression(e.getMessage());
    }

    public void clearError() {
        for (String str : expressionList) {
            if (str.endsWith("!")) {
                expressionList.remove(str);
            }
        }
    }

    public void evaluate() {
        String expression = getExpression();
        expression = expression.replaceAll("÷", "/");
        expression = expression.replaceAll("×", "*");
        BigDecimal result;
        try {
            result = calculator.evaluate(expression);
        } catch (ParseException e) {
            return;
        } catch (InvalidOperationException e) {
            showError(e);
            return;
        }

        String text = getNumberString(result);
        clearExpression();
        addByCharToExpression(text);
    }

    public void evaluate(View view) {
        clearError();
        evaluate();
    }

    public void addOperatorToExpression(View view) {
        clearError();
        char ch = view.getTag().toString().charAt(0);
        if (expressionList.size() > 0) {
            String prevElem = expressionList.get(expressionList.size() - 1);
            char prevChar = prevElem.charAt(0);

            if (prevChar == '(') return;

            if (CharUtil.isOperator(prevChar) &&
                    CharUtil.isOperator(ch) &&
                    !(ch == '-' && prevChar != '+' && prevChar != '-')) {
                eraseLeftExpression();
            }

            if (expressionList.size() > 1) {
                prevElem = expressionList.get(expressionList.size() - 1);
                prevChar = prevElem.charAt(0);
                if (CharUtil.isOperator(prevChar) && ch != '-') {
                    eraseLeftExpression();
                }
            }
            addToExpression(String.valueOf(ch));
        }
    }

    public void addToExpression(String text) {
        expressionList.add(text);
        setExpression();
    }

    public void addToExpression(View view) {
        clearError();
        String text = view.getTag().toString();
        if (text.equals(".")) {
            if (expressionList.size() > 0) {
                int dotCount = 0;
                for (char ch : getExpression().toCharArray()) {
                    if (ch == '.') dotCount++;
                    if (CharUtil.isOperator(ch) || ch == '(' || ch == ')') dotCount = 0;
                }

                if (dotCount > 0) return;

                String prevElem = expressionList.get(expressionList.size() - 1);
                char prevChar = prevElem.charAt(0);
                if (!CharUtil.isNumber(prevChar)) {
                    addToExpression("0");
                }
            } else {
                addToExpression("0");
            }
        } else if (text.contains("0")) {
            int dotCount = 0;
            int digitCount = 0;
            for (char ch : getExpression().toCharArray()) {
                if (ch == '.') {
                    dotCount++;
                    digitCount = 0;
                } else if (CharUtil.isOperator(ch) || ch == '(' || ch == ')') {
                    dotCount = 0;
                    digitCount = 0;
                } else if (CharUtil.isNumber(ch) && ch != '0') {
                    digitCount++;
                }
            }
            if (dotCount == 0 && digitCount == 0) {
                if (text.equals("00")) {
                    text = "0";
                }
                if (expressionList.size() > 0 && expressionList.get(expressionList.size() - 1).contains("0"))
                    return;
            }
        }
        addToExpression(text);
    }

    public void addByCharToExpression(String text) {
        for (char ch : text.toCharArray()) {
            expressionList.add(String.valueOf(ch));
        }
        setExpression();
    }

    public void eraseLeftExpression() {
        if (expressionList.size() > 0) {
            expressionList.remove(expressionList.size() - 1);
        }
        setExpression();
    }

    public void eraseLeftExpression(View view) {
        clearError();
        eraseLeftExpression();
    }

    public void clearExpression() {
        expressionList.clear();
        setExpression();
    }

    public void clearExpression(View view) {
        clearExpression();
    }
}