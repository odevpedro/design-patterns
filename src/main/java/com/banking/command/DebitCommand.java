package com.banking.command;

import com.banking.model.Account;

public class DebitCommand implements BankingCommand {
    private final Account account;
    private final double amount;

    public DebitCommand(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() {
        account.debit(amount);
    }

    @Override
    public void undo() {
        account.credit(amount);
    }

    @Override
    public String getDescription() {
        return "DEBIT " + amount + " from account " + account.getAccountNumber();
    }
}
