package EditorTexto;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

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

        FileDialog fd = new FileDialog(app.ventana, "Abrir", FileDialog.LOAD);
        fd.setVisible(true);

        // Busca la dirección y el nombre del archivo
        if(fd.getFile() != null) {
            NombreArchivo = fd.getFile();
            DireccionArchivo = fd.getDirectory();
            app.ventana.setTitle(NombreArchivo);
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(DireccionArchivo + NombreArchivo));

            app.textArea.setText("");

            String line = null;

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

        // Sobre escribe el archivo
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

    // Menú ver
    public String Comparar() {
        FileDialog fd1 = new FileDialog(app.ventana, "Seleccionar archivo 1", FileDialog.LOAD);
        fd1.setVisible(true);
        FileDialog fd2 = new FileDialog(app.ventana, "Seleccionar archivo 2", FileDialog.LOAD);
        fd2.setVisible(true);

        if (fd1.getFile() != null && fd2.getFile() != null) {
            String archivo1 = fd1.getDirectory() + fd1.getFile();
            String archivo2 = fd2.getDirectory() + fd2.getFile();

            try {
                String contenido1 = new String(Files.readAllBytes(Paths.get(archivo1)));
                String contenido2 = new String(Files.readAllBytes(Paths.get(archivo2)));

                if (contenido1.equals(contenido2)) {
                    return "Los archivos son iguales.";
                } else {
                    return "Los archivos son diferentes.";
                }
            } catch (Exception e) {
                return "Error al comparar los archivos.";
            }
        } else {
            return "No se seleccionaron dos archivos.";
        }
    }
}
