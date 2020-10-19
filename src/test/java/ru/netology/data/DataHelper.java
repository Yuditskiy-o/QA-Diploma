package ru.netology.data;

import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor
public class DataHelper {

    @Value
    public static class Number {
        String Number;
    }

    public static Number getValidApprovedCard() {
        return new Number("4444444444444441");
    }

    public static Number getValidDeclinedCard() {
        return new Number("4444444444444442");
    }

    public static Number getEmptyCard() {
        return new Number("");
    }

    public static Number getInvalidCardWith15Symbols() {
        return new Number("444444444444444");
    }

    public static Number getOtherBankCard() {
        return new Number("5559444444444444");
    }

    @Value
    public static class Month {
        String Month;
    }

    public static Month getValidMonth() {
        return new Month("10");
    }

    public static Month getEmptyMonth() {
        return new Month("");
    }

    public static Month getInvalidMonthLess() {
        return new Month("00");
    }

    public static Month getInvalidMonthMore() {
        return new Month("13");
    }

    public static Month getInvalidFormatMonthLess() {
        return new Month("8");
    }

    @Value
    public static class Year {
        String Year;
    }

    public static Year getValidYear() {
        return new Year("22");
    }

    public static Year getEmptyYear() {
        return new Year("");
    }

    public static Year getEarlyYear() {
        return new Year("19");
    }

    public static Year getFutureYear() {
        return new Year("50");
    }

    @Value
    public static class Owner {
        String Owner;
    }

    public static Owner getValidOwner() {
        return new Owner("Ivanov Petr");
    }

    public static Owner getEmptyOwner() {
        return new Owner("");
    }

    public static Owner getInvalidOwnerWith1Word() {
        return new Owner("Ivan");
    }

    public static Owner getInvalidOwnerWith3Words() {
        return new Owner("Ivanov Petr Ivanovich");
    }

    public static Owner getInvalidOwnerWithLowerCase() {
        return new Owner("ivanov petr");
    }

    public static Owner getInvalidOwnerWithUpperCase() {
        return new Owner("IVANOV PETR");
    }

    public static Owner getInvalidOwnerWithCyrillic() {
        return new Owner("Иванов Петр");
    }

    public static Owner getInvalidOwnerWithDigits() {
        return new Owner("Иванов Петр");
    }

    public static Owner getInvalidOwnerWithSymbols() {
        return new Owner("%№%№");
    }

    @Value
    public static class Cvv {
        String Cvv;
    }

    public static Cvv getValidCVV() {
        return new Cvv("111");
    }

    public static Cvv getEmptyCVV() {
        return new Cvv("");
    }

    public static Cvv getInvalidCVVWith1Symbol() {
        return new Cvv("1");
    }

    public static Cvv getInvalidCVVWith2Symbols() {
        return new Cvv("11");
    }

    public static Cvv getInvalidCVVWith4Symbols() {
        return new Cvv("1111");
    }
}