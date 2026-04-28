package com.banking.decorator;

import com.banking.builder.AccountBuilder;
import com.banking.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecoratorTest {

    private Account base;

    @BeforeEach
    void setUp() {
        base = new AccountBuilder()
                .withAccountNumber("DEC-001")
                .withOwner("Decorator User")
                .withInitialBalance(1000.0)
                .build();
    }

    // --- LoggingAccountDecorator ---

    @Test
    void loggingDecoratorDelegatesCredit() {
        Account logged = new LoggingAccountDecorator(base);
        logged.credit(200.0);
        assertEquals(1200.0, base.getBalance());
    }

    @Test
    void loggingDecoratorDelegatesDebit() {
        Account logged = new LoggingAccountDecorator(base);
        logged.debit(300.0);
        assertEquals(700.0, base.getBalance());
    }

    @Test
    void loggingDecoratorExposesCorrectBalance() {
        Account logged = new LoggingAccountDecorator(base);
        logged.credit(100.0);
        assertEquals(logged.getBalance(), base.getBalance());
    }

    @Test
    void loggingDecoratorExposesAccountNumber() {
        Account logged = new LoggingAccountDecorator(base);
        assertEquals("DEC-001", logged.getAccountNumber());
    }

    @Test
    void loggingDecoratorPropagatesInvalidDebit() {
        Account logged = new LoggingAccountDecorator(base);
        assertThrows(IllegalStateException.class, () -> logged.debit(9999.0));
    }

    // --- FeeChargeDecorator ---

    @Test
    void feeDecoratorChargesPercentageOnDebit() {
        Account withFee = new FeeChargeDecorator(base, 10.0);
        withFee.debit(100.0);
        // debit 100 + 10% fee = 110 total
        assertEquals(890.0, base.getBalance());
    }

    @Test
    void feeDecoratorDoesNotAffectCredit() {
        Account withFee = new FeeChargeDecorator(base, 10.0);
        withFee.credit(100.0);
        assertEquals(1100.0, base.getBalance());
    }

    @Test
    void feeDecoratorZeroPercentageNoExtra() {
        Account withFee = new FeeChargeDecorator(base, 0.0);
        withFee.debit(100.0);
        assertEquals(900.0, base.getBalance());
    }

    // --- Stacked decorators ---

    @Test
    void stackedDecoratorsApplyBothBehaviors() {
        Account stacked = new FeeChargeDecorator(new LoggingAccountDecorator(base), 5.0);
        stacked.debit(200.0);
        // debit 200 + 5% = 210 total
        assertEquals(790.0, base.getBalance());
    }
}
