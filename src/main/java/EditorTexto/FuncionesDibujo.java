package EditorTexto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FuncionesDibujo extends JPanel {

    App app;
    ArrayList<Point> points = new ArrayList<>();

    // Heredar el constructor de la clase App
    public FuncionesDibujo(App app) {
        this.app = app;
        setBackground(Color.WHITE);
        setupDrawing();
    }

    public FuncionesDibujo() {
        setBackground(Color.WHITE);
        setupDrawing();
    }

    private void setupDrawing() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                points.add(e.getPoint());
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                points.add(e.getPoint());
                repaint();
            }
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        for (Point point : points) {
            g.fillOval(point.x, point.y, 4, 4);
        }
    }

    public void Dibujar() {
        JFrame frame = new JFrame("Dibujar");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new FuncionesDibujo());
        frame.setVisible(true);
    }
}