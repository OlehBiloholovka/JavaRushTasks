package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH +"verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH +"login");


    @Override
    public void execute() throws InterruptOperationException {
//        ConsoleHelper.writeMessage(res.getString("before"));
//        while (true){
//            ConsoleHelper.writeMessage(res.getString("specify.data"));
//            String[] strings = ConsoleHelper.readString().split(" ");
//            String enteredCardNumber = strings[0];
//            String enteredPin = strings[1];
//
////            ConsoleHelper.writeMessage(res.getString("before"));
//
//
//            boolean isDigitCardNumber = enteredCardNumber.chars().allMatch(Character::isDigit);
//            boolean isDigitPin = enteredPin.chars().allMatch(Character::isDigit);
////            if (strings.length != 2
////                    || enteredCardNumber.length() != 12 || enteredPin.length() != 4
////                    || !isDigitCardNumber || !isDigitPin) {
////                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
////                continue;
////            } else {
////                if (strings.length != 2
////                        || enteredCardNumber.length() != 12 || enteredPin.length() != 4
////                        || !isDigitCardNumber || !isDigitPin
////                        || !validCreditCards.containsKey(enteredCardNumber)
////                        || !validCreditCards.getString(enteredCardNumber).equals(enteredPin)) {
////                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), enteredCardNumber));
////                    ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
////                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
////                    continue;
////                }
////            }
//            if (strings.length != 2
//                    || enteredCardNumber.length() != 12 || enteredPin.length() != 4
//                    || !isDigitCardNumber || !isDigitPin
//                    || !validCreditCards.containsKey(enteredCardNumber)
//                    || !validCreditCards.getString(enteredCardNumber).equals(enteredPin)) {
//                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), enteredCardNumber));
//                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
//                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
//                continue;
//            }
//
//            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), enteredCardNumber));
//            break;
//        }
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true)
        {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String s1 = ConsoleHelper.readString();
            String s2 = ConsoleHelper.readString();
            if (validCreditCards.containsKey(s1)) {
                if (validCreditCards.getString(s1).equals(s2))
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), s1));
                else {
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), s1));
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                    continue;
                }
            } else {
                ConsoleHelper.writeMessage(res.getString("not.verified.format"));
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                continue;
            }

            break;
        }
    }
}
