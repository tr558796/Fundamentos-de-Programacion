import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Estudiante {
    private final String matricula;
    private final String nombre;
    private double calificacion;

    public Estudiante(String matricula, String nombre, double calificacion) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.calificacion = calificacion;
    }

    public String getMatricula() { return matricula; }
    public String getNombre() { return nombre; }
    public double getCalificacion() { return calificacion; }
    public void setCalificacion(double calificacion) { this.calificacion = calificacion; }

    @Override
    public String toString() {
        return String.format("Matrícula: %-10s | Nombre: %-25s | Calificación: %.2f", matricula, nombre, calificacion);
    }
}

public class Estudiantes {
    private static final List<Estudiante> estudiantes = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;
        do {
            System.out.println("\n--- SISTEMA DE ADMINISTRACIÓN DE ESTUDIANTES ---");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Mostrar estudiantes ordenados alfabéticamente");
            System.out.println("3. Buscar estudiante por matrícula");
            System.out.println("4. Modificar calificación");
            System.out.println("5. Eliminar estudiante");
            System.out.println("6. Mostrar promedio general");
            System.out.println("7. Identificar calificación más alta y más baja");
            System.out.println("8. Mostrar estudiantes con promedio mayor a 9.0");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1 -> registrarEstudiante();
                case 2 -> mostrarOrdenados();
                case 3 -> buscarEstudiante();
                case 4 -> modificarCalificacion();
                case 5 -> eliminarEstudiante();
                case 6 -> mostrarPromedio();
                case 7 -> mostrarExtremos();
                case 8 -> mostrarSobresalientes();
                case 9 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 9);
    }

    private static void registrarEstudiante() {
        System.out.print("Ingrese matrícula: ");
        String matricula = scanner.nextLine().trim();

        if (buscarPorMatricula(matricula) != null) {
            System.out.println("Error: La matrícula ya se encuentra registrada.");
            return;
        }

        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine().trim();

        double calificacion = leerDouble("Ingrese calificación (0-10): ");

        estudiantes.add(new Estudiante(matricula, nombre, calificacion));
        System.out.println("Estudiante registrado exitosamente.");
    }

    private static void mostrarOrdenados() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        List<Estudiante> copia = new ArrayList<>(estudiantes);
        copia.sort(Comparator.comparing(Estudiante::getNombre, String.CASE_INSENSITIVE_ORDER));
        System.out.println("\n--- ESTUDIANTES ORDENADOS ALFABÉTICAMENTE ---");
        copia.forEach(System.out::println);
    }

    private static void buscarEstudiante() {
        System.out.print("Ingrese matrícula a buscar: ");
        String matricula = scanner.nextLine().trim();
        Estudiante est = buscarPorMatricula(matricula);
        if (est != null) {
            System.out.println("Estudiante encontrado:\n" + est);
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }

    private static void modificarCalificacion() {
        System.out.print("Ingrese matrícula del estudiante a modificar: ");
        String matricula = scanner.nextLine().trim();
        Estudiante est = buscarPorMatricula(matricula);
        if (est != null) {
            double nuevaCal = leerDouble("Ingrese la nueva calificación: ");
            est.setCalificacion(nuevaCal);
            System.out.println("Calificación actualizada correctamente.");
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }

    private static void eliminarEstudiante() {
        System.out.print("Ingrese matrícula del estudiante a eliminar: ");
        String matricula = scanner.nextLine().trim();
        Estudiante est = buscarPorMatricula(matricula);
        if (est != null) {
            estudiantes.remove(est);
            System.out.println("Estudiante eliminado correctamente.");
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }

    private static void mostrarPromedio() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        double suma = 0;
        for (Estudiante e : estudiantes) {
            suma += e.getCalificacion();
        }
        System.out.printf("Promedio general del grupo: %.2f\n", (suma / estudiantes.size()));
    }

    private static void mostrarExtremos() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        Estudiante max = Collections.max(estudiantes, Comparator.comparingDouble(Estudiante::getCalificacion));
        Estudiante min = Collections.min(estudiantes, Comparator.comparingDouble(Estudiante::getCalificacion));

        System.out.println("Calificación más alta: " + max.getNombre() + " (" + max.getCalificacion() + ")");
        System.out.println("Calificación más baja: " + min.getNombre() + " (" + min.getCalificacion() + ")");
    }

    private static void mostrarSobresalientes() {
        List<Estudiante> sobresalientes = estudiantes.stream()
                .filter(e -> e.getCalificacion() > 9.0)
                .toList();

        if (sobresalientes.isEmpty()) {
            System.out.println("No hay estudiantes con promedio mayor a 9.0.");
        } else {
            System.out.println("\n--- ESTUDIANTES CON PROMEDIO MAYOR A 9.0 ---");
            sobresalientes.forEach(System.out::println);
        }
    }

    private static Estudiante buscarPorMatricula(String matricula) {
        for (Estudiante e : estudiantes) {
            if (e.getMatricula().equalsIgnoreCase(matricula)) {
                return e;
            }
        }
        return null;
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un valor numérico decimal.");
            }
        }
    }
}