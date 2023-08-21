import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * The MainFrame class represents the main page of the application.
 * It contains buttons to navigate to the Code Review and Documentation pages.
 * It extends the JFrame class.
 * 
 */
public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Main Page");
        
        // Set the size of the window
        setSize(800, 600);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the window
        setLocationRelativeTo(null);

        // Create a panel to hold the buttons vertically
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Align buttons vertically

        JButton codeReviewButton = new JButton("Code Review");
        JButton documentationButton = new JButton("Documentation");

        codeReviewButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        documentationButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally

        // Disable focus for the buttons
        codeReviewButton.setFocusable(false);
        documentationButton.setFocusable(false);

        // Add actions to the buttons
        codeReviewButton.addActionListener(e -> {
            CodeReviewPage codeReviewPage = new CodeReviewPage(this);
            codeReviewPage.setVisible(true);
            setVisible(false);
        });

        // Add actions to the buttons
        documentationButton.addActionListener(e -> {
            DocumentationPage documentationPage = new DocumentationPage(this);
            documentationPage.setVisible(true);
            setVisible(false);
        });

        // Add buttons to the buttonPanel
        buttonPanel.add(Box.createVerticalStrut(20)); // Adjust the space as needed
        buttonPanel.add(codeReviewButton);
        buttonPanel.add(Box.createVerticalStrut(10)); // Space between buttons
        buttonPanel.add(documentationButton);
        buttonPanel.add(Box.createVerticalStrut(20)); // Adjust the space as needed

        // Add the buttonPanel to the frame
        add(buttonPanel, BorderLayout.CENTER);
    }
}

