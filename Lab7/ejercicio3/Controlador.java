package ejercicio3;

public class Controlador { // Clase Controlador para gestionar la lógica de interacción entre el modelo y la vista
    private GestorEmpleados gestor; // Instancia del gestor para manejar los datos y archivos
    private ConsolaView vista; // Instancia de la vista para manejar la interacción con la consola

    public Controlador() { // Constructor de Controlador
        this.gestor = new GestorEmpleados(); // Inicializa el objeto GestorEmpleados
        this.vista = new ConsolaView(); // Inicializa el objeto ConsolaView
    }

    public void iniciar() { // Método principal para iniciar el flujo de la aplicación
        vista.mostrarMensaje("=== EMPLEADOS ALMACENADOS AL INICIAR ===");
        vista.mostrarLista(gestor.leerEmpleados()); // Lee y muestra los empleados guardados en el archivo al arrancar

        int opcion = 0; // Almacena la opción elegida en el menú
        do {
            opcion = vista.mostrarMenu(); // Muestra el menú y captura la opción del usuario
            switch (opcion) {
                case 1: // Opción para listar todos los empleados
                    vista.mostrarLista(gestor.leerEmpleados()); // Muestra la lista actualizada desde el archivo
                    break;

                case 2: // Opción para agregar un nuevo empleado
                    Empleado nuevo = vista.pedirDatosEmpleado(); // Solicita los datos del nuevo empleado por consola
                    if (gestor.agregarEmpleado(nuevo)) { // Intenta agregar y guardar el empleado
                        vista.mostrarMensaje("¡Éxito! Empleado agregado y guardado en el archivo.");
                    } else {
                        vista.mostrarMensaje("Error: Ya existe un empleado registrado con ese número o falló la escritura.");
                    }
                    break;

                case 3: // Opción para buscar un empleado por número
                    int numBuscar = vista.pedirNumeroEmpleado(); // Solicita el número a buscar
                    Empleado encontrado = gestor.buscarEmpleado(numBuscar); // Realiza la búsqueda en el archivo
                    if (encontrado != null) {
                        vista.mostrarMensaje("¡Empleado encontrado!\n" + encontrado);
                    } else {
                        vista.mostrarMensaje("No se encontró ningún empleado con el número ingresado.");
                    }
                    break;

                case 4: // Opción para eliminar un empleado por número
                    int numEliminar = vista.pedirNumeroEmpleado(); // Solicita el número del empleado a eliminar
                    if (gestor.eliminarEmpleado(numEliminar)) { // Intenta eliminar y actualizar el archivo
                        vista.mostrarMensaje("¡Éxito! Empleado eliminado y archivo actualizado.");
                    } else {
                        vista.mostrarMensaje("Error: No se pudo eliminar. El número no existe o falló la escritura.");
                    }
                    break;

                case 5: // Opción para salir del programa
                    vista.mostrarMensaje("Saliendo del programa. ¡Hasta luego!");
                    break;

                default: // Caso por defecto si la opción no es válida
                    vista.mostrarMensaje("Opción no válida. Por favor, elija un número del 1 al 5.");
            }
            System.out.println("\n--------------------------------------------------");
        } while (opcion != 5); // El bucle continúa mientras no se elija la opción de salir
    }
}