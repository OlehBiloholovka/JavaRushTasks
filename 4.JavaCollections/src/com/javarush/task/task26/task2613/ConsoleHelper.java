package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH +"common");
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }
    public static String readString() throws InterruptOperationException{
        try {
            String readLine = bis.readLine();
            if (readLine.toUpperCase().equals("EXIT")) {
//                writeMessage(res.getString("the.end"));
                throw new InterruptOperationException();
            }
            return readLine;
        } catch (IOException e) {
            return "";
        }
    }

    public static String askCurrencyCode() throws InterruptOperationException{
        writeMessage(res.getString("choose.currency.code"));
        while (true) {
            String string = readString();
            if (string.length() != 3) {
                writeMessage(res.getString("invalid.data"));
            } else {
                return string.toUpperCase();
            }
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException{
        while (true) {
            writeMessage(String.format(res.getString("choose.denomination.and.count.format")
                    , currencyCode));
            String string = readString();
            String[] strings = string.split(" ");
            if (strings.length != 2
                    || !strings[0].chars().allMatch(Character::isDigit)
                    || !strings[1].chars().allMatch(Character::isDigit)) {
                writeMessage(res.getString("invalid.data"));
            } else {
                return strings;
            }
        }
    }

    public static Operation askOperation() throws InterruptOperationException{
        while (true){
            writeMessage(String.format("%s:\n1.%s\n2.%s\n3.%s\n4.%s"
                    , res.getString("choose.operation")
                    , res.getString("operation.INFO")
                    , res.getString("operation.DEPOSIT")
                    , res.getString("operation.WITHDRAW")
                    , res.getString("operation.EXIT")));
            try {
                int operationOrdinal = Integer.parseInt(readString());
                return Operation.getAllowableOperationByOrdinal(operationOrdinal);
            } catch (NumberFormatException e){
                writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static void printExitMessage(){
        writeMessage(res.getString("the.end"));
    }
}
