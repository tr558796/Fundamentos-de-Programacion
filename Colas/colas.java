import java.util.Scanner;

public class colas {

    public static void main(String[] args) {
        String[] cola = new String[5];
        int frente = 0;
        int fin = -1;
        int contador = 0;
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- MENU DE GESTION DE COLA ---");
            System.out.println("1. Agregar elemento");
            System.out.println("2. Ver frente de la cola");
            System.out.println("3. Editar nombre de un elemento especifico");
            System.out.println("4. Borrar un elemento especifico");
            System.out.println("5. Ver estado actual de la cola");
            System.out.println("6. Comprobar si esta vacia");
            System.out.println("7. Salir");
            System.out.println("8. Eliminar elemento");
            System.out.print("Elige una opcion: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        if (contador >= cola.length) {
                            System.out.println("Error: Desbordamiento de cola. La cola esta llena.");
                        } else {
                            System.out.print("Ingresa el nombre del nuevo elemento: ");
                            String nuevo = scanner.nextLine();
                            fin = (fin + 1) % cola.length;
                            cola[fin] = nuevo;
                            contador++;
                            System.out.println("Elemento agregado exitosamente.");
                        }
                        break;

                    case 2:
                        if (contador == 0) {
                            System.out.println("Error: Subdesbordamiento de cola. La cola esta vacia.");
                        } else {
                            System.out.println("Elemento al frente: " + cola[frente]);
                        }
                        break;

                    case 3:
                        if (contador == 0) {
                            System.out.println("Error: Subdesbordamiento de cola. La cola esta vacia.");
                        } else {
                            System.out.print("Cola actual: [");
                            for (int i = 0; i < contador; i++) {
                                int idx = (frente + i) % cola.length;
                                System.out.print(cola[idx] + (i == contador - 1 ? "" : ", "));
                            }
                            System.out.println("]");

                            System.out.print("Ingresa el nombre exacto del elemento que deseas editar: ");
                            String buscado = scanner.nextLine();
                            boolean encontrado = false;

                            for (int i = 0; i < contador; i++) {
                                int idx = (frente + i) % cola.length;
                                if (cola[idx].equalsIgnoreCase(buscado)) {
                                    System.out.print("Ingresa el nuevo nombre para el elemento: ");
                                    cola[idx] = scanner.nextLine();
                                    encontrado = true;
                                    break;
                                }
                            }

                            if (encontrado) {
                                System.out.println("Elemento modificado exitosamente.");
                            } else {
                                System.out.println("El elemento no se encuentra en la cola.");
                            }
                        }
                        break;

                    case 4:
                        if (contador == 0) {
                            System.out.println("Error: Subdesbordamiento de cola. La cola esta vacia.");
                        } else {
                            System.out.print("Cola actual: [");
                            for (int i = 0; i < contador; i++) {
                                int idx = (frente + i) % cola.length;
                                System.out.print(cola[idx] + (i == contador - 1 ? "" : ", "));
                            }
                            System.out.println("]");

                            System.out.print("Ingresa el nombre exacto del elemento que deseas borrar: ");
                            String buscado = scanner.nextLine();
                            int posRelativa = -1;

                            for (int i = 0; i < contador; i++) {
                                int idx = (frente + i) % cola.length;
                                if (cola[idx].equalsIgnoreCase(buscado)) {
                                    posRelativa = i;
                                    break;
                                }
                            }

                            if (posRelativa != -1) {
                                for (int i = posRelativa; i < contador - 1; i++) {
                                    int actualIdx = (frente + i) % cola.length;
                                    int siguienteIdx = (frente + i + 1) % cola.length;
                                    cola[actualIdx] = cola[siguienteIdx];
                                }
                                int ultimoIdx = (frente + contador - 1) % cola.length;
                                cola[ultimoIdx] = null;
                                fin = (fin - 1 + cola.length) % cola.length;
                                contador--;
                                System.out.println("Elemento eliminado exitosamente.");
                            } else {
                                System.out.println("El elemento no se encuentra en la cola.");
                            }
                        }
                        break;

                    case 5:
                        if (contador == 0) {
                            System.out.println("Cola actual: [] Vacia");
                        } else {
                            System.out.print("Cola actual: [");
                            for (int i = 0; i < contador; i++) {
                                int idx = (frente + i) % cola.length;
                                System.out.print(cola[idx] + (i == contador - 1 ? "" : ", "));
                            }
                            System.out.println("]");
                        }
                        break;

                    case 6:
                        if (contador == 0) {
                            System.out.println("Si, la cola esta vacia.");
                        } else {
                            System.out.println("No, la cola contiene elementos.");
                        }
                        break;

                    case 7:
                        System.out.println("Saliendo del programa...");
                        break;

                    case 8:
                        if (contador == 0) {
                            System.out.println("Error: Subdesbordamiento de cola. La cola esta vacia.");
                        } else {
                            String atendido = cola[frente];
                            cola[frente] = null;
                            frente = (frente + 1) % cola.length;
                            contador--;
                            System.out.println("Elemento borrado: " + atendido);
                        }
                        break;

                    default:
                        System.out.println("Opcion invalida. Elige un numero entre 1 y 8.");
                }
            } else {
                System.out.println("Por favor, ingresa un numero valido.");
                scanner.next();
            }

        } while (opcion != 7);

        scanner.close();
    }
}