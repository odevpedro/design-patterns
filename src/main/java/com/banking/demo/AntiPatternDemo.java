package com.banking.demo;

public class AntiPatternDemo {
    public static void main(String[] args) {
        int paymentMethod = 2;
        double amount = 3.0;

        System.out.println("=== Without Strategy (Anti-Pattern) ===");
        
        if (paymentMethod == 1) {
            System.out.println("Validating credit card...");
            System.out.println("Paid R$ " + amount + " with credit card.");
        } else if (paymentMethod == 2) {
            System.out.println("Generating barcode...");
            System.out.println("Paid R$ " + amount + " with boleto.");
        } else if (paymentMethod == 3) {
            System.out.println("Generating QR Code...");
            System.out.println("Paid R$ " + amount + " with Pix.");
        } else {
            throw new IllegalStateException("Payment method not supported.");
        }
    }
}