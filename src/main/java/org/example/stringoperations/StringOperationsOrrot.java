package org.example.stringoperations;

import common.util.ComputingException;
import common.util.SyntaxException;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringOperationsOrrot {

    private static final Pattern NUMERIC_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static final String OPERATOR_ADD = "+";
    public static final String OPERATOR_MINUS = "-";
    public static final String OPERATOR_MULTIPLY = "*";
    public static final String OPERATOR_DIVIDE = "/";

    public static Integer computeExpression(String expression) {

        String[] characters = expression.split(" ");
        Stack<String> charsPile = IntStream.range(0, characters.length)
                .mapToObj(i -> characters[(characters.length - 1) - i])
                .collect(Collectors.toCollection(Stack::new));
        return computeStack(charsPile);
    }

    private static Integer computeStack(Stack<String> charsPile) {

        Stack<String> numbersAndParenthesis = new Stack<>();
        Integer result = null;
        String operator = null;

        // if first character is not parenthesis, returns an error
        if (!isStartParenthesis(charsPile.peek())) {
            throw new SyntaxException("The syntax is not correct");
        }
        numbersAndParenthesis.push(charsPile.pop());

        while (!charsPile.empty()) {
            if (isStartParenthesis(numbersAndParenthesis.peek()) && isOperator(charsPile.peek())) {
                operator = charsPile.pop();
            } else if (isNumber(charsPile.peek()) && Objects.nonNull(operator) ) {
                numbersAndParenthesis.push(charsPile.pop());
            } else if (isNumber(numbersAndParenthesis.peek()) && isEndParenthesis(charsPile.peek())) {
                numbersAndParenthesis.push(charsPile.pop());
                return computeSingleExpression(operator, numbersAndParenthesis);
            } else if (isStartParenthesis(charsPile.peek())) {
                Integer parenthesisResult = computeStack(charsPile);
                numbersAndParenthesis.add(parenthesisResult.toString());
            } else {
                throw new SyntaxException("The syntax is not correct");
            }
        }

        return result;
    }

    private static boolean isOperator(String symbol) {
        return OPERATOR_ADD.equals(symbol) || OPERATOR_MINUS.equals(symbol) || OPERATOR_MULTIPLY.equals(symbol) || OPERATOR_DIVIDE.equals(symbol);
    }

    private static boolean isNumber(String symbol) {
        return NUMERIC_PATTERN.matcher(symbol).matches();
    }

    private static boolean isStartParenthesis(String character) {
        return character.equals("(");
    }

    private static boolean isEndParenthesis(String character) {
        return character.equals(")");
    }

    private static Integer computeSingleExpression(String operator, Stack<String> numbersAndParenthesis) {

        // This violates the Open Close principle. This should be separated to classes to rid the switch off.
        List<Integer> integers = extractIntegers(numbersAndParenthesis);
        IntStream intStream = integers.stream().mapToInt(Integer::intValue);

        switch (operator) {
            case OPERATOR_ADD:
                return intStream.sum();
            case OPERATOR_MINUS:
                return intStream.reduce((a, b) -> a - b).getAsInt();
            case OPERATOR_MULTIPLY:
                return intStream.reduce((a, b) -> a * b).getAsInt();
            case OPERATOR_DIVIDE:
                // This operation can cause an exception!!
                return intStream.reduce((a, b) -> a / b).getAsInt();
        }
        throw new ComputingException(String.format("Not valid operator %s was identified", operator.toString()));
    }

    private static List<Integer> extractIntegers(Stack<String> numbersAndParenthesis) {

        List<Integer> integers = new LinkedList<>();
        boolean containsEndParenthesis = false;

        while (!numbersAndParenthesis.empty()) {

            if (isEndParenthesis(numbersAndParenthesis.peek()) && !containsEndParenthesis) {
                containsEndParenthesis = true;
                numbersAndParenthesis.pop();
            } else if (containsEndParenthesis && isNumber(numbersAndParenthesis.peek())) {
                //integers.add(Integer.parseInt(numbersAndParenthesis.pop()));
                integers.add(0, Integer.parseInt(numbersAndParenthesis.pop()));
            } else if (containsEndParenthesis && isStartParenthesis(numbersAndParenthesis.peek())) {
                numbersAndParenthesis.pop();
                if (!numbersAndParenthesis.empty()) {
                    throw new ComputingException("The expression was not processed well. StartParenthesis in wrong position. Please contact Orrot.");
                }
            } else {
                throw new ComputingException("The expression was not processed well. EndParenthesis in wrong position. Please contact Orrot.");
            }
        }

        if (integers.isEmpty()) {
            throw new ComputingException("The expression was not processed well. No numbers. Please contact Orrot.");
        }
        return integers;
    }
}
