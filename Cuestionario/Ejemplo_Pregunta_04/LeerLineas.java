import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LeerLineas {
    public static void main(String[] args) {
        try (BufferedReader entrada = Files.newBufferedReader(Paths.get("ejemplo.txt"))) {
            String linea;
            while ((linea = entrada.readLine()) != null) {
                System.out.println(linea); // Una linea a la vez
            }
        } catch (IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
        }
    }
}
