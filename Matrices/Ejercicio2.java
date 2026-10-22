import java.util.Scanner;

public class Ejercicio2 {
    private static final int FILAS = 4;
    private static final int COLS = 4;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] matriz = new int[FILAS][COLS];
        boolean matRellenada = false;
        int opcion;

        do {
            System.out.println("\n========== MENÚ DE MATRIZ 4x4 ==========");
            System.out.println("1. Rellenar matriz con valores únicos");
            System.out.println("2. Suma de cada fila y columna");
            System.out.println("3. Suma de una fila específica");
            System.out.println("4. Suma de una columna específica");
            System.out.println("5. Elemento mayor y menor con sus posiciones");
            System.out.println("6. Contar números pares");
            System.out.println("7. Contar números impares");
            System.out.println("8. Generar matriz con el cuadrado de cada valor");
            System.out.println("9. Suma de la diagonal principal");
            System.out.println("10. Suma de la diagonal inversa");
            System.out.println("11. Media de todos los valores");
            System.out.println("12. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            if (opcion == 1) {
                System.out.println("\n--- Rellenando Matriz 4x4 (Sin repetidos) ---");
                for (int i = 0; i < FILAS; i++) {
                    for (int j = 0; j < COLS; j++) {
                        int num;
                        boolean repetido;
                        do {
                            repetido = false;
                            System.out.print("Ingrese valor para [" + i + "][" + j + "]: ");
                            num = sc.nextInt();

                            for (int r = 0; r <= i; r++) {
                                int limiteCol = (r == i) ? j : COLS;
                                for (int c = 0; c < limiteCol; c++) {
                                    if (matriz[r][c] == num) {
                                        repetido = true;
                                        break;
                                    }
                                }
                                if (repetido) break;
                            }

                            if (repetido) {
                                System.out.println("¡Error! El número ya existe en la matriz. Ingrese otro.");
                            }
                        } while (repetido);

                        matriz[i][j] = num;
                    }
                }
                matRellenada = true;
                System.out.println("¡Matriz rellenada exitosamente!");
                continue;
            }

            if (opcion == 12) {
                System.out.println("Saliendo del programa...");
                break;
            }

            if (!matRellenada) {
                System.out.println("\n¡ATENCIÓN! Debe rellenar la matriz primero (Opción 1).");
                continue;
            }

            mostrarMatriz(matriz);

            switch (opcion) {
                case 2:
                    for (int i = 0; i < FILAS; i++) {
                        int sumaFila = 0;
                        for (int j = 0; j < COLS; j++) sumaFila += matriz[i][j];
                        System.out.println("Suma Fila " + i + ": " + sumaFila);
                    }
                    for (int j = 0; j < COLS; j++) {
                        int sumaCol = 0;
                        for (int i = 0; i < FILAS; i++) sumaCol += matriz[i][j];
                        System.out.println("Suma Columna " + j + ": " + sumaCol);
                    }
                    break;

                case 3:
                    int fila;
                    do {
                        System.out.print("Ingrese índice de fila (0 a " + (FILAS - 1) + "): ");
                        fila = sc.nextInt();
                    } while (fila < 0 || fila >= FILAS);
                    int sFila = 0;
                    for (int j = 0; j < COLS; j++) sFila += matriz[fila][j];
                    System.out.println("Suma de la Fila " + fila + ": " + sFila);
                    break;

                case 4:
                    int col;
                    do {
                        System.out.print("Ingrese índice de columna (0 a " + (COLS - 1) + "): ");
                        col = sc.nextInt();
                    } while (col < 0 || col >= COLS);
                    int sCol = 0;
                    for (int i = 0; i < FILAS; i++) sCol += matriz[i][col];
                    System.out.println("Suma de la Columna " + col + ": " + sCol);
                    break;

                case 5:
                    int mayor = matriz[0][0], menor = matriz[0][0];
                    int fMayor = 0, cMayor = 0, fMenor = 0, cMenor = 0;
                    for (int i = 0; i < FILAS; i++) {
                        for (int j = 0; j < COLS; j++) {
                            if (matriz[i][j] > mayor) {
                                mayor = matriz[i][j];
                                fMayor = i; cMayor = j;
                            }
                            if (matriz[i][j] < menor) {
                                menor = matriz[i][j];
                                fMenor = i; cMenor = j;
                            }
                        }
                    }
                    System.out.println("Mayor: " + mayor + " en posición [" + fMayor + "][" + cMayor + "]");
                    System.out.println("Menor: " + menor + " en posición [" + fMenor + "][" + cMenor + "]");
                    break;

                case 6:
                    int pares = 0;
                    for (int[] f : matriz) for (int v : f) if (v % 2 == 0) pares++;
                    System.out.println("Cantidad de números pares: " + pares);
                    break;

                case 7:
                    int impares = 0;
                    for (int[] f : matriz) for (int v : f) if (v % 2 != 0) impares++;
                    System.out.println("Cantidad de números impares: " + impares);
                    break;

                case 8:
                    int[][] matrizCuadrados = new int[FILAS][COLS];
                    for (int i = 0; i < FILAS; i++) {
                        for (int j = 0; j < COLS; j++) {
                            matrizCuadrados[i][j] = matriz[i][j] * matriz[i][j];
                        }
                    }
                    System.out.println("\nNueva Matriz (Valores al cuadrado):");
                    mostrarMatriz(matrizCuadrados);
                    break;

                case 9:
                    int diagPrincipal = 0;
                    for (int i = 0; i < FILAS; i++) diagPrincipal += matriz[i][i];
                    System.out.println("Suma Diagonal Principal: " + diagPrincipal);
                    break;

                case 10:
                    int diagInversa = 0;
                    for (int i = 0; i < FILAS; i++) diagInversa += matriz[i][FILAS - 1 - i];
                    System.out.println("Suma Diagonal Inversa: " + diagInversa);
                    break;

                case 11:
                    double sumaTotal = 0;
                    for (int[] f : matriz) for (int v : f) sumaTotal += v;
                    System.out.printf("Media de los valores: %.2f\n", (sumaTotal / (FILAS * COLS)));
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 12);

        sc.close();
    }

    private static void mostrarMatriz(int[][] m) {
        System.out.println("\nMatriz actual:");
        for (int[] fila : m) {
            for (int val : fila) {
                System.out.printf("%6d", val);
            }
            System.out.println();
        }
        System.out.println();
    }
}