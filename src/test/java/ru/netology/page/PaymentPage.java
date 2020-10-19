package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {
    private ElementsCollection fields = $$(".input__control");
    private SelenideElement numberField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("[placeholder='08']");
    private SelenideElement yearField = $("[placeholder='22']");
    private SelenideElement ownerField =  fields.get(3);
    private SelenideElement cvvField = $("[placeholder='999']");

    private SelenideElement button = $(byText("Продолжить"));

    private SelenideElement successNotification = $(byText("Операция одобрена Банком."));
    private SelenideElement failNotification = $(byText("Ошибка! Банк отказал в проведении операции."));
    private SelenideElement wrongFormat = $(byText("Неверный формат"));
    private SelenideElement wrongTerm = $(byText("Неверно указан срок действия карты"));
    private SelenideElement cardExpired = $(byText("Истёк срок действия карты"));
    private SelenideElement fieldRequired = $(byText("Поле обязательно для заполнения"));

}
