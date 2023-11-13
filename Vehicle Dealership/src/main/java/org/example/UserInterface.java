package org.example;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;


public class UserInterface {
    public Dealership dealership;

    public UserInterface(Dealership dealership) {
        this.dealership = dealership;
    }

    public void display() {
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            System.out.printf("Welcome to %s, the best place to buy a car" , this.dealership.getName());
            System.out.println("Select from the following options:");
            System.out.println("0. Exit Program");
            System.out.println("1. View all Vehicles");
            System.out.println("2. Search for all vehicles by price");
            System.out.println("3. Search for vehicles by make/model");
            System.out.println("4. Search for vehicles by year");
            System.out.println("5. Search for vehicles by color");
            System.out.println("6. Search for vehicles by vehicle type");
            System.out.println("7. Add a vehicle");
            System.out.println("9. Remove a vehicle");

            int userInput = scanner.nextInt();

            switch (userInput) {

                case 0:
                    System.exit(0);
                    break;
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;

                case 8:

                    break;
                default:
                    System.out.println("Please select from options given!");
            }
            scanner.close();

        }
    }
    private void listAllVehicles() {
        List<Vehicle> allVehicles = dealership.getAllVehicles();
        displayFilteredVehicles(allVehicles);
    }
    private void displayFilteredVehicles(List<Vehicle> filteredVehicles) {
    }
    private void addVehicle() {
        System.out.println("Enter the following information");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter VIN number: ");
        int vin = scanner.nextInt();
        System.out.print("Enter vehicle's year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter vehicle's make: ");
        String make = scanner.nextLine();
        System.out.print("Enter vehicle's model: ");
        String model = scanner.nextLine();
        System.out.print("Enter vehicle type (Car, Truck, SUV, Van): ");
        String vehicleType = scanner.nextLine();
        System.out.print("Enter vehicle's color: ");
        String color = scanner.nextLine();
        System.out.print("Enter vehicle's odometer: ");
        int odometer = scanner.nextInt();
        System.out.print("Enter vehicle's price: ");
        double price = scanner.nextDouble();
        Vehicle newVehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(newVehicle);
        System.out.println("Vehicle added");
    }
    private void searchByPrice() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter min price");
        double minPrice = userInput.nextDouble();
        System.out.println("Enter max price");
        double maxPrice = userInput.nextDouble();
        List<Vehicle> filteredVehicles = dealership.getVehiclesByPrice(minPrice, maxPrice);
        displayFilteredVehicles(filteredVehicles);
    }
    private void searchByMakeModel() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter make");
        String make = userInput.nextLine();
        System.out.println("Enter model");
        String model = userInput.nextLine();
        List<Vehicle> filteredVehicles = dealership.getVehiclesByMakeModel(make, model);
        displayFilteredVehicles(filteredVehicles);
    }
    private void searchByYear() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter min year");
        int minYear = userInput.nextInt();
        System.out.println("Enter max year");
        int maxYear = userInput.nextInt();

        List<Vehicle> filteredVehicles = dealership.getVehiclesByYear(minYear, maxYear);
        displayFilteredVehicles(filteredVehicles);
    }
    private void searchByColor() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter vehicle color");
        String color = userInput.nextLine();
        List<Vehicle> filteredVehicles = dealership.getVehiclesByColor(color);
        displayFilteredVehicles(filteredVehicles);
    }
    private void searchByType() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter vehicle type");
        String type = userInput.nextLine();
        List<Vehicle> filteredVehicles = dealership.getVehiclesByType(type);
        displayFilteredVehicles(filteredVehicles);
    }
}