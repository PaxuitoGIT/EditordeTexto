package EditorTexto;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class FuncionesMiscelaneas implements KeyListener {

    App app;
    JLabel mousePositionLabel;

    // Constructor
    public FuncionesMiscelaneas(App app) {
        this.app = app;
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

    // Métodos de la interfaz KeyListener que sirven para crear atajos de teclado
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        //Si se presiona la tecla F1, se crea un nuevo archivo
        if(e.getKeyCode()==KeyEvent.VK_F1) {
            app.funcionesArchivo.newNuevo();
        }
        //Si se presiona la tecla F2, se abre un archivo
        if(e.getKeyCode()==KeyEvent.VK_F2) {
            app.funcionesArchivo.Abrir();
        }
        //Si se presiona la tecla Ctrl + S, se guarda el archivo
        if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_S) {
            app.funcionesArchivo.Guardar();
        }
        //Si se presiona la tecla Ctrl + Z, se deshace la última acción
        if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_Z) {
            app.funcionesEditar.Deshacer();
        }
        //Si se presiona la tecla Ctrl + Y, se rehace la última acción
        if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_Y) {
            app.funcionesEditar.Rehacer();
        }
        //Si se presiona la tecla F3, se activa o desactiva el salto de línea
        if (e.getKeyCode() == KeyEvent.VK_F3) {
            app.funcionesContacto.agregarContacto();
        }
        //Si se presiona la tecla F4, se dibuja una figura
        if (e.getKeyCode() == KeyEvent.VK_F4) {
            app.funcionesDibujo.Dibujar();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}