package src.archivos; // Define el paquete de la carpeta archivos

public class TestFile { // Clase de prueba para ser leida por FileInputStream
    private String mensaje; // Atributo de prueba para el archivo

    public TestFile() { // Constructor de la clase TestFile
        this.mensaje = "¡Hola estrellitas! Este archivo de prueba ha sido leido mediante FileInputStream."; // Inicializa el mensaje
    }

    public void mostrarMensaje() { // Metodo para mostrar el contenido
        System.out.println(mensaje); // Imprime el mensaje en consola
    }
}