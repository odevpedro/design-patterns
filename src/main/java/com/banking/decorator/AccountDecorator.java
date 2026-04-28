package com.banking.decorator;

import com.banking.model.Account;
import com.banking.observer.TransactionObserver;

public abstract class AccountDecorator implements Account {
    protected final Account wrapped;

    protected AccountDecorator(Account wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public String getAccountNumber() { return wrapped.getAccountNumber(); }
    @Override
    public String getOwner() { return wrapped.getOwner(); }
    @Override
    public double getBalance() { return wrapped.getBalance(); }
    @Override
    public void credit(double amount) { wrapped.credit(amount); }
    @Override
    public void debit(double amount) { wrapped.debit(amount); }
    @Override
    public void addObserver(TransactionObserver observer) { wrapped.addObserver(observer); }
    @Override
    public void removeObserver(TransactionObserver observer) { wrapped.removeObserver(observer); }
}
