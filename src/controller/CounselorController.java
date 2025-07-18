package controller;

import dao.CounselorDAO;
import static java.lang.String.format;
import model.Counselor;
import view.panels.CounselorPanel;
import view.components.ConfirmationDialog;
import utils.Validator;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CounselorController {
    private final CounselorPanel view;
    private final CounselorDAO counselorDao;
    private final Logger LOGGER = Logger.getLogger(getClass().getName());

    public CounselorController(CounselorPanel view, Connection connection) {
        this.view = view;
        this.counselorDao = new CounselorDAO(connection);
    }

    // Loads all counselors into the UI table
    public void loadCounselorsIntoTable() {
        try {
            List<Counselor> counselors = counselorDao.readCounselors();
            if (counselors == null) {
                LOGGER.warning("Counselor list returned by DAO is null.");
                view.showError("Could not load counselor data.");
                return;
            }
            view.loadCounselorTable(counselors);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to load counselor table", e);
            view.showError("An error occurred while loading the counselor table.");
        }
    }

    // Handles adding a new counselor
    public void handleAddCounselor() {
        try {
            String[] inputs = view.getInputs();
            String firstName = inputs[0].trim();
            String lastName = inputs[1].trim();
            String specialization = inputs[2].trim();
            boolean available = view.getAvailability();

            // Input validation
            if (!Validator.validateCounselor(firstName, lastName, specialization, available)) {
                view.showError(Validator.getErrorMessage());
                return;
            }

            Counselor counselor = new Counselor(0, firstName, lastName, specialization, available);
            counselorDao.addCounselor(counselor);
            loadCounselorsIntoTable();
            view.clearInputs();
            view.showInfo("Counselor added successfully.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to add counselor", e);
            view.showError("An error occurred while adding the counselor.");
        }
    }

    // Handles updating the selected counselor
    public void handleUpdateCounselor() {
        try {
            int selectedIndex = view.getSelectedCounselorIndex();
            if (selectedIndex < 0) {
                view.showError("Please select a counselor to update.");
                return;
            }

            Counselor counselor = counselorDao.getCounselorList().get(selectedIndex);
            String[] inputs = view.getInputs();
            String firstName = inputs[0].trim();
            String lastName = inputs[1].trim();
            String specialization = inputs[2].trim();
            boolean available = view.getAvailability();

            // Input validation
            if (!Validator.validateCounselor(firstName, lastName, specialization, available)) {
                view.showError(Validator.getErrorMessage());
                return;
            }

            // Update counselor fields
            counselor.setFirstName(firstName);
            counselor.setLastName(lastName);
            counselor.setSpecialisation(specialization);
            counselor.setAvailable(available);

            counselorDao.updateCounselor(counselor);
            loadCounselorsIntoTable();
            view.clearInputs();
            view.showInfo("Counselor added successfully.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to update counselor", e);
            view.showError("An error occurred while updating the counselor.");
        }
    }

    // Handles deleting the selected counselor
    public void handleDeleteCounselor() {
    try {
        int selectedIndex = view.getSelectedCounselorIndex();
        if (selectedIndex < 0) {
            view.showError("Please select a counselor to delete.");
            return;
        }

        Counselor counselor = counselorDao.getCounselorList().get(selectedIndex);
         boolean success = counselorDao.deleteCounselor(counselor);

  
         String type = "Counselor";
         String details = String.format(counselor.getFullName());
         
         
            if (success && ConfirmationDialog.confirmDelete(view, type, details)) {
                loadCounselorsIntoTable();
                view.clearInputs();
                view.showInfo("Counselor deleted successfully.");
            } else {
                view.showError("Cannot delete counselor: This counselor is assigned elsewhere.");
            }
        

        // Confirm deletion
        
    } catch (Exception e) {
        LOGGER.log(Level.SEVERE, "Failed to delete counselor", e);
        view.showError("An error occurred while deleting the counselor.");
    }
}

    // Populates input fields when a row in the table is selected
    public void handleCounselorSelection() {
        try {
            int selectedIndex = view.getSelectedCounselorIndex();
            if (selectedIndex < 0) return;

           
            Counselor counselor = counselorDao.getCounselorList().get(selectedIndex);
            view.setInputs(
                counselor.getFirstName(),
                counselor.getLastName(),
                counselor.getSpecialisation(),
                String.valueOf(counselor.isAvailable())
            );
            
            
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Failed to load counselor data on row click", e);
            view.showError("An error occurred while loading counselor details.");
        }
    }

    // Clears all input fields in the view
    public void handleClearInputs() {
        view.clearInputs();
    }
}
