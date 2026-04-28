package com.banking.decorator;

import com.banking.model.Account;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggingAccountDecorator extends AccountDecorator {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LoggingAccountDecorator(Account wrapped) {
        super(wrapped);
    }

    @Override
    public void credit(double amount) {
        System.out.println("[LOG] " + now() + " | CREDIT | Account: " + getAccountNumber() + " | Amount: " + amount);
        wrapped.credit(amount);
        System.out.println("[LOG] New balance: " + getBalance());
    }

    @Override
    public void debit(double amount) {
        System.out.println("[LOG] " + now() + " | DEBIT  | Account: " + getAccountNumber() + " | Amount: " + amount);
        wrapped.debit(amount);
        System.out.println("[LOG] New balance: " + getBalance());
    }

    private String now() {
        return LocalDateTime.now().format(FORMATTER);
    }
}
