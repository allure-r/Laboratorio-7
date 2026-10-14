import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class DemoJFileChooser extends JFrame {
    private JTextArea area = new JTextArea(20, 65);

    public DemoJFileChooser() {
        super("Informacion de archivos con JFileChooser");
        area.setEditable(false);
        add(new JScrollPane(area));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        abrirSelector(); // Seleccion inicial
    }

    private void abrirSelector() {
        JFileChooser selector = new JFileChooser();
        selector.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        if (selector.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            mostrarInformacion(selector.getSelectedFile().toPath());
        } else {
            area.setText("No se selecciono ningun archivo ni directorio.");
        }
    }

    private void mostrarInformacion(Path ruta) {
        StringBuilder texto = new StringBuilder();
        try {
            texto.append("Nombre: ").append(ruta.getFileName()).append('\n');
            texto.append("Es directorio: ").append(Files.isDirectory(ruta)).append('\n');
            texto.append("Ruta absoluta: ").append(ruta.toAbsolutePath()).append('\n');
            texto.append("Modificacion: ").append(Files.getLastModifiedTime(ruta)).append('\n');
            texto.append("Tamano: ").append(Files.size(ruta)).append(" bytes\n");
            if (Files.isDirectory(ruta)) {
                texto.append("\nContenido:\n");
                try (DirectoryStream<Path> datos = Files.newDirectoryStream(ruta)) {
                    for (Path elemento : datos) {
                        texto.append(" - ").append(elemento.getFileName()).append('\n');
                    }
                }
            }
            area.setText(texto.toString());
        } catch (IOException | SecurityException e) {
            area.setText("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DemoJFileChooser::new);
    }
}
