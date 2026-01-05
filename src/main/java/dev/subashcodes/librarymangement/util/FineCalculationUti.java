package dev.subashcodes.librarymangement.util;

public class FineCalculationUti {

    public static double calculateFine(long overdueDays, double finePerDay) {
        if (overdueDays <= 0) {
            return 0.0;
        }
        return overdueDays * finePerDay;
    }
}
