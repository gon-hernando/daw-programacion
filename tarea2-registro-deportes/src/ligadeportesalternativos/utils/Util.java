package ligadeportesalternativos.utils;

import java.util.Scanner;

public class Util {

    // =============================
    // ---- VALIDAR STRING ----
    // =============================

    /**
     * Valida que un String no sea nulo ni esté vacío.
     *
     * @param tipo  descripción del campo que se está validando
     * @param texto valor a validar
     * @throws IllegalArgumentException si el texto es nulo o vacío
     */

    public static void validarString(String texto, String tipo) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException(String.format("Error: %s no puede estar vacío", tipo));
        }
    }

    // =============================
    // ---- VALIDAR ENTERO ----
    // =============================

    public static int validarEntero(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("Error: valor vacío o nulo");
        }

        try {
            return Integer.parseInt(valor);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error: formato de número no válido");
        }
    }

    public static void validarEdad(int edad, int edadMinima, int edadMaxima) {

        if (edad < edadMinima || edad > edadMaxima) {
            throw new IllegalArgumentException("Error: edad fuera de rango");
        }
    }

    // ==================
    // ---- REGISTRO ----
    // ==================

    /**
     * Solicita y valida entrada de texto por consola, asegurando que no esté vacío.
     *
     * @param entrada Scanner para entrada de datos
     * @param texto   mensaje a mostrar al usuario
     * @return cadena introducida y validada
     */

    public static String registro(Scanner entrada, String texto) {

        while (true) {

            System.out.print(texto);
            String registro = entrada.nextLine().trim();

            try {
                Util.validarString(registro, texto);
                return registro;

            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    }

}
