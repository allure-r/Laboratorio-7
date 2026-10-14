import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Serial1 {
    public static void main(String[] args) {
        Alumno[] alumnos = {
            new Alumno("12345678", "Lucas Gonzalez", 20, new Fecha(5, 9, 2011)),
            new Alumno("98765432", "Anacleto Jimenez", 19, new Fecha(7, 9, 2011)),
            new Alumno("78234212", "Maria Zapata", 21, new Fecha(8, 9, 2011))
        };
        try (ObjectOutputStream salida = new ObjectOutputStream(
                Files.newOutputStream(Paths.get("alumnos.dat")))) {
            for (Alumno alumno : alumnos) {
                salida.writeObject(alumno); // Guarda cada objeto
            }
            System.out.println("Se guardaron 3 alumnos en alumnos.dat.");
        } catch (IOException e) {
            System.out.println("Error al serializar: " + e.getMessage());
        }
    }
}
