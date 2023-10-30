package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Book[] bookArray = new Book[20];

        bookArray[0] = new Book(1, "11111", "Percy Jackson and The Lightning Thief", false, "");
        bookArray[1] = new Book(2, "22222", "Percy Jackson and The Sea of Monsters", false, "");
        bookArray[2] = new Book(3, "33333", "Percy Jackson and The Titan's Curse", false, "");
        bookArray[3] = new Book(4, "44444", "Percy Jackson and The Battle of the Labyrinth", false, "");
        bookArray[4] = new Book(5, "55555", "Percy Jackson and The Last Olympian", false, "");
        bookArray[5] = new Book(6, "66666", "The Lost Hero", false, "");
        bookArray[6] = new Book(7, "77777", "The Son of Neptune", false, "");
        bookArray[7] = new Book(8, "88888", "The Mark of Athena", false, "");
        bookArray[8] = new Book(9, "99999", "The House of Hades", false, "");
        bookArray[9] = new Book(10, "10101", "The Blood of Olympus", false, "");
        bookArray[10] = new Book(11, "11112", "Magnus Chase and the Sword of Summer", false, "");
        bookArray[11] = new Book(12, "12121", "Magnus Chase and the Hammer of Thor", false, "");
        bookArray[12] = new Book(13, "13131", "Magnus Chase and the Ship of the Dead", false, "");
        bookArray[13] = new Book(14, "14141", "Animal Farm", false, "");
        bookArray[14] = new Book(15, "15151", "The Great Gatsby", false, "");
        bookArray[15] = new Book(16, "16161", "Fahrenheit 451", false, "");
        bookArray[16] = new Book(17, "17171", "Moby Dick", false, "");
        bookArray[17] = new Book(18, "18181", "The Hobbit", false, "");
        bookArray[18] = new Book(19, "19191", "The Crucible", false, "");
        bookArray[19] = new Book(20, "20202", "The Hate U Give", false, "");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Neighborhood Library");
            System.out.println("Please select an option:");
            System.out.println("1) Show available books");
            System.out.println("2) Show checked out books");
            System.out.println("3) Exit");
            int userSelection = scanner.nextInt();

            switch (userSelection) {
                case 1:
                    displayAvailableBooks(bookArray); // Pass the bookArray to the method
                    break;
                case 2:
                    displayCheckedOutBooks(bookArray); // Pass the bookArray to the method
                    break;
                case 3:
                    System.out.println("Have a great day!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid. Please select option 1, 2, or 3");
                    break;
            }
        }
    }

    public static void displayAvailableBooks(Book[] books) { // Pass the bookArray as a parameter
        Scanner scanner = new Scanner(System.in);

        while (true) {
            boolean availBooks = false;

            for (Book book : books) { // Iterate over the bookArray
                if (book != null && !book.isCheckedOut()) { // Check for null and availability
                    System.out.println("ID: " + book.getBookId() + ", Title: " + book.getBookTitle());
                    availBooks = true;
                }
            }

            if (!availBooks) {
                System.out.println("Sorry, all books are currently checked out");
                break;
            }

            System.out.println("Enter your book selection here by typing the title: ");
            System.out.println("Or enter 0 to exit back to the home screen");
            String titleOfBook = scanner.nextLine();

            if (titleOfBook.equals("0")) {
                break;
            }

            boolean bookFound = false;
            for (Book book : books) {
                if (book != null && book.getBookTitle().equalsIgnoreCase(titleOfBook) && !book.isCheckedOut()) {
                    System.out.println("Please enter your name to check out this book:");
                    String readersName = scanner.nextLine();

                    book.checkOut(readersName);
                    System.out.println("Thank you " + readersName + ", your book has been successfully checked out");
                    bookFound = true;
                    break;
                }
            }

            if (!bookFound) {
                System.out.println("Book not found");
            }
        }
    }

    public static void displayCheckedOutBooks(Book[] books) { // Pass the bookArray as a parameter
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            boolean checkedOutBooks = false;

            for (Book book : books) { // Iterate over the bookArray
                if (book != null && book.isCheckedOut()) { // Check for null and checked out status
                    System.out.println("ID: " + book.getBookId() + ", Title: " + book.getBookTitle() + ", is checked out by " + book.getCheckedOutTo());
                    checkedOutBooks = true;
                }
            }

            if (!checkedOutBooks) {
                System.out.println("You haven't checked out a book");
                break;
            }

            System.out.println("If you'd like to check in a book, please enter the book ID or enter 0 to return back to the home screen");
            int bookID = scanner.nextInt();
            scanner.nextLine();

            if (bookID == 0) {
                exit = true;
            } else {
                boolean bookFound = false;
                for (Book book : books) {
                    if (book != null && book.getBookId() == bookID && book.isCheckedOut()) { // Check for null and checked out status
                        book.checkIn();
                        System.out.println("Your book has been checked in");
                        bookFound = true;
                        break;
                    }
                }

                if (!bookFound) {
                    System.out.println("No book found");
                }
            }
        }
    }
}