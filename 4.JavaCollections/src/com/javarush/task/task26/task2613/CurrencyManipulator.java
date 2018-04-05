package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations;


    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        denominations = new HashMap<>();

    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count){
        if (!denominations.containsKey(denomination)) {
            denominations.put(denomination, count);
        } else {
            denominations.put(denomination, denominations.get(denomination) + count);
        }
    }

    public int getTotalAmount(){
        AtomicInteger totalAmount = new AtomicInteger();
        denominations.forEach((integer, integer2) -> totalAmount.addAndGet(integer * integer2));
        return totalAmount.get();
    }

    public boolean hasMoney(){
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount){
        return expectedAmount <= getTotalAmount();
    }

    /*1.3.4. Списать деньги со счета. Для этого в манируляторе создай метод
Map<Integer, Integer> withdrawAmount(int expectedAmount), который вернет карту Map<номинал, количество>.
Подробно логику этого метода смотри в п.2.
    * */

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException{

/*2. Логика основного метода withdrawAmount:
2.1. Внимание!!! Метод withdrawAmount должен возвращать минимальное количество банкнот, которыми набирается запрашиваемая сумма.
Используйте Жадный алгоритм (use google).
Если есть несколько вариантов, то использовать тот, в котором максимальное количество банкнот высшего номинала.
Если для суммы 600 результат - три банкноты: 500 + 50 + 50 = 200 + 200 + 200, то выдать первый вариант.

Пример, надо выдать 600.
В манипуляторе есть следующие банкноты:
500 - 2
200 - 3
100 - 1
50 - 12

Результат должен быть таким:
500 - 1
100 - 1

т.е. всего две банкноты (это минимальное количество банкнот) номиналом 500 и 100.

2.2. Мы же не можем одни и те же банкноты выдавать несколько раз, поэтому
если мы нашли вариант выдачи денег (п.2.1. успешен), то вычесть все эти банкноты из карты в манипуляторе.

2.3. метод withdrawAmount должен кидать NotEnoughMoneyException, если купюрами невозможно выдать запрашиваемую сумму.

Пример, надо выдать 600.
В манипуляторе есть следующие банкноты:
500 - 2
200 - 2

Результат - данными банкнотами невозможно выдать запрашиваемую сумму. Кинуть исключение.
Не забудьте, что в некоторых случаях картой кидается ConcurrentModificationException.
* */
        Map<Integer, Integer> denominationThis = new HashMap<>(denominations);

        Map<Integer, Integer> resultMap = new LinkedHashMap<>();

//        Map<Integer, Integer> resultMap = new HashMap<>();

        ArrayList<Integer> keyList = new ArrayList<>(denominations.keySet());
        Collections.sort(keyList);
        Collections.reverse(keyList);

        int sum = 0;
        int amount = expectedAmount;
        for (int i = 0; i < keyList.size(); i++) {
            int key = keyList.get(i);
            if (key > expectedAmount) {
                continue;
            }
            if (denominationThis.get(key) <= 0) continue;
            if (resultMap.containsKey(key)) {
                System.out.println("key - " + key + ": " + resultMap.get(key));
                resultMap.replace(key, resultMap.get(key), resultMap.get(key)+1);
            } else {
                resultMap.put(key, 1);
            }
            sum += key;
            expectedAmount -= key;
            denominationThis.replace(key, denominationThis.get(key), denominationThis.get(key)-1);
            if (denominationThis.get(key) > 0) i--;
            if (sum == amount) {
                denominations = denominationThis;
                return resultMap;
            }
        }
        throw new NotEnoughMoneyException();
    }
}
