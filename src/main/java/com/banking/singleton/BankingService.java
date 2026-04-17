package com.banking.singleton;

import com.banking.model.Account;
import com.banking.observer.TransactionObserver;
import com.banking.observer.TransactionEvent;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BankingService implements TransactionObserver {
    private static BankingService instance;
    private final Map<String, Account> accounts;
    private int transactionCount;

    private BankingService() {
        this.accounts = new ConcurrentHashMap<>();
        this.transactionCount = 0;
    }

    public static synchronized BankingService getInstance() {
        if (instance == null) {
            instance = new BankingService();
        }
        return instance;
    }

    public void registerAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public int getAccountCount() { return accounts.size(); }
    public int getTransactionCount() { return transactionCount; }

    @Override
    public void onTransaction(TransactionEvent event) {
        transactionCount++;
        System.out.println("[BankingService] Transaction logged: " + event.getType() +
            " | Account: " + event.getAccountNumber() +
            " | Amount: " + event.getAmount() +
            " | Total transactions: " + transactionCount);
    }
}