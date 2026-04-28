package com.banking.strategy;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class PaymentProcessorTest {

    @Test
    void payDelegatesToStrategy() {
        AtomicBoolean called = new AtomicBoolean(false);
        AtomicReference<Double> received = new AtomicReference<>();

        PaymentMethod testMethod = amount -> {
            called.set(true);
            received.set(amount);
        };

        new PaymentProcessor(testMethod).pay(250.0);

        assertTrue(called.get());
        assertEquals(250.0, received.get());
    }

    @Test
    void payWithNullStrategyThrows() {
        PaymentProcessor processor = new PaymentProcessor(null);
        assertThrows(IllegalStateException.class, () -> processor.pay(100.0));
    }

    @Test
    void pixStrategyDoesNotThrow() {
        assertDoesNotThrow(() -> new PaymentProcessor(new Pix()).pay(99.9));
    }

    @Test
    void creditCardStrategyDoesNotThrow() {
        assertDoesNotThrow(() -> new PaymentProcessor(new CreditCard()).pay(199.9));
    }

    @Test
    void boletoStrategyDoesNotThrow() {
        assertDoesNotThrow(() -> new PaymentProcessor(new Boleto()).pay(49.9));
    }
}
