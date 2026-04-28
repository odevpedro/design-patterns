package com.banking.proxy;

import com.banking.model.Account;
import com.banking.observer.TransactionObserver;

public class AccountAccessProxy implements Account {
    private final Account account;
    private boolean frozen;

    public AccountAccessProxy(Account account) {
        this.account = account;
        this.frozen = false;
    }

    public void freeze() {
        this.frozen = true;
        System.out.println("[PROXY] Account " + account.getAccountNumber() + " has been frozen.");
    }

    public void unfreeze() {
        this.frozen = false;
        System.out.println("[PROXY] Account " + account.getAccountNumber() + " has been unfrozen.");
    }

    public boolean isFrozen() { return frozen; }

    @Override
    public String getAccountNumber() { return account.getAccountNumber(); }
    @Override
    public String getOwner() { return account.getOwner(); }
    @Override
    public double getBalance() { return account.getBalance(); }

    @Override
    public void credit(double amount) {
        checkAccess("CREDIT");
        account.credit(amount);
    }

    @Override
    public void debit(double amount) {
        checkAccess("DEBIT");
        account.debit(amount);
    }

    @Override
    public void addObserver(TransactionObserver observer) { account.addObserver(observer); }
    @Override
    public void removeObserver(TransactionObserver observer) { account.removeObserver(observer); }

    private void checkAccess(String operation) {
        if (frozen) {
            throw new IllegalStateException("[PROXY] Operation " + operation +
                " denied: account " + account.getAccountNumber() + " is frozen.");
        }
    }
}
