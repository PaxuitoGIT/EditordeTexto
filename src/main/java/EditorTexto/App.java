package EditorTexto;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class App implements ActionListener {

    JFrame ventana;

    // Texto
    JTextArea textArea;
    JScrollPane scrollVentana;
    boolean SaltoLineaOn = false;
    // Menú Barra
    JMenuBar barraMenu;
    JMenu menuArchivo, menuEditar, menuFormato, menuVer, menuAgenda, menuDibujo;
    // Archivo Menú Items
    JMenuItem iNuevo, iAbrir, iGuardar, iGuardarComo, iSalir;
    // Editar Menú
    JMenuItem iDeshacer, iRehacer;
    //Formato Menú Items
    JMenuItem iSaltoLinea, iFuenteArial, iFuenteCSMS, iFuenteTNR, iFuenteTamano8, iFuenteTamano12, iFuenteTamano16, iFuenteTamano20, iFuenteTamano24, iFuenteTamano28;
    JMenu menuTamanoFuente, menuTipoFuente;
    //Ver Menú Items
    JMenuItem iComparar, iAnalizarTexto, iBuscarPalabra;
    // Menú Agenda
    JMenuItem iAgregarContacto, iMostrarContacto;
    // Menú Dibujo
    JMenuItem iDibujar;
    // Funciones
    FuncionesArchivo funcionesArchivo = new FuncionesArchivo(this);
    FuncionesFormato funcionesFormato = new FuncionesFormato(this);
    FuncionesEditar funcionesEditar = new FuncionesEditar(this);
    FuncionesVer funcionesVer = new FuncionesVer(this, funcionesArchivo);
    FuncionesContacto funcionesContacto = new FuncionesContacto(this);
    FuncionesMiscelaneas funcionesMiscelaneas = new FuncionesMiscelaneas();
    FuncionesDibujo funcionesDibujo = new FuncionesDibujo(this);

    public static List<FuncionesContacto.Contacto> listaContactos = new ArrayList<>();
    UndoManager um = new UndoManager();

    public static void main( String[] args ) {

        new App();

    }

    // Constructor
    public App() {

        CrearVentana();
        CrearTextArea();
        CrearBarraMenu();
        CrearItemsMenuArchivo();
        crearItemsMenuEditar();
        CrearItemsMenuFormato();
        crearItemsMenuVer();
        crearItemsMenuAgenda();
        crearItemsMenuDibujo();

        // Formato predeterminado
        funcionesFormato.FuenteSelect = "Arial";
        funcionesFormato.crearFuente(12);
        funcionesFormato.SaltoLinea();
        ventana.setVisible(true);
    }

    // Crea la ventana de la aplicación
    public void CrearVentana() {

        ventana = new JFrame("Editor de Texto");
        ventana.setSize(800, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Agregar etiqueta de posición del ratón
        ventana.add(funcionesMiscelaneas.getMousePositionLabel(), BorderLayout.SOUTH);

    }

    // Crea el área de texto
    public void CrearTextArea() {

        textArea = new JTextArea();

        // Deshacer y Rehacer
        textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e) {
                um.addEdit(e.getEdit());
            }
        });

        // Mouse Tracking
        funcionesMiscelaneas.addMouseTracking(textArea);

        // Barra de desplazamiento
        scrollVentana = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollVentana.setBorder(BorderFactory.createEmptyBorder());
        ventana.add(scrollVentana);

    }

    // Crea la barra de menú
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

        menuAgenda = new JMenu("Agenda");
        barraMenu.add(menuAgenda);

        menuDibujo = new JMenu("Dibujo");
        barraMenu.add(menuDibujo);

    }

    // Crea los items del menú Archivo
    public void CrearItemsMenuArchivo() {

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

    // Crea los items del menú Editar
    public void crearItemsMenuEditar() {
        iDeshacer = new JMenuItem("Deshacer");
        iDeshacer.addActionListener(this);
        iDeshacer.setActionCommand("Deshacer");
        menuEditar.add(iDeshacer);

        iRehacer = new JMenuItem("Rehacer");
        iRehacer.addActionListener(this);
        iRehacer.setActionCommand("Rehacer");
        menuEditar.add(iRehacer);
    }

    // Crea los items del menú Formato
    public void CrearItemsMenuFormato() {
        iSaltoLinea = new JMenuItem("Salto de Linea: Desactivado");
        iSaltoLinea.addActionListener(this);
        iSaltoLinea.setActionCommand("Salto de Linea");
        menuFormato.add(iSaltoLinea);

        menuTipoFuente = new JMenu("Tipo de Fuente");
        menuFormato.add(menuTipoFuente);

        // Fuentes
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

        // Tamaños de fuente
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

    // Crea los items del menú Ver
    public void crearItemsMenuVer() {
        iComparar = new JMenuItem("Comparar");
        iComparar.addActionListener(this);
        iComparar.setActionCommand("Comparar");
        menuVer.add(iComparar);

        iAnalizarTexto = new JMenuItem("Analizar Texto");
        iAnalizarTexto.addActionListener(this);
        iAnalizarTexto.setActionCommand("AnalizarTexto");
        menuVer.add(iAnalizarTexto);

        iBuscarPalabra = new JMenuItem("Buscar Palabra");
        iBuscarPalabra.addActionListener(this);
        iBuscarPalabra.setActionCommand("BuscarPalabra");
        menuVer.add(iBuscarPalabra);
    }

    public void crearItemsMenuAgenda() {
        iAgregarContacto = new JMenuItem("Agregar Contacto");
        iAgregarContacto.addActionListener(this);
        iAgregarContacto.setActionCommand("AgregarContacto");
        menuAgenda.add(iAgregarContacto);

        iMostrarContacto = new JMenuItem("Mostrar Contacto");
        iMostrarContacto.addActionListener(this);
        iMostrarContacto.setActionCommand("MostrarContacto");
        menuAgenda.add(iMostrarContacto);
    }

    public void crearItemsMenuDibujo() {
        iDibujar = new JMenuItem("Dibujar");
        iDibujar.addActionListener(this);
        iDibujar.setActionCommand("Dibujar");
        menuDibujo.add(iDibujar);
    }

    // Acciones de los botones
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
            case "Deshacer":
                funcionesEditar.Deshacer();
                break;
            case "Rehacer":
                funcionesEditar.Rehacer();
                break;
            case "Salto de Linea":
                funcionesFormato.SaltoLinea();
                break;
            case "Arial":
                funcionesFormato.FuenteSelect = "Arial";
                funcionesFormato.crearFuente(textArea.getFont().getSize());
                break;
            case "Comic Sans MS":
                funcionesFormato.FuenteSelect = "Comic Sans MS";
                funcionesFormato.crearFuente(textArea.getFont().getSize());
                break;
            case "Times New Roman":
                funcionesFormato.FuenteSelect = "Times New Roman";
                funcionesFormato.crearFuente(textArea.getFont().getSize());
                break;
            case "8":
                funcionesFormato.crearFuente(8);
                break;
            case "12":
                funcionesFormato.crearFuente(12);
                break;
            case "16":
                funcionesFormato.crearFuente(16);
                break;
            case "20":
                funcionesFormato.crearFuente(20);
                break;
            case "24":
                funcionesFormato.crearFuente(24);
                break;
            case "28":
                funcionesFormato.crearFuente(28);
                break;
            case "Comparar":
                String resultado = funcionesVer.Comparar();
                textArea.setText(resultado);
                break;
            case "AnalizarTexto":
                String resultadoAnalizar = funcionesVer.AnalizarTexto();
                textArea.setText(resultadoAnalizar);
                break;
            case "BuscarPalabra":
                String palabra = JOptionPane.showInputDialog("Ingrese la palabra a buscar:");
                if (palabra != null && !palabra.isEmpty()) {
                    int contador = funcionesVer.buscarPalabra(textArea.getText(), palabra);
                    JOptionPane.showMessageDialog(ventana, "La palabra '" + palabra + "' aparece " + contador + " veces.");
                }
                break;
            case "AgregarContacto":
                funcionesContacto.agregarContacto();
                break;
            case "MostrarContacto":
                funcionesContacto.mostrarContacto();
                break;
            case "Dibujar":
                funcionesDibujo.Dibujar();
                break;
        }
    }
}
