/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Wellness_Management_System.View;

import javax.swing.*;
import java.awt.Component;

/**
 *
 * @author MC
 */
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