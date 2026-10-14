package ejercicio3;

public class Empleado { // Clase Empleado
    private int numero; // Almacena el número del empleado
    private String nombre; // Almacena el nombre del empleado
    private double sueldo; // Almacena el sueldo del empleado

    public Empleado(int numero, String nombre, double sueldo) { // Constructor de Empleado
        setNumero(numero); // Asigna y valida el número del empleado
        setNombre(nombre); // Asigna y valida el nombre del empleado
        setSueldo(sueldo); // Asigna y valida el sueldo del empleado
    }

    public int getNumero() { // Método get() para acceder al número
        return this.numero; // Retorna el número del empleado
    }

    public void setNumero(int numero) { // Método set() para modificar el número
        if (numero <= 0) { // Valida que el número sea positivo
            throw new IllegalArgumentException("El número de empleado debe ser positivo.");
        }
        this.numero = numero; // Asigna el número del empleado
    }

    public String getNombre() { // Método get() para acceder al nombre
        return this.nombre; // Retorna el nombre del empleado
    }

    public void setNombre(String nombre) { // Método set() para modificar el nombre
        if (nombre == null || nombre.trim().isEmpty()) { // Valida que el nombre no esté vacío
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (!nombre.trim().matches("^[a-zA-Z\\s]+$")) { // Valida que contenga solo letras y espacios
            throw new IllegalArgumentException("El nombre solo debe contener letras y espacios.");
        }
        this.nombre = nombre.trim(); // Asigna el nombre limpio del empleado
    }

    public double getSueldo() { // Método get() para acceder al sueldo
        return this.sueldo; // Retorna el sueldo del empleado
    }

    public void setSueldo(double sueldo) { // Método set() para modificar el sueldo
        if (sueldo <= 0) { // Valida que el sueldo sea mayor a cero
            throw new IllegalArgumentException("El sueldo debe ser mayor a 0.");
        }
        this.sueldo = sueldo; // Asigna el sueldo del empleado
    }

    @Override
    public String toString() { // Método toString para representar el objeto como texto
        return "Numero: " + numero + " | Nombre: " + nombre + " | Sueldo: " + sueldo; // Retorna la cadena formateada
    }
}