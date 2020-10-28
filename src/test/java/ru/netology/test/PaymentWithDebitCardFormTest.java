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
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.SQLHelper.getPaymentId;
import static ru.netology.data.SQLHelper.getStatusForPaymentWithCreditCard;

public class PaymentWithDebitCardFormTest {
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

    @BeforeEach
    void setUpAllTests() {
        open("http://localhost:8080");
        mainPage.payWithDebitCard();
    }

    @Nested
    public class HappyPath1Tests {

        @Test
        public void shouldDoPaymentWhenValidApprovedCard() {
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

    @Nested
    public class FieldNumberTests {

        @Test
        void shouldNotDoPaymentWhenEmptyCard() {
            val info = getEmptyCardNumber();
            paymentPage.fillForm(info);
            paymentPage.waitIfShouldFillFieldMessage();
        }

        @Test
        void shouldNotDoPaymentWhen15Symbols() {
            val info = getInvalidCardNumberWith15Symbols();
            paymentPage.fillForm(info);
            paymentPage.waitIfWrongFormatMessage();
        }

        @Test
        void shouldNotDoPaymentWhenAnotherBankCard() {
            val info = getAnotherBankCardNumber();
            paymentPage.fillForm(info);
            paymentPage.waitIfFailMessage();
        }
    }

    @Nested
    public class FieldMonthTests {

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
    public class FieldYearTests {

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
    public class FieldOwnerTests {

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
    public class FieldCVVTests {

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
