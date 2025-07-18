package controller;

import dao.StudentDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import model.Student;
import view.panels.StudentPanel;
import java.sql.*;
import utils.Validator;
import view.components.ConfirmationDialog;


public class StudentController {
    private final StudentPanel view;
    private final StudentDAO studentDao;
    private final Logger LOGGER = Logger.getLogger(getClass().getName());

    public StudentController(StudentPanel view, Connection connection) {
        this.view = view;
        this.studentDao = new StudentDAO(connection);
    }

    public void loadStudentsIntoTable() {
        try {
            List<Student> students = studentDao.readStudents();
            if (students == null) {
                LOGGER.warning("Student list returned by DAO is null.");
                view.showError("Could not load student data.");
                return;
            }
            view.loadStudentTable(students);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to load student table", e);
            view.showError("An error occurred while loading the student table.");
        }
    }

    public void handleAddStudent() {
        try {
            String[] inputs = view.getInputs();
            String firstName = inputs[0].trim();
            String lastName = inputs[1].trim();

            if (!Validator.validateStudent(firstName, lastName)) {
                view.showError(Validator.getErrorMessage());
                return;
            }

            Student student = new Student();
            student.setFirstName(firstName);
            student.setLastName(lastName);

            studentDao.addStudent(student);
            loadStudentsIntoTable();
            view.clearInputs();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to add student", e);
            view.showError("An error occurred while adding the student.");
        }
    }

    public void handleUpdateStudent() {
        try {
            int selectedIndex = view.getSelectedStudentIndex();
            if (selectedIndex < 0) {
                view.showError("Please select a student to update.");
                return;
            }

            Student student = studentDao.getStudentList().get(selectedIndex);
            String[] inputs = view.getInputs();
            String firstName = inputs[0].trim();
            String lastName = inputs[1].trim();

            if (!Validator.validateStudent(firstName, lastName)) {
                view.showError(Validator.getErrorMessage());
                return;
            }

            student.setFirstName(firstName);
            student.setLastName(lastName);

            studentDao.updateStudent(student);
            loadStudentsIntoTable();
            view.clearInputs();
            view.showInfo("student updated");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to update student", e);
            view.showError("An error occurred while updating the student.");
        }
    }

    public void handleDeleteStudent() {
        try {
            int selectedIndex = view.getSelectedStudentIndex();
            if (selectedIndex < 0) {
                view.showError("Please select a student to delete.");
                return;
            }

            Student student = studentDao.getStudentList().get(selectedIndex);
            String type = "student";
            String details = student.getFullName();

            if (ConfirmationDialog.confirmDelete(view, type, details)) {
                studentDao.deleteStudent(student);
                loadStudentsIntoTable();
                view.clearInputs();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to delete student", e);
            view.showError("An error occurred while deleting the student.");
        }
    }

    public void handleStudentSelection() {
        try {
            int selectedIndex = view.getSelectedStudentIndex();
            if (selectedIndex < 0) return;

            Student student = studentDao.getStudentList().get(selectedIndex);
            view.setInputs(student.getFirstName(), student.getLastName());
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Failed to load student data on row click", e);
            view.showError("An error occurred while loading student details.");
        }
    }

    public void handleClearInputs() {
        view.clearInputs();
    }
}
