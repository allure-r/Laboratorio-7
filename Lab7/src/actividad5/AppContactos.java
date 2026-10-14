package src.actividad5;

public class AppContactos { // Clase principal AppContactos para iniciar la aplicación
    public static void main(String[] args) { // Método main ejecutable
        Agenda agenda = new Agenda(); // Crea una nueva instancia de la agenda cargando los datos
        agenda.bucle(); // Inicia el bucle de interacción para buscar contactos
    }
}