import java.io.BufferedReader;
import java.io.File;
import java.nio.*;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class ProcesoAlmacenamiento {
    public static void main(String[] args) {

        try (BufferedReader bt = new BufferedReader(new InputStreamReader(System.in))) {

            String linea;
            while ((linea = bt.readLine()) != null) {

                String[] palabras = linea.split(",");
                String dni = palabras[2].trim();
                String nombreArchivo = dni + ".txt";
                Files.writeString(Path.of(nombreArchivo), linea);
                System.out.println(linea);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
