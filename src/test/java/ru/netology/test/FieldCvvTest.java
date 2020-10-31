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

public class FieldCvvTest {
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
    public class FieldCvvOfDebitCardTests {

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
    public class FieldCvvOfCreditCardTests {

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
