package com.banking.command;

import com.banking.builder.AccountBuilder;
import com.banking.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    private Account alice;
    private Account bob;

    @BeforeEach
    void setUp() {
        alice = new AccountBuilder()
                .withAccountNumber("CMD-001")
                .withOwner("Alice")
                .withInitialBalance(1000.0)
                .build();
        bob = new AccountBuilder()
                .withAccountNumber("CMD-002")
                .withOwner("Bob")
                .withInitialBalance(500.0)
                .build();
    }

    // --- CreditCommand ---

    @Test
    void creditCommandExecuteIncreasesBalance() {
        new CreditCommand(alice, 300.0).execute();
        assertEquals(1300.0, alice.getBalance());
    }

    @Test
    void creditCommandUndoRestoresBalance() {
        CreditCommand cmd = new CreditCommand(alice, 300.0);
        cmd.execute();
        cmd.undo();
        assertEquals(1000.0, alice.getBalance());
    }

    @Test
    void creditCommandDescriptionContainsAmount() {
        CreditCommand cmd = new CreditCommand(alice, 300.0);
        assertTrue(cmd.getDescription().contains("300.0"));
        assertTrue(cmd.getDescription().contains("CMD-001"));
    }

    // --- DebitCommand ---

    @Test
    void debitCommandExecuteDecreasesBalance() {
        new DebitCommand(alice, 200.0).execute();
        assertEquals(800.0, alice.getBalance());
    }

    @Test
    void debitCommandUndoRestoresBalance() {
        DebitCommand cmd = new DebitCommand(alice, 200.0);
        cmd.execute();
        cmd.undo();
        assertEquals(1000.0, alice.getBalance());
    }

    @Test
    void debitCommandDescriptionContainsAmount() {
        DebitCommand cmd = new DebitCommand(alice, 200.0);
        assertTrue(cmd.getDescription().contains("200.0"));
        assertTrue(cmd.getDescription().contains("CMD-001"));
    }

    // --- TransferCommand ---

    @Test
    void transferCommandExecuteMovesBalance() {
        new TransferCommand(alice, bob, 400.0).execute();
        assertEquals(600.0, alice.getBalance());
        assertEquals(900.0, bob.getBalance());
    }

    @Test
    void transferCommandUndoRevertsTransfer() {
        TransferCommand cmd = new TransferCommand(alice, bob, 400.0);
        cmd.execute();
        cmd.undo();
        assertEquals(1000.0, alice.getBalance());
        assertEquals(500.0, bob.getBalance());
    }

    @Test
    void transferCommandDescriptionContainsBothAccounts() {
        TransferCommand cmd = new TransferCommand(alice, bob, 100.0);
        assertTrue(cmd.getDescription().contains("CMD-001"));
        assertTrue(cmd.getDescription().contains("CMD-002"));
    }

    // --- TransactionHistory ---

    @Test
    void historyExecuteRunsCommand() {
        TransactionHistory history = new TransactionHistory();
        history.execute(new CreditCommand(alice, 100.0));
        assertEquals(1100.0, alice.getBalance());
    }

    @Test
    void historyUndoRevertsLastCommand() {
        TransactionHistory history = new TransactionHistory();
        history.execute(new CreditCommand(alice, 100.0));
        history.undo();
        assertEquals(1000.0, alice.getBalance());
    }

    @Test
    void historyRedoReappliesUndoneCommand() {
        TransactionHistory history = new TransactionHistory();
        history.execute(new CreditCommand(alice, 100.0));
        history.undo();
        history.redo();
        assertEquals(1100.0, alice.getBalance());
    }

    @Test
    void historyUndoWhenEmptyDoesNotThrow() {
        TransactionHistory history = new TransactionHistory();
        assertDoesNotThrow(history::undo);
    }

    @Test
    void historyRedoWhenEmptyDoesNotThrow() {
        TransactionHistory history = new TransactionHistory();
        assertDoesNotThrow(history::redo);
    }

    @Test
    void historyNewExecuteClearsRedoStack() {
        TransactionHistory history = new TransactionHistory();
        history.execute(new CreditCommand(alice, 100.0));
        history.undo();
        history.execute(new DebitCommand(alice, 50.0));
        // redo should not re-apply the credit since a new command was issued
        history.redo();
        assertEquals(950.0, alice.getBalance());
    }

    @Test
    void historyGetHistoryReturnsExecutedDescriptions() {
        TransactionHistory history = new TransactionHistory();
        history.execute(new CreditCommand(alice, 100.0));
        history.execute(new DebitCommand(alice, 50.0));

        List<String> log = history.getHistory();
        assertEquals(2, log.size());
    }

    @Test
    void historyUndoMultipleCommandsInOrder() {
        TransactionHistory history = new TransactionHistory();
        history.execute(new CreditCommand(alice, 200.0));
        history.execute(new DebitCommand(alice, 100.0));
        history.undo();
        history.undo();
        assertEquals(1000.0, alice.getBalance());
    }
}
