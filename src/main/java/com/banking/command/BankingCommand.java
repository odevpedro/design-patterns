package com.banking.command;

public interface BankingCommand {
    void execute();
    void undo();
    String getDescription();
}
