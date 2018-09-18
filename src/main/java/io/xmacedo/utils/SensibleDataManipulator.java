package io.xmacedo.utils;

public class SensibleDataManipulator {

    public static String getLastDigitsFromCardNumber(String cardNumber) {
        Integer cardNumberLenght = cardNumber.length();
        return cardNumber.substring(cardNumberLenght - 4);
    }
}
