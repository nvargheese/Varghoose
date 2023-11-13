package org.example;

import java.time.LocalDate;

public class SalesContract extends Contract {

    private boolean isFinanced;

    public SalesContract(LocalDate dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold, boolean isFinanced) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.isFinanced = isFinanced;
    }

    public boolean isFinanced() {
        return isFinanced;
    }

    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }

    @Override
    public double getTotalPrice() {
        double basePrice = getVehicleSold().getPrice();
        double salesTax = basePrice * 0.05;
        double recordingFee = 100;
        double processingFee;

        if (basePrice < 10000) {
            processingFee = 295;
        } else {
            processingFee = 495;
        }

        if (!isFinanced) {
            return basePrice + salesTax + recordingFee + processingFee;
        } else {
            double interestRate;
            int loanLength;

            if (basePrice >= 10000) {
                interestRate = 0.0425;
                loanLength = 48;
            } else {
                interestRate = 0.0525;
                loanLength = 24;
            }

            double monthlyPayment = calculateMonthlyPayment(getTotalPrice(), interestRate, loanLength);
            double interestPaid = calculateInterestPaid(getTotalPrice(), monthlyPayment, loanLength);

            return getTotalPrice() + interestPaid;
        }
    }

    @Override
    public double getMonthlyPayment() {
        if (isFinanced) {
            double interestRate;
            int loanLength;

            if (getTotalPrice() >= 10000) {
                interestRate = 0.0425;
                loanLength = 48;
            } else {
                interestRate = 0.0525;
                loanLength = 24;
            }

            return calculateMonthlyPayment(getTotalPrice(), interestRate, loanLength);
        } else {
            return 0;
        }
    }

    public double getSalesTax() {
        double basePrice = getVehicleSold().getPrice();
        return basePrice * 0.05;
    }

    public double getRecordingFee() {
        return 100;
    }

    public double getProcessingFee() {
        double basePrice = getVehicleSold().getPrice();

        double processingFee;

        if (basePrice < 10000) {
            processingFee = 295;
        } else {
            processingFee = 495;
        }

        return processingFee;
    }

    public double getInterestRate() {
        double basePrice = getVehicleSold().getPrice();

        if (basePrice >= 10000) {
            return 0.0425;
        } else {
            return 0.0525;
        }
    }

    public int getLoanLength() {
        if (getVehicleSold().getPrice() >= 10000) {
            return 48;
        } else {
            return 24;
        }
    }

    private double calculateMonthlyPayment(double principal, double interestRate, int loanLength) {
        if (interestRate == 0) {
            return 0;
        }

        double monthlyInterestRate = interestRate / 12;
        double denominator = 1;

        for (int i = 0; i < loanLength; i++) {
            denominator *= (1 + monthlyInterestRate);
        }

        return (principal * monthlyInterestRate) / (1 - (1 / denominator));
    }

    private double calculateInterestPaid(double principal, double monthlyPayment, int loanLength) {
        return (monthlyPayment * loanLength) - principal;
    }

    public Vehicle getVehicleSold() {
        return null;
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