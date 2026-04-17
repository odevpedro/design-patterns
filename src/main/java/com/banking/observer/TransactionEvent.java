package com.banking.observer;

public class TransactionEvent {
    private final String type;
    private final String accountNumber;
    private final double amount;
    private final double balanceAfter;

    public TransactionEvent(String type, String accountNumber, double amount, double balanceAfter) {
        this.type = type;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
    }

    public String getType() { return type; }
    public String getAccountNumber() { return accountNumber; }
    public double getAmount() { return amount; }
    public double getBalanceAfter() { return balanceAfter; }
}