package com.banking.demo;

import com.banking.builder.AccountBuilder;
import com.banking.model.Account;
import com.banking.proxy.AccountAccessProxy;

public class ProxyDemo {
    public static void main(String[] args) {
        System.out.println("=== Proxy Pattern Demo ===\n");

        Account base = new AccountBuilder()
                .withAccountNumber("22222-2")
                .withOwner("Bob")
                .withInitialBalance(500.0)
                .build();

        AccountAccessProxy proxy = new AccountAccessProxy(base);

        System.out.println("--- Operacao normal via proxy ---");
        proxy.credit(200.0);
        System.out.println("Balance: " + proxy.getBalance());

        System.out.println("\n--- Conta sendo congelada ---");
        proxy.freeze();

        try {
            proxy.debit(100.0);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        try {
            proxy.credit(50.0);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n--- Conta sendo descongelada ---");
        proxy.unfreeze();
        proxy.debit(100.0);
        System.out.println("Balance apos descongelamento + debito: " + proxy.getBalance());
    }
}
