package com.banking.factory;

import com.banking.model.Account;
import com.banking.model.AccountImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountFactoryTest {

    private AccountFactory factory;

    @BeforeEach
    void setUp() {
        factory = new AccountFactory();
    }

    @Test
    void createStandardAccountViaInstance() {
        Account account = factory.createAccount(AccountFactory.TYPE_STANDARD, "10001-1", "Alice");
        assertEquals(0.0, account.getBalance());
        assertFalse(((AccountImpl) account).isPremium());
    }

    @Test
    void createPremiumAccountViaInstance() {
        Account account = factory.createAccount(AccountFactory.TYPE_PREMIUM, "10002-2", "Bob");
        assertEquals(5000.0, account.getBalance());
        assertTrue(((AccountImpl) account).isPremium());
    }

    @Test
    void createSavingsAccountViaInstance() {
        Account account = factory.createAccount(AccountFactory.TYPE_SAVINGS, "10003-3", "Carol");
        assertEquals(100.0, account.getBalance());
        assertFalse(((AccountImpl) account).isPremium());
    }

    @Test
    void createWithUnknownTypeThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                factory.createAccount("UNKNOWN", "99999-9", "Dave")
        );
    }

    @Test
    void staticCreateStandardAccount() {
        Account account = AccountFactory.createStandardAccount("20001-1", "Alice");
        assertEquals(0.0, account.getBalance());
    }

    @Test
    void staticCreatePremiumAccount() {
        Account account = AccountFactory.createPremiumAccount("20002-2", "Bob");
        assertEquals(5000.0, account.getBalance());
        assertTrue(((AccountImpl) account).isPremium());
    }

    @Test
    void staticCreateSavingsAccount() {
        Account account = AccountFactory.createSavingsAccount("20003-3", "Carol");
        assertEquals(100.0, account.getBalance());
    }

    @Test
    void accountNumberAndOwnerAreSetCorrectly() {
        Account account = factory.createAccount(AccountFactory.TYPE_STANDARD, "30001-1", "Frank");
        assertEquals("30001-1", account.getAccountNumber());
        assertEquals("Frank", account.getOwner());
    }
}
