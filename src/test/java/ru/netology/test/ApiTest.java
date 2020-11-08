package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.RestApiHelper.*;

class ApiTest {

    @Test
    void shouldGiveResponseForValidApprovedDebitCard() {
        val validApprovedCardForApi = getValidApprovedCardData();
        val response = fillPaymentFormWithDebitCardData(validApprovedCardForApi);
        assertTrue(response.contains("APPROVED"));
    }

    @Test
    void shouldGiveResponseForValidDeclinedDebitCard() {
        val validDeclinedCardForApi = getValidDeclinedCardData();
        val response = fillPaymentFormWithDebitCardData(validDeclinedCardForApi);
        assertTrue(response.contains("DECLINED"));
    }

    @Test
    void shouldGiveResponseForValidApprovedCreditCard() {
        val validApprovedCardForApi = getValidApprovedCardData();
        val response = fillPaymentFormWithCreditCardData(validApprovedCardForApi);
        assertTrue(response.contains("APPROVED"));
    }

    @Test
    void shouldGiveResponseForValidDeclinedCreditCard() {
        val validDeclinedCardForApi = getValidDeclinedCardData();
        val response = fillPaymentFormWithCreditCardData(validDeclinedCardForApi);
        assertTrue(response.contains("DECLINED"));
    }
}
