package controller;

import dao.AppointmentDAO;
import model.Appointment;
import view.panels.AppointmentPanel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.components.ConfirmationDialog;

public class AppointmentController {

    private final AppointmentPanel view;
    private final AppointmentDAO appointmentDAO;
    private final Logger LOGGER = Logger.getLogger(getClass().getName());

    public AppointmentController(AppointmentPanel view, Connection connection) {
        this.view = view;
        this.appointmentDAO = new AppointmentDAO(connection);
    }

    // Fetches student names and IDs from the database
    public Map<String, Integer> getStudentMap() {
        try {
            return appointmentDAO.getAllStudents();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to fetch students", e);
            return new LinkedHashMap<>();
        }
    }

    // Fetches counselor names and IDs from the database
    public Map<String, Integer> getCounselorMap() {
        try {
            return appointmentDAO.getAllCounselors();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to fetch counselors", e);
            return new LinkedHashMap<>();
        }
    }

    // Retrieves all appointments from the database
    public List<Appointment> getAppointments() {
        try {
            return appointmentDAO.getAllAppointments();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to fetch appointments", e);
            return new ArrayList<>();
        }
    }

    // Handles deleting a selected appointment
    public void handleDeleteAppointment() {
        try {
            int selectedIndex = view.getSelectedAppointmentIndex();
            if (selectedIndex < 0) {
                view.showError("Please select an appointment to delete.");
                return;
            }

            List<Appointment> appointments = getAppointments();
            Appointment appointment = appointments.get(selectedIndex);

            // Build appointment details string for confirmation dialog
            String type = "appointment";
            String details = appointment.getStudentName() + " with " + appointment.getCounselorName() + " on "
                    + appointment.getDate() + " at " + appointment.getTime();

            // Confirm before deleting
            if (ConfirmationDialog.confirmDelete(view, type, details)) {
                appointmentDAO.deleteAppointment(appointment.getId());
                loadAppointmentsIntoTable();
                view.clearInputs();
                view.showInfo("Appointment deleted successfully.");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to delete appointment", e);
            view.showError("An error occurred while deleting the appointment.");
        }
    }

    // Handles booking a new appointment
    public void handleBookAppointment() {
        try {
            Object[] inputs = view.getInputs();

            String studentName = (String) inputs[0];
            String counselorName = (String) inputs[1];
            LocalDate date = (LocalDate) inputs[2];
            LocalTime time = (LocalTime) inputs[3];
            String status = inputs[4].toString();

            Integer studentId = getStudentMap().get(studentName);
            Integer counselorId = getCounselorMap().get(counselorName);

            // Validate selected student and counselor
            if (studentId == null || counselorId == null) {
                view.showError("Invalid student or counselor selected.");
                return;
            }

            Appointment appt = new Appointment(0, studentId, counselorId, date, time, status);
            appointmentDAO.createAppointment(appt);
            loadAppointmentsIntoTable();
            view.clearInputs();
            view.showInfo("Appointment booked successfully.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to book appointment", e);
            view.showError("Failed to book appointment: " + e.getMessage());
        }
    }

    // Handles updating a selected appointment
    public void handleUpdateAppointment() {
        try {
            int selectedIndex = view.getSelectedAppointmentIndex();
            if (selectedIndex < 0) {
                view.showError("Please select an appointment to update.");
                return;
            }

            List<Appointment> appointments = getAppointments();
            Appointment existing = appointments.get(selectedIndex);

            Object[] inputs = view.getInputs();

            String studentName = (String) inputs[0];
            String counselorName = (String) inputs[1];
            LocalDate date = (LocalDate) inputs[2];
            LocalTime time = (LocalTime) inputs[3];
            String status = inputs[4].toString();

            Integer studentId = getStudentMap().get(studentName);
            Integer counselorId = getCounselorMap().get(counselorName);

            // Validate selected student and counselor
            if (studentId == null || counselorId == null) {
                view.showError("Invalid student or counselor selected.");
                return;
            }

            Appointment updated = new Appointment(existing.getId(), studentId, counselorId, date, time, status);
            //checks status of appointment
            updated = checkStatus(existing, updated);
            appointmentDAO.updateAppointment(updated);
            loadAppointmentsIntoTable();
            view.clearInputs();
            view.showInfo("Appointment updated successfully.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to update appointment", e);
            view.showError("An error occurred while updating the appointment.");
        }
    }
    // Handles canceling an existing appointment

    public void handleCancelAppointment() {
        try {
            int selectedIndex = view.getSelectedAppointmentIndex();
            if (selectedIndex < 0) {
                view.showError("Please select an appointment to update.");
                return;
            }

            List<Appointment> appointments = getAppointments();
            Appointment existing = appointments.get(selectedIndex);

            Object[] inputs = view.getInputs();

            String studentName = (String) inputs[0];
            String counselorName = (String) inputs[1];
            LocalDate date = (LocalDate) inputs[2];
            LocalTime time = (LocalTime) inputs[3];
            String status = "Cancelled";

            Integer studentId = getStudentMap().get(studentName);
            Integer counselorId = getCounselorMap().get(counselorName);

            // Validate selected student and counselor
            if (studentId == null || counselorId == null) {
                view.showError("Invalid student or counselor selected.");
                return;
            }

            Appointment updated = new Appointment(existing.getId(), studentId, counselorId, date, time, status);
            appointmentDAO.updateAppointment(updated);
            loadAppointmentsIntoTable();
            view.clearInputs();
            view.showInfo("Appointment cancelled successfully.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to update appointment", e);
            view.showError("An error occurred while updating the appointment.");
        }
    }

    // Loads selected appointment details into the input fields
    public void handleSelectedAppointment() {
        try {
            int selectedIndex = view.getSelectedAppointmentIndex();
            if (selectedIndex < 0) {
                return;
            }

            List<Appointment> appointments = getAppointments();
            Appointment appt = appointments.get(selectedIndex);

            view.setInputs(
                    appt.getStudentName(),
                    appt.getCounselorName(),
                    appt.getDate(),
                    appt.getTime(),
                    appt.getStatus()
            );
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Failed to load selected appointment", e);
            view.showError("Could not load selected appointment.");
        }
    }

    // Clears input fields in the view
    public void handleClearInputs() {
        view.clearInputs();
    }

    // Loads all appointments into the appointment table in the UI
    public void loadAppointmentsIntoTable() {
        List<Appointment> appointments = getAppointments();
        ArrayList<Appointment> chkAppointments = new ArrayList<Appointment>();
        for (Appointment appointment : appointments) {
            //checks status of appointments befor adding them to table
            appointment = checkStatus(appointment);
            chkAppointments.add(appointment);
        }
        view.loadAppointmentTable(chkAppointments);
    }

    public Appointment checkStatus(Appointment appointment) {
        if (appointment.getDate().isAfter(LocalDate.now())) {//
            appointment.setStatus("No Show");
        }
        return appointment;
    }

    public Appointment checkStatus(Appointment appointment, Appointment newAppointment) {
        if (newAppointment.getStatus().equals("Completed")) {
            appointment.setStatus("Completed");
        }
        else if ((!appointment.getStatus().equals("Cancelled") && !appointment.getStatus().equals("Completed"))) {

            if (!appointment.getDate().isEqual(newAppointment.getDate())) {
                appointment.setStatus("Rescheduled");
            }
            if (newAppointment.getDate().getDayOfYear() < LocalDate.now().getDayOfYear() && !appointment.getStatus().equals("Completed")) {//
                appointment.setStatus("No Show");
            }
        }

        return appointment;
    }
}
