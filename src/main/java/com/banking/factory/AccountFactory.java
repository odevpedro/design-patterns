package com.banking.factory;

import com.banking.builder.AccountBuilder;
import com.banking.model.Account;

public class AccountFactory {
    public static final String TYPE_STANDARD = "STANDARD";
    public static final String TYPE_PREMIUM = "PREMIUM";
    public static final String TYPE_SAVINGS = "SAVINGS";

    public Account createAccount(String type, String accountNumber, String owner) {
        return switch (type) {
            case TYPE_PREMIUM -> new AccountBuilder()
                .withAccountNumber(accountNumber)
                .withOwner(owner)
                .withInitialBalance(5000.0)
                .withPremium(true)
                .build();
            case TYPE_SAVINGS -> new AccountBuilder()
                .withAccountNumber(accountNumber)
                .withOwner(owner)
                .withInitialBalance(100.0)
                .withPremium(false)
                .build();
            case TYPE_STANDARD -> new AccountBuilder()
                .withAccountNumber(accountNumber)
                .withOwner(owner)
                .withInitialBalance(0.0)
                .withPremium(false)
                .build();
            default -> throw new IllegalArgumentException("Unknown account type: " + type);
        };
    }

    public static Account createStandardAccount(String accountNumber, String owner) {
        return new AccountBuilder()
            .withAccountNumber(accountNumber)
            .withOwner(owner)
            .withInitialBalance(0.0)
            .withPremium(false)
            .build();
    }

    public static Account createPremiumAccount(String accountNumber, String owner) {
        return new AccountBuilder()
            .withAccountNumber(accountNumber)
            .withOwner(owner)
            .withInitialBalance(5000.0)
            .withPremium(true)
            .build();
    }

    public static Account createSavingsAccount(String accountNumber, String owner) {
        return new AccountBuilder()
            .withAccountNumber(accountNumber)
            .withOwner(owner)
            .withInitialBalance(100.0)
            .withPremium(false)
            .build();
    }
}