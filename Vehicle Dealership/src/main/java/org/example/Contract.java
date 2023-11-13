package org.example;

import java.time.LocalDate;

public abstract class Contract {
    public Contract(LocalDate dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold) {
    }

    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();
}
