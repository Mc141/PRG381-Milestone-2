
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import javax.swing.UIManager;
import view.MainDashboard;
import javax.swing.JPopupMenu;
import javax.swing.UnsupportedLookAndFeelException;
import utils.AbstractDBConnection;
import utils.DBConnection;
import utils.DatabaseInitializer;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            AbstractDBConnection db = new DBConnection();
            db.connect();
            connection = db.getConnection();
            
            // DatabaseInitializer.initialise(connection); // Ran once for db setup
            
            FlatLaf.registerCustomDefaultsSource("resources/themes");
            UIManager.setLookAndFeel(new FlatDarkLaf());

            FlatLaf.setUseNativeWindowDecorations(false);
            JPopupMenu.setDefaultLightWeightPopupEnabled(false);
            FlatLaf.updateUI();

            
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Failed to set FlatLaf theme.");
        }
        
        Connection finalConnection = connection; // Required because it's used inside a lambda
        java.awt.EventQueue.invokeLater(() -> {
            new MainDashboard(finalConnection).setVisible(true);
        });
    }
}