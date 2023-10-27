package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner (System.in);

        System.out.println("What is your principal?");
        double principal = scanner.nextDouble();
        System.out.println("What is your interest rate?");
        double interestRate = scanner.nextDouble();
        System.out.println("Enter the length of your loan in years");
        double loanLength = scanner.nextDouble();
        double monthlyPayment = calculateMonthlyPayment(principal, interestRate, loanLength);
        System.out.printf("Your monthly payment is %.2f\n", monthlyPayment);
        double totalInterest= calculateTotalInterest(monthlyPayment, loanLength, principal);
        System.out.printf("Your total interest is %.2f", totalInterest);
    }

    public static double calculateMonthlyPayment(double principal, double interestRate, double years){
        double result;
        double loanLength = years * 12;
        double interestRateAsDecimal = (interestRate / 12) / 100;
        result = principal * ((interestRateAsDecimal * Math.pow( 1 + interestRateAsDecimal, loanLength)) / Math.pow(1 + interestRateAsDecimal, loanLength - 1));
        return result;
    }
    public static double calculateTotalInterest(double monthlyPayment,double years, double principal){
        double result;
        double loanLength = years * 12;
        result =(monthlyPayment*loanLength) - principal;
        return result;
    }
}