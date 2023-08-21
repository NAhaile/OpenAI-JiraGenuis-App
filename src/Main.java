import javax.swing.*;

public class Main {

    // Main method
    /**
     * The main method is the entry point of the program.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
