package EditorTexto;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FuncionesVer {
    App app;
    FuncionesArchivo funcionesArchivo;

    // Heredar el constructor de la clase App y FuncionesArchivo
    public FuncionesVer(App app, FuncionesArchivo funcionesArchivo) {
        this.app = app;
        this.funcionesArchivo = funcionesArchivo;
    }

    // Comparar dos archivos y devuelve si son iguales o diferentes
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

    // Analiza el texto de un archivo y devuelve el número de palabras y la frecuencia de cada una
    public String AnalizarTexto() {
        // Lee el archivo seleccionado
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
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append(" ");
            }
            br.close();

            // Divide el texto en palabras
            String[] palabras = sb.toString().split("\\s+");

            // Cuenta el número de palabras
            int numPalabras = palabras.length;
            resultado.append("Número de palabras: ").append(numPalabras).append("\n");

            // Cuenta la frecuencia de cada palabra
            Map<String, Integer> frecuenciaPalabras = new HashMap<>();
            for (String palabra : palabras) {
                String palabraLimpia = palabra.replaceAll("[^a-zA-Z]", "").toLowerCase();
                if (!palabraLimpia.isEmpty()) {
                    frecuenciaPalabras.put(palabraLimpia, frecuenciaPalabras.getOrDefault(palabraLimpia, 0) + 1);
                }
            }

            // Ordena las palabras por frecuencia en orden descendente
            resultado.append("Frecuencia de palabras:").append("\n");
            frecuenciaPalabras.entrySet().stream()
                    .sorted(Map.Entry.<String,Integer>comparingByValue().reversed())
                    .forEach(entry -> resultado.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n"));

        } catch (Exception e) {
            resultado.append("Error al abrir el archivo");
        }
        return resultado.toString();
    }

    // Busca una palabra en un texto y devuelve el número de veces que aparece
    public int buscarPalabra(String texto, String palabra) {
        String[] palabras = texto.split("\\s+");
        int contador = 0;
        for (String p : palabras) {
            if (p.equalsIgnoreCase(palabra)) {
                contador++;
            }
        }
        return contador;
    }
}
