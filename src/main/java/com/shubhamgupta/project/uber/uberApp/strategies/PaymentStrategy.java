package com.shubhamgupta.project.uber.uberApp.strategies;

import com.shubhamgupta.project.uber.uberApp.entities.Payment;

public interface PaymentStrategy {
    static final double PLATFORM_COMMISSION = 0.3;
    void processPayment(Payment payment);
}
