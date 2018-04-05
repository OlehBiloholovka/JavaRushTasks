package com.javarush.task.task34.task3404;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Рекурсия для мат. выражения
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recursion("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6
        String s;
        s = "cos(2*(-5+1.5*4)+28)-sin(2*(-5+1.5*4)+28)";
        System.out.print(s + " expected output 0.37 13  actually ");
        solution.recursion(s, 0);
        s = "1^cos(0)";// вместо ^ может быть любой другой оператор, учитывайте
        System.out.print(s + " expected output 1 2  actually ");
        solution.recursion(s, 0);
        s = "sin(2*(-5+1.5*4)+28)";
        System.out.print(s + " expected output 0.5 6 actually ");
        solution.recursion(s, 0);
        s = "tan(45)";
        System.out.print(s + " expected output 1 1 actually ");
        solution.recursion(s, 0);
        s = "tan(-45)";
        System.out.print(s + " expected output -1 2 actually ");
        solution.recursion(s, 0);
        s = "0.305";
        System.out.print(s + " expected output 0.3 0 actually ");
        solution.recursion(s, 0);
        s = "0.3051";
        System.out.print(s + " expected output 0.31 0 actually ");
        solution.recursion(s, 0);
        s = "(0.3051)";
        System.out.print(s + " expected output 0.31 0 actually ");
        solution.recursion(s, 0);
        s = "1+(1+(1+1)*(1+1))*(1+1)+1";
        System.out.print(s + " expected output 12 8 actually ");
        solution.recursion(s, 0);
        s = "tan(44+sin(89-cos(180)^2))";
        System.out.print(s + " expected output 1 6 actually ");
        solution.recursion(s, 0);
        s = "-2+(-2+(-2)-2*(2+2))";
        System.out.print(s + " expected output -14 8 actually ");
        solution.recursion(s, 0);
        s = "sin(80+(2+(1+1))*(1+1)+2)";
        System.out.print(s + " expected output 1 7 actually ");
        solution.recursion(s, 0);
        s = "1+4/2/2+2^2+2*2-2^(2-1+1)";
        System.out.print(s + " expected output 6 11 actually ");
        solution.recursion(s, 0);
    }

    public void recursion(final String expression, int countOperation) {

        String expressionWithoutSpaces = expression;
        if (expression.contains(" ")) expressionWithoutSpaces = expression.replace(" ", "");

        int indexOfOpenParenthesis = expressionWithoutSpaces.indexOf("(");
        String subExpressionStart = "";
        String subExpressionEnd = "";
        String substring = expressionWithoutSpaces;

        if (indexOfOpenParenthesis != -1){
            int indexOfCloseParenthesis = expressionWithoutSpaces.indexOf(")");

            while (true) {
                int indexOfNextOpenParenthesis = expressionWithoutSpaces
                        .indexOf("(", indexOfOpenParenthesis + 1);
                if (indexOfNextOpenParenthesis != -1
                        && indexOfNextOpenParenthesis < indexOfCloseParenthesis){
                    indexOfOpenParenthesis = indexOfNextOpenParenthesis;
                } else {
                    break;
                }
            }
            int indexOfSubstringStart = indexOfOpenParenthesis + 1;

            substring = expressionWithoutSpaces.substring(indexOfSubstringStart, indexOfCloseParenthesis).trim();

            if (indexOfSubstringStart != 0) {
                subExpressionStart = expressionWithoutSpaces.substring(0, indexOfSubstringStart).trim();
            }
            subExpressionEnd = expressionWithoutSpaces.substring(indexOfCloseParenthesis);

        }

        for (Operation operation : Operation.values()) {
            if (checkOperation(operation, substring)){
                int indexOfOperation = indexOfOperation(operation, substring);

                String beforeOperationString = substring.substring(0, indexOfOperation).trim();
                String afterOperationString = substring.substring(indexOfOperation + 1).trim();

                String stringA = "";
                String stringB = "";
                Pattern p = Pattern.compile("-?\\d+(\\.\\d+)?$");
                Matcher m = p.matcher(beforeOperationString);
                if(m.find()) {
                    stringA = m.group(0);
                    beforeOperationString = beforeOperationString
                            .substring(0, beforeOperationString.lastIndexOf(stringA));

                }

                p = Pattern.compile("^-?\\d+(\\.\\d+)?");
                m = p.matcher(afterOperationString);
                if(m.find()) {
                    stringB = m.group(0);
                    afterOperationString = afterOperationString.substring(stringB.length());
                }

                double a = Double.parseDouble(stringA);
                if (a < 0) countOperation++;
                double b = Double.parseDouble(stringB);

                double result = calculateOperation(operation, a, b);

                substring = subExpressionStart + beforeOperationString + result + afterOperationString + subExpressionEnd;

                recursion(substring , ++countOperation);
                return;
            }
        }

        for (TrigonometricFunction function : TrigonometricFunction.values()) {
            if (checkTrigonometricFunction(function, subExpressionStart)){
                double radians = Math.toRadians(Double.parseDouble(substring));

                double result = 0;
                String trigonometricFunctionString = "";

                switch (function){
                    case COS:
                        result = Math.cos(radians);
                        trigonometricFunctionString = "cos(";
                        break;
                    case SIN:
                        result = Math.sin(radians);
                        trigonometricFunctionString = "sin(";
                        break;
                    case TAN:
                        result = Math.tan(radians);
                        trigonometricFunctionString = "tan(";
                        break;
                }

                double roundedResult = Math.round(result * 100.0) / 100.0;

                int indexOfTrigonometricFunction = subExpressionStart.lastIndexOf(trigonometricFunctionString);
                subExpressionStart = subExpressionStart.substring(0, indexOfTrigonometricFunction);

                String newExpression = subExpressionStart + roundedResult + subExpressionEnd.substring(1);

                recursion(newExpression, ++countOperation);
                return;
            }
        }

        if (!subExpressionStart.isEmpty()) {
            String newExpression = subExpressionStart
                    .substring(0, subExpressionStart.length() - 1).trim() + substring;
            if (!subExpressionEnd.isEmpty()) {
                newExpression = newExpression + subExpressionEnd.substring(1);
            }
            recursion(newExpression, countOperation);
            return;
        }

        Double expressionResult = Math.round(Double.parseDouble(expressionWithoutSpaces) * 100) / 100.0;
        String result = expressionResult.toString();
        if (Math.rint(expressionResult) == expressionResult) {
            result = String.valueOf(expressionResult.intValue());
        }

        System.out.println(String.format("%s %d", result, countOperation));


    }

    private enum Operation{
        POW,
        DIVIDE,
        MULTIPLY,
        MINUS,
        PLUS
    }

    private enum TrigonometricFunction{
        COS,
        SIN,
        TAN
    }

    private boolean checkOperation(Operation operation, String substring){
        switch (operation){
            case POW:
                return substring.contains("^");
            case DIVIDE:
                return substring.contains("/");
            case MULTIPLY:
                return substring.contains("*");
            case MINUS:
                return indexOfOperation(operation, substring) != -1;
            case PLUS:
                return substring.contains("+");
        }
        return false;
    }

    private int indexOfOperation(Operation operation, String expression){
        switch (operation){
            case POW:
                return expression.indexOf("^");
            case DIVIDE:
                return expression.indexOf("/");
            case MULTIPLY:
                return expression.indexOf("*");
            case MINUS:
                int index = expression.indexOf("-", 1);
                while (index != -1 && !Character.isDigit(expression.charAt(index - 1))) {
                    index = expression.indexOf("-", index + 1);
                }
                return index;
            case PLUS:
                return expression.indexOf("+");
        }
        return -1;
    }

    private double calculateOperation(Operation operation, double a, double b){
        switch (operation){
            case POW:
                double pow = Math.pow(a, b);
                if (a < 0) pow *= -1;
                return pow;
            case DIVIDE:
                return a / b;
            case MULTIPLY:
                return a * b;
            case MINUS:
                return a - b;
            case PLUS:
                return a + b;
            default:
                return 0;
        }
    }

    private boolean checkTrigonometricFunction(TrigonometricFunction trigonometricFunction
            , String subExpressionStart){
        switch (trigonometricFunction){
            case COS:
                return subExpressionStart.endsWith("cos(");
            case SIN:
                return subExpressionStart.endsWith("sin(");
            case TAN:
                return subExpressionStart.endsWith("tan(");
        }
        return false;
    }

    public Solution() {
        //don't delete
    }
}
