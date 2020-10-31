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

public class FieldYearTest {
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
    public class FieldYearOfDebitCardTests {

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
    public class FieldYearOfCreditCardTests {

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
