package src.actividad5;

public class Persona { // Clase Persona para almacenar los datos de cada contacto
    protected String nombre; // Almacena el nombre del contacto
    protected String telefono; // Almacena el teléfono del contacto
    protected String direccion; // Almacena la dirección del contacto

    public Persona(String nombre, String telefono, String direccion) { // Constructor de Persona
        this.nombre = nombre; // Asigna el nombre
        this.telefono = telefono; // Asigna el teléfono
        this.direccion = direccion; // Asigna la dirección
    }

    public String getNombre() { // Método get() para acceder al nombre
        return nombre; // Retorna el nombre del contacto
    }

    @Override
    public String toString() { // Método toString para representar los datos de la persona en formato de texto
        return this.nombre + "\t" + this.telefono + "\t" + this.direccion + "\n"; // Retorna la cadena tabulada con los datos
    }
}