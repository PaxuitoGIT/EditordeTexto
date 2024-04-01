package EditorTexto;

import javax.swing.*;

public class App {

    JFrame ventana;
    JTextArea textArea;
    public static void main( String[] args ) {

        new App();

    }
    public App() {

        CrearVentana();
        CrearTextArea();

        ventana.setVisible(true);
    }

    public void CrearVentana() {

        ventana = new JFrame("Editor de Texto");
        ventana.setSize(800, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void CrearTextArea() {

        textArea = new JTextArea();
        ventana.add(textArea);

    }
}
