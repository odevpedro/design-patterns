package com.banking.command;

import com.banking.model.Account;

public class CreditCommand implements BankingCommand {
    private final Account account;
    private final double amount;

    public CreditCommand(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() {
        account.credit(amount);
    }

    @Override
    public void undo() {
        account.debit(amount);
    }

    @Override
    public String getDescription() {
        return "CREDIT " + amount + " to account " + account.getAccountNumber();
    }
}
