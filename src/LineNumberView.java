import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * The LineNumberView class represents the line numbers on the left side of the text area.
 * It extends the JComponent class.
 */
public class LineNumberView extends JComponent {
    private final JTextComponent textComponent;

    // Constructor
    // The constructor takes a JTextComponent as a parameter.
    // It adds a document listener to the text component to track changes in the text area.
    public LineNumberView(JTextComponent textComponent) {
        this.textComponent = textComponent;
        setPreferredSize(new Dimension(40, 0)); // Adjust the width as needed

        // Add a document listener to the text component
        textComponent.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { // insertUpdate is called when the text is added
                repaint();
            }

            @Override
            public void removeUpdate(DocumentEvent e) { // removeUpdate is called when the text is deleted
                repaint();
            }

            @Override
            public void changedUpdate(DocumentEvent e) { // changedUpdate is called when the text
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Get information about the text component
        FontMetrics metrics = textComponent.getFontMetrics(textComponent.getFont());
        int lineHeight = metrics.getHeight();
        int linesInTextComponent = textComponent.getDocument().getDefaultRootElement().getElementCount();
        int lineNumbersWidth = metrics.stringWidth(Integer.toString(linesInTextComponent)) + 5;

        // Get information about the visible lines
        Rectangle clip = g.getClipBounds();
        int startingOffset = textComponent.viewToModel(new Point(0, clip.y));
        int endingOffset = textComponent.viewToModel(new Point(0, clip.y + clip.height));

        // Draw the line numbers
        Element root = textComponent.getDocument().getDefaultRootElement();
        for (int i = root.getElementIndex(startingOffset); i <= root.getElementIndex(endingOffset); i++) {
            String lineText = Integer.toString(i + 1);
            int x = lineNumbersWidth - metrics.stringWidth(lineText);
            int y = i * lineHeight + lineHeight - metrics.getDescent();
            g.drawString(lineText, x, y);
        }
    }
}
