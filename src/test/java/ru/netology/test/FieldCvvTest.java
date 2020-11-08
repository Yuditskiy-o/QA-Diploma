package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;

import static ru.netology.data.DataHelper.*;

public class FieldCvvTest extends TestBase {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @Nested
    class FieldCvvOfDebitCardTests {

        @BeforeEach
        void setUpAllDebitCardTests() {
            mainPage.payWithDebitCard();
        }

        @Test
        void shouldNotDoPaymentWhenСVVIsEmpty() {
            val info = getEmptyCVV();
            paymentPage.fillForm(info);
            paymentPage.waitIfShouldFillFieldMessage();
        }

        @Test
        void shouldNotDoPaymentWhenСVVIs1Digit() {
            val info = getInvalidCVVWith1Digit();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongFormatMessage();
        }

        @Test
        void shouldNotDoPaymentWhenСVVIs2Digits() {
            val info = getInvalidCVVWith2Digits();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongFormatMessage();
        }
    }

    @Nested
    class FieldCvvOfCreditCardTests {

        @BeforeEach
        void setUpAllCreditCardTests() {
            mainPage.payWithCreditCard();
        }

        @Test
        void shouldNotDoPaymentWhenСVVIsEmpty() {
            val info = getEmptyCVV();
            paymentPage.fillForm(info);
            paymentPage.waitIfShouldFillFieldMessage();
        }

        @Test
        void shouldNotDoPaymentWhenСVVIs1Digit() {
            val info = getInvalidCVVWith1Digit();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongFormatMessage();
        }

        @Test
        void shouldNotDoPaymentWhenСVVIs2Digits() {
            val info = getInvalidCVVWith2Digits();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongFormatMessage();
        }
    }
}
