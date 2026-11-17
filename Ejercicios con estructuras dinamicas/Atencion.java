import java.util.LinkedList;
import java.util.Scanner;

class Cliente {
    private int turno;
    private String nombre;

    public Cliente(int turno, String nombre) {
        this.turno = turno;
        this.nombre = nombre;
    }

    public int getTurno() { return turno; }
    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        return "Turno #" + turno + " - Cliente: " + nombre;
    }
}

public class Atencion {
    private static final LinkedList<Cliente> colaEspera = new LinkedList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;
        do {
            System.out.println("\n--- SISTEMA DE ATENCIÓN DE CLIENTES ---");
            System.out.println("1. Registrar cliente (Agregar a la cola)");
            System.out.println("2. Atender siguiente cliente");
            System.out.println("3. Ver siguiente cliente a ser atendido");
            System.out.println("4. Mostrar clientes pendientes");
            System.out.println("5. Consultar cantidad de clientes en espera");
            System.out.println("6. Cancelar un turno específico");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1 -> registrarCliente();
                case 2 -> atenderCliente();
                case 3 -> verSiguiente();
                case 4 -> mostrarPendientes();
                case 5 -> consultarCantidad();
                case 6 -> cancelarTurno();
                case 7 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 7);
    }

    private static void registrarCliente() {
        System.out.print("Ingrese número de turno: ");
        int turno = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese nombre del cliente: ");
        String nombre = scanner.nextLine().trim();

        colaEspera.addLast(new Cliente(turno, nombre));
        System.out.println("Cliente agregado al final de la cola.");
    }

    private static void atenderCliente() {
        if (colaEspera.isEmpty()) {
            System.out.println("No hay clientes en la cola.");
        } else {
            Cliente atendido = colaEspera.pollFirst();
            System.out.println("Atendiendo a: " + atendido);
        }
    }

    private static void verSiguiente() {
        if (colaEspera.isEmpty()) {
            System.out.println("No hay clientes en la cola.");
        } else {
            System.out.println("Próximo cliente a atender: " + colaEspera.peekFirst());
        }
    }

    private static void mostrarPendientes() {
        if (colaEspera.isEmpty()) {
            System.out.println("La cola está vacía.");
        } else {
            System.out.println("\n--- CLIENTES EN ESPERA ---");
            colaEspera.forEach(System.out::println);
        }
    }

    private static void consultarCantidad() {
        System.out.println("Clientes actualmente esperando: " + colaEspera.size());
    }

    private static void cancelarTurno() {
        System.out.print("Ingrese el número de turno a cancelar: ");
        int turno = Integer.parseInt(scanner.nextLine());

        boolean removido = colaEspera.removeIf(c -> c.getTurno() == turno);
        if (removido) {
            System.out.println("Turno #" + turno + " cancelado con éxito.");
        } else {
            System.out.println("No se encontró ningún cliente con el turno #" + turno + ".");
        }
    }
}