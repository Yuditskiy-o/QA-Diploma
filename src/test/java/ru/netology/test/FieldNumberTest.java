package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;

import static ru.netology.data.DataHelper.*;

public class FieldNumberTest extends TestBase {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @Nested
    class FieldNumberOfDebitCardTests {

        @BeforeEach
        void setUpAllDebitCardTests() {
            mainPage.payWithDebitCard();
        }

        @Test
        void shouldNotDoPaymentWhenEmptyCard() {
            val info = getEmptyCardNumber();
            paymentPage.fillForm(info);
            paymentPage.waitIfShouldFillFieldMessage();
        }

        @Test
        void shouldNotDoPaymentWhen15Symbols() {
            val info = getInvalidCardNumberWith15Symbols();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongFormatMessage();
        }

        @Test
        void shouldNotDoPaymentWhenAnotherBankCard() {
            val info = getAnotherBankCardNumber();
            paymentPage.fillForm(info);
            paymentPage.waitIfFailMessage();
        }
    }

    @Nested
    class FieldNumberOfCreditCardTests {

        @BeforeEach
        void setUpAllCreditCardTests() {
            mainPage.payWithCreditCard();
        }

        @Test
        void shouldNotDoPaymentWhenEmptyCard() {
            val info = getEmptyCardNumber();
            paymentPage.fillForm(info);
            paymentPage.waitIfShouldFillFieldMessage();
        }

        @Test
        void shouldNotDoPaymentWhen15Symbols() {
            val info = getInvalidCardNumberWith15Symbols();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongFormatMessage();
        }

        @Test
        void shouldNotDoPaymentWhenAnotherBankCard() {
            val info = getAnotherBankCardNumber();
            paymentPage.fillForm(info);
            paymentPage.waitIfFailMessage();
        }
    }
}
