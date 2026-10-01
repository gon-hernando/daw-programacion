package ligadeportesalternativos.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUtils {

    // =======================
    // ---- VALIDAR SELECCION ----
    // =======================

    /**
     * Lee y valida un número entero desde consola.
     *
     * @param entrada Scanner para entrada de datos
     * @param mensaje mensaje a mostrar al usuario
     * @return número entero introducido
     */

    public static int validarSeleccion(Scanner entrada, String mensaje) {

        while (true) {

            int entero = -1;

            System.out.print(mensaje);

            try {
                entero = entrada.nextInt();
                entrada.nextLine();
                return entero;

            } catch (InputMismatchException e) {
                entrada.nextLine();
                System.err.println("Error: formato de número incorrecto");
            }
        }
    }

}
