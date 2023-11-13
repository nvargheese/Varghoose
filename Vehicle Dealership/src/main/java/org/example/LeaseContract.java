package org.example;

import java.time.LocalDate;

public class LeaseContract extends Contract {
    public LeaseContract(LocalDate dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
    }
    @Override
    public double getTotalPrice() {
        double vehiclePrice = getVehicleSold().getPrice();
        double leaseFee = 0.07 * vehiclePrice;
        return vehiclePrice + leaseFee;
    }
    @Override
    public double getMonthlyPayment() {
        double vehiclePrice = getVehicleSold().getPrice();
        double expectedEndingValue = getExpectedEndingValue();
        double leaseFee = 0.07 * vehiclePrice;
        double monthlyInterestRate = 0.04 / 12;

        int numberOfPayments = 36;

        double numerator = (vehiclePrice - expectedEndingValue + leaseFee) * monthlyInterestRate;
        double denominator = 1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments);

        return numerator / denominator;
    }

    double getExpectedEndingValue() {
        return 0.5 * getVehicleSold().getPrice();
    }

    public Vehicle getVehicleSold() {
    }

    public Object getDateOfContract() {
        return null;
    }

    public Object getCustomerName() {
        return null;
    }

    public Object getCustomerEmail() {
        return null;
    }
}
