package ru.netology.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.SQLHelper;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.DataHelper.getInvalidOwnerWithSymbols;

public class FieldOwnerTest {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeAll
    static void setUp() {
        Configuration.screenshots = false;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterAll
    static void tearDown() {
        SQLHelper.cleanDb();
        SelenideLogger.removeListener("allure");
    }

    @Nested
    public class FieldOwnerOfDebitCardTests {

        @BeforeEach
        void setUpAllTests() {
            open("http://localhost:8080");
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
    public class FieldOwnerOfCreditCardTests {

        @BeforeEach
        void setUpAllTests() {
            open("http://localhost:8080");
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
