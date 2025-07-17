package controller;

import dao.FeedbackDAO;
import model.Feedback;
import view.panels.FeedbackPanel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Validator;
import view.components.ConfirmationDialog;

public class FeedbackController {

    private final FeedbackPanel view;
    private final FeedbackDAO feedbackDAO;
    private final Logger LOGGER = Logger.getLogger(getClass().getName());

    public FeedbackController(FeedbackPanel view, Connection connection) {
        this.view = view;
        this.feedbackDAO = new FeedbackDAO(connection);
    }

    // Fetches a map of student names to their IDs
    public Map<String, Integer> getStudentMap() {
        try {
            return feedbackDAO.getAllStudents();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to fetch students", e);
            return new LinkedHashMap<>();
        }
    }

    // Fetches a map of counselor names to their IDs
    public Map<String, Integer> getCounselorMap() {
        try {
            return feedbackDAO.getAllCounselors();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to fetch counselors", e);
            return new LinkedHashMap<>();
        }
    }

    // Fetches a map of appointment descriptions to their IDs
    public Map<String, Integer> getAppointmentMap() {
        try {
            return feedbackDAO.getAllAppointments();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to fetch appointments", e);
            return new LinkedHashMap<>();
        }
    }

    // Retrieves all feedback records from the database
    public List<Feedback> getFeedbacks() {
        try {
            return feedbackDAO.getAllFeedbacks();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to fetch feedback", e);
            return new ArrayList<>();
        }
    }

    // Handles deleting the selected feedback
    public void deleteFeedbackLog() {
        try {
            int selectedIndex = view.getSelectedFeedbackIndex();
            if (selectedIndex < 0) {
                view.showError("Please select feedback to delete.");
                return;
            }

            List<Feedback> feedbackList = getFeedbacks();
            Feedback fb = feedbackList.get(selectedIndex);

            // Confirm deletion with feedback ID for clarity
            String details = "Feedback ID: " + fb.getId();
            if (ConfirmationDialog.confirmDelete(view, "feedback", details)) {
                feedbackDAO.deleteFeedback(fb.getId());
                loadFeedbackIntoTable();
                view.clearInputs();
                view.showInfo("Feedback deleted successfully.");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to delete feedback", e);
            view.showError("An error occurred while deleting the feedback.");
        }
    }

    // Handles submission of new feedback
public void handleSubmitFeedback() {
    try {
        Object[] inputs = view.getInputs();

        String studentName = (String) inputs[0];
        String counselorName = (String) inputs[1];
        String appointmentDesc = (String) inputs[2];
        int rating = Integer.parseInt(inputs[3].toString());
        String comments = (String) inputs[4];

        Integer studentId = getStudentMap().get(studentName);
        Integer counselorId = getCounselorMap().get(counselorName);
        Integer appointmentId = getAppointmentMap().get(appointmentDesc);

        // Validate mapped IDs
        if (studentId == null || counselorId == null || appointmentId == null) {
            view.showError("Invalid student, counselor, or appointment selected.");
            return;
        }

        // Apply validation here
        if (!Validator.validateFeedback(studentId, counselorId, appointmentId, rating, comments)) {
            view.showError(Validator.getErrorMessage());
            return;
        }

        Feedback fb = new Feedback(0, studentId, counselorId, appointmentId, rating, comments);
        feedbackDAO.createFeedback(fb);
        loadFeedbackIntoTable();
        view.clearInputs();
        view.showInfo("Feedback submitted successfully.");
    } catch (Exception e) {
        LOGGER.log(Level.SEVERE, "Failed to submit feedback", e);
        view.showError("Failed to submit feedback: " + e.getMessage());
    }
}

// Handles updating the selected feedback entry
public void handleUpdateFeedback() {
    try {
        int selectedIndex = view.getSelectedFeedbackIndex();
        if (selectedIndex < 0) {
            view.showError("Please select feedback to update.");
            return;
        }

        List<Feedback> feedbackList = getFeedbacks();
        Feedback existing = feedbackList.get(selectedIndex);

        Object[] inputs = view.getInputs();
        String studentName = (String) inputs[0];
        String counselorName = (String) inputs[1];
        String appointmentDesc = (String) inputs[2];
        int rating = Integer.parseInt(inputs[3].toString());
        String comments = (String) inputs[4];

        Integer studentId = getStudentMap().get(studentName);
        Integer counselorId = getCounselorMap().get(counselorName);
        Integer appointmentId = getAppointmentMap().get(appointmentDesc);

        // Validate mapped IDs before updating
        if (studentId == null || counselorId == null || appointmentId == null) {
            view.showError("Invalid student, counselor, or appointment selected.");
            return;
        }

        // Apply validation here
        if (!Validator.validateFeedback(studentId, counselorId, appointmentId, rating, comments)) {
            view.showError(Validator.getErrorMessage());
            return;
        }

        Feedback updated = new Feedback(existing.getId(), studentId, counselorId, appointmentId, rating, comments);
        feedbackDAO.updateFeedback(updated);
        loadFeedbackIntoTable();
        view.clearInputs();
        view.showInfo("Feedback updated successfully.");
    } catch (Exception e) {
        LOGGER.log(Level.SEVERE, "Failed to update feedback", e);
        view.showError("An error occurred while updating the feedback.");
    }
}


    // Loads selected feedback into input fields for editing
    public void handleSelectedFeedback() {
        try {
            int selectedIndex = view.getSelectedFeedbackIndex();
            if (selectedIndex < 0) return;

            List<Feedback> feedbackList = getFeedbacks();
            Feedback fb = feedbackList.get(selectedIndex);

            String studentName = getNameFromMap(getStudentMap(), fb.getStudentId());
            String counselorName = getNameFromMap(getCounselorMap(), fb.getCounselorId());
            String appointmentDesc = getNameFromMap(getAppointmentMap(), fb.getAppointmentId());

            view.setInputs(studentName, counselorName, appointmentDesc, fb.getRating(), fb.getComments());
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Failed to load selected feedback", e);
            view.showError("Could not load selected feedback.");
        }
    }

    // Clears all input fields in the view
    public void handleClearInputs() {
        view.clearInputs();
    }

    // Loads all feedback into the feedback table in the view
    public void loadFeedbackIntoTable() {
        List<Feedback> feedbackList = getFeedbacks();
        view.loadFeedbackTable(feedbackList);
    }

    // Utility: Gets a key (name/desc) from a map based on its value (ID)
    private String getNameFromMap(Map<String, Integer> map, int id) {
        return map.entrySet().stream()
                .filter(entry -> entry.getValue() == id)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("");
    }
}


