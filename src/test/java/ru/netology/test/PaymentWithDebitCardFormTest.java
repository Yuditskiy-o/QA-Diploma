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

public class PaymentWithDebitCardFormTest {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeAll
    static void setUpAll() {
        Configuration.screenshots = false;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterAll
    static void cleanDataBases() {
        SQLHelper.cleanDb();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
        mainPage.payWithDebitCard();
    }

    @Nested
    public class HappyPathOneTests {
        private final DataHelper.Month month = getValidMonth();
        private final DataHelper.Year year = getValidYear();
        private final DataHelper.Owner owner = getValidOwner();
        private final DataHelper.Cvv cvv = getValidCVV();

        @Test
        public void shouldDoPaymentWhenValidApprovedCards() {
            val number = getValidApprovedCard();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.successMessage();
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
            val number = getValidDeclinedCard();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.failMessage();
            val paymentId = getPaymentId();
            val expectedStatus = "DECLINED";
            val actualStatus = getStatusForPaymentWithDebitCard(paymentId);
            assertEquals(expectedStatus, actualStatus);
        }
    }

    @Nested
    public class FieldNumberTests {
        private final DataHelper.Month month = getValidMonth();
        private final DataHelper.Year year = getValidYear();
        private final DataHelper.Owner owner = getValidOwner();
        private final DataHelper.Cvv cvv = getValidCVV();

        @Test
        void shouldNotDoPaymentWhenEmptyCard() {
            val number = getEmptyCard();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.shouldFillMessage();
        }

        @Test
        void shouldNotDoPaymentWhen15Symbols() {
            val number = getInvalidCardWith15Symbols();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongFormatMessage();
        }

        @Test
        void shouldNotDoPaymentWhenAnotherBankCard() {
            val number = getAnotherBankCard();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongFormatMessage();
        }
    }

    @Nested
    public class FieldMonthTests {
        private final DataHelper.Number number = getValidApprovedCard();
        private final DataHelper.Year year = getValidYear();
        private final DataHelper.Owner owner = getValidOwner();
        private final DataHelper.Cvv cvv = getValidCVV();

        @Test
        void shouldNotDoPaymentWhenEmptyMonth() {
            val month = getEmptyMonth();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.shouldFillMessage();
        }

        @Test
        void shouldNotDoPaymentWhenMonthIsZeroZero() {
            val month = getInvalidFormatMonthIsZeroZero();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongTermMessage();
        }

        @Test
        void shouldNotDoPaymentWhenIrrelevantMonth() {
            val month = getInvalidFormatMonthIsIrrelevant();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongTermMessage();
        }

        @Test
        void shouldNotDoPaymentWhenMonthIs1Digit() {
            val month = getInvalidFormatMonthIsOneDigit();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongFormatMessage();
        }
    }

    @Nested
    public class FieldYearTests {
        private final DataHelper.Number number = getValidApprovedCard();
        private final DataHelper.Month month = getValidMonth();
        private final DataHelper.Owner owner = getValidOwner();
        private final DataHelper.Cvv cvv = getValidCVV();

        @Test
        void shouldNotDoPaymentWhenYearEmpty() {
            val year = getEmptyYear();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.shouldFillMessage();
        }

        @Test
        void shouldNotDoPaymentWhenYearIsEarly() {
            val year = getEarlyYear();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.cardExpiredMessage();
        }

        @Test
        void shouldNotDoPaymentWhenYearIsFuture() {
            val year = getFutureYear();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongTermMessage();
        }
    }

    @Nested
    public class FieldOwnerTests {
        private final DataHelper.Number number = getValidApprovedCard();
        private final DataHelper.Month month = getValidMonth();
        private final DataHelper.Year year = getValidYear();
        private final DataHelper.Cvv cvv = getValidCVV();

        @Test
        void shouldNotDoPaymentWhenOwnerEmpty() {
            val owner = getEmptyOwner();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.shouldFillMessage();
        }

        @Test
        void shouldNotDoPaymentWhenOwnerIsOneLatinWord() {
            val owner = getInvalidOwnerWithOneWord();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongFormatMessage();
        }

        @Test
        void shouldNotDoPaymentWhenOwnerIsThreeLatinWords() {
            val owner = getInvalidOwnerWithThreeWords();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongFormatMessage();
        }

        @Test
        void shouldNotDoPaymentWhenOwnerInLowerCase() {
            val owner = getInvalidOwnerWithLowerCase();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongFormatMessage();
        }

        @Test
        void shouldNotDoPaymentWhenOwnerInUpperCase() {
            val owner = getInvalidOwnerWithUpperCase();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongFormatMessage();
        }

        @Test
        void shouldNotDoPaymentWhenOwnerInCyrillic() {
            val owner = getInvalidOwnerWithCyrillic();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongFormatMessage();
        }

        @Test
        void shouldNotDoPaymentWhenOwnerWithDigits() {
            val owner = getInvalidOwnerWithDigits();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongFormatMessage();
        }

        @Test
        void shouldNotDoPaymentWhenOwnerWithSymbols() {
            val owner = getInvalidOwnerWithSymbols();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongFormatMessage();
        }
    }

    @Nested
    public class FieldCVVTests {
        private final DataHelper.Number number = getValidApprovedCard();
        private final DataHelper.Month month = getValidMonth();
        private final DataHelper.Year year = getValidYear();
        private final DataHelper.Owner owner = getValidOwner();

        @Test
        void shouldNotDoPaymentWhenСVVIsEmpty() {
            val cvv = getEmptyCVV();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.shouldFillMessage();
        }

        @Test
        void shouldNotDoPaymentWhenСVVIs1Digit() {
            val cvv = getInvalidCVVWith1Digit();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongFormatMessage();
        }

        @Test
        void shouldNotDoPaymentWhenСVVIs2Digits() {
            val cvv = getInvalidCVVWith2Digits();
            paymentPage.fillForm(number, month, year, owner, cvv);
            paymentPage.wrongFormatMessage();
        }
    }
}
