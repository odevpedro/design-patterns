package com.banking.command;

import com.banking.model.Account;

public class TransferCommand implements BankingCommand {
    private final Account from;
    private final Account to;
    private final double amount;

    public TransferCommand(Account from, Account to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public void execute() {
        from.debit(amount);
        to.credit(amount);
    }

    @Override
    public void undo() {
        to.debit(amount);
        from.credit(amount);
    }

    @Override
    public String getDescription() {
        return "TRANSFER " + amount + " from " + from.getAccountNumber() + " to " + to.getAccountNumber();
    }
}
