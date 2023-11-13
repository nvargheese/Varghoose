package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

public class ContractFileManager {

    private static final String CONTRACTS_FILE_PATH = "src/main/resources/contract.csv";

    public static void saveContractsToFile(List<? extends Contract> contracts, boolean append) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CONTRACTS_FILE_PATH, append))) {
            if (!append) {
                writer.println("ContractType|VIN|Year|Make|Model|VehicleType|Color|Odometer|Price|DayOfContract|CustomerName|CustomerEmail|ExpectedEndingValue|LeaseFee|TotalPrice|MonthlyPayment");
            }
            for (Contract contract : contracts) {
                if (contract instanceof SalesContract) {
                    SalesContract salesContract = (SalesContract) contract;
                    Vehicle vehicle = salesContract.getVehicleSold();
                    writer.println(String.format("Sale|%d|%d|%s|%s|%s|%s|%d|%.2f|%s|%s|%s|%s|%.2f|%.2f|%.2f",
                            vehicle.getVin(),
                            vehicle.getYear(),
                            vehicle.getMake(),
                            vehicle.getModel(),
                            vehicle.getVehicleType(),
                            vehicle.getColor(),
                            vehicle.getOdometer(),
                            vehicle.getPrice(),
                            salesContract.getDateOfContract().toString(),
                            salesContract.getCustomerName(),
                            salesContract.getCustomerEmail(),
                            0.0,
                            0.0,
                            salesContract.getTotalPrice(),
                            salesContract.getMonthlyPayment()));
                } else if (contract instanceof LeaseContract) {
                    LeaseContract leaseContract = (LeaseContract) contract;
                    Vehicle vehicle = leaseContract.getVehicleSold();
                    writer.println(String.format("Lease|%d|%d|%s|%s|%s|%s|%d|%.2f|%s|%s|%s|%s|%.2f|%.2f",
                            vehicle.getVin(),
                            vehicle.getYear(),
                            vehicle.getMake(),
                            vehicle.getModel(),
                            vehicle.getVehicleType(),
                            vehicle.getColor(),
                            vehicle.getOdometer(),
                            vehicle.getPrice(),
                            leaseContract.getDateOfContract().toString(),
                            leaseContract.getCustomerName(),
                            leaseContract.getCustomerEmail(),
                            leaseContract.getExpectedEndingValue(),
                            leaseContract.getTotalPrice(),
                            leaseContract.getMonthlyPayment()));
                }
            }
            System.out.println("Contracts data has been saved successfully.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
