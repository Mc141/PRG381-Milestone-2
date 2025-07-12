package view.components;

import javax.swing.*;
import java.awt.Component;

public class ConfirmationDialog {

    public static boolean confirmDelete(Component parent, String entryType, String entryDetails) {
        String message = "Are you sure you want to delete this " + entryType + "?\nDetails: " + entryDetails;

        int result = JOptionPane.showConfirmDialog(
            parent,
            message,
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        return result == JOptionPane.YES_OPTION;
    }
}