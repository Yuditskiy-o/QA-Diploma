package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private static SelenideElement buyWithDebitCardButton = $(byText("Купить"));
    private static SelenideElement buyWithCreditCardButton = $(byText("Купить в кредит"));
    private static SelenideElement headerForSelectedWay = $("#root > div > h3");

    public PaymentPage payWithDebitCard() {
        buyWithDebitCardButton.click();
        headerForSelectedWay.shouldHave(Condition.exactText("Оплата по карте"));
        return new PaymentPage();
    }

    public PaymentPage payWithCreditCard() {
        buyWithCreditCardButton.click();
        headerForSelectedWay.shouldHave(Condition.exactText("Кредит по данным карты"));
        return new PaymentPage();
    }
}
