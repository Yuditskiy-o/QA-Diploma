package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.netology.data.DataHelper.getValidApprovedCardData;
import static ru.netology.data.DataHelper.getValidDeclinedCardData;
import static ru.netology.data.SQLHelper.*;

public class HappyPathTest extends TestBase {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @Nested
    class HappyPath1OfDebitCardTests {

        @BeforeEach
        void setUpAllDebitCardTests() {
            mainPage.payWithDebitCard();
        }

        @Test
        void shouldDoPaymentWhenValidApprovedCard() {
            val info = getValidApprovedCardData();
            paymentPage.fillForm(info);
            paymentPage.waitIfSuccessMessage();
            val expectedAmount = "4500000";
            val actualAmount = getPaymentAmount();
            assertEquals(expectedAmount, actualAmount);
            val expectedStatus = "APPROVED";
            val actualStatus = getStatusForPaymentWithDebitCard();
            assertEquals(expectedStatus, actualStatus);
            val expectedId = getPaymentId();
            assertNotNull(expectedId);
            val actualId = getOrderPaymentId();
            assertNotNull(actualId);
            assertEquals(expectedId, actualId);
        }

        @Test
        void shouldNotDoPaymentWhenValidDeclinedCard() {
            val info = getValidDeclinedCardData();
            paymentPage.fillForm(info);
            paymentPage.waitIfFailMessage();
            val expectedStatus = "DECLINED";
            val actualStatus = getStatusForPaymentWithDebitCard();
            assertEquals(expectedStatus, actualStatus);
            val expectedId = getPaymentId();
            assertNotNull(expectedId);
            val actualId = getOrderPaymentId();
            assertNotNull(actualId);
            assertEquals(expectedId, actualId);
        }
    }

    @Nested
    class HappyPath2OfCreditCardTests {

        @BeforeEach
        void setUpAllCreditCardTests() {
            mainPage.payWithCreditCard();
        }

        @Test
        void shouldDoPaymentWhenValidApprovedCard() {
            val info = getValidApprovedCardData();
            paymentPage.fillForm(info);
            paymentPage.waitIfSuccessMessage();
            val expectedStatus = "APPROVED";
            val actualStatus = getStatusForPaymentWithCreditCard();
            assertEquals(expectedStatus, actualStatus);
            val expectedId = getCreditId();
            assertNotNull(expectedId);
            val actualId = getOrderCreditId();
            assertNotNull(actualId);
            assertEquals(expectedId, actualId);
        }

        @Test
        void shouldNotDoPaymentWhenValidDeclinedCard() {
            val info = getValidDeclinedCardData();
            paymentPage.fillForm(info);
            paymentPage.waitIfFailMessage();
            val expectedStatus = "DECLINED";
            val actualStatus = getStatusForPaymentWithCreditCard();
            assertEquals(expectedStatus, actualStatus);
            val expectedId = getCreditId();
            assertNotNull(expectedId);
            val actualId = getOrderCreditId();
            assertNotNull(actualId);
            assertEquals(expectedId, actualId);
        }
    }
}
