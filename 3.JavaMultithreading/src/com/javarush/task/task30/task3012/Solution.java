package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
    }

    public void createExpression(int number) {
        StringBuilder result = new StringBuilder().append(number).append(" =");
        String [] formats = {""," + %d"," - %d"};
        for (int i = number, tri = 1; 0 < i; i = ++i / 3, tri *= 3) {
            result.append(String.format(formats[i%3],tri));
        }
        System.out.println(result);
        //напишите тут ваш код
//        StringBuilder stringBuilder = new StringBuilder(number + " =");
//        int div = number;
//        int i = 0;
//        String[] stringFormats = {"", " + %d", " - %d"};
//        while ((div /= 3) >= 3) {
//            int mod = div % 3;
//            if (mod == 2) {
//                div++;
//            }
//
//            stringBuilder.append(stringFormats[mod]).append(Math.pow(3, i));
//            i++;




//            switch (mod) {
//                case 1:
//                    stringBuilder.append("+");
//                    break;
//                case 0:
//                    stringBuilder.append("0");
//                    break;
//                case 2:
//                    stringBuilder.append("-");
//                    break;
//                default:
//                    break;
//            }
//        }
//
//        str

//        switch (div) {
//            case 1:
//                stringBuilder.append("+");
//                break;
//            case 0:
//                stringBuilder.append("0");
//                break;
//            case 2:
//                stringBuilder.append("+-");
//            default:
//                break;
//        }

//        String string = stringBuilder.toString();
//        char[] chars = stringBuilder.toString().toCharArray();
//        StringBuilder resultStringBuilder = new StringBuilder(number + " =");
//        for (int i = 0; i < chars.length; i++) {
//            if (chars[i] == '0'){
//                continue;
//            } else {
//                resultStringBuilder.append(" ").append(chars[i]).append(" ").append((int) Math.pow(3, i));
//            }
//        }
//
//        System.out.println(resultStringBuilder.toString());

    }
}