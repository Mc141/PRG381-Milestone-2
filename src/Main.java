
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import javax.swing.UIManager;
import view.MainDashboard;
import javax.swing.JPopupMenu;
import javax.swing.UnsupportedLookAndFeelException;
import utils.AbstractDBConnection;
import utils.DBConnection;
import utils.DatabaseInitializer;

public class Main {
    public static void main(String[] args) {
        try {
            AbstractDBConnection connection = new DBConnection();
            connection.connect();
            
            // DatabaseInitializer.initialise(connection); // Ran once for db setup
            
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