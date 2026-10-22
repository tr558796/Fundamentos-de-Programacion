import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Duplicados {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int cantidad = leerInt(scanner, "Ingrese la cantidad de transacciones a registrar: ");

        int[] transacciones = new int[cantidad];
        System.out.println("Ingrese los " + cantidad + " identificadores de transacción:");
        for (int i = 0; i < cantidad; i++) {
            transacciones[i] = leerInt(scanner, "Registro [" + i + "]: ");
        }

        int[] copiaOrdenada = Arrays.copyOf(transacciones, transacciones.length);
        Arrays.sort(copiaOrdenada);

        int[] duplicadosTemp = new int[copiaOrdenada.length];
        int totalDuplicadosUnicos = 0;

        for (int i = 0; i < copiaOrdenada.length - 1; i++) {
            if (copiaOrdenada[i] == copiaOrdenada[i + 1]) {
                boolean yaExiste = false;
                for (int k = 0; k < totalDuplicadosUnicos; k++) {
                    if (duplicadosTemp[k] == copiaOrdenada[i]) {
                        yaExiste = true;
                        break;
                    }
                }
                if (!yaExiste) {
                    duplicadosTemp[totalDuplicadosUnicos++] = copiaOrdenada[i];
                }
            }
        }

        int[] depuradoTemp = new int[transacciones.length];
        int totalUnicos = 0;

        for (int id : transacciones) {
            boolean repetido = false;
            for (int i = 0; i < totalUnicos; i++) {
                if (depuradoTemp[i] == id) {
                    repetido = true;
                    break;
                }
            }
            if (!repetido) {
                depuradoTemp[totalUnicos++] = id;
            }
        }

        int[] depurado = Arrays.copyOf(depuradoTemp, totalUnicos);
        int registrosEliminados = transacciones.length - depurado.length;

        int[] duplicados = new int[totalDuplicadosUnicos];
        int posDup = 0;
        for (int id : depurado) {
            for (int i = 0; i < totalDuplicadosUnicos; i++) {
                if (id == duplicadosTemp[i]) {
                    duplicados[posDup++] = id;
                    break;
                }
            }
        }

        System.out.println("\n=== RESULTADOS ===");
        System.out.println("Arreglo original: " + Arrays.toString(transacciones));
        System.out.println("Arreglo depurado: " + Arrays.toString(depurado));

        String textoDuplicados = Arrays.toString(duplicados)
                .replace("[", "")
                .replace("]", "");

        System.out.println("Identificadores duplicados: " + textoDuplicados);
        System.out.println("Registros eliminados: " + registrosEliminados);

        scanner.close();
    }

    public static int leerInt(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número entero válido.");
                scanner.nextLine();
            }
        }
    }
}