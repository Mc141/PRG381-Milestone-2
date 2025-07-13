package utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Pattern;

public class Validator {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z\\s'-]+$");
    private static String errorMessage = "";

    // === ERROR HANDLING ===
    public static String getErrorMessage() {
        return errorMessage;
    }

    private static void setError(String message) {
        errorMessage = message;
    }

    // === GENERAL VALIDATIONS ===
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isValidName(String name) {
        if (isNullOrEmpty(name)) {
            setError("Name cannot be empty.");
            return false;
        }
        if (!NAME_PATTERN.matcher(name).matches()) {
            setError("Name contains invalid characters.");
            return false;
        }
        return true;
    }

    public static boolean isValidSpecialisation(String specialisation) {
        if (isNullOrEmpty(specialisation)) {
            setError("Specialisation cannot be empty.");
            return false;
        }
        if (!NAME_PATTERN.matcher(specialisation).matches()) {
            setError("Specialisation contains invalid characters.");
            return false;
        }
        return true;
    }

    public static boolean isValidRating(int rating) {
        if (rating < 1 || rating > 5) {
            setError("Rating must be between 1 and 5.");
            return false;
        }
        return true;
    }

    public static boolean isValidTime(LocalTime time) {
        if (time == null) {
            setError("Time cannot be null.");
            return false;
        }
        return true;
    }

    public static boolean isValidDate(LocalDate date) {
        if (date == null) {
            setError("Date cannot be null.");
            return false;
        }
        if (date.isBefore(LocalDate.now())) {
            setError("Date cannot be in the past.");
            return false;
        }
        return true;
    }

    // === COUNSELOR PANEL VALIDATIONS ===
    public static boolean validateCounselor(String firstName, String lastName, String specialisation, Boolean available) {
        if (!isValidName(firstName)) return false;
        if (!isValidName(lastName)) return false;
        if (!isValidSpecialisation(specialisation)) return false;
        if (available == null) {
            setError("Availability must be specified.");
            return false;
        }
        return true;
    }

    // === STUDENT PANEL VALIDATIONS ===
    public static boolean validateStudent(String firstName, String lastName) {
        if (!isValidName(firstName)) return false;
        if (!isValidName(lastName)) return false;
        return true;
    }

    // === APPOINTMENT PANEL VALIDATIONS ===
    public static boolean validateAppointment(Object studentId, Object counselorId, LocalDate date, LocalTime time, String status) {
        if (studentId == null || counselorId == null) {
            setError("Student and Counselor must be selected.");
            return false;
        }
        if (!isValidDate(date)) return false;
        if (!isValidTime(time)) return false;
        if (isNullOrEmpty(status)) {
            setError("Status cannot be empty.");
            return false;
        }
        return true;
    }

    // === FEEDBACK PANEL VALIDATIONS ===
    public static boolean validateFeedback(Object studentId, Object counselorId, Object appointmentId, int rating, String comments) {
        if (studentId == null || counselorId == null || appointmentId == null) {
            setError("Student, Counselor, and Appointment must be selected.");
            return false;
        }
        if (!isValidRating(rating)) return false;
        if (isNullOrEmpty(comments)) {
            setError("Comments cannot be empty.");
            return false;
        }
        return true;
    }
}
