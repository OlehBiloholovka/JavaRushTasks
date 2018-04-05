package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH +"deposit");
    @Override
    public void execute() throws InterruptOperationException{
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator
                = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        while (true) {
            try {
                String[] nominalAndCount = ConsoleHelper.getValidTwoDigits(currencyCode);

                int nominal = Integer.parseInt(nominalAndCount[0]);
                int count = Integer.parseInt(nominalAndCount[1]);


                ConsoleHelper.writeMessage(res.getString("before"));
                manipulator.addAmount(nominal
                        , count);
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), nominal * count, currencyCode));
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
                continue;
            }
            break;
        }
    }
}
