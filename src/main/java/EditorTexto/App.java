package EditorTexto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App implements ActionListener {

    JFrame ventana;
    JTextArea textArea;
    JScrollPane scrollVentana;
    JMenuBar barraMenu;
    JMenu menuArchivo, menuEditar, menuFormato, menuVer;
    JMenuItem iNuevo, iAbrir, iGuardar, iGuardarComo, iSalir;

    FuncionesArchivo funcionesArchivo = new FuncionesArchivo(this);
    public static void main( String[] args ) {

        new App();

    }
    public App() {

        CrearVentana();
        CrearTextArea();
        CrearBarraMenu();
        CrearItemsMenu();

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

    public void CrearBarraMenu() {

        barraMenu = new JMenuBar();
        ventana.setJMenuBar(barraMenu);

        menuArchivo = new JMenu("Archivo");
        barraMenu.add(menuArchivo);

        menuEditar = new JMenu("Editar");
        barraMenu.add(menuEditar);

        menuFormato = new JMenu("Formato");
        barraMenu.add(menuFormato);

        menuVer = new JMenu("Ver");
        barraMenu.add(menuVer);

    }

    public void CrearItemsMenu() {

        iNuevo = new JMenuItem("Nuevo");
        iNuevo.addActionListener(this);
        iNuevo.setActionCommand("Nuevo");
        menuArchivo.add(iNuevo);

        iAbrir = new JMenuItem("Abrir");
        iAbrir.addActionListener(this);
        iAbrir.setActionCommand("Abrir");
        menuArchivo.add(iAbrir);

        iGuardar = new JMenuItem("Guardar");
        iGuardar.addActionListener(this);
        iGuardar.setActionCommand("Guardar");
        menuArchivo.add(iGuardar);

        iGuardarComo = new JMenuItem("Guardar Como");
        iGuardarComo.addActionListener(this);
        iGuardarComo.setActionCommand("Guardar Como");
        menuArchivo.add(iGuardarComo);

        iSalir = new JMenuItem("Salir");
        iSalir.addActionListener(this);
        iSalir.setActionCommand("Salir");
        menuArchivo.add(iSalir);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String comando = e.getActionCommand();

        switch (comando) {
            case "Nuevo":
                funcionesArchivo.newNuevo();
                break;
            case "Abrir":
                funcionesArchivo.Abrir();
                break;
            case "Guardar Como":
                funcionesArchivo.GuardarComo();
                break;
            case "Guardar":
                funcionesArchivo.Guardar();
                break;
            case "Salir":
                System.exit(0);
                break;
        }
    }
}
