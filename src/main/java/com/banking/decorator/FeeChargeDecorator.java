package com.banking.decorator;

import com.banking.model.Account;

public class FeeChargeDecorator extends AccountDecorator {
    private final double feePercentage;

    public FeeChargeDecorator(Account wrapped, double feePercentage) {
        super(wrapped);
        this.feePercentage = feePercentage;
    }

    @Override
    public void debit(double amount) {
        double fee = amount * feePercentage / 100.0;
        System.out.printf("[FEE] Charging fee: %.2f (%.1f%%) on debit of %.2f%n", fee, feePercentage, amount);
        wrapped.debit(amount + fee);
    }
}
