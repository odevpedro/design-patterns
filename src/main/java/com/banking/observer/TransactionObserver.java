package com.banking.observer;

public interface TransactionObserver {
    void onTransaction(TransactionEvent event);
}