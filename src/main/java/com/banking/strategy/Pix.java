package com.banking.strategy;

public class Pix implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Generating QR Code...");
        System.out.println("Paid R$ " + amount + " with Pix.");
    }
}