package ligadeportesalternativos.interfaz;

import java.util.Scanner;

import ligadeportesalternativos.clases.deportes.Deporte;
import ligadeportesalternativos.clases.deportes.GestorDeportes;
import ligadeportesalternativos.utils.ConsoleUtils;

public class Menu {

    public static void inicio(Scanner entrada) {

        boolean salir = false;

        do {

            System.out.println("\nMENÚ DE INICIO\n");

            System.out.println("1. Registro de usuarios");
            System.out.println("2. Estadísticas");
            System.out.println("0. Salir\n");

            int seleccion = ConsoleUtils.validarSeleccion(entrada, "Seleccione opción: ");

            switch (seleccion) {

                case 1:

                    Deporte deporteSeleccionado = Registro.seleccionarDeporte(entrada);
                    Registro.registroUsuario(entrada, deporteSeleccionado);
                    break;

                case 2:

                    GestorDeportes.estadisticas();
                    break;

                case 0:

                    salir = true;
                    System.out.println("Fin del programa");
                    break;

                default:
                    System.err.println("Selección errónea");
                    break;
            }

        } while (!salir);
    }

}
