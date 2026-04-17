package com.banking.demo;

import com.banking.singleton.BankingService;
import com.banking.factory.AccountFactory;
import com.banking.model.Account;

public class SingletonDemo {
    public static void main(String[] args) {
        BankingService service = BankingService.getInstance();

        Account acc1 = AccountFactory.createStandardAccount("11111-1", "User One");
        Account acc2 = AccountFactory.createStandardAccount("22222-2", "User Two");

        acc1.addObserver(service);
        acc2.addObserver(service);

        service.registerAccount(acc1);
        service.registerAccount(acc2);

        System.out.println("=== Singleton Pattern Demo ===");
        System.out.println("Accounts registered: " + service.getAccountCount());

        acc1.credit(100.0);
        acc2.credit(200.0);
        acc1.debit(30.0);

        System.out.println("Total transactions: " + service.getTransactionCount());

        BankingService service2 = BankingService.getInstance();
        System.out.println("Same instance? " + (service == service2));
    }
}