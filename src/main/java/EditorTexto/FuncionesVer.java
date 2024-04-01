package EditorTexto;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class FuncionesVer {
    App app;
    FuncionesArchivo funcionesArchivo;

    public FuncionesVer(App app, FuncionesArchivo funcionesArchivo) {
        this.app = app;
        this.funcionesArchivo = funcionesArchivo;
    }

    public String AnalizarTexto() {
        FileDialog fd = new FileDialog(app.ventana, "Seleccione archivo para analizar", FileDialog.LOAD);
        fd.setVisible(true);
        if (fd.getFile() != null) {
            funcionesArchivo.NombreArchivo = fd.getFile();
            funcionesArchivo.DireccionArchivo = fd.getDirectory();
            app.ventana.setTitle(funcionesArchivo.NombreArchivo);
        }

        StringBuilder resultado = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(funcionesArchivo.DireccionArchivo + funcionesArchivo.NombreArchivo));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line).append(" ");
            }
            br.close();

            String[] palabras = sb.toString().split("\\s+");

            int numPalabras = palabras.length;
            resultado.append("Número de palabras: ").append(numPalabras).append("\n");

            Map<String, Integer> frecuenciaPalabras = new HashMap<>();
            for (String palabra : palabras) {
                String palabraLimpia = palabra.replaceAll("[^a-zA-Z]", "").toLowerCase();
                if (!palabraLimpia.isEmpty()) {
                    frecuenciaPalabras.put(palabraLimpia, frecuenciaPalabras.getOrDefault(palabraLimpia, 0) + 1);
                }
            }

            resultado.append("Frecuencia de palabras:").append("\n");
            frecuenciaPalabras.entrySet().stream()
                    .sorted(Map.Entry.<String,Integer>comparingByValue().reversed())
                    .forEach(entry -> resultado.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n"));

        } catch (Exception e) {
            resultado.append("Error al abrir el archivo");
        }
        return resultado.toString();
    }

}
