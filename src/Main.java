
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import javax.swing.UIManager;
import view.MainDashboard;
import javax.swing.JPopupMenu;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
    public static void main(String[] args) {
        try {
            FlatLaf.registerCustomDefaultsSource("resources/themes");
            UIManager.setLookAndFeel(new FlatDarkLaf());

            FlatLaf.setUseNativeWindowDecorations(false);
            JPopupMenu.setDefaultLightWeightPopupEnabled(false);
            FlatLaf.updateUI();

            
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Failed to set FlatLaf theme.");
        }

        java.awt.EventQueue.invokeLater(() -> {
            new MainDashboard().setVisible(true);
        });
    }
}