package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;

import static ru.netology.data.DataHelper.*;

public class FieldOwnerTest extends TestBase {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @Nested
    class FieldOwnerOfDebitCardTests {

        @BeforeEach
        void setUpAllDebitCardTests() {
            mainPage.payWithDebitCard();
        }

        @Test
        void shouldNotDoPaymentWhenOwnerEmpty() {
            val info = getEmptyOwner();
            paymentPage.fillForm(info);
            paymentPage.waitIfShouldFillFieldMessage();
        }

        @Test
        void shouldNotDoPaymentWhenOwnerIsOneLatinWord() {
            val info = getInvalidOwnerWithOneWord();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongFormatMessage();
        }

        @Test
        void shouldNotDoPaymentWhenOwnerIsThreeLatinWords() {
            val info = getInvalidOwnerWithThreeWords();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongFormatMessage();
        }

        @Test
        void shouldNotDoPaymentWhenOwnerInLowerCase() {
            val info = getInvalidOwnerWithLowerCase();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongFormatMessage();
        }

        @Test
        void shouldNotDoPaymentWhenOwnerInUpperCase() {
            val info = getInvalidOwnerWithUpperCase();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongFormatMessage();
        }

        @Test
        void shouldNotDoPaymentWhenOwnerInCyrillic() {
            val info = getInvalidOwnerWithCyrillic();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongFormatMessage();
        }

        @Test
        void shouldNotDoPaymentWhenOwnerWithDigits() {
            val info = getInvalidOwnerWithDigits();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongFormatMessage();
        }

        @Test
        void shouldNotDoPaymentWhenOwnerWithSymbols() {
            val info = getInvalidOwnerWithSymbols();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongFormatMessage();
        }
    }

    @Nested
    class FieldOwnerOfCreditCardTests {

        @BeforeEach
        void setUpAllCreditCardTests() {
            mainPage.payWithCreditCard();
        }

        @Test
        void shouldNotDoPaymentWhenOwnerEmpty() {
            val info = getEmptyOwner();
            paymentPage.fillForm(info);
            paymentPage.waitIfShouldFillFieldMessage();
        }

        @Test
        void shouldNotDoPaymentWhenOwnerIsOneLatinWord() {
            val info = getInvalidOwnerWithOneWord();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongFormatMessage();
        }

        @Test
        void shouldNotDoPaymentWhenOwnerIsThreeLatinWords() {
            val info = getInvalidOwnerWithThreeWords();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongFormatMessage();
        }

        @Test
        void shouldNotDoPaymentWhenOwnerInLowerCase() {
            val info = getInvalidOwnerWithLowerCase();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongFormatMessage();
        }

        @Test
        void shouldNotDoPaymentWhenOwnerInUpperCase() {
            val info = getInvalidOwnerWithUpperCase();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongFormatMessage();
        }

        @Test
        void shouldNotDoPaymentWhenOwnerInCyrillic() {
            val info = getInvalidOwnerWithCyrillic();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongFormatMessage();
        }

        @Test
        void shouldNotDoPaymentWhenOwnerWithDigits() {
            val info = getInvalidOwnerWithDigits();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongFormatMessage();
        }

        @Test
        void shouldNotDoPaymentWhenOwnerWithSymbols() {
            val info = getInvalidOwnerWithSymbols();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongFormatMessage();
        }
    }
}
