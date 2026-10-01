package garaje.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public static int mostrarMenu(Scanner entrada) {

        // ===============
        // ---- MENÚ ----
        // ===============

        while (true) {

            System.out.println("\n==== MENÚ GARAJE ====\n");

            System.out.println("1. Añadir nuevo vehículo");
            System.out.println("2. Mostrar información de vehículos");
            System.out.println("3. Usar vehículos");
            System.out.println("4. Salir");

            System.out.print("\nSeleccione opción: ");

            try {
                int seleccion = entrada.nextInt();
                entrada.nextLine();

                if (seleccion >= 1 && seleccion <= 4) {
                    return seleccion;
                } else {
                    System.err.print("Error: Seleccione número entre 1-4");
                }

            } catch (Exception e) {
                entrada.nextLine();
                System.err.print("Error: Selección fuera de rango");
            }

        }

    }

    public static int menuSeleccionarVehiculo(Scanner entrada) {

        while (true) {

            System.out.println("1. Bicicleta");
            System.out.println("2. Coche");
            System.out.println("3. Motocicleta");
            System.out.println("4. Salir");

            System.out.print("\nSeleccione tipo de vehículo: ");

            try {
                int seleccion = entrada.nextInt();
                entrada.nextLine();
                return seleccion;

            } catch (Exception e) {
                entrada.nextLine();
                System.err.println("Error: selección no válida");
            }

        }
    }

    public static int menuAcciones(Scanner entrada) {

        while (true) {

            System.out.println("\n1. Arrancar");
            System.out.println("2. Mover");
            System.out.println("3. Detener");
            System.out.println("4. Comprobar estado");
            System.out.println("5. Salir");

            System.out.print("\nSeleccione tipo de acción: ");

            try {
                int seleccion = entrada.nextInt();
                entrada.nextLine();

                return seleccion;

            } catch (InputMismatchException e) {
                System.err.println("Debe introducir un número válido");
                entrada.nextLine();
            }
        }
    }

}
