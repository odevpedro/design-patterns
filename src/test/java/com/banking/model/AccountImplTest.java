package com.banking.model;

import com.banking.observer.TransactionEvent;
import com.banking.observer.TransactionObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountImplTest {

    private AccountImpl account;

    @BeforeEach
    void setUp() {
        account = new AccountImpl("00001-1", "Alice", 500.0);
    }

    @Test
    void creditIncreasesBalance() {
        account.credit(200.0);
        assertEquals(700.0, account.getBalance());
    }

    @Test
    void debitDecreasesBalance() {
        account.debit(100.0);
        assertEquals(400.0, account.getBalance());
    }

    @Test
    void creditWithNegativeAmountThrows() {
        assertThrows(IllegalArgumentException.class, () -> account.credit(-50.0));
    }

    @Test
    void creditWithZeroAmountThrows() {
        assertThrows(IllegalArgumentException.class, () -> account.credit(0.0));
    }

    @Test
    void debitWithNegativeAmountThrows() {
        assertThrows(IllegalArgumentException.class, () -> account.debit(-50.0));
    }

    @Test
    void debitWithZeroAmountThrows() {
        assertThrows(IllegalArgumentException.class, () -> account.debit(0.0));
    }

    @Test
    void debitExceedingBalanceThrows() {
        assertThrows(IllegalStateException.class, () -> account.debit(1000.0));
    }

    @Test
    void getAccountNumberReturnsCorrectValue() {
        assertEquals("00001-1", account.getAccountNumber());
    }

    @Test
    void getOwnerReturnsCorrectValue() {
        assertEquals("Alice", account.getOwner());
    }

    @Test
    void observerIsNotifiedOnCredit() {
        List<TransactionEvent> received = new ArrayList<>();
        account.addObserver(received::add);

        account.credit(100.0);

        assertEquals(1, received.size());
        assertEquals("CREDIT", received.get(0).getType());
        assertEquals(100.0, received.get(0).getAmount());
        assertEquals(600.0, received.get(0).getBalanceAfter());
    }

    @Test
    void observerIsNotifiedOnDebit() {
        List<TransactionEvent> received = new ArrayList<>();
        account.addObserver(received::add);

        account.debit(50.0);

        assertEquals(1, received.size());
        assertEquals("DEBIT", received.get(0).getType());
        assertEquals(50.0, received.get(0).getAmount());
        assertEquals(450.0, received.get(0).getBalanceAfter());
    }

    @Test
    void removedObserverIsNotNotified() {
        List<TransactionEvent> received = new ArrayList<>();
        TransactionObserver observer = received::add;

        account.addObserver(observer);
        account.removeObserver(observer);
        account.credit(100.0);

        assertEquals(0, received.size());
    }

    @Test
    void multipleObserversAreAllNotified() {
        List<TransactionEvent> first = new ArrayList<>();
        List<TransactionEvent> second = new ArrayList<>();

        account.addObserver(first::add);
        account.addObserver(second::add);
        account.credit(100.0);

        assertEquals(1, first.size());
        assertEquals(1, second.size());
    }
}
