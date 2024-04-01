package EditorTexto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App implements ActionListener {

    JFrame ventana;

    // Texto
    JTextArea textArea;
    JScrollPane scrollVentana;
    boolean SaltoLineaOn = false;
    // Menú Barra
    JMenuBar barraMenu;
    JMenu menuArchivo, menuEditar, menuFormato, menuVer;
    // Archivo Menú Items
    JMenuItem iNuevo, iAbrir, iGuardar, iGuardarComo, iSalir;
    //Formato Menú Items
    JMenuItem iSaltoLinea, iFuenteArial, iFuenteCSMS, iFuenteTNR, iFuenteTamano8, iFuenteTamano12, iFuenteTamano16, iFuenteTamano20, iFuenteTamano24, iFuenteTamano28;
    JMenu menuTamanoFuente, menuTipoFuente;

    FuncionesArchivo funcionesArchivo = new FuncionesArchivo(this);
    FuncionesFormato funcionesFormato = new FuncionesFormato(this);
    public static void main( String[] args ) {

        new App();

    }
    public App() {

        CrearVentana();
        CrearTextArea();
        CrearBarraMenu();
        CrearItemsMenu();
        CrearItemsMenuFormato();

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

    public void CrearItemsMenuFormato() {
        iSaltoLinea = new JMenuItem("Salto de Linea: Desactivado");
        iSaltoLinea.addActionListener(this);
        iSaltoLinea.setActionCommand("Salto de Linea");
        menuFormato.add(iSaltoLinea);

        menuTipoFuente = new JMenu("Tipo de Fuente");
        menuFormato.add(menuTipoFuente);

        iFuenteArial = new JMenuItem("Arial");
        iFuenteArial.addActionListener(this);
        iFuenteArial.setActionCommand("Arial");
        menuTipoFuente.add(iFuenteArial);

        iFuenteCSMS = new JMenuItem("Comic Sans MS");
        iFuenteCSMS.addActionListener(this);
        iFuenteCSMS.setActionCommand("Comic Sans MS");
        menuTipoFuente.add(iFuenteCSMS);

        iFuenteTNR = new JMenuItem("Times New Roman");
        iFuenteTNR.addActionListener(this);
        iFuenteTNR.setActionCommand("Times New Roman");
        menuTipoFuente.add(iFuenteTNR);

        menuTamanoFuente = new JMenu("Tamaño de Fuente");
        menuFormato.add(menuTamanoFuente);

        iFuenteTamano8 = new JMenuItem("8");
        iFuenteTamano8.addActionListener(this);
        iFuenteTamano8.setActionCommand("8");
        menuTamanoFuente.add(iFuenteTamano8);

        iFuenteTamano12 = new JMenuItem("12");
        iFuenteTamano12.addActionListener(this);
        iFuenteTamano12.setActionCommand("12");
        menuTamanoFuente.add(iFuenteTamano12);

        iFuenteTamano16 = new JMenuItem("16");
        iFuenteTamano16.addActionListener(this);
        iFuenteTamano16.setActionCommand("16");
        menuTamanoFuente.add(iFuenteTamano16);

        iFuenteTamano20 = new JMenuItem("20");
        iFuenteTamano20.addActionListener(this);
        iFuenteTamano20.setActionCommand("20");
        menuTamanoFuente.add(iFuenteTamano20);

        iFuenteTamano24 = new JMenuItem("24");
        iFuenteTamano24.addActionListener(this);
        iFuenteTamano24.setActionCommand("24");
        menuTamanoFuente.add(iFuenteTamano24);

        iFuenteTamano28 = new JMenuItem("28");
        iFuenteTamano28.addActionListener(this);
        iFuenteTamano28.setActionCommand("28");
        menuTamanoFuente.add(iFuenteTamano28);
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
            case "Salto de Linea":
                funcionesFormato.SaltoLinea();
                break;
        }
    }
}
