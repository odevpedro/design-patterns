package com.banking.strategy;

public class Boleto implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Generating barcode...");
        System.out.println("Paid R$ " + amount + " with boleto.");
    }
}