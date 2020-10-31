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

public class FieldMonthTest {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeAll
    static void setUp() {
        Configuration.screenshots = false;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterAll
    static void tearDown() {
        SelenideLogger.removeListener("allure");
    }

    @Nested
    public class FieldMonthOfDebitCardTests {

        @BeforeEach
        void setUpAllTests() {
            open(System.getProperty("sut.url"));
            mainPage.payWithDebitCard();
        }

        @AfterEach
        void cleanDb() {
            SQLHelper.cleanDb();
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
    public class FieldMonthOfCreditCardTests {

        @BeforeEach
        void setUpAllTests() {
            open(System.getProperty("sut.url"));
            mainPage.payWithCreditCard();
        }

        @AfterEach
        void cleanDb() {
            SQLHelper.cleanDb();
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
