package src.actividad5;

import java.io.FileInputStream; // Importa FileInputStream para leer bytes del archivo
import java.io.FileNotFoundException; // Importa la excepción por si no se encuentra el archivo
import java.io.IOException; // Importa la excepción general de E/S
import java.io.InputStream; // Importa InputStream para manejar flujos de entrada

public class Agenda { // Clase Agenda para coordinar la carga y búsqueda de contactos
    ArrayPersona lista; // Lista dinámica basada en arreglo para almacenar los contactos
    FileInputStream agendaFile; // Flujo de entrada para leer el archivo de la agenda
    final int longLinea = 32; // Longitud máxima en bytes para leer cada línea

    public Agenda() { // Constructor de Agenda
        this.lista = cargaContactos(); // Carga los contactos al inicializar la agenda
    }

    public void bucle() { // Método principal para interactuar con el usuario por consola
        String nombre; // Variable para almacenar el nombre ingresado
        System.out.println("Introduzca un nombre o <Enter>:");
        try {
            while ((nombre = leeEntrada(System.in)) != null && !nombre.trim().isEmpty()) { // Sale si se presiona Enter
                lista.printArray(nombre); // Busca y muestra los contactos que coincidan
                System.out.println("Introduzca un nombre o <Enter>:");
            }
            System.out.println("Programa finalizado."); // Mensaje de salida al presionar Enter
        } catch (Exception e) { // Captura cualquier excepción imprevista durante el bucle
            System.out.println("Error: " + e.getMessage());
        }
    }

    private String leeEntrada(InputStream entrada) throws IOException { // Método para leer líneas desde un flujo
        byte chars[] = new byte[longLinea]; // Arreglo de bytes para almacenar la lectura
        for (int i = 0; i < longLinea; i++) {
            chars[i] = 0; // Limpia el arreglo de bytes
        }
        int contador = 0; // Contador de bytes leídos
        int byteLeido; // Variable para almacenar el byte leído como int

        while (contador < longLinea) { // Bucle mientras no se exceda la longitud máxima
            byteLeido = entrada.read(); // Lee un byte del flujo como int (0-255 o -1 si EOF)
            if (byteLeido == -1) { // Verifica si se llegó al final del flujo
                if (contador == 0) { // Si no se leyó ningún byte
                    return null; // Retorna null indicando que no hay más datos
                }
                break; // Si ya se leyeron bytes, sale del bucle para retornar la última línea
            }
            if (byteLeido == '\n') { // Si el byte es salto de línea
                break; // Sale del bucle, la línea está completa
            }
            if (byteLeido == '\r') { // Si el byte es retorno de carro (Windows)
                continue; // Lo ignora y continúa leyendo el siguiente byte
            }
            chars[contador++] = (byte) byteLeido; // Almacena el byte y avanza el contador
        }
        return new String(chars, 0, contador); // Convierte los bytes leídos a String
    }

    private Persona cargaAgenda() throws IOException { // Método para leer un bloque y crear un objeto Persona
        String nombre = leeEntrada(agendaFile); // Lee el nombre del archivo
        if (nombre == null || nombre.trim().isEmpty()) { // Si no hay más nombres
            return null; // Retorna nulo
        }
        String telefono = leeEntrada(agendaFile); // Lee el teléfono del archivo
        String direccion = leeEntrada(agendaFile); // Lee la dirección del archivo
        // Verifica que teléfono y dirección no sean null antes de hacer trim
        if (telefono == null) telefono = ""; // Si el teléfono es null, asigna cadena vacía
        if (direccion == null) direccion = ""; // Si la dirección es null, asigna cadena vacía
        return new Persona(nombre.trim(), telefono.trim(), direccion.trim()); // Retorna la Persona
    }

    private ArrayPersona cargaContactos() { // Método para cargar todos los contactos del archivo
        ArrayPersona directorio = new ArrayPersona(); // Crea una nueva instancia contenedora
        Persona nuevaPersona; // Variable temporal para cada contacto leído
        try {
            agendaFile = new FileInputStream("src/actividad5/agenda.txt"); // Abre el archivo
            while (true) { // Bucle infinito hasta agotar el archivo
                nuevaPersona = cargaAgenda(); // Carga los datos de la persona
                if (nuevaPersona == null) { // Si ya no hay datos
                    return directorio; // Retorna el directorio completo
                }
                directorio.addArray(nuevaPersona); // Agrega la persona al arreglo
            }
        } catch (FileNotFoundException e) { // Captura si el archivo no existe
            System.out.println("No hay archivo de agenda: " + e.getMessage());
            System.exit(1);
        } catch (IOException e) { // Captura errores de lectura
            System.out.println("Error de lectura: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) { // Captura errores generales
            System.out.println("Error en la carga de los contactos: " + e.getMessage());
            e.printStackTrace(); // Muestra la traza completa del error para depuración
            System.exit(1);
        }
        return directorio; // Retorna el directorio con los contactos cargados
    }
}