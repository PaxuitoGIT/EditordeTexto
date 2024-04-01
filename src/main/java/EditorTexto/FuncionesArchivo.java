package EditorTexto;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class FuncionesArchivo {

    App app;
    String NombreArchivo;
    String DireccionArchivo;
    public FuncionesArchivo(App app) {

        this.app = app;

    }

    public void newNuevo() {

        app.textArea.setText("");
        app.ventana.setTitle("Nuevo");
    }

    public void Abrir() {

        FileDialog fd = new FileDialog(app.ventana, "Abrir", FileDialog.LOAD);
        fd.setVisible(true);

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
}
