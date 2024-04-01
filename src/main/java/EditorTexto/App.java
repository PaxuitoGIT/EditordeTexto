package EditorTexto;

import javax.swing.*;

public class App {

    JFrame ventana;
    JTextArea textArea;
    JScrollPane scrollVentana;
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

        scrollVentana = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollVentana.setBorder(BorderFactory.createEmptyBorder());
        ventana.add(scrollVentana);

    }
}
