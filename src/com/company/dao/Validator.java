package com.company.dao;

public class Validator {

    /* public static boolean generalValidCheck(String cardNumber)
    {
        double controlSum = 0, taken;

        try {
            for (int i = 1; i < cardNumber.length(); i += 2) {
                taken = Integer.parseInt(String.valueOf(cardNumber.charAt(i)));
                controlSum += (taken * 2) % 10 + (taken * 2) / 10;
            }
            for (int i = 0; i < cardNumber.length(); i += 2) {
                taken = Integer.parseInt(String.valueOf(cardNumber.charAt(i)));
                controlSum += taken;
            }
            return controlSum % 10 == 0;
        } catch (Exception exception) {
            return false;
        }
    } */

    public static boolean luhnValidation(String cardNumber){

        double digit;
        int i = 0, controlSum = 0;

        try {
            long temp = Long.parseLong(cardNumber);

            while (temp > 0) {
                digit = temp % 10;
                temp = temp / 10;

                if (i % 2 != 0)
                    digit *= 2;

                if (digit > 9)
                    digit = (digit % 10) + 1;
                else
                    digit *= 1;

                controlSum += digit;
                i++;
            }

            return controlSum % 10 == 0;
        } catch (Exception exception) {
            return false;
        }
    }

}
