package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {
    private ElementsCollection fields = $$(".input__control");
    private SelenideElement numberField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("[placeholder='08']");
    private SelenideElement yearField = $("[placeholder='22']");
    private SelenideElement ownerField = fields.get(3);
    private SelenideElement cvvField = $("[placeholder='999']");

    private SelenideElement continueButton = $(byText("Продолжить"));

    private SelenideElement successNotification = $(byText("Операция одобрена Банком."));
    private SelenideElement failNotification = $(byText("Ошибка! Банк отказал в проведении операции."));
    private SelenideElement wrongFormat = $(byText("Неверный формат"));
    private SelenideElement wrongTerm = $(byText("Неверно указан срок действия карты"));
    private SelenideElement cardExpired = $(byText("Истёк срок действия карты"));
    private SelenideElement fieldRequired = $(byText("Поле обязательно для заполнения"));

    public void fillForm(DataHelper.Number number, DataHelper.Month Month, DataHelper.Year Year, DataHelper.Owner Owner, DataHelper.Cvv Cvv) {
        numberField.setValue(number.getNumber());
        monthField.setValue(Month.getMonth());
        yearField.setValue(Year.getYear());
        ownerField.setValue(Owner.getOwner());
        cvvField.setValue(Cvv.getCvv());
        continueButton.click();
    }

    public void successMessage() {
        successNotification.waitUntil(Condition.visible, 10000);
    }

    public void failMessage() {
        failNotification.waitUntil(Condition.visible, 10000);
    }

    public void wrongFormatMessage() {
        wrongFormat.waitUntil(Condition.visible, 10000);
    }

    public void wrongTermMessage() {
        wrongTerm.waitUntil(Condition.visible, 10000);
    }

    public void cardExpiredMessage() {
        cardExpired.waitUntil(Condition.visible, 10000);
    }

    public void shouldFillMessage() {
        fieldRequired.waitUntil(Condition.visible, 10000);
    }
}
