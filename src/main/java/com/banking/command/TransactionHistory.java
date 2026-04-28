package com.banking.command;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TransactionHistory {
    private final Deque<BankingCommand> history = new ArrayDeque<>();
    private final Deque<BankingCommand> undone = new ArrayDeque<>();

    public void execute(BankingCommand command) {
        command.execute();
        history.push(command);
        undone.clear();
        System.out.println("[HISTORY] Executed: " + command.getDescription());
    }

    public void undo() {
        if (history.isEmpty()) {
            System.out.println("[HISTORY] Nothing to undo.");
            return;
        }
        BankingCommand command = history.pop();
        command.undo();
        undone.push(command);
        System.out.println("[HISTORY] Undone: " + command.getDescription());
    }

    public void redo() {
        if (undone.isEmpty()) {
            System.out.println("[HISTORY] Nothing to redo.");
            return;
        }
        BankingCommand command = undone.pop();
        command.execute();
        history.push(command);
        System.out.println("[HISTORY] Redone: " + command.getDescription());
    }

    public List<String> getHistory() {
        List<String> result = new ArrayList<>();
        for (BankingCommand cmd : history) {
            result.add(cmd.getDescription());
        }
        return result;
    }
}
