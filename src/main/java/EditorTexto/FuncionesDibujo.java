package EditorTexto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class FuncionesDibujo extends JPanel {

    App app;
    ArrayList<Line2D> lines = new ArrayList<>();
    Point lastPoint;

    // Heredar el constructor de la clase App
    public FuncionesDibujo(App app) {
        this.app = app;
        setBackground(Color.WHITE);
        setupDrawing();
    }


    // Dibuja las líneas cuandp se clickea y se arrastra el mouse
    private void setupDrawing() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastPoint = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Point newPoint = e.getPoint();
                lines.add(new Line2D.Float(lastPoint, newPoint));
                lastPoint = newPoint;
                repaint();
            }
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    // Pinta las líneas de color negro
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        for (Line2D line : lines) {
            g2.draw(line);
        }
    }

    // Abre una ventana nueva para dibujar
    public void Dibujar() {
        JFrame frame = new JFrame("Dibujar");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new FuncionesDibujo(this.app));
        frame.setVisible(true);
    }
}