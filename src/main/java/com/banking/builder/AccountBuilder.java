package com.banking.builder;

import com.banking.model.Account;
import com.banking.model.AccountImpl;

public class AccountBuilder {
    private String accountNumber;
    private String owner;
    private double initialBalance;
    private String document;
    private String email;
    private boolean premium;

    public AccountBuilder() {}

    public AccountBuilder withAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public AccountBuilder withOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public AccountBuilder withInitialBalance(double initialBalance) {
        this.initialBalance = initialBalance;
        return this;
    }

    public AccountBuilder withDocument(String document) {
        this.document = document;
        return this;
    }

    public AccountBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public AccountBuilder withPremium(boolean premium) {
        this.premium = premium;
        return this;
    }

    public Account build() {
        if (accountNumber == null || accountNumber.isEmpty()) {
            throw new IllegalStateException("Account number is required");
        }
        if (owner == null || owner.isEmpty()) {
            throw new IllegalStateException("Owner is required");
        }
        return new AccountImpl(accountNumber, owner, initialBalance, document, email, premium);
    }
}