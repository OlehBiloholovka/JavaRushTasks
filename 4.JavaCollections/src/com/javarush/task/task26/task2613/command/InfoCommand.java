package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;

class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH +"info");

    @Override
    public void execute() throws InterruptOperationException{
        Collection<CurrencyManipulator> manipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (manipulators.isEmpty()) {
            ConsoleHelper.writeMessage(String.format("%s%s", res.getString("before"), res.getString("no.money")));
        }else {
            manipulators.forEach(manipulator -> {
                if (manipulator.hasMoney()) {
                    ConsoleHelper
                            .writeMessage(String.format("%s%s - %d"
                                    , res.getString("before")
                                    , manipulator.getCurrencyCode()
                                    , manipulator.getTotalAmount()));
                } else {
                    ConsoleHelper.writeMessage(String.format("%s%s", res.getString("before"), res.getString("no.money")));
                }
            });
        }
    }
}
