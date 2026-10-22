import java.util.Scanner;

public class Ejercico1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el número de vendedores (n): ");
        int n = sc.nextInt();
        System.out.print("Ingrese el número de zonas (m): ");
        int m = sc.nextInt();
        System.out.print("Ingrese el precio unitario de cada computadora ($): ");
        double precioUnitario = sc.nextDouble();

        int[][] ventas = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print("Computadoras vendidas por Vendedor " + (i + 1) + " en Zona " + (j + 1) + ": ");
                ventas[i][j] = sc.nextInt();
            }
        }

        int maxVentasZona = -1;
        int zonaMasVendedora = -1;
        for (int j = 0; j < m; j++) {
            int sumaZona = 0;
            for (int i = 0; i < n; i++) {
                sumaZona += ventas[i][j];
            }
            if (sumaZona > maxVentasZona) {
                maxVentasZona = sumaZona;
                zonaMasVendedora = j + 1;
            }
        }

        int totalGeneral = 0;
        int minVentasVendedor = Integer.MAX_VALUE;
        int vendedorMenos = -1;

        int maxVentasVendedor = -1;
        int vendedorMas = -1;

        for (int i = 0; i < n; i++) {
            int sumaVendedor = 0;
            for (int j = 0; j < m; j++) {
                sumaVendedor += ventas[i][j];
            }
            totalGeneral += sumaVendedor;

            if (sumaVendedor < minVentasVendedor) {
                minVentasVendedor = sumaVendedor;
                vendedorMenos = i + 1;
            }

            if (sumaVendedor > maxVentasVendedor) {
                maxVentasVendedor = sumaVendedor;
                vendedorMas = i + 1;
            }
        }

        System.out.println("\n================ RESULTADOS ================");
        System.out.println("• Zona con más computadoras vendidas: Zona " + zonaMasVendedora + " (" + maxVentasZona + " computadoras)");
        
        System.out.println("• Vendedor con MENOS computadoras vendidas: Vendedor " + vendedorMenos);
        System.out.println("  - Cantidad: " + minVentasVendedor + " computadoras");
        System.out.println("  - Monto de venta: $" + (minVentasVendedor * precioUnitario));

        System.out.println("• Vendedor con MÁS computadoras vendidas: Vendedor " + vendedorMas);
        System.out.println("  - Cantidad: " + maxVentasVendedor + " computadoras");
        System.out.println("  - Monto de venta: $" + (maxVentasVendedor * precioUnitario));

        System.out.println("• Cantidad total de computadoras vendidas en todas las zonas: " + totalGeneral);
        System.out.println("  - Monto total global: $" + (totalGeneral * precioUnitario));
        
        sc.close();
    }
}