import java.io.*;
import java.nio.*;
import java.util.Arrays;


public class ProcesoAlmacenamiento {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        try {
            if (args.length == 0 || args[0].isBlank()) {
                throw new Exception("No se recibieron datos del alumno.");
            }
            String datosDeArgs = args[0];

            String[] palabrasDeArgs = datosDeArgs.split(",");
            if (palabrasDeArgs.length != 5) {
                throw new Exception("Datos recibidos incompletos");
            }
            String dniAlumno = palabrasDeArgs[2];
            String nombreArchivo = dniAlumno + ".txt";

            FileWriter fw = new FileWriter(nombreArchivo);
            fw.write(datosDeArgs);
            fw.close();


        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


    }
}
