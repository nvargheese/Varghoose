package org.example;

import java.util.List;

public class Calendar {
    public static double calculateMonthToDate(List<Transaction> ledger) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        int currentYear = calendar.get(java.util.Calendar.YEAR);
        int currentMonth = calendar.get(java.util.Calendar.MONTH);
        double monthToDateDeposit = 0.0;
        for (Transaction entry : ledger) {
            java.util.Calendar entryDate = java.util.Calendar.getInstance();
            entryDate.setTimeInMillis(Long.parseLong(entry.getDate()));
            int entryYear = entryDate.get(java.util.Calendar.YEAR);
            int entryMonth = entryDate.get(java.util.Calendar.MONTH) + 1;
            if (currentYear == entryYear && currentMonth == entryMonth && entry.getAmount() > 0) {
                monthToDateDeposit += entry.getAmount();
            }
        }
        return monthToDateDeposit;
    }
    public static double calculatePreviousMonth(List<Transaction> ledger) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        int currentYear = calendar.get(java.util.Calendar.YEAR);
        int currentMonth = calendar.get(java.util.Calendar.MONTH) + 1;
        int previousYear = currentYear;
        int previousMonth = currentMonth - 1;
        if (previousMonth < 0) {
            previousMonth = 11;
            previousYear--;
        }
        double previousMonthDeposit = 0.0;
        for (Transaction entry : ledger) {
            java.util.Calendar entryDate = java.util.Calendar.getInstance();
            entryDate.setTimeInMillis(Long.parseLong(entry.getDate()));
            int entryYear = entryDate.get(java.util.Calendar.YEAR);
            int entryMonth = entryDate.get(java.util.Calendar.MONTH) + 1;
            if (entryYear == previousYear && entryMonth == previousMonth && entry.getAmount() > 0) {
                previousMonthDeposit += entry.getAmount();
            }
        }
        return previousMonthDeposit;
    }
    public static double calculateYearToDate(List<Transaction> ledger) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        int currentYear = calendar.get(java.util.Calendar.YEAR);
        int currentMonth = calendar.get(java.util.Calendar.MONTH);
        double yearToDateDeposit = 0.0;
        for (Transaction entry : ledger) {
            java.util.Calendar entryDate = java.util.Calendar.getInstance();
            entryDate.setTimeInMillis(Long.parseLong(entry.getDate()));
            int entryYear = entryDate.get(java.util.Calendar.YEAR);
            int entryMonth = entryDate.get(java.util.Calendar.MONTH) + 1;
            if (entryYear == currentYear && entryMonth <= currentMonth && entry.getAmount() > 0) {
                yearToDateDeposit += entry.getAmount();
            }
        }
        return yearToDateDeposit;
    }
    public static double calculatePreviousYear(List<Transaction> ledger) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        int currentYear = calendar.get(java.util.Calendar.YEAR);
        double previousYearDeposit = 0.0;
        for (Transaction entry : ledger) {
            java.util.Calendar entryDate = java.util.Calendar.getInstance();
            entryDate.setTimeInMillis(Long.parseLong(entry.getDate()));
            int entryYear = entryDate.get(java.util.Calendar.YEAR);
            if (entryYear == currentYear - 1 && entry.getAmount() > 0) {
                previousYearDeposit += entry.getAmount();
            }
        }
        return previousYearDeposit;
    }
}
