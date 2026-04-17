package com.banking.strategy;

public class CreditCard implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Validating credit card...");
        System.out.println("Paid R$ " + amount + " with credit card.");
    }
}