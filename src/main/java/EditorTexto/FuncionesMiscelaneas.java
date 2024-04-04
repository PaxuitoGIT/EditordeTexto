package EditorTexto;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class FuncionesMiscelaneas {

    JLabel mousePositionLabel;

    // Constructor
    public FuncionesMiscelaneas() {
        mousePositionLabel = new JLabel();
        mousePositionLabel.setHorizontalAlignment(JLabel.RIGHT);
    }

    // Getter
    public JLabel getMousePositionLabel() {
        return mousePositionLabel;
    }

    // Rastrear la posición del ratón en un JTextArea
    public void addMouseTracking(JTextArea textArea) {
        textArea.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                mousePositionLabel.setText("X: " + mouseX + ", Y: " + mouseY);
            }
        });
    }
}