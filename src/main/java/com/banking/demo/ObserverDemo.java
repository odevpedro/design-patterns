package com.banking.demo;

import com.banking.model.Account;
import com.banking.factory.AccountFactory;
import com.banking.observer.TransactionObserver;
import com.banking.observer.TransactionEvent;

public class ObserverDemo {
    public static void main(String[] args) {
        Account account = AccountFactory.createStandardAccount("12345-6", "John Doe");

        account.addObserver(new TransactionObserver() {
            @Override
            public void onTransaction(TransactionEvent event) {
                System.out.println("[Log] Transaction: " + event.getType() +
                    " | Amount: " + event.getAmount());
            }
        });

        System.out.println("=== Observer Pattern Demo ===");
        System.out.println("Account: " + account.getAccountNumber());
        
        account.credit(100.0);
        account.debit(30.0);
        
        System.out.println("Final balance: " + account.getBalance());
    }
}