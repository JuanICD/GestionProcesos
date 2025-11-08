import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProcesoDevolucion {

    public static void main(String[] args) {
        try (BufferedReader bt = new BufferedReader(new InputStreamReader(System.in))) {
            String dniDado = bt.readLine();
            if (dniDado == null || dniDado.isBlank()) {
                return;
            }
            if (dniDado.equals("TODOS")) {
                devolverTodosLosAlumnos();
            } else {
                buscarDNI(dniDado.trim());
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void buscarDNI(String dni) {
        String nombreArchivo = dni.trim() + ".txt";

        try {
            //Lee el archivo que se encuentra en el path
            String lineaLeida = Files.readString(Paths.get(nombreArchivo));
            imprimirAlumnos(lineaLeida);

        } catch (FileNotFoundException e) {
            System.err.println("Error: No se encontró ningún alumno con el DNI: " + dni);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo del alumno: " + e.getMessage());
        }
    }

    public static void devolverTodosLosAlumnos() {
        //Obtenemos la ruta del directorio donde se guardan los alumnos
        Path rutaActual = Paths.get(".");
        try (var files = Files.list(rutaActual)) {

            files
                    .filter(path -> path.toString().endsWith(".txt"))
                    .forEach(archivo -> {
                        try {
                            String datosAlumno = Files.readString(archivo);
                            imprimirAlumnos(datosAlumno);
                            System.out.println();
                        } catch (IOException e) {
                            System.err.println("Error al leer el archivo del alumno: " + e.getMessage());
                        }
                    });

        } catch (IOException e) {
            System.err.println("Error al listar archivos del directorio");
        }
    }

    public static void imprimirAlumnos(String datos){

        String [] strDeArchivo = datos.split(",");

        if (strDeArchivo.length < 5) {
            System.out.println("No existe en el sistema");

        }
        System.out.println("DNI: " + strDeArchivo[2].trim());
        System.out.println("Nombre: " + strDeArchivo[0].trim() + " Apellido: " + strDeArchivo[1].trim());
        System.out.println("Fecha: " + strDeArchivo[3].trim());
        System.out.println("Nota media alumno: " + strDeArchivo[4].trim());

    }
}
