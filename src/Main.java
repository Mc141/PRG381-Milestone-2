
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import javax.swing.UIManager;
import Wellness_Management_System.View.MainDashboard;
import javax.swing.JPopupMenu;

public class Main {
    public static void main(String[] args) {
        try {
            FlatLaf.registerCustomDefaultsSource("theme");
            UIManager.setLookAndFeel(new FlatDarkLaf());

            FlatLaf.setUseNativeWindowDecorations(false);
            JPopupMenu.setDefaultLightWeightPopupEnabled(false);
            FlatLaf.updateUI();

            
        } catch (Exception e) {
            System.err.println("Failed to set FlatLaf theme.");
        }

        java.awt.EventQueue.invokeLater(() -> {
            new MainDashboard().setVisible(true);
        });
    }
}