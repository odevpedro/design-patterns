package com.banking.model;

import com.banking.observer.TransactionObserver;

public interface Account {
    String getAccountNumber();
    String getOwner();
    double getBalance();
    void credit(double amount);
    void debit(double amount);
    void addObserver(TransactionObserver observer);
    void removeObserver(TransactionObserver observer);
}