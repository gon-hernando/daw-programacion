package clases.util;

import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

import clases.participantes.Participante;

/**
 * Clase de utilidades generales para validación de datos,
 * entrada por consola y manejo de colecciones.
 *
 * <p>
 * Proporciona métodos estáticos reutilizables para evitar duplicación
 * de lógica en el resto del sistema de gestión de eventos.
 * </p>
 * * @author Gonzalo Hernando
 */

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

    public static void validarString(String tipo, String texto) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException(String.format("Error: %s no puede estar vacío", tipo));
        }
    }

    // =============================
    // ---- VALIDAR EMAIL ----
    // =============================

    /**
     * Valida el formato de un email y comprueba que no exista
     * ya en el sistema.
     *
     * @param email     email a validar
     * @param mapaEmail mapa de emails existentes
     * @throws IllegalArgumentException si el formato es inválido o ya existe
     */

    public static void validarEmail(String email, Map<String, Participante> mapaEmail) {

        if (email == null || !email.matches("[0-9a-zA-Z._%+-]+@[0-9a-zA-Z.-]+\\.[A-Za-z]{2,}")) {
            throw new IllegalArgumentException("Error: formato de email no válido");
        }

        if (mapaEmail.containsKey(email)) {
            throw new IllegalArgumentException("Error: ya existe un participante con ese email");
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

    public static String registro(Scanner entrada, String texto, String nombreDato) {

        while (true) {
            System.out.print(texto);
            String registro = entrada.nextLine().trim();

            try {
                Util.validarString(nombreDato, registro);
                return registro;

            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    // =======================
    // ---- VALIDAR ENTERO ----
    // =======================

    /**
     * Lee y valida un número entero desde consola.
     *
     * @param entrada Scanner para entrada de datos
     * @param mensaje mensaje a mostrar al usuario
     * @return número entero introducido
     */

    public static int validarEntero(Scanner entrada, String mensaje) {

        int seleccion = -1;

        while (true) {

            System.out.printf("\n%s", mensaje);

            try {
                seleccion = entrada.nextInt();
                entrada.nextLine();
                return seleccion;

            } catch (Exception e) {
                entrada.nextLine();
                System.err.println("Error: selección no válida");
            }
        }
    }

    // ==============================
    // ---- COMPROBAR COLECCIÓN ----
    // ==============================

    /**
     * Comprueba si una colección contiene elementos.
     *
     * @param coleccion colección a comprobar
     * @param mensaje   mensaje a mostrar si está vacía
     * @param <T>       tipo de elementos de la colección
     * @return true si la colección tiene elementos, false si está vacía
     */

    public static <T> boolean comprobarColeccion(Collection<T> coleccion, String mensaje) {
        if (coleccion.isEmpty()) {
            System.out.println(mensaje);
            return false;
        }
        return true;
    }

    // ==========================
    // ---- MOSTRAR COLECCION ----
    // ==========================

    /**
     * Muestra por consola los elementos de una colección numerados.
     *
     * @param coleccion colección a mostrar
     * @param <T>       tipo de elementos de la colección
     */

    public static <T> void mostrarColeccion(Collection<T> coleccion) {

        if (comprobarColeccion(coleccion, "No hay elementos que mostrar")) {
            int contador = 0;

            for (T elemento : coleccion) {
                contador++;
                System.out.printf("%d. %s\n", contador, elemento);
            }

            System.out.println("0. Salir");
        }
    }
}
