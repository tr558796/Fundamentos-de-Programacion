import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese número de estudiantes (N): ");
        int n = sc.nextInt();
        System.out.print("Ingrese número de exámenes (M): ");
        int m = sc.nextInt();

        double[][] notas = new double[n][m];
        double[] promediosEstudiantes = new double[n];

        System.out.println("\n--- REGISTRO DE CALIFICACIONES ---");
        for (int i = 0; i < n; i++) {
            double suma = 0;
            System.out.println("Estudiante " + (i + 1) + ":");
            for (int j = 0; j < m; j++) {
                System.out.print("  Examen " + (j + 1) + ": ");
                notas[i][j] = sc.nextDouble();
                suma += notas[i][j];
            }
            promediosEstudiantes[i] = suma / m;
        }

        System.out.println("\n--- PROMEDIOS POR ESTUDIANTE ---");
        for (int i = 0; i < n; i++) {
            System.out.printf("Estudiante %d: Promedio = %.2f\n", (i + 1), promediosEstudiantes[i]);
        }

        int cantExcelentes = 0;
        int cantDeficientes = 0;

        for (double prom : promediosEstudiantes) {
            if (prom >= 9.0 && prom <= 10.0) cantExcelentes++;
            if (prom < 7.0) cantDeficientes++;
        }

        System.out.println("\n================ LISTADO SOBRESALIENTE (Promedio 9.0 - 10.0) ================");
        if (cantExcelentes > 0) {
            double[][] excelentes = new double[cantExcelentes][m + 1];
            int idx = 0;
            for (int i = 0; i < n; i++) {
                if (promediosEstudiantes[i] >= 9.0 && promediosEstudiantes[i] <= 10.0) {
                    for (int j = 0; j < m; j++) excelentes[idx][j] = notas[i][j];
                    excelentes[idx][m] = promediosEstudiantes[i];
                    
                    System.out.printf("Estudiante %d -> Notas: ", (i + 1));
                    for (int j = 0; j < m; j++) System.out.print(excelentes[idx][j] + " | ");
                    System.out.printf("Promedio: %.2f\n", excelentes[idx][m]);
                    idx++;
                }
            }
        } else {
            System.out.println("No hay estudiantes con promedio entre 9.0 y 10.0");
        }

        System.out.println("\n================ LISTADO CON PROMEDIO INFERIOR A 7.0 ================");
        if (cantDeficientes > 0) {
            double[][] deficientes = new double[cantDeficientes][m + 1];
            int idx = 0;
            for (int i = 0; i < n; i++) {
                if (promediosEstudiantes[i] < 7.0) {
                    for (int j = 0; j < m; j++) deficientes[idx][j] = notas[i][j];
                    deficientes[idx][m] = promediosEstudiantes[i];

                    System.out.printf("Estudiante %d -> Notas: ", (i + 1));
                    for (int j = 0; j < m; j++) System.out.print(deficientes[idx][j] + " | ");
                    System.out.printf("Promedio: %.2f\n", deficientes[idx][m]);
                    idx++;
                }
            }
        } else {
            System.out.println("No hay estudiantes con promedio inferior a 7.0");
        }

        double[] promediosExamenes = new double[m];
        double maxPromExamen = -1;
        double minPromExamen = 11.0;
        int examenMasAlto = -1;
        int examenMasBajo = -1;

        for (int j = 0; j < m; j++) {
            double sumaCol = 0;
            for (int i = 0; i < n; i++) {
                sumaCol += notas[i][j];
            }
            promediosExamenes[j] = sumaCol / n;

            if (promediosExamenes[j] > maxPromExamen) {
                maxPromExamen = promediosExamenes[j];
                examenMasAlto = j + 1;
            }
            if (promediosExamenes[j] < minPromExamen) {
                minPromExamen = promediosExamenes[j];
                examenMasBajo = j + 1;
            }
        }

        System.out.println("\n================ ANÁLISIS DE EXÁMENES ================");
        System.out.printf("• El examen con el promedio MÁS ALTO fue el Examen %d (Promedio: %.2f)\n", examenMasAlto, maxPromExamen);
        System.out.printf("• El examen con el promedio MÁS BAJO fue el Examen %d (Promedio: %.2f)\n", examenMasBajo, minPromExamen);

        sc.close();
    }
}