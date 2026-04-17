package com.banking.model;

import com.banking.observer.TransactionEvent;
import com.banking.observer.TransactionObserver;
import java.util.ArrayList;
import java.util.List;

public class AccountImpl implements Account {
    private final String accountNumber;
    private final String owner;
    private double balance;
    private final String document;
    private final String email;
    private final boolean premium;
    private final List<TransactionObserver> observers;

    public AccountImpl(String accountNumber, String owner, double initialBalance) {
        this(accountNumber, owner, initialBalance, null, null, false);
    }

    public AccountImpl(String accountNumber, String owner, double initialBalance,
               String document, String email, boolean premium) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = initialBalance;
        this.document = document;
        this.email = email;
        this.premium = premium;
        this.observers = new ArrayList<>();
    }

    @Override
    public String getAccountNumber() { return accountNumber; }
    @Override
    public String getOwner() { return owner; }
    @Override
    public double getBalance() { return balance; }
    public String getDocument() { return document; }
    public String getEmail() { return email; }
    public boolean isPremium() { return premium; }

    @Override
    public void credit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Credit amount must be positive");
        balance += amount;
        notifyObservers(new TransactionEvent("CREDIT", accountNumber, amount, balance));
    }

    @Override
    public void debit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Debit amount must be positive");
        if (amount > balance) throw new IllegalStateException("Insufficient funds");
        balance -= amount;
        notifyObservers(new TransactionEvent("DEBIT", accountNumber, amount, balance));
    }

    @Override
    public void addObserver(TransactionObserver observer) { observers.add(observer); }
    @Override
    public void removeObserver(TransactionObserver observer) { observers.remove(observer); }

    private void notifyObservers(TransactionEvent event) {
        for (TransactionObserver observer : observers) {
            observer.onTransaction(event);
        }
    }
}