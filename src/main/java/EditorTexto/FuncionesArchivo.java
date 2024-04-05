package EditorTexto;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
public class FuncionesArchivo {

    App app;
    String NombreArchivo;
    String DireccionArchivo;

    // Heredar el constructor de la clase App
    public FuncionesArchivo(App app) {

        this.app = app;

    }

    // Crea un nuevo archivo
    public void newNuevo() {

        app.textArea.setText("");
        app.ventana.setTitle("Nuevo");
        NombreArchivo = null;
        DireccionArchivo = null;
    }

    // Abre un archivo
    public void Abrir() {

        // Abre un cuadro de diálogo para seleccionar el archivo
        FileDialog fd = new FileDialog(app.ventana, "Abrir", FileDialog.LOAD);
        fd.setVisible(true);

        // Busca la dirección y el nombre del archivo
        if(fd.getFile() != null) {
            NombreArchivo = fd.getFile();
            DireccionArchivo = fd.getDirectory();
            app.ventana.setTitle(NombreArchivo);
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(DireccionArchivo + NombreArchivo)); // Lee el archivo

            app.textArea.setText("");

            String line;

            while((line = br.readLine()) != null) {

                app.textArea.append(line + "\n");
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error al abrir el archivo");
        }
    }

    // Guarda el archivo
    public void Guardar() {

        // Sobrescribe el archivo
        if(NombreArchivo == null) {
            GuardarComo();
        } else {
            try {
                FileWriter fw = new FileWriter(DireccionArchivo + NombreArchivo);
                fw.write(app.textArea.getText());
                app.ventana.setTitle(NombreArchivo);
                fw.close();
            } catch (Exception e) {
                System.out.println("Error al guardar el archivo");
            }

        }

    }

    // Guarda el archivo como
    public void GuardarComo() {

        FileDialog fd = new FileDialog(app.ventana, "Guardar como", FileDialog.SAVE);
        fd.setVisible(true);

        if(fd.getFile() != null) {
            NombreArchivo = fd.getFile();
            DireccionArchivo = fd.getDirectory();
            app.ventana.setTitle(NombreArchivo);
        }

        try {
            FileWriter fw = new FileWriter(DireccionArchivo + NombreArchivo);
            fw.write(app.textArea.getText());
            fw.close();

        } catch (Exception e) {
            System.out.println("Error al guardar el archivo");
        }
    }

    // Salir del programa
    public void Salir() {
        System.exit(0);
    }
}
