import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * The CodeReviewPage class represents the code review page of the application.
 * It contains two text areas for code base and code changes, a text area for review result,
 * and buttons to save, clear, review, and go back to the main page.
 * It extends the JFrame class.
 */
public class CodeReviewPage extends JFrame {
    private JTextArea codeBaseTextArea;
    private JTextArea codeChangesTextArea;
    private JTextArea reviewResultTextArea;
    private JButton backButton;
    private JButton saveButton;
    private JButton clearButton;
    private JButton reviewButton;

    public CodeReviewPage(JFrame mainFrame) {
        setTitle("Code Review Page");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a JSplitPane to divide the space between Code Base and Code Changes
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(0.5); // Equal split
        add(splitPane, BorderLayout.CENTER);

        // Code Base Panel
        JPanel codeBasePanel = new JPanel(new BorderLayout());
        codeBasePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
                "Code Base", TitledBorder.CENTER, TitledBorder.TOP));
        codeBaseTextArea = createTextAreaWithLineNumbers(10, 30);
        JScrollPane codeBaseScrollPane = new JScrollPane(codeBaseTextArea);
        codeBaseScrollPane.setRowHeaderView(new LineNumberView(codeBaseTextArea));
        codeBasePanel.add(codeBaseScrollPane, BorderLayout.CENTER);

        // Code Changes Panel
        JPanel codeChangesPanel = new JPanel(new BorderLayout());
        codeChangesPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
                "Code Changes", TitledBorder.CENTER, TitledBorder.TOP));
        codeChangesTextArea = createTextAreaWithLineNumbers(10, 30);
        JScrollPane codeChangesScrollPane = new JScrollPane(codeChangesTextArea);
        codeChangesScrollPane.setRowHeaderView(new LineNumberView(codeChangesTextArea));
        codeChangesPanel.add(codeChangesScrollPane, BorderLayout.CENTER);

        // Add Code Base and Code Changes panels to the split pane
        splitPane.setLeftComponent(codeBasePanel);
        splitPane.setRightComponent(codeChangesPanel);

        // Review Result Panel
        JPanel reviewResultPanel = new JPanel(new BorderLayout());
        reviewResultPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
                "Review Result", TitledBorder.CENTER, TitledBorder.TOP));
        reviewResultTextArea = new JTextArea(10, 30);
        reviewResultTextArea.setEditable(false); // Review result is not editable
        JScrollPane reviewResultScrollPane = new JScrollPane(reviewResultTextArea);
        reviewResultPanel.add(reviewResultScrollPane, BorderLayout.CENTER);

        // Buttons
        backButton = new JButton("Back");
        saveButton = new JButton("Save");
        clearButton = new JButton("Clear");
        reviewButton = new JButton("Review");

        // Disable focus for the buttons
        backButton.setFocusable(false);
        saveButton.setFocusable(false);
        clearButton.setFocusable(false);
        reviewButton.setFocusable(false);

        backButton.addActionListener(e -> {
            mainFrame.setVisible(true);
            dispose();
        });

        saveButton.addActionListener(e -> {
            saveCodeReviewToFile(codeChangesTextArea.getText()); // Save only changes
        });

        clearButton.addActionListener(e -> {
            codeBaseTextArea.setText("");
            codeChangesTextArea.setText("");
            reviewResultTextArea.setText("");
        });

        reviewButton.addActionListener(e -> {
            performCodeReview();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(backButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(reviewButton);

        // Add Review Result panel and Buttons to the main layout
        add(reviewResultPanel, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JTextArea createTextAreaWithLineNumbers(int rows, int columns) {
        JTextArea textArea = new JTextArea(rows, columns);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Create a scroll pane for the text area
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setRowHeaderView(new LineNumberView(textArea));

        return textArea;
    }

    private void performCodeReview() {
        // Implement code comparison logic here and display the results in
        // reviewResultTextArea.
        // You can compare codeBaseTextArea.getText() and codeChangesTextArea.getText()
        // and display the differences in reviewResultTextArea.
        // For code comparison, you can use libraries like Apache Commons Text Diff.

        String codeBaseText = codeBaseTextArea.getText();
        String codeChangesText = codeChangesTextArea.getText();

        // Perform code comparison and generate the review result
        // For demonstration, let's just display the combined code for now.
        String reviewResultText = "Code Base:\n" + codeBaseText + "\n\nCode Changes:\n" + codeChangesText;

        reviewResultTextArea.setText(reviewResultText);
    }

    private void saveCodeReviewToFile(String codeChangesContent) {
        try {
            // Generate a unique filename based on class name and timestamp
            String className = this.getClass().getSimpleName();
            long timestamp = System.currentTimeMillis();
            String fileName = className + "_" + timestamp + ".txt";

            // Create a new file and write the code review content to it
            File file = new File(fileName);
            FileWriter writer = new FileWriter(file);
            writer.write(codeChangesContent);
            writer.close();

            JOptionPane.showMessageDialog(this, "Code review changes saved as: " + fileName,
                    "Save Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving code review changes.",
                    "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}
