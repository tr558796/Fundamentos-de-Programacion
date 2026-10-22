import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Analisis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numSucursales = leerInt(scanner, "Ingrese el número de sucursales a registrar: ");
        int numMeses = 12;

        double[][] ventas = new double[numSucursales][numMeses];

        for (int i = 0; i < numSucursales; i++) {
            System.out.println("\n--- Ingreso de ventas para la Sucursal " + (i + 1) + " ---");
            for (int j = 0; j < numMeses; j++) {
                ventas[i][j] = leerDouble(scanner, "Venta Mes " + (j + 1) + ": ");
            }
        }

        double[][] resultados = new double[numSucursales][4];
        double sumaGlobalAnual = 0;

        for (int i = 0; i < numSucursales; i++) {
            double totalAnual = 0;
            double ventaMax = ventas[i][0];
            int mejorMes = 1;

            for (int j = 0; j < numMeses; j++) {
                double ventaMes = ventas[i][j];
                totalAnual += ventaMes;

                if (ventaMes > ventaMax) {
                    ventaMax = ventaMes;
                    mejorMes = j + 1;
                }
            }

            double promedioMensual = totalAnual / numMeses;
            resultados[i][0] = i + 1;
            resultados[i][1] = totalAnual;
            resultados[i][2] = promedioMensual;
            resultados[i][3] = mejorMes;

            sumaGlobalAnual += totalAnual;
        }

        double promedioGlobalAnual = sumaGlobalAnual / numSucursales;

        System.out.println("\n=== REPORTE INDIVIDUAL DE SUCURSALES ===");
        for (double[] res : resultados) {
            System.out.println("ID Sucursal: " + (int)res[0] 
                    + " | Total Anual: $" + String.format("%.2f", res[1]) 
                    + " | Promedio Mensual: $" + String.format("%.2f", res[2]) 
                    + " | Mejor Mes: Mes " + (int)res[3]);
        }

        System.out.println("\nPromedio Global Anual: $" + String.format("%.2f", promedioGlobalAnual));

        System.out.println("\nSucursales que superan el promedio global:");
        for (double[] res : resultados) {
            if (res[1] > promedioGlobalAnual) {
                System.out.println("- Sucursal " + (int)res[0] + " (Total Anual: $" + String.format("%.2f", res[1]) + ")");
            }
        }

        double[][] ranking = Arrays.copyOf(resultados, resultados.length);
        Arrays.sort(ranking, (a, b) -> Double.compare(b[1], a[1]));

        System.out.println("\n=== RANKING DE SUCURSALES (MAYOR A MENOR VENTA ANUAL) ===");
        for (int i = 0; i < ranking.length; i++) {
            int idSucursal = (int) ranking[i][0];
            double totalAnual = ranking[i][1];
            double promedioMensual = ranking[i][2];
            int mejorMes = (int) ranking[i][3];

            System.out.println("Lugar " + (i + 1) + ":"
                    + " ID Sucursal = " + idSucursal
                    + " | Total Anual = $" + String.format("%.2f", totalAnual)
                    + " | Promedio Mensual = $" + String.format("%.2f", promedioMensual)
                    + " | Mejor Mes = Mes " + mejorMes);
        }

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

    public static double leerDouble(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un valor numérico válido.");
                scanner.nextLine();
            }
        }
    }
}