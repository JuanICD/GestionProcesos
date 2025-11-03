import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

            do {
                System.out.println("Escribe el nombre del alumno");
                String nombre = sc.nextLine();
                if (nombre.equalsIgnoreCase("REINICIAR")) {
                    System.out.println("Insercion cancelada, volviendo al menu principal...");
                    return;
                }

                if (nombre.isBlank()) {
                    System.out.println("El campo no puede estar vacio");
                } else {

                    alumno.setNombre(nombre);
                }

            } while (alumno.getNombre() == null);

            do {
                System.out.println("Escribe el apellido del alumno");
                String apellido = sc.nextLine();
                if (apellido.equalsIgnoreCase("REINICIAR")) {
                    System.out.println("Insercion cancelada, volviendo al menu principal...");
                    return;
                }

                if (apellido.isBlank()) {
                    System.out.println("El campo no puede estar vacio");
                } else {
                    alumno.setApellido(apellido);
                }
            } while (alumno.getApellido() == null);

            do {
                System.out.println("Escribe el DNI del alumno");
                String dni = sc.nextLine();
                if (dni.equalsIgnoreCase("REINICIAR")) {
                    System.out.println("Insercion cancelada, volviendo al menu principal...");
                    return;
                }

                if (dni.isBlank()) {
                    System.out.println("El campo no puede estar vacio");
                } else {

                    alumno.setDni(dni);
                }
            } while (alumno.getDni() == null);

            insertarFecha(alumno);
            insertarNota(alumno);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


    }

    private static void insertarNota(Alumno alumno) {

        boolean notaValida = false;

        do {
            try {
                System.out.println("Introduce la nota del alumno (deja en blanco para 0): ");
                String strNota = sc.nextLine();

                float nota;

                if (strNota.isBlank()) {
                    nota = 0.0f;
                } else {
                    nota = Float.parseFloat(strNota);
                }

                if (nota < 0.0f || nota > 10.0f) {
                    System.err.println("Error: La nota debe estar entre 0 y 10.");
                } else {
                    alumno.setNotaMedia(nota);
                    System.out.println("Nota guardada: " + nota);
                    notaValida = true;
                }

            } catch (NumberFormatException e) {
                System.err.println("Error: Debe introducir un número válido o dejarlo en blanco.");
            }

        } while (!notaValida);
    }

    private static void insertarFecha(Alumno alumno) {
        LocalDate fechaNacimiento = null;
        do {
            try {

                System.out.println("Fecha de nacimiento del alumno (DD/MM/AAAA): ");

                System.out.println("Día:");
                int dia = Integer.parseInt(sc.nextLine());
                if (dia < 1 || dia > 31)
                    throw new Exception("El día introducido no es válido");

                System.out.println("Mes:");
                int mes = Integer.parseInt(sc.nextLine());
                if (mes < 1 || mes > 12)
                    throw new Exception("El mes introducido no es válido");

                System.out.println("Año:");
                int anio = Integer.parseInt(sc.nextLine());


                fechaNacimiento = LocalDate.of(anio, mes, dia);
                alumno.setFechaNacimiento(fechaNacimiento);


            } catch (DateTimeException e) {
                System.err.println("Error: La fecha introducida no es válida. Inténtelo de nuevo.");
            } catch (NumberFormatException error) {
                System.err.println("Error: Debe introducir un número. Inténtelo de nuevo.");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } while (alumno.getFechaNacimiento() == null);

    }

    private static void obtenerDatosAlumno() {

        ProcesoDevolucion pd = new ProcesoDevolucion();

    }


    private static void buscarDNI() {
    }

}
