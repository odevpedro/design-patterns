package com.banking.demo;

import com.banking.builder.AccountBuilder;
import com.banking.model.Account;
import com.banking.model.AccountImpl;

public class BuilderDemo {
    public static void main(String[] args) {
        Account fullAccount = new AccountBuilder()
            .withAccountNumber("12345-6")
            .withOwner("John Doe")
            .withInitialBalance(1000.0)
            .withDocument("123.456.789-00")
            .withEmail("john@example.com")
            .withPremium(true)
            .build();

        Account simpleAccount = new AccountBuilder()
            .withAccountNumber("99999-9")
            .withOwner("Jane Doe")
            .build();

        System.out.println("=== Builder Pattern Demo ===");
        System.out.println("Full account: " + fullAccount.getAccountNumber() +
            " | Balance: " + fullAccount.getBalance() +
            " | Premium: " + ((AccountImpl)fullAccount).isPremium());
        System.out.println("Simple account: " + simpleAccount.getAccountNumber() +
            " | Balance: " + simpleAccount.getBalance());
    }
}