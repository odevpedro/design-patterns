package com.banking.demo;

import com.banking.strategy.*;

public class StrategyDemo {
    public static void main(String[] args) {
        System.out.println("=== Strategy Pattern Demo (Classic) ===");
        
        var creditCard = new CreditCard();
        var boleto = new Boleto();
        var pix = new Pix();
        
        var processor = new PaymentProcessor(creditCard);
        processor.pay(100.0);
        
        processor = new PaymentProcessor(boleto);
        processor.pay(50.0);
        
        processor = new PaymentProcessor(pix);
        processor.pay(25.0);
    }
}