import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

class Tarea {
    private String id;
    private String descripcion;
    private String prioridad;
    private boolean completada;

    public Tarea(String id, String descripcion, String prioridad) {
        this.id = id;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.completada = false;
    }

    public String getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public String getPrioridad() { return prioridad; }
    public boolean isCompletada() { return completada; }
    public void setCompletada(boolean completada) { this.completada = completada; }

    @Override
    public String toString() {
        String estado = completada ? "[COMPLETADA]" : "[PENDIENTE]";
        return String.format("ID: %-6s | Estado: %-12s | Prioridad: %-8s | Descripción: %s", id, estado, prioridad, descripcion);
    }
}

public class Tareaspendientes {
    private static final LinkedList<Tarea> listaTareas = new LinkedList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;
        do {
            System.out.println("\n--- SISTEMA DE GESTIÓN DE TAREAS PENDIENTES ---");
            System.out.println("1. Agregar tarea al inicio");
            System.out.println("2. Agregar tarea al final");
            System.out.println("3. Insertar tarea en posición específica");
            System.out.println("4. Eliminar tarea por ID");
            System.out.println("5. Buscar tarea por descripción");
            System.out.println("6. Mostrar todas las tareas pendientes");
            System.out.println("7. Marcar tarea como completada");
            System.out.println("8. Mostrar primera y última tarea registrada");
            System.out.println("9. Recorrer la lista en orden inverso");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1 -> agregarInicio();
                case 2 -> agregarFinal();
                case 3 -> insertarPosicion();
                case 4 -> eliminarTarea();
                case 5 -> buscarPorDescripcion();
                case 6 -> mostrarPendientes();
                case 7 -> marcarCompletada();
                case 8 -> mostrarExtremos();
                case 9 -> recorrerInverso();
                case 10 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 10);
    }

    private static Tarea crearTarea() {
        System.out.print("Ingrese ID de la tarea: ");
        String id = scanner.nextLine().trim();
        System.out.print("Ingrese descripción: ");
        String descripcion = scanner.nextLine().trim();
        System.out.print("Ingrese prioridad (Alta/Media/Baja): ");
        String prioridad = scanner.nextLine().trim();
        return new Tarea(id, descripcion, prioridad);
    }

    private static void agregarInicio() {
        listaTareas.addFirst(crearTarea());
        System.out.println("Tarea agregada al inicio de la lista.");
    }

    private static void agregarFinal() {
        listaTareas.addLast(crearTarea());
        System.out.println("Tarea agregada al final de la lista.");
    }

    private static void insertarPosicion() {
        System.out.print("Ingrese la posición (índice 0 a " + listaTareas.size() + "): ");
        int pos = Integer.parseInt(scanner.nextLine());
        if (pos >= 0 && pos <= listaTareas.size()) {
            listaTareas.add(pos, crearTarea());
            System.out.println("Tarea insertada en la posición " + pos + ".");
        } else {
            System.out.println("Posición inválida.");
        }
    }

    private static void eliminarTarea() {
        System.out.print("Ingrese ID de la tarea a eliminar: ");
        String id = scanner.nextLine().trim();
        boolean removido = listaTareas.removeIf(t -> t.getId().equalsIgnoreCase(id));
        if (removido) {
            System.out.println("Tarea eliminada correctamente.");
        } else {
            System.out.println("No se encontró ninguna tarea con ese ID.");
        }
    }

    private static void buscarPorDescripcion() {
        System.out.print("Ingrese texto a buscar en la descripción: ");
        String texto = scanner.nextLine().trim().toLowerCase();
        boolean encontrada = false;
        for (Tarea t : listaTareas) {
            if (t.getDescripcion().toLowerCase().contains(texto)) {
                System.out.println(t);
                encontrada = true;
            }
        }
        if (!encontrada) {
            System.out.println("No se encontraron tareas coincidentes.");
        }
    }

    private static void mostrarPendientes() {
        System.out.println("\n--- TAREAS PENDIENTES ---");
        boolean hayPendientes = false;
        for (Tarea t : listaTareas) {
            if (!t.isCompletada()) {
                System.out.println(t);
                hayPendientes = true;
            }
        }
        if (!hayPendientes) {
            System.out.println("No hay tareas pendientes.");
        }
    }

    private static void marcarCompletada() {
        System.out.print("Ingrese ID de la tarea completada: ");
        String id = scanner.nextLine().trim();
        for (Tarea t : listaTareas) {
            if (t.getId().equalsIgnoreCase(id)) {
                t.setCompletada(true);
                System.out.println("Tarea marcada como completada.");
                return;
            }
        }
        System.out.println("No se encontró la tarea especificada.");
    }

    private static void mostrarExtremos() {
        if (listaTareas.isEmpty()) {
            System.out.println("La lista de tareas está vacía.");
            return;
        }
        System.out.println("Primera tarea registrada: " + listaTareas.getFirst());
        System.out.println("Última tarea registrada: " + listaTareas.getLast());
    }

    private static void recorrerInverso() {
        if (listaTareas.isEmpty()) {
            System.out.println("La lista está vacía.");
            return;
        }
        System.out.println("\n--- RECORRIDO EN ORDEN INVERSO ---");
        Iterator<Tarea> it = listaTareas.descendingIterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}