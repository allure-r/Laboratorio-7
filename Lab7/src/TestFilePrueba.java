package src;

import java.io.FileInputStream; // Importa FileInputStream para leer bytes de un archivo
import java.io.FileNotFoundException; // Importa la excepción para archivos no encontrados
import java.io.IOException; // Importa la excepción general de E/S
import javax.swing.JFrame; // Importa JFrame para la interfaz gráfica

public class TestFilePrueba { // Clase principal para probar la lectura de archivos con FileInputStream
    public static void main(String[] args) throws IOException { // Método principal ejecutable
        FileInputStream file; // Declara el flujo para leer bytes del archivo
        byte b[] = new byte[1024]; // Crea un arreglo de bytes con capacidad de 1024
        try {
            file = new FileInputStream("src/archivos/TestFile.java"); // Inicializa el flujo apuntando al archivo de texto
            file.read(b); // Lee los bytes del archivo y los almacena en el arreglo
            String s = new String(b); // Convierte el arreglo de bytes a un objeto String
            ViewFile view = new ViewFile(s); // Crea una instancia de la ventana ViewFile pasando el texto leído
            view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configura la operación de cierre de la ventana
            view.setSize(400, 150); // Establece las dimensiones de la ventana
            view.setVisible(true); // Hace visible la ventana en pantalla
        } catch (FileNotFoundException e) { // Captura la excepción si el archivo no existe
            System.out.println(e.getMessage()); // Imprime el mensaje del error
        } catch (IOException e) { // Captura excepciones generales de entrada/salida
            System.out.println(e.getMessage()); // Imprime el mensaje del error
        }
    }
}
