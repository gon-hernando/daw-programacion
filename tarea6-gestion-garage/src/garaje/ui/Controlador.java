package garaje.ui;

import java.util.Scanner;

import garaje.gestion.Garaje;

public class Controlador {

    public static void iniciar(Scanner entrada) {

        // VARIABLES AUXILIARES

        boolean salir = false;

        do {

            int seleccion = Menu.mostrarMenu(entrada);

            switch (seleccion) {

                // AÑADIR VEHICULOS

                case 1:
                    System.out.println("\n====AÑADIR VEHÍCULOS====\n");
                    Garaje.añadirVehiculo(entrada);

                    break;

                // MOSTRAR INFO

                case 2:
                    System.out.println("\n====MOSTRAR INFORMACIÓN DE VEHÍCULOS====\n");
                    Garaje.listarVehiculo();

                    break;

                // USAR

                case 3:
                    System.out.println("\n====USAR VEHÍCULO====\n");

                    if (Garaje.getVehiculos().size() > 0) { // opción para salir
                        System.out.println("0. Salir");
                    }
                    Garaje.listarVehiculo();
                    Garaje.ejecutarAccion(entrada);

                    break;

                // SALIR

                case 4:

                    salir = true;
                    System.out.println("\n====FIN DE LA APLICACIÓN====\n");

                    break;

                default:
                    System.err.println("Error en la selección\n");

                    break;
            }

        } while (!salir);

    }

}
