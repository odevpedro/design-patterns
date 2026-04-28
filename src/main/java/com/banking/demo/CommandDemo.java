package com.banking.demo;

import com.banking.builder.AccountBuilder;
import com.banking.command.CreditCommand;
import com.banking.command.DebitCommand;
import com.banking.command.TransactionHistory;
import com.banking.command.TransferCommand;
import com.banking.model.Account;

public class CommandDemo {
    public static void main(String[] args) {
        System.out.println("=== Command Pattern Demo ===\n");

        Account alice = new AccountBuilder()
                .withAccountNumber("33333-3")
                .withOwner("Alice")
                .withInitialBalance(1000.0)
                .build();

        Account bob = new AccountBuilder()
                .withAccountNumber("44444-4")
                .withOwner("Bob")
                .withInitialBalance(500.0)
                .build();

        TransactionHistory history = new TransactionHistory();

        System.out.println("--- Executando comandos ---");
        history.execute(new CreditCommand(alice, 500.0));
        history.execute(new DebitCommand(alice, 200.0));
        history.execute(new TransferCommand(alice, bob, 300.0));

        System.out.println("\nBalance Alice: " + alice.getBalance());
        System.out.println("Balance Bob: " + bob.getBalance());

        System.out.println("\n--- Desfazendo ultimo comando (transferencia) ---");
        history.undo();

        System.out.println("Balance Alice apos undo: " + alice.getBalance());
        System.out.println("Balance Bob apos undo: " + bob.getBalance());

        System.out.println("\n--- Refazendo transferencia ---");
        history.redo();

        System.out.println("Balance Alice apos redo: " + alice.getBalance());
        System.out.println("Balance Bob apos redo: " + bob.getBalance());

        System.out.println("\n--- Historico de comandos ---");
        history.getHistory().forEach(d -> System.out.println("  " + d));
    }
}
