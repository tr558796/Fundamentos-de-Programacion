import java.util.Scanner;

public class Pila {

    public static void main(String[] args) {
        String[] pila = new String[5];
        int cima = -1;
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- MENU DE GESTION DE PILA ---");
            System.out.println("1. Agregar elemento");
            System.out.println("2. Ver elemento en la cima");
            System.out.println("3. Editar nombre de un libro especifico");
            System.out.println("4. Borrar un libro en medio");
            System.out.println("5. Ver estado actual de la pila");
            System.out.println("6. Comprobar si esta vacia");
            System.out.println("7. Salir");
            System.out.println("8. Eliminar el ultimo agregado");
            System.out.print("Elige una opcion: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        if (cima >= pila.length - 1) {
                            System.out.println("Error: Desbordamiento de pila. La pila esta llena.");
                        } else {
                            System.out.print("Ingresa el nombre del nuevo libro: ");
                            String nuevoLibro = scanner.nextLine();
                            cima = cima + 1;
                            pila[cima] = nuevoLibro;
                            System.out.println("Libro agregado exitosamente.");
                        }
                        break;

                    case 2:
                        if (cima < 0) {
                            System.out.println("Error: Subdesbordamiento de pila. La pila esta vacia.");
                        } else {
                            System.out.println("Elemento en la cima: " + pila[cima]);
                        }
                        break;

                    case 3:
                        if (cima < 0) {
                            System.out.println("Error: Subdesbordamiento de pila. La pila esta vacia.");
                        } else {
                            System.out.print("Pila actual: [");
                            for (int i = 0; i <= cima; i++) {
                                System.out.print(pila[i] + (i == cima ? "" : ", "));
                            }
                            System.out.println("]");

                            System.out.print("Ingresa el nombre exacto del libro que deseas editar: ");
                            String libroBuscado = scanner.nextLine();
                            boolean encontrado = false;

                            for (int i = 0; i <= cima; i++) {
                                if (pila[i].equalsIgnoreCase(libroBuscado)) {
                                    System.out.print("Ingresa el nuevo nombre para el libro: ");
                                    pila[i] = scanner.nextLine();
                                    encontrado = true;
                                    break;
                                }
                            }

                            if (encontrado) {
                                System.out.println("Libro modificado exitosamente.");
                            } else {
                                System.out.println("El libro no se encuentra en la pila.");
                            }
                        }
                        break;

                    case 4:
                        if (cima < 0) {
                            System.out.println("Error: Subdesbordamiento de pila. La pila esta vacia.");
                        } else {
                            System.out.print("Pila actual: [");
                            for (int i = 0; i <= cima; i++) {
                                System.out.print(pila[i] + (i == cima ? "" : ", "));
                            }
                            System.out.println("]");

                            System.out.print("Ingresa el nombre exacto del libro que deseas borrar: ");
                            String libroBuscado = scanner.nextLine();
                            int indiceEncontrado = -1;

                            for (int i = 0; i <= cima; i++) {
                                if (pila[i].equalsIgnoreCase(libroBuscado)) {
                                    indiceEncontrado = i;
                                    break;
                                }
                            }

                            if (indiceEncontrado != -1) {
                                cima = indiceEncontrado - 1;
                                System.out.println("El libro y todos los elementos superiores han sido eliminados.");
                            } else {
                                System.out.println("El libro no se encuentra en la pila.");
                            }
                        }
                        break;

                    case 5:
                        if (cima < 0) {
                            System.out.println("Pila actual: [] Vacia");
                        } else {
                            System.out.print("Pila actual: [");
                            for (int i = 0; i <= cima; i++) {
                                System.out.print(pila[i] + (i == cima ? "" : ", "));
                            }
                            System.out.println("]");
                        }
                        break;

                    case 6:
                        if (cima < 0) {
                            System.out.println("Si, la pila esta vacia.");
                        } else {
                            System.out.println("No, la pila contiene elementos.");
                        }
                        break;

                    case 7:
                        System.out.println("Saliendo del programa...");
                        break;

                    case 8:
                        if (cima < 0) {
                            System.out.println("Error: Subdesbordamiento de pila. La pila esta vacia.");
                        } else {
                            String eliminado = pila[cima];
                            pila[cima] = null;
                            cima--;
                            System.out.println("Elemento eliminado: " + eliminado);
                        }
                        break;

                    default:
                        System.out.println("Opcion invalida. Elige un numero entre 1 y 7.");
                }
            } else {
                System.out.println("Por favor, ingresa un numero valido.");
                scanner.next();
            }

        } while (opcion != 7);

        scanner.close();
    }
}