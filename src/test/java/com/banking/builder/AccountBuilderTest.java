package com.banking.builder;

import com.banking.model.Account;
import com.banking.model.AccountImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountBuilderTest {

    @Test
    void buildWithRequiredFieldsOnly() {
        Account account = new AccountBuilder()
                .withAccountNumber("12345-6")
                .withOwner("Bob")
                .build();

        assertEquals("12345-6", account.getAccountNumber());
        assertEquals("Bob", account.getOwner());
        assertEquals(0.0, account.getBalance());
    }

    @Test
    void buildWithAllFields() {
        AccountImpl account = (AccountImpl) new AccountBuilder()
                .withAccountNumber("99999-9")
                .withOwner("Carol")
                .withInitialBalance(1000.0)
                .withDocument("123.456.789-00")
                .withEmail("carol@example.com")
                .withPremium(true)
                .build();

        assertEquals("99999-9", account.getAccountNumber());
        assertEquals("Carol", account.getOwner());
        assertEquals(1000.0, account.getBalance());
        assertEquals("123.456.789-00", account.getDocument());
        assertEquals("carol@example.com", account.getEmail());
        assertTrue(account.isPremium());
    }

    @Test
    void buildWithoutAccountNumberThrows() {
        assertThrows(IllegalStateException.class, () ->
                new AccountBuilder().withOwner("Dave").build()
        );
    }

    @Test
    void buildWithEmptyAccountNumberThrows() {
        assertThrows(IllegalStateException.class, () ->
                new AccountBuilder().withAccountNumber("").withOwner("Dave").build()
        );
    }

    @Test
    void buildWithoutOwnerThrows() {
        assertThrows(IllegalStateException.class, () ->
                new AccountBuilder().withAccountNumber("11111-1").build()
        );
    }

    @Test
    void buildWithEmptyOwnerThrows() {
        assertThrows(IllegalStateException.class, () ->
                new AccountBuilder().withAccountNumber("11111-1").withOwner("").build()
        );
    }

    @Test
    void defaultPremiumIsFalse() {
        AccountImpl account = (AccountImpl) new AccountBuilder()
                .withAccountNumber("22222-2")
                .withOwner("Eve")
                .build();

        assertFalse(account.isPremium());
    }
}
