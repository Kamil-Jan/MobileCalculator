package com.kamiljan.mobilecalculator.calculator.tokenizer;

import com.kamiljan.mobilecalculator.calculator.exceptions.ParseException;
import com.kamiljan.mobilecalculator.calculator.tokens.NumberToken;
import com.kamiljan.mobilecalculator.calculator.tokens.OperationToken;
import com.kamiljan.mobilecalculator.calculator.tokens.Token;

import java.util.ArrayList;
import java.util.Stack;

public class RPNTokenizer implements Tokenizer {
    public ArrayList<Token> tokenize(char[] array) {
        ArrayList<Token> tokens = new BasicTokenizer().tokenize(array);

        Stack<Token> operationTokenStack = new Stack<>();
        ArrayList<Token> outputQueue = new ArrayList<>();

        for (Token curToken : tokens) {
            // The token is a number
            if (curToken instanceof NumberToken) {
                outputQueue.add(curToken);
            }
            // The token is an operator
            else if (curToken instanceof OperationToken) {
                // Pop previous operators onto the output queue
                while (canPushOperators(operationTokenStack, (OperationToken) curToken)) {
                    outputQueue.add(operationTokenStack.pop());
                }
                operationTokenStack.push(curToken);
            }
            // The token is a left parenthesis
            else if (curToken.getValue().equals("(")) {
                operationTokenStack.push(curToken);
            }
            // The token is a right parenthesis
            else if (curToken.getValue().equals(")")) {
                boolean leftParenthesisIsFound = false;
                while (!operationTokenStack.isEmpty()) {
                    if (operationTokenStack.peek().getValue().equals("(")) {
                        leftParenthesisIsFound = true;
                        operationTokenStack.pop();
                        break;
                    }
                    outputQueue.add(operationTokenStack.pop());
                }

                if (!leftParenthesisIsFound) {
                    throw new ParseException("Mismatched parentheses!");
                }
            }
        }
        while (operationTokenStack.size() > 0) {
            if (operationTokenStack.peek().getValue().equals("(") ||
                    operationTokenStack.peek().getValue().equals(")")) {
                throw new ParseException("Mismatched parentheses!");
            }
            outputQueue.add(operationTokenStack.pop());
        }

        return outputQueue;
    }

    public boolean canPushOperators(Stack<Token> operationTokenStack,
                                    OperationToken curToken) {
        if (operationTokenStack.isEmpty()) {
            return false;
        }

        if (operationTokenStack.peek().getValue().equals("(")) {
            return false;
        }

        OperationToken topToken = (OperationToken) operationTokenStack.peek();
        if (topToken.compareTo(curToken) > 0) return true;
        return topToken.compareTo(curToken) == 0 && curToken.isLeftAssociative();
    }
}
