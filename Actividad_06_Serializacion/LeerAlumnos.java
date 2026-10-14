import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LeerAlumnos {
    public static void main(String[] args) {
        try (ObjectInputStream entrada = new ObjectInputStream(
                Files.newInputStream(Paths.get("alumnos.dat")))) {
            while (true) {
                Alumno alumno = (Alumno) entrada.readObject(); // Recupera objetos
                System.out.println(alumno);
            }
        } catch (EOFException e) {
            System.out.println("Lectura finalizada.");
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.out.println("Error al leer los alumnos: " + e.getMessage());
        }
    }
}
