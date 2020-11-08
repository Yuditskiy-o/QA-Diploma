package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;

import static ru.netology.data.DataHelper.*;

public class FieldYearTest extends TestBase {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @Nested
    class FieldYearOfDebitCardTests {

        @BeforeEach
        void setUpAllDebitCardTests() {
            mainPage.payWithDebitCard();
        }

        @Test
        void shouldNotDoPaymentWhenYearEmpty() {
            val info = getEmptyYear();
            paymentPage.fillForm(info);
            paymentPage.waitIfShouldFillFieldMessage();
        }

        @Test
        void shouldNotDoPaymentWhenYearIsEarly() {
            val info = getEarlyYear();
            paymentPage.fillForm(info);
            paymentPage.waitIfCardExpiredMessage();
        }

        @Test
        void shouldNotDoPaymentWhenYearIsFuture() {
            val info = getFutureYear();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongTermMessage();
        }
    }

    @Nested
    class FieldYearOfCreditCardTests {

        @BeforeEach
        void setUpAllCreditCardTests() {
            mainPage.payWithCreditCard();
        }

        @Test
        void shouldNotDoPaymentWhenYearEmpty() {
            val info = getEmptyYear();
            paymentPage.fillForm(info);
            paymentPage.waitIfShouldFillFieldMessage();
        }

        @Test
        void shouldNotDoPaymentWhenYearIsEarly() {
            val info = getEarlyYear();
            paymentPage.fillForm(info);
            paymentPage.waitIfCardExpiredMessage();
        }

        @Test
        void shouldNotDoPaymentWhenYearIsFuture() {
            val info = getFutureYear();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongTermMessage();
        }
    }
}
