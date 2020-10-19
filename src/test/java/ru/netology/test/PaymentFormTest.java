package ru.netology.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.SQLHelper.*;

public class PaymentFormTest {
    private final String approvedStatus = "APPROVED";
    private final String declinedStatus = "DECLINED";

    @BeforeAll
    static void setUpAll() {
        Configuration.screenshots = false;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void cleanDataBases() {
        SQLHelper.cleanDb();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    private final DataHelper.Month month = getValidMonth();
    private final DataHelper.Year year = getValidYear();
    private final DataHelper.Owner owner = getValidOwner();
    private final DataHelper.Cvv cvv = getValidCVV();

    @Test
    public void shouldReceivePaymentWhenValidApprovedCard() {
        val mainPage = new MainPage();
        mainPage.payWithDebitCard();
        val paymentPage = new PaymentPage();
        val number = getValidApprovedCard();
        paymentPage.fillForm(number, month, year, owner, cvv);
        paymentPage.successMessage();
        comparePaymentAndTransactionID();
        val expectedStatus = approvedStatus;
        val actualStatus = getPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }


}
