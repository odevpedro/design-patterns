package com.banking.strategy;

public class PaymentProcessor {
    private PaymentMethod paymentMethod;

    public PaymentProcessor(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void pay(double amount) {
        if (paymentMethod == null)
            throw new IllegalStateException("Payment method not supported.");
        paymentMethod.pay(amount);
    }
}