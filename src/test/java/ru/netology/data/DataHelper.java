package ru.netology.data;

import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor
public class DataHelper {

    @Value
    public static class CardValidInformationModel {
        String Number;
        String Month;
        String Year;
        String Owner;
        String Сvv;
    }

    public static CardValidInformationModel getValidApprovedCardData() {
        return new CardValidInformationModel("4444 4444 4444 4441", "10", "22", "Ivanov Petr", "111");
    }

    public static CardValidInformationModel getValidDeclinedCardData() {
        return new CardValidInformationModel("4444 4444 4444 4442", "10", "22", "Ivanov Petr", "111");
    }

//    FIELD NUMBER

    public static CardValidInformationModel getEmptyCardNumber() {
        return new CardValidInformationModel("", "10", "22", "Ivanov Petr", "111");
    }

    public static CardValidInformationModel getInvalidCardNumberWith15Symbols() {
        return new CardValidInformationModel("4444 4444 4444 444", "10", "22", "Ivanov Petr", "111");
    }

    public static CardValidInformationModel getAnotherBankCardNumber() {
        return new CardValidInformationModel("5559 4444 4444 4444", "10", "22", "Ivanov Petr", "111");
    }

//    FIELD MONTH

    public static CardValidInformationModel getEmptyMonth() {
        return new CardValidInformationModel("4444 4444 4444 4441", "", "22", "Ivanov Petr", "111");
    }

    public static CardValidInformationModel getInvalidFormatMonthIsZeroZero() {
        return new CardValidInformationModel("4444 4444 4444 4441", "00", "22", "Ivanov Petr", "111");
    }

    public static CardValidInformationModel getInvalidFormatMonthIsIrrelevant() {
        return new CardValidInformationModel("4444 4444 4444 4441", "13", "22", "Ivanov Petr", "111");
    }

    public static CardValidInformationModel getInvalidFormatMonthIsOneDigit() {
        return new CardValidInformationModel("4444 4444 4444 4441", "8", "22", "Ivanov Petr", "111");
    }

//    FIELD YEAR

    public static CardValidInformationModel getEmptyYear() {
        return new CardValidInformationModel("4444 4444 4444 4441", "10", "", "Ivanov Petr", "111");
    }

    public static CardValidInformationModel getEarlyYear() {
        return new CardValidInformationModel("4444 4444 4444 4441", "10", "19", "Ivanov Petr", "111");
    }

    public static CardValidInformationModel getFutureYear() {
        return new CardValidInformationModel("4444 4444 4444 4441", "10", "50", "Ivanov Petr", "111");
    }

//    FIELD OWNER

    public static CardValidInformationModel getEmptyOwner() {
        return new CardValidInformationModel("4444 4444 4444 4441", "10", "22", "", "111");
    }

    public static CardValidInformationModel getInvalidOwnerWithOneWord() {
        return new CardValidInformationModel("4444 4444 4444 4441", "10", "22", "Ivan", "111");
    }

    public static CardValidInformationModel getInvalidOwnerWithThreeWords() {
        return new CardValidInformationModel("4444 4444 4444 4441", "10", "22", "Ivanov Petr Ivanovich", "111");
    }

    public static CardValidInformationModel getInvalidOwnerWithLowerCase() {
        return new CardValidInformationModel("4444 4444 4444 4441", "10", "22", "ivanov petr", "111");
    }

    public static CardValidInformationModel getInvalidOwnerWithUpperCase() {
        return new CardValidInformationModel("4444 4444 4444 4441", "10", "22", "IVANOV PETR", "111");
    }

    public static CardValidInformationModel getInvalidOwnerWithCyrillic() {
        return new CardValidInformationModel("4444 4444 4444 4441", "10", "22", "Иванов Петр", "111");
    }

    public static CardValidInformationModel getInvalidOwnerWithDigits() {
        return new CardValidInformationModel("4444 4444 4444 4441", "10", "22", "12345", "111");
    }

    public static CardValidInformationModel getInvalidOwnerWithSymbols() {
        return new CardValidInformationModel("4444 4444 4444 4441", "10", "22", "%№%№", "111");
    }

//    FIELD CVV

    public static CardValidInformationModel getEmptyCVV() {
        return new CardValidInformationModel("4444 4444 4444 4441", "10", "22", "Ivanov Petr", "");
    }

    public static CardValidInformationModel getInvalidCVVWith1Digit() {
        return new CardValidInformationModel("4444 4444 4444 4441", "10", "22", "Ivanov Petr", "1");
    }

    public static CardValidInformationModel getInvalidCVVWith2Digits() {
        return new CardValidInformationModel("4444 4444 4444 4441", "10", "22", "Ivanov Petr", "11");
    }
}
