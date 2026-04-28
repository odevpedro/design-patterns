package com.banking.demo;

import com.banking.builder.AccountBuilder;
import com.banking.decorator.FeeChargeDecorator;
import com.banking.decorator.LoggingAccountDecorator;
import com.banking.model.Account;

public class DecoratorDemo {
    public static void main(String[] args) {
        System.out.println("=== Decorator Pattern Demo ===\n");

        Account base = new AccountBuilder()
                .withAccountNumber("11111-1")
                .withOwner("Alice")
                .withInitialBalance(1000.0)
                .build();

        System.out.println("--- Base account (sem decorators) ---");
        base.credit(200.0);
        base.debit(100.0);
        System.out.println("Balance: " + base.getBalance());

        System.out.println("\n--- Account com LoggingDecorator ---");
        Account logged = new LoggingAccountDecorator(base);
        logged.credit(300.0);
        logged.debit(50.0);

        System.out.println("\n--- Account com LoggingDecorator + FeeChargeDecorator (2%) ---");
        Account withFee = new FeeChargeDecorator(new LoggingAccountDecorator(base), 2.0);
        withFee.debit(100.0);

        System.out.println("\nBalance final: " + base.getBalance());
    }
}
