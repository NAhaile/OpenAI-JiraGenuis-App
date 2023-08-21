import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * The DocumentationPage class represents the documentation page of the application.
 * It contains a template text area, an input text area, and buttons to save, clear, and document.
 * It extends the JFrame class.
 */
public class DocumentationPage extends JFrame {
    private JTextArea templateTextArea;
    private JTextArea userInputTextArea;
    private JButton backButton;
    private JButton saveButton;
    private JButton clearButton;
    private JButton documentButton;
    private JButton editButton;
    private boolean isDocumentEditable;

    public DocumentationPage(JFrame mainFrame) {
        setTitle("Documentation Page");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        isDocumentEditable = true;

        // Create a main panel to hold the template and input text areas
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Create a label for the template text area
        JLabel templateLabel = new JLabel("Template");
        templateLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align
        mainPanel.add(templateLabel);

        // Create the template text area (larger size)
        templateTextArea = new JTextArea(15, 30); // Adjust rows as needed
        JScrollPane templateScrollPane = new JScrollPane(templateTextArea);
        mainPanel.add(templateScrollPane);

        // Create a label for the user input text area
        JLabel inputLabel = new JLabel("Input");
        inputLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align
        mainPanel.add(inputLabel);
        
        // Create the user input text area (smaller size with smaller font)
        userInputTextArea = new JTextArea(5, 30);
        userInputTextArea.setFont(new Font("SansSerif", Font.PLAIN, 12)); // Adjust the font size as needed
        JScrollPane userInputScrollPane = new JScrollPane(userInputTextArea);
        mainPanel.add(userInputScrollPane);

        // Create buttons and their actions
        backButton = new JButton("Back");
        saveButton = new JButton("Save");
        clearButton = new JButton("Clear");
        documentButton = new JButton("Document");
        editButton = new JButton("Edit");
        editButton.setEnabled(false); // Initially disabled

        backButton.addActionListener(e -> {
            mainFrame.setVisible(true);
            dispose();
        });

        saveButton.addActionListener(e -> {
            // Save logic here
            templateTextArea.setEditable(false); // Disable editing after saving
            editButton.setEnabled(true); // Enable edit button
        });

        clearButton.addActionListener(e -> {
            if (isDocumentEditable) {
                // Clear the text area in edit mode
                templateTextArea.setText("");
            } else {
                // In save mode, just disable edit without clearing
                templateTextArea.setEditable(false);
            }
            editButton.setEnabled(false); // Disable edit when clearing
        });

        documentButton.addActionListener(e -> {
            saveGeneratedDocument();
        });

        editButton.addActionListener(e -> {
    // Set the button text and state before enabling/disabling editing
    if (isDocumentEditable) {
        editButton.setText("Edit");
    } else {
        editButton.setText("Save");
    }

    // Enable or disable editing
    isDocumentEditable = !isDocumentEditable;
    templateTextArea.setEditable(isDocumentEditable);
        });

        // Create a button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(backButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(documentButton);
        buttonPanel.add(editButton);

        // Add the main panel and button panel to the main frame
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void saveGeneratedDocument() {
        try {
            // Generate a file name
            String className = this.getClass().getSimpleName();
            long timestamp = System.currentTimeMillis();
            String fileName = className + "_" + timestamp + "_document.txt";

            // Create a new file and write the generated document content to it
            File file = new File(fileName);
            FileWriter writer = new FileWriter(file);
            writer.write(userInputTextArea.getText());
            writer.close();

            JOptionPane.showMessageDialog(this, "Document saved as: " + fileName,
                    "Save Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving document.",
                    "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
