package ejercicio3;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsolaView { // Clase ConsolaView para gestionar la interfaz de usuario en consola
    private Scanner scanner = new Scanner(System.in); // Objeto Scanner para leer la entrada del teclado

    public int mostrarMenu() { // Método para mostrar el menú principal y capturar la opción del usuario
        int opcion = 0; // Almacena la opción seleccionada
        boolean valida = false; // Bandera para controlar la validez de la entrada
        while (!valida) { // Bucle para asegurar que se ingrese una opción válida
            try {
                System.out.println("\n=== GESTOR DE EMPLEADOS ===");
                System.out.println("1. Listar todos los empleados");
                System.out.println("2. Agregar un nuevo empleado");
                System.out.println("3. Buscar un empleado por su número");
                System.out.println("4. Eliminar un empleado por su número");
                System.out.println("5. Salir del programa");
                System.out.print("Elija una opción: ");
                opcion = scanner.nextInt(); // Lee la opción entera ingresada
                scanner.nextLine(); // Limpia el buffer del scanner
                valida = true; // Marca la entrada como válida para salir del bucle
            } catch (InputMismatchException e) { // Captura si el usuario ingresa algo que no es un entero
                System.out.println("Error: Debe ingresar un número entero.");
                scanner.nextLine(); // Limpia el buffer ante el error
            }
        }
        return opcion; // Retorna la opción elegida
    }

    public void mostrarLista(ArrayList<Empleado> lista) { // Método para mostrar la lista completa de empleados
        if (lista.isEmpty()) { // Verifica si la lista está vacía
            System.out.println("No hay empleados registrados en el archivo.");
        } else {
            System.out.println("\n--- LISTA DE EMPLEADOS ---");
            for (Empleado e : lista) { // Recorre la lista de empleados
                System.out.println(e); // Imprime los datos de cada empleado utilizando su toString
            }
        }
    }

    public Empleado pedirDatosEmpleado() { // Método para solicitar y validar los datos de un nuevo empleado
        int numero = 0; // Almacena el número del empleado
        double sueldo = 0.0; // Almacena el sueldo del empleado
        String nombre = ""; // Almacena el nombre del empleado
        boolean valida; // Bandera de validación para los campos

        valida = false;
        while (!valida) { // Bucle para validar el número de empleado
            try {
                System.out.print("Ingrese el número del empleado (int, mayor a 0): ");
                numero = scanner.nextInt(); // Lee el número entero
                scanner.nextLine(); // Limpia el buffer
                if (numero <= 0) { // Valida que sea positivo
                    System.out.println("Error: El número debe ser mayor a 0.");
                } else {
                    valida = true; // Marca como válido si cumple la condición
                }
            } catch (InputMismatchException e) { // Captura si se ingresa un tipo de dato incorrecto
                System.out.println("Error: Debe ingresar un número entero.");
                scanner.nextLine();
            }
        }

        valida = false;
        while (!valida) { // Bucle para validar el nombre del empleado
            System.out.print("Ingrese el nombre del empleado (solo letras y espacios): ");
            nombre = scanner.nextLine().trim(); // Lee el nombre y quita espacios extremos
            if (nombre.isEmpty()) { // Verifica que no esté vacío
                System.out.println("Error: El nombre no puede estar vacío.");
            } else if (!nombre.matches("^[a-zA-Z\\s]+$")) { // Valida que contenga solo letras y espacios
                System.out.println("Error: El nombre solo debe contener letras y espacios.");
            } else {
                valida = true; // Marca como válido si pasa todas las validaciones
            }
        }

        valida = false;
        while (!valida) { // Bucle para validar el sueldo del empleado
            try {
                System.out.print("Ingrese el sueldo del empleado (double, mayor a 0): ");
                sueldo = scanner.nextDouble(); // Lee el número decimal
                scanner.nextLine(); // Limpia el buffer
                if (sueldo <= 0) { // Valida que sea mayor a cero
                    System.out.println("Error: El sueldo debe ser mayor a 0.");
                } else {
                    valida = true; // Marca como válido si cumple
                }
            } catch (InputMismatchException e) { // Captura errores de tipo de dato decimal
                System.out.println("Error: Debe ingresar un número decimal válido.");
                scanner.nextLine();
            }
        }

        return new Empleado(numero, nombre, sueldo); // Retorna una nueva instancia de Empleado
    }

    public int pedirNumeroEmpleado() { // Método para solicitar únicamente un número de empleado
        int numero = 0; // Almacena el número ingresado
        boolean valida = false; // Bandera de validación
        while (!valida) { // Bucle para asegurar el ingreso de un entero correcto
            try {
                System.out.print("Ingrese el número del empleado: ");
                numero = scanner.nextInt(); // Lee el número
                scanner.nextLine(); // Limpia el buffer
                valida = true; // Sale del bucle si es correcto
            } catch (InputMismatchException e) { // Captura errores de entrada
                System.out.println("Error: Debe ingresar un número entero."); // Muestra un mensaje de error 
                scanner.nextLine(); // Limpia el buffer ante el error
            }
        }
        return numero; // Retorna el número del empleado
    }

    public void mostrarMensaje(String mensaje) { // Método genérico para mostrar mensajes en pantalla
        System.out.println(mensaje); // Imprime el texto recibido por parámetro
    }
}