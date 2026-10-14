package ejercicio3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorEmpleados { // Clase GestorEmpleados
    private String nombreArchivo = "empleados.txt"; // Nombre del archivo donde se almacenan los datos

    public ArrayList<Empleado> leerEmpleados() { // Método para leer los empleados desde el archivo
        ArrayList<Empleado> lista = new ArrayList<>(); // Crea una lista dinámica para los empleados
        File archivo = new File(nombreArchivo); // Crea un objeto File con la ruta del archivo

        if (!archivo.exists()) { // Verifica si el archivo no existe todavía
            return lista; // Retorna la lista vacía si el archivo no existe
        }

        try (Scanner scanner = new Scanner(archivo)) { // Inicializa el Scanner para leer el archivo con try-with-resources
            int lineaNum = 0; // Contador para llevar el número de línea actual
            while (scanner.hasNextLine()) { // Bucle para recorrer cada línea del archivo
                lineaNum++; // Incrementa el contador de líneas
                String linea = scanner.nextLine(); // Lee la línea completa
                String[] partes = linea.split(";"); // Divide la línea usando el punto y coma como separador
                if (partes.length == 3) { // Valida que la línea tenga exactamente 3 partes
                    int numero = Integer.parseInt(partes[0].trim()); // Convierte la primera parte a entero (número)
                    String nombre = partes[1].trim(); // Obtiene la segunda parte (nombre)
                    double sueldo = Double.parseDouble(partes[2].trim()); // Convierte la tercera parte a double (sueldo)
                    lista.add(new Empleado(numero, nombre, sueldo)); // Crea y añade el empleado a la lista
                } else {
                    System.out.println("Advertencia: Línea " + lineaNum + " con formato incorrecto, se ignora."); // Mensaje si la línea
                                                                                                             // no tiene el formato correcto
                }
            }
        } catch (FileNotFoundException e) { // Captura la excepción si no se encuentra el archivo
            System.out.println("Error: No se encontró el archivo de empleados.");
        } catch (NumberFormatException e) { // Captura la excepción si falla la conversión numérica
            System.out.println("Error al interpretar los datos numéricos del archivo: " + e.getMessage());
        } catch (IllegalArgumentException e) { // Captura la excepción de validación de los datos
            System.out.println("Error de validación en los datos del archivo: " + e.getMessage());
        }
        return lista; // Retorna la lista con los empleados leídos
    }

    public boolean guardarArchivo(ArrayList<Empleado> lista) { // Método para guardar la lista de empleados en el archivo
        try (PrintWriter salida = new PrintWriter(new FileWriter(nombreArchivo, false))) { // Inicializa PrintWriter para
                                                                                                  // sobrescribir el archivo
            for (Empleado emp : lista) { // Recorre cada empleado de la lista
                salida.println(emp.getNumero() + ";" + emp.getNombre() + ";" + emp.getSueldo()); // Escribe los datos separados
                                                                                                 // por punto y coma
            }
            return true; // Retorna verdadero si se guardó con éxito
        } catch (IOException e) { // Captura errores de escritura en el archivo
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
            return false; // Retorna falso si ocurrió un error
        }
    }

    public boolean agregarEmpleado(Empleado nuevo) { // Método para agregar un nuevo empleado
        ArrayList<Empleado> lista = leerEmpleados(); // Lee la lista actual de empleados del archivo

        for (Empleado e : lista) { // Recorre la lista para verificar duplicados
            if (e.getNumero() == nuevo.getNumero()) { // Compara si el número de empleado ya existe
                return false; // Retorna falso si ya está registrado
            }
        }

        lista.add(nuevo); // Añade el nuevo empleado a la lista
        return guardarArchivo(lista); // Guarda la lista actualizada en el archivo y retorna el resultado
    }

    public Empleado buscarEmpleado(int numero) { // Método para buscar un empleado por su número
        ArrayList<Empleado> lista = leerEmpleados(); // Lee la lista de empleados del archivo
        for (Empleado e : lista) { // Recorre la lista buscando coincidencias
            if (e.getNumero() == numero) { // Compara si el número coincide
                return e; // Retorna el empleado encontrado
            }
        }
        return null; // Retorna nulo si no se encuentra el empleado
    }

    public boolean eliminarEmpleado(int numero) { // Método para eliminar un empleado por su número
        ArrayList<Empleado> lista = leerEmpleados(); // Lee la lista actual de empleados

        boolean eliminado = lista.removeIf(e -> e.getNumero() == numero); // Remueve de la lista el empleado que coincida con el número

        if (eliminado) { // Si se eliminó exitosamente de la lista
            return guardarArchivo(lista); // Actualiza el archivo guardando la nueva lista y retorna el resultado
        }
        return false; // Retorna falso si no se pudo eliminar
    }
}