package src;

import javax.swing.JFrame; // Importa JFrame para la ventana gráfica
import javax.swing.JTextArea; // Importa JTextArea para mostrar texto multilínea

public class ViewFile extends JFrame { // Clase ViewFile que extiende de JFrame para mostrar la interfaz
    private JTextArea areaTexto; // Componente de área de texto para mostrar el contenido

    public ViewFile(String s) { // Constructor de la clase ViewFile que recibe el texto
        super("Mostrando el contenido de un archivo"); // Llama al constructor de la superclase con el título de la ventana
        areaTexto = new JTextArea(s, 5, 40); // Inicializa el área de texto con el contenido, filas y columnas
        add(areaTexto); // Añade el área de texto al contenedor de la ventana
    }
}
