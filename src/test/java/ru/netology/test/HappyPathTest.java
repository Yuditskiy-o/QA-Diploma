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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.getValidApprovedCardData;
import static ru.netology.data.DataHelper.getValidDeclinedCardData;
import static ru.netology.data.SQLHelper.*;

public class HappyPathTest {
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
    public class HappyPath1OfDebitCardTests {

        @BeforeEach
        void setUpAllTests() {
            open("http://localhost:8080");
            mainPage.payWithDebitCard();
        }

        @Test
        void shouldDoPaymentWhenValidApprovedCard() {
            val info = getValidApprovedCardData();
            paymentPage.fillForm(info);
            paymentPage.waitIfSuccessMessage();
            val paymentId = getPaymentId();
            val expectedAmount = "4500000";
            val actualAmount = getPaymentAmount(paymentId);
            val expectedStatus = "APPROVED";
            val actualStatus = getStatusForPaymentWithDebitCard(paymentId);
            assertEquals(expectedAmount, actualAmount);
            assertEquals(expectedStatus, actualStatus);
        }

        @Test
        void shouldNotDoPaymentWhenValidDeclinedCard() {
            val info = getValidDeclinedCardData();
            paymentPage.fillForm(info);
            paymentPage.waitIfFailMessage();
            val paymentId = getPaymentId();
            val expectedStatus = "DECLINED";
            val actualStatus = getStatusForPaymentWithDebitCard(paymentId);
            assertEquals(expectedStatus, actualStatus);
        }
    }

    @Nested
    public class HappyPath2OfCreditCardTests {
        @BeforeEach
        void setUpAllTests() {
            open("http://localhost:8080");
            mainPage.payWithCreditCard();
        }

        @Test
        void shouldDoPaymentWhenValidApprovedCard() {
            val info = getValidApprovedCardData();
            paymentPage.fillForm(info);
            paymentPage.waitIfSuccessMessage();
            val paymentId = getPaymentId();
            val expectedStatus = "APPROVED";
            val actualStatus = getStatusForPaymentWithCreditCard(paymentId);
            assertEquals(expectedStatus, actualStatus);
        }

        @Test
        void shouldNotDoPaymentWhenValidDeclinedCard() {
            val info = getValidDeclinedCardData();
            paymentPage.fillForm(info);
            paymentPage.waitIfFailMessage();
            val paymentId = getPaymentId();
            val expectedStatus = "DECLINED";
            val actualStatus = getStatusForPaymentWithCreditCard(paymentId);
            assertEquals(expectedStatus, actualStatus);
        }
    }
}
