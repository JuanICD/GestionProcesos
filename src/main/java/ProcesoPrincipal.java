import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProcesoPrincipal {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        mostrarMenu();

        sc.close();
    }

    public static void mostrarMenu() {
        try {
            String opcion;
            boolean seguir = true;
            do {
                System.out.println("""
                        1. Búsqueda de alumno por DNI.
                        2. Insertar un nuevo alumno.
                        3. Obtener todos los alumnos.
                        4. Finalizar.
                        """);
                opcion = sc.nextLine();
                switch (opcion) {
                    case "1" -> buscarDNI();
                    case "2" -> insertarAlumnoV2();
                    case "3" -> obtenerDatosAlumno();
                    case "4" -> {
                        System.out.println("Saliendo...");
                        seguir = false;
                    }
                    default -> System.out.println("Numero no valido, intentalo de nuevo");
                }

            } while (seguir);

        } catch (Exception e) {
            e.getMessage();
        }
    }

    private static void insertarAlumnoV2() {
        try {

            System.out.println("Escribe REINICIAR para volver al menu principal");
            //Creacion del alumno
            Alumno alumno = new Alumno();

            String nombre = pedirDatosValidos("nombre");
            if (nombre.equalsIgnoreCase("REINICIAR")) {
                return;
            }
            alumno.setNombre(nombre);

            String apellido = pedirDatosValidos("apellido");
            if (apellido.equalsIgnoreCase("REINICIAR")) {
                return;
            }
            alumno.setApellido(apellido);

            String dni = pedirDatosValidos("DNI");
            if (dni.equalsIgnoreCase("REINICIAR")) {
                return;
            }
            alumno.setDni(dni);


            String fecha = pedirFecha();
            if (fecha.equalsIgnoreCase("REINCIAR"))
                return;
            alumno.setFechaNacimiento(LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            String nota = pedirNota();
            if (nota.equalsIgnoreCase("REINICIAR"))
                return;
            alumno.setNotaMedia(Float.parseFloat(nota));

            StringBuilder sb = new StringBuilder();
            sb.append(nombre + ", ");
            sb.append(apellido + ", ");
            sb.append(dni + ", ");
            sb.append(fecha + ", ");
            sb.append(nota);
            String datosJuntos = sb.toString();
            lanzarProcesoAlmacenamiento(datosJuntos);


        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


    }

    private static void lanzarProcesoAlmacenamiento(String datosJuntos) {
        try {

            ProcessBuilder pb = new ProcessBuilder("java", "ProcesoAlmacenamiento");
            Process proceso = pb.start();
            proceso.getOutputStream().write(datosJuntos.getBytes());
            proceso.getOutputStream().close();
            proceso.waitFor();


        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }

    private static String pedirDatosValidos(String nombreCampo) {
        String dato = "";
        boolean esValido = false;

        do {
            try {
                System.out.println("Escribe el " + nombreCampo + " del alumno");
                dato = sc.nextLine();
                if (dato.equalsIgnoreCase("REINICIAR")) {
                    System.out.println("Saliendo...");
                    return "REINICIAR";
                }
                if (dato.isBlank()) {
                    throw new Exception("El campo no puede estar vacio");
                }
                esValido = true;
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage() + ". Inténtalo de nuevo.");
            }

        } while (!esValido);
        return dato;
    }

    private static String pedirNota() {
        String strNota = "";
        boolean notaValida = false;

        do {
            try {
                System.out.println("Introduce la nota del alumno (deja en blanco para 0): ");
                strNota = sc.nextLine();

                if (strNota.equalsIgnoreCase("REINICIAR")) {
                    System.out.println("...Inserción cancelada.");
                    return "REINICIAR";
                }

                if (strNota.isBlank()) {
                    return "0.0";
                }

                float nota = Float.parseFloat(strNota);

                if (nota < 0.0f || nota > 10.0f) {
                    System.err.println("Error: La nota debe estar entre 0 y 10. Inténtalo de nuevo.");
                } else {
                    notaValida = true;
                }

            } catch (NumberFormatException e) {
                System.err.println("Error: Debe introducir un número válido (ej: 8.5) o dejarlo en blanco.");
            }
        } while (!notaValida);

        return strNota;
    }

    private static String pedirFecha() {
        String strFecha = "";
        boolean fechaValida = false;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        do {
            try {
                System.out.println("Fecha de nacimiento del alumno (DD/MM/AAAA): ");
                strFecha = sc.nextLine();

                if (strFecha.equalsIgnoreCase("REINICIAR")) {
                    System.out.println("Inserción cancelada...");
                    return "REINICIAR";
                }

                if (strFecha.isBlank()) {
                    throw new Exception("La fecha no puede estar vacía.");
                }

                LocalDate.parse(strFecha, dtf);

                fechaValida = true;

            } catch (DateTimeParseException e) {
                System.err.println("Error: Formato de fecha incorrecto o fecha no válida. Use DD/MM/AAAA.");
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        } while (!fechaValida);

        return strFecha;
    }


    private static void obtenerDatosAlumno() {


    }

    private static void buscarDNI() {
    }

}
