package util;

import java.util.Scanner;

import clases.CuentaBancaria;

/**
 * Clase {@code Validar} que proporciona métodos estáticos para validar
 * distintos datos relacionados con una cuenta bancaria.
 * <p>
 * Permite validar NIF o NIE, código de cuenta cliente (CCC),
 * nombre del titular y los dígitos de control del CCC.
 *
 * @author gonzaloHernando
 * @version 1.0
 * @since 2026-01-26
 */

public class Util {

    // Constructor vacío privado

    /**
     * Constructor vacío private.
     */

    private Util() {
    };

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

    // ===========================
    // ==== VALIDAR NIF o NIE ====
    // ===========================

    /**
     * Valida el NIF o NIE asociado a la cuenta
     * 
     * @param nifONie NIF o NIE a validar
     * @throws IllegalArgumentException si NIE/NIF tiene formato incorrecto
     * @throws IllegalArgumentException si NIE/NIF tiene letra de control incorrecta
     */

    public static void esValidoNifONie(String nifONie) {

        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        nifONie = nifONie.toUpperCase(); // estandarizamos a mayuscula

        if (!nifONie.matches("^[XYZ0-9][0-9]{7}[A-Z]$")) {
            throw new IllegalArgumentException("Formato de NIF o NIE incorrecto"); // chequeamos formato NIF o NIE
        }

        char primerCaracter = nifONie.charAt(0);
        String numero = nifONie.substring(0, 8);

        if (primerCaracter == 'X') { // Para NIE
            numero = "0" + numero.substring(1);
        } else if (primerCaracter == 'Y') {
            numero = "1" + numero.substring(1);
        } else if (primerCaracter == 'Z') {
            numero = "2" + numero.substring(1);
        }

        int n = Integer.parseInt(numero);
        char letraCorrecta = letras.charAt(n % 23);

        if (nifONie.charAt(8) != letraCorrecta) {
            throw new IllegalArgumentException("Letra de NIF o NIE incorrecta");
        }
    }

    // ========================
    // ==== VALIDAR NOMBRE ====
    // ========================

    /**
     * Valida el nombre del titular de la cuenta (36 caracteres máximmo)
     * 
     * @param nombreTitularCuenta Nombre a validar
     * @throws IllegalArgumentException si nombreTitularCuenta es null
     * @throws IllegalArgumentException si formato incorrecto
     * @throws IllegalArgumentException si más de 36 caracteres
     */

    public static void validarNombre(String nombreTitularCuenta) {

        if (nombreTitularCuenta == null) { // validacion del nombre, evitar null

            throw new IllegalArgumentException("Error: el nombre del titular no puede ser nulo");
        }

        if (nombreTitularCuenta.length() > 36) { // validacion del nombre, permitiendo solo letras
                                                 // reales y espacio en blanco
            throw new IllegalArgumentException("Error: el nombre del titular debe contener 36 caracteres máximo");
        }

        if (!nombreTitularCuenta.matches("[\\p{L} ]+")) { // validacion de formato del nombre
                                                          // letras reales UNICOde y espacios en blnaco
            throw new IllegalArgumentException("Error: formato del nombre del titular incorrecto");
        }

    }

    // ====================================
    // ==== VALIDAR DIGITOS DE CONTROL ====
    // ====================================

    /**
     * Valida los dígitos de un código de cuenta de cliente
     * 
     * @param codigoCuentaCliente Código de cuenta a validar
     * @return {@code true} si el código es válido, {@code false} en caso contrario
     * @see CuentaBancaria#calcularDigitos(String, String, String)
     */

    public static boolean validarDigitos(String codigoCuentaCliente) {
        boolean valido = false;

        // Extraer dígitos de CCC

        String entidad = codigoCuentaCliente.substring(0, 4);
        String oficina = codigoCuentaCliente.substring(4, 8);
        String numeroCuenta = codigoCuentaCliente.substring(10, 20);

        // Calcular digitosControl

        String digitosControl = CuentaBancaria.calcularDigitos(entidad, oficina, numeroCuenta);

        // Validad digitosControl

        if (codigoCuentaCliente.substring(8, 10).equals(digitosControl)) {
            valido = true; // condicion para ok
        }

        return valido;
    }

    // ==================================
    // ==== VALIDAR INGRESO/RETIRADA ====
    // =================================

    /**
     * Valida que una cantidad monetaria tenga 2 decimales.
     *
     * @param cantidad Cantidad a validar
     * @throws IllegalArgumentException si incluye decimales
     */

    public static void validarCantidad(double cantidad) {

        String[] partesNumero = String.valueOf(cantidad).split("\\."); // dividimos por decimales y guardamos en array

        if (partesNumero.length == 2 && partesNumero[1].length() > 2) { // validamos que no haya mas de 2 decimales
            throw new IllegalArgumentException("Error: solo se permiten números con hasta 2 decimales");
        }
    }

    // ========================
    // ==== VALIDACIÓN CCC ====
    // ========================

    /**
     * Valida el CCC de la cuenta (longitud y digitos de control)
     * 
     * @param codigoCuentaCliente CCC a validar
     * @throws IllegalArgumentException si CCC es nulo
     * @throws IllegalArgumentException si CCC no cumple formato
     * @throws IllegalArgumentException si CCC contiene digitos de control erroneos
     * 
     * @see Util#validarDigitos(String) Método que valida los dígitos de control
     */

    public static void esCuentaValida(String codigoCuentaCliente) {

        if (codigoCuentaCliente == null) {
            throw new IllegalArgumentException("Error: El CCC no puede ser nulo");
        }
        if (!codigoCuentaCliente.matches("\\d{20}")) {
            throw new IllegalArgumentException("Error: El CCC no cumple el formato necesario");
        }
        if (!Util.validarDigitos(codigoCuentaCliente)) {
            throw new IllegalArgumentException("Error: Digitos de control incorrectos");
        }

    }

}
