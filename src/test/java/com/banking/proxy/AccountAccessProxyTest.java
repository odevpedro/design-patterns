package com.banking.proxy;

import com.banking.builder.AccountBuilder;
import com.banking.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountAccessProxyTest {

    private Account base;
    private AccountAccessProxy proxy;

    @BeforeEach
    void setUp() {
        base = new AccountBuilder()
                .withAccountNumber("PROXY-001")
                .withOwner("Proxy User")
                .withInitialBalance(500.0)
                .build();
        proxy = new AccountAccessProxy(base);
    }

    @Test
    void proxyCreditDelegatesToAccount() {
        proxy.credit(200.0);
        assertEquals(700.0, proxy.getBalance());
    }

    @Test
    void proxyDebitDelegatesToAccount() {
        proxy.debit(100.0);
        assertEquals(400.0, proxy.getBalance());
    }

    @Test
    void frozenAccountBlocksDebit() {
        proxy.freeze();
        assertThrows(IllegalStateException.class, () -> proxy.debit(100.0));
    }

    @Test
    void frozenAccountBlocksCredit() {
        proxy.freeze();
        assertThrows(IllegalStateException.class, () -> proxy.credit(100.0));
    }

    @Test
    void balanceUnchangedWhenFrozenOperationBlocked() {
        proxy.freeze();
        try { proxy.debit(100.0); } catch (IllegalStateException ignored) {}
        assertEquals(500.0, proxy.getBalance());
    }

    @Test
    void unfreezeAllowsDebit() {
        proxy.freeze();
        proxy.unfreeze();
        assertDoesNotThrow(() -> proxy.debit(100.0));
        assertEquals(400.0, proxy.getBalance());
    }

    @Test
    void isFrozenReflectsStateAfterFreeze() {
        proxy.freeze();
        assertTrue(proxy.isFrozen());
    }

    @Test
    void isFrozenReflectsStateAfterUnfreeze() {
        proxy.freeze();
        proxy.unfreeze();
        assertFalse(proxy.isFrozen());
    }

    @Test
    void proxyExposesAccountNumberAndOwner() {
        assertEquals("PROXY-001", proxy.getAccountNumber());
        assertEquals("Proxy User", proxy.getOwner());
    }

    @Test
    void notFrozenByDefault() {
        assertFalse(proxy.isFrozen());
    }
}
