package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH +"withdraw");
    @Override
    public void execute() throws InterruptOperationException{
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        int sum;
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true){
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            String readString = ConsoleHelper.readString();

            try {

                try {
                    sum = Integer.parseInt(readString);
                    if (sum <= 0) throw new NumberFormatException();
                } catch (NumberFormatException e){
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                    continue;
                }


                if (!manipulator.isAmountAvailable(sum)){
//                    continue;
                    throw new NotEnoughMoneyException();
                }



                Map<Integer, Integer> withdrawAmount = null;
                try {
                    withdrawAmount = manipulator.withdrawAmount(sum);
                } catch (NotEnoughMoneyException e) {
                    ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                    continue;
                }

                withdrawAmount.forEach((integer, integer2) -> {
                    ConsoleHelper.writeMessage(String.format("\t%d - %d", integer, integer2));
                });
                ConsoleHelper.writeMessage(String.format(res.getString("success.format")
                        , sum, currencyCode));

            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                continue;
            }
            break;
        }




    }
}
