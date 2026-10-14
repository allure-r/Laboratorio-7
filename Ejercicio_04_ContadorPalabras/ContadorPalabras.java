import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ContadorPalabras {
    public static void main(String[] args) {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Seleccione un archivo de texto");
        while (true) { // Reintenta cuando la ruta es invalida
            if (selector.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
                System.out.println("Seleccion cancelada.");
                return;
            }
            Path archivo = selector.getSelectedFile().toPath();
            if (!Files.isRegularFile(archivo) || !Files.isReadable(archivo)) {
                JOptionPane.showMessageDialog(null,
                        "El archivo no existe o no se puede leer. Intente otra vez.");
                continue;
            }
            try {
                contar(archivo);
                return;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "No se pudo leer el archivo: " + e.getMessage());
            }
        }
    }

    private static void contar(Path archivo) throws IOException {
        int lineas = 0, palabras = 0, caracteres = 0;
        Map<String, Integer> frecuencias = new HashMap<>();
        try (BufferedReader entrada = Files.newBufferedReader(
                archivo, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = entrada.readLine()) != null) {
                lineas++;
                caracteres += linea.length(); // No cuenta salto de linea
                StringBuilder palabra = new StringBuilder();
                for (int i = 0; i <= linea.length(); i++) {
                    char c = i < linea.length() ? linea.charAt(i) : ' ';
                    if (Character.isLetterOrDigit(c)) {
                        palabra.append(Character.toLowerCase(c));
                    } else if (palabra.length() > 0) {
                        String clave = palabra.toString();
                        frecuencias.put(clave, frecuencias.getOrDefault(clave, 0) + 1);
                        palabras++;
                        palabra.setLength(0); // Inicia otra palabra
                    }
                }
            }
        }
        System.out.println("Archivo: " + archivo.toAbsolutePath());
        System.out.println("Total de lineas: " + lineas);
        System.out.println("Total de palabras: " + palabras);
        System.out.println("Total de caracteres (sin saltos): " + caracteres);
        System.out.printf("Promedio de palabras por linea: %.2f%n",
                lineas == 0 ? 0.0 : (double) palabras / lineas);
        System.out.println("Palabras mas frecuentes (hasta 10):");
        List<Map.Entry<String, Integer>> orden = new ArrayList<>(frecuencias.entrySet());
        orden.sort(Comparator.<Map.Entry<String, Integer>>comparingInt(
                Map.Entry::getValue).reversed().thenComparing(Map.Entry::getKey));
        for (int i = 0; i < Math.min(10, orden.size()); i++) {
            Map.Entry<String, Integer> item = orden.get(i);
            System.out.println("- " + item.getKey() + ": " + item.getValue());
        }
    }
}
