package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import static org.example.Calendar.*;

public class FinancialTracker {
    public static void main(String[] args) throws IOException {
        try {
            File file = new File("src/main/resources/transactions.csv");
            boolean fileExists = file.exists();
            if (!fileExists) {
                FileWriter newTransactionWriter = new FileWriter("src/main/resources/transactions.csv");
                newTransactionWriter.write("Date|Time|Description|Vendor|Amount\n");
                newTransactionWriter.close();
            }
        } catch (IOException ex) {
            System.out.println("Could not find that path!");
        }
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/transactions.csv");
            Scanner scanner = new Scanner(fileInputStream);
            boolean input;
            while (scanner.hasNextLine()) {
                input = scanner.hasNextLine();
                String line = scanner.nextLine();
                fileInputStream.close();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find that file.");

        } catch (IOException ex) {
            System.out.println("Error occurred reading the file");
        }
        Scanner scanner = new Scanner(System.in);
        double accountBalance = 0.0;
        List<Transaction> ledger = new ArrayList<>();
        while (true) {
            System.out.println("Make A Selection:");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Enter Your Choice: ");
            String choice = scanner.nextLine().toUpperCase();
            switch (choice) {
                case "D":
                    System.out.println("You selected 'Add Deposit'.");
                    System.out.print("Enter the deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine();
                    if (depositAmount < 0) {
                        System.out.println("Invalid deposit amount. Enter a positive amount.");
                    } else {
                        accountBalance += depositAmount;
                        System.out.println("Deposit of $" + depositAmount + " added. Your new balance is: $" + accountBalance);
                        System.out.print("Enter date (YYYY-MM-DD: ");
                        String date = scanner.nextLine();
                        System.out.print("Enter time (HH:MM): ");
                        String time = scanner.nextLine();
                        System.out.print("Enter description: ");
                        String description = scanner.nextLine();
                        System.out.print("Enter vendor: ");
                        String vendor = scanner.nextLine();
                        Transaction newTransaction = new Transaction(date, time, description, vendor, depositAmount);
                        ledger.add(newTransaction);
                        saveTransactions(ledger);
                        System.out.println("Deposit added. \n");
                    }
                    break;
                case "P":
                    System.out.println("You selected 'Make Payment'.");
                    System.out.println("Enter your payment amount");
                    double paymentAmount = scanner.nextDouble();
                    scanner.nextLine();
                    if (paymentAmount < 0) {
                        System.out.println("Invalid payment amount. Enter a positive amount");
                    } else if (paymentAmount > accountBalance) {
                        System.out.println("Insufficient funds. Your current balance is: $" + accountBalance);
                    } else {
                        accountBalance -= paymentAmount;
                        System.out.println("Payment of $" + paymentAmount + " made. Your new balance is: $" + accountBalance);
                        System.out.print("Enter the date (YYYY-MM-DD): ");
                        String date = scanner.nextLine();
                        System.out.print("Enter the time (HH:MM): ");
                        String time = scanner.nextLine();
                        System.out.print("Enter the description: ");
                        String description = scanner.nextLine();
                        System.out.print("Enter the vendor: ");
                        String vendor = scanner.nextLine();
                        Transaction newTransaction = new Transaction(date, time, description, vendor, -paymentAmount);
                        ledger.add(newTransaction);
                        saveTransactions(ledger);
                        System.out.println("Deposit added. \n");
                    }
                    break;
                case "L":
                    System.out.println("You selected 'Ledger'.");
                    displayLedger(ledger);
                    break;
                case "X":
                    System.out.println("Exiting application");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    public static void displayLedger(List<Transaction> ledger) {
        System.out.println("Make A Ledger Selection: ");
        System.out.println("A) Display All Entries");
        System.out.println("D) Deposits");
        System.out.println("P) Payments");
        System.out.println("R) Reports");
        System.out.println("H) Return to Home");
        System.out.println("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        String selection = scanner.nextLine().toUpperCase();
        switch (selection) {
            case "A":
                System.out.println("All Entries:");
                for (Transaction entry : ledger) {
                    System.out.println(entry.getTime() + " - Amount: $" + entry.getAmount() + " - Description: " + entry.getDescription() + " - Vendor: " + entry.getVendor() + " - Date: " + entry.getDate());
                }
                break;
            case "D":
                System.out.println("Your Deposits:");
                for (Transaction entry : ledger) {
                    if (entry.getAmount() > 0) {
                        System.out.println(entry.getTime() + " - Amount: $" + entry.getAmount() + " - Description: " + entry.getDescription() + " - Vendor: " + entry.getVendor() + " - Date: " + entry.getDate());
                    }
                }
                break;
            case "P":
                System.out.println("Your payments:");
                for (Transaction entry : ledger) {
                    if (entry.getAmount() < 0) {
                        System.out.println(entry.getTime() + " - Amount: $" + entry.getAmount() + " - Description: " + entry.getDescription() + " - Vendor: " + entry.getVendor() + " - Date: " + entry.getDate());
                    }
                }
                break;
            case "R":
                System.out.println("Your reports:");
                runReports(ledger);
                break;
            case "H":
                System.out.println("You are returning to the home screen.");
                break;
            default:
                System.out.println("Invalid choice. Try choosing from options provided.");
                break;
        }
    }
    public static void runReports(List<Transaction> ledger) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Select A Reports Option:");
            System.out.println("1) Month to date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year to date");
            System.out.println("4) Previous year");
            System.out.println("5) Search by vendor");
            System.out.println("0) Go back to report page");
            System.out.println("Please select from provided options: ");
            String reportChoice = scanner.nextLine();
            switch (reportChoice) {
                case "1":
                    double monthToDate = calculateMonthToDate(ledger);
                    System.out.println("Month to date deposit: $" + monthToDate);
                    break;
                case "2":
                    double previousMonthDeposit = calculatePreviousMonth(ledger);
                    System.out.println("Previous month deposit: $" + previousMonthDeposit);
                    break;
                case "3":
                    double yearToDateDeposit = calculateYearToDate(ledger);
                    System.out.println("Year to date deposit: $" + yearToDateDeposit);
                    break;
                case "4":
                    double previousYearDeposit = calculatePreviousYear(ledger);
                    System.out.println("Previous year deposit: $" + previousYearDeposit);
                    break;
                case "5":
                    searchByVendor(ledger);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid. Select from options provided.");
                    break;
            }
        }
    }
    private static void searchByVendor(List<Transaction> ledger) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the vendor name to search by: ");
        String vendorName = scanner.nextLine();
        boolean found = false;
        System.out.println("Transactions by vendor: " + vendorName);
        for (Transaction entry : ledger) {
            if (entry.getVendor().equalsIgnoreCase(vendorName)) {
                System.out.println(entry.getTime() + " - Amount: $" + entry.getAmount() + " - Description: " + entry.getDescription() + " - Vendor: " + entry.getVendor() + " - Date: " + entry.getDate());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No transactions found for vendor: " + vendorName);
        }
    }
    private static void displayTransactions(List<Transaction> ledger) {
        System.out.println("List of Transactions:");

        if (ledger.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction transaction : ledger) {
                System.out.println("Date: " + transaction.getDate());
                System.out.println("Time: " + transaction.getTime());
                System.out.println("Description: " + transaction.getDescription());
                System.out.println("Vendor: " + transaction.getVendor());
                System.out.println("Amount: $" + transaction.getAmount());
                System.out.println();
            }
        }
    }
    private static void saveTransactions(List<Transaction> ledger) throws IOException {
        try {
            File file = new File("src/main/resources/transactions.csv");

            boolean fileExists = file.exists();
            if (!fileExists) {
                FileWriter newTransactionWriter = new FileWriter("src/main/resources/transactions.csv");
                newTransactionWriter.write("Date|Time|Description|Vendor|Amount\n");
                newTransactionWriter.close();
            }
            FileWriter appendTransactionWriter = new FileWriter("src/main/resources/transactions.csv", true);
            for (Transaction transaction : ledger) {
                appendTransactionWriter.write(transaction.getDate() + "|" + transaction.getTime() + "|" + transaction.getDescription() + "|" + transaction.getVendor() + "|" + transaction.getAmount() + "\n");
            }

            appendTransactionWriter.close();

        } catch (IOException ex) {
            System.out.println("Could not find that path!");
        }

    }
}