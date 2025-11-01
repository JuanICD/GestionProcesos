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
                    case "2" -> insertarAlumno();
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

    private static void insertarAlumno() {

        System.out.println("Escribe REINICIAR para volver al menu principal");

        Alumno alumno = new Alumno();

        String nombre = validarDato("nombre");

        if (nombre.equalsIgnoreCase("REINICIAR")) {
            System.out.println("Insercion cancelada, volviendo al menu principal...");
            return;
        }
        alumno.setNombre(nombre);
        //System.out.println("Nombre guardado: " + alumno.getNombre());

        String apellido = validarDato("apellido");

        if (apellido.equalsIgnoreCase("REINICIAR")) {
            System.out.println("Insercion cancelada, volviendo al menu principal...");
            return;
        }
        alumno.setApellido(apellido);

        String dni = validarDato("dni");
        if (dni.equalsIgnoreCase("REINICIAR")){
            System.out.println("Insercion cancelada, volviendo al menu principal...");
            return;
        }
        alumno.setDni(dni);

        String fechaNacimiento = validarDato("fecha de nacimiento");
        if (fechaNacimiento.equalsIgnoreCase("REINICIAR")){
            System.out.println("Insercion cancelada, volviendo al menu principal...");
            return;

        }
        alumno.setFechaNacimiento(fechaNacimiento);


    }

    private static String validarDato(String dato) {
        String datoLeido;
        while (true) {
            System.out.println("Introduce el " + dato + " del alumno");
            datoLeido = sc.nextLine();
            if (datoLeido.equalsIgnoreCase("REINICIAR")) {
                return datoLeido;
            }
            if (datoLeido.isBlank()) {
                System.out.println("El campo no puede estar vacio");
            } else {
                return datoLeido;
            }
        }


    }

    private static void obtenerDatosAlumno() {

        ProcesoDevolucion pd = new ProcesoDevolucion();

    }


    private static void buscarDNI() {
    }

}
