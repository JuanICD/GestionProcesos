import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class ProcesoDevolucion {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));

        try {
            if (args.length == 0 || args[0].isBlank()) {
                throw new Exception("No se recibieron datos del alumno.");
            }
            String comando = args[0];
            String[] partes = comando.split(",");//separo los dos valores la palabra y los datos
            String accion = partes[0].trim();//--> como la palabra esta en el indice 0 uso el switch en base a eso

            switch (accion) {
                case "TODOS" -> devolverTodosLosAlumnos();
                case "BUSCAR" -> {
                    if (partes.length > 1) {
                        buscarDNI(partes[1]);
                    }
                }
                case "ELIMINAR" -> {
                    if (partes.length > 1) {
                        eliminarAlumnoPorDNI(partes[1]);
                    }
                }
            }

        } catch (Exception e) {
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
        try {
            File rutaActual = new File(".");
            String[] listaDeNombres = rutaActual.list();

            if (listaDeNombres != null) {
                Arrays.stream(listaDeNombres)
                        .filter(nombre -> nombre.endsWith(".txt"))
                        .forEach(nombreArchivo -> {
                            try (BufferedReader bf = new BufferedReader(new FileReader(nombreArchivo))) {

                                String contenido = bf.readLine();
                                if (contenido != null && !contenido.isBlank()) {
                                    imprimirAlumnos(contenido);
                                    System.out.println("---------------------------");
                                }
                            } catch (IOException e) {
                                System.err.println("Error al leer el archivo: " + nombreArchivo);
                            }
                        });
            } else {
                throw new Exception();
            }


        } catch (Exception e) {
            System.err.println("Error al listar archivos del directorio");
        }
    }

    public static void eliminarAlumnoPorDNI(String dni) {
        String nombreArchivo = dni.trim() + ".txt";
        try {
            File archivoEliminar = new File(nombreArchivo);
            if (archivoEliminar.exists()) {
                if (archivoEliminar.delete()) {
                    System.out.println("Alumno eliminado exitosamente");
                } else {
                    System.err.println("Error al eliminar el alumno: " + dni);
                }
            }

        } catch (Exception e) {
            System.err.println("Error al leer el archivo del alumno: " + dni);
        }
    }

    public static void imprimirAlumnos(String datos) {

        String[] strDeArchivo = datos.split(",");

        if (strDeArchivo.length < 5) {
            System.out.println("No existe en el sistema");
            return;
        }
        System.out.println("DNI: " + strDeArchivo[2].trim());
        System.out.println("Nombre: " + strDeArchivo[0].trim() + " Apellido: " + strDeArchivo[1].trim());
        System.out.println("Fecha: " + strDeArchivo[3].trim());
        System.out.println("Nota media alumno: " + strDeArchivo[4].trim());

    }
}
