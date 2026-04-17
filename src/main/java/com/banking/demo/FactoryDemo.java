package com.banking.demo;

import com.banking.factory.AccountFactory;
import com.banking.model.Account;

public class FactoryDemo {
    public static void main(String[] args) {
        AccountFactory factory = new AccountFactory();

        Account standard = factory.createAccount(
            AccountFactory.TYPE_STANDARD,
            "00001-1",
            "John Standard"
        );

        Account premium = factory.createAccount(
            AccountFactory.TYPE_PREMIUM,
            "00002-2",
            "John Premium"
        );

        Account savings = factory.createAccount(
            AccountFactory.TYPE_SAVINGS,
            "00003-3",
            "John Savings"
        );

        System.out.println("=== Factory Pattern Demo ===");
        System.out.println("Standard: " + standard.getAccountNumber() + " | Balance: " + standard.getBalance());
        System.out.println("Premium: " + premium.getAccountNumber() + " | Balance: " + premium.getBalance());
        System.out.println("Savings: " + savings.getAccountNumber() + " | Balance: " + savings.getBalance());

        Account quick = AccountFactory.createPremiumAccount("99999-9", "Quick User");
        System.out.println("Quick: " + quick.getAccountNumber() + " | Balance: " + quick.getBalance());
    }
}