package com.banking.demo;

import java.util.function.Consumer;

public class ModernStrategyDemo {
    public static void main(String[] args) {
        String paymentMethod = "BOLETO";
        
        System.out.println("=== Strategy Pattern Demo (Modern - Enum) ===");
        
        PaymentType.valueOf(paymentMethod).pay(2.0);
    }
}

class PaymentMethods {
    public static void creditCard(Double amount) {
        System.out.println("Validating credit card...");
        System.out.println("Paid R$ " + amount + " with credit card.");
    }
    public static void boleto(Double amount) {
        System.out.println("Generating barcode...");
        System.out.println("Paid R$ " + amount + " with boleto.");
    }
    public static void pix(Double amount) {
        System.out.println("Generating QR Code...");
        System.out.println("Paid R$ " + amount + " with Pix.");
    }
}

enum PaymentType {
    CREDIT_CARD(PaymentMethods::creditCard),
    BOLETO(PaymentMethods::boleto),
    PIX(PaymentMethods::pix);

    private Consumer<Double> paymentStrategy;

    PaymentType(Consumer<Double> paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void pay(Double amount) {
        paymentStrategy.accept(amount);
    }
}