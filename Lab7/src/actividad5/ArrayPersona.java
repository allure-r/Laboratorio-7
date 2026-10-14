package src.actividad5;

public class ArrayPersona { // Clase ArrayPersona para gestionar un arreglo estático de objetos Persona
    public Persona arreglo[]; // Arreglo unidimensional de objetos Persona
    final int max = 128; // Capacidad máxima del arreglo
    int tamano = 0; // Contador que lleva el número actual de personas en el arreglo

    public ArrayPersona() { // Constructor de ArrayPersona
        this.arreglo = new Persona[this.max]; // Inicializa el arreglo con la capacidad máxima definida
    }

    public void printArray(String nombre) { // Método para buscar e imprimir personas que coincidan con el nombre
        for (int i = 0; i < this.tamano; i++) { // Bucle para recorrer el arreglo de contactos
            if (this.arreglo[i].getNombre().equalsIgnoreCase(nombre.trim())) { // Compara ignorando espacios y mayúsculas/minúsculas
                System.out.println(this.arreglo[i]); // Imprime los datos del contacto encontrado
            }
        }
    }

    public void addArray(Persona persona) { // Método para añadir una persona al arreglo
        if (this.tamano == max) { // Verifica si el arreglo ha alcanzado su límite máximo
            System.exit(1); // Sale del programa si está lleno
        }
        this.arreglo[this.tamano++] = persona; // Añade la persona en la posición actual e incrementa el tamaño
    }
}