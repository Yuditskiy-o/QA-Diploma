package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;

import static ru.netology.data.DataHelper.*;

public class FieldMonthTest extends TestBase {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @Nested
    class FieldMonthOfDebitCardTests {

        @BeforeEach
        void setUpAllDebitCardTests() {
            mainPage.payWithDebitCard();
        }

        @Test
        void shouldNotDoPaymentWhenEmptyMonth() {
            val info = getEmptyMonth();
            paymentPage.fillForm(info);
            paymentPage.waitIfShouldFillFieldMessage();
        }

        @Test
        void shouldNotDoPaymentWhenMonthIsZeroZero() {
            val info = getInvalidFormatMonthIsZeroZero();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongTermMessage();
        }

        @Test
        void shouldNotDoPaymentWhenIrrelevantMonth() {
            val info = getInvalidFormatMonthIsIrrelevant();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongTermMessage();
        }

        @Test
        void shouldNotDoPaymentWhenMonthIs1Digit() {
            val info = getInvalidFormatMonthIsOneDigit();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongFormatMessage();
        }
    }

    @Nested
    class FieldMonthOfCreditCardTests {

        @BeforeEach
        void setUpAllCreditCardTests() {
            mainPage.payWithCreditCard();
        }

        @Test
        void shouldNotDoPaymentWhenEmptyMonth() {
            val info = getEmptyMonth();
            paymentPage.fillForm(info);
            paymentPage.waitIfShouldFillFieldMessage();
        }

        @Test
        void shouldNotDoPaymentWhenMonthIsZeroZero() {
            val info = getInvalidFormatMonthIsZeroZero();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongTermMessage();
        }

        @Test
        void shouldNotDoPaymentWhenIrrelevantMonth() {
            val info = getInvalidFormatMonthIsIrrelevant();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongTermMessage();
        }

        @Test
        void shouldNotDoPaymentWhenMonthIs1Digit() {
            val info = getInvalidFormatMonthIsOneDigit();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongFormatMessage();
        }
    }
}
