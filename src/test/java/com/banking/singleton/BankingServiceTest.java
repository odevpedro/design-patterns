package com.banking.singleton;

import com.banking.builder.AccountBuilder;
import com.banking.model.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankingServiceTest {

    @Test
    void getInstanceReturnsSameReference() {
        BankingService first = BankingService.getInstance();
        BankingService second = BankingService.getInstance();
        assertSame(first, second);
    }

    @Test
    void registerAndRetrieveAccount() {
        BankingService service = BankingService.getInstance();
        Account account = new AccountBuilder()
                .withAccountNumber("SING-001")
                .withOwner("Singleton User")
                .build();

        service.registerAccount(account);

        assertSame(account, service.getAccount("SING-001"));
    }

    @Test
    void getAccountReturnsNullForUnknown() {
        BankingService service = BankingService.getInstance();
        assertNull(service.getAccount("NONEXISTENT-999"));
    }

    @Test
    void transactionCountIncreasesOnCreditViaObserver() {
        BankingService service = BankingService.getInstance();
        Account account = new AccountBuilder()
                .withAccountNumber("SING-002")
                .withOwner("Observer User")
                .withInitialBalance(500.0)
                .build();
        account.addObserver(service);

        int before = service.getTransactionCount();
        account.credit(100.0);

        assertEquals(before + 1, service.getTransactionCount());
    }

    @Test
    void transactionCountIncreasesOnDebitViaObserver() {
        BankingService service = BankingService.getInstance();
        Account account = new AccountBuilder()
                .withAccountNumber("SING-003")
                .withOwner("Observer User 2")
                .withInitialBalance(500.0)
                .build();
        account.addObserver(service);

        int before = service.getTransactionCount();
        account.debit(50.0);

        assertEquals(before + 1, service.getTransactionCount());
    }

    @Test
    void accountCountIncreasesAfterRegistration() {
        BankingService service = BankingService.getInstance();
        int before = service.getAccountCount();

        Account account = new AccountBuilder()
                .withAccountNumber("SING-COUNT-" + before)
                .withOwner("Count User")
                .build();
        service.registerAccount(account);

        assertEquals(before + 1, service.getAccountCount());
    }
}
