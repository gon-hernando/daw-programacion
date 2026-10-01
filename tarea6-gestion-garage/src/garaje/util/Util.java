package garaje.util;

/**
 * Clase utilitaria que proporciona métodos estáticos de validación
 * para los distintos atributos del sistema de gestión de vehículos.
 * <p>
 * Incluye validaciones para matrícula, marca, modelo, tipo de manillar,
 * número de marchas, movimiento y cilindrada.
 * </p>
 * <p>
 * Todos los métodos lanzan {@link IllegalArgumentException}
 * si los valores proporcionados no cumplen las reglas establecidas.
 * </p>
 * 
 * @author Gonzalo
 * @version 1.0
 */

public class Util {

    /**
     * Valida que la matrícula no sea nula, vacía y cumpla el formato
     * español estándar (4 números seguidos de 3 letras mayúsculas).
     * 
     * @param matricula Matrícula a validar.
     * @throws IllegalArgumentException si la matrícula es nula, está vacía
     *                                  o no cumple el formato correcto.
     */

    public static void validarMatricula(String matricula) {

        if (matricula == null || matricula.isBlank()) {
            throw new IllegalArgumentException("Error: matrícula no puede estar vacía");
        }

        if (!matricula.matches("[0-9]{4}[A-Z]{3}")) {
            throw new IllegalArgumentException("Error: formato de matrícula no válido");
        }
    }

    /**
     * Valida que la marca no sea nula ni esté vacía.
     * 
     * @param marca Marca del vehículo.
     * @throws IllegalArgumentException si la marca es nula o está vacía.
     */

    public static void validarMarca(String marca) {
        if (marca == null || marca.isBlank()) {
            throw new IllegalArgumentException("Error: el campo marca no puede estar vacío");
        }
    }

    /**
     * Valida que el modelo no sea nulo ni esté vacío.
     * 
     * @param modelo Modelo del vehículo.
     * @throws IllegalArgumentException si el modelo es nulo o está vacío.
     */

    public static void validarModelo(String modelo) {
        if (modelo == null || modelo.isBlank()) {
            throw new IllegalArgumentException("Error: modelo no puede estar vacío");
        }
    }

    /**
     * Valida que el tipo de manillar no sea nulo ni esté vacío.
     * 
     * @param tipoManillar Tipo de manillar de la motocicleta.
     * @throws IllegalArgumentException si el tipo de manillar es nulo o está vacío.
     */

    public static void validarManillar(String tipoManillar) {
        if (tipoManillar == null || tipoManillar.isBlank()) {
            throw new IllegalArgumentException("Error: tipo de manillar no puede estar vacío");
        }
    }

    /**
     * Valida que el número de marchas esté dentro del rango permitido (0-20).
     * 
     * @param numMarchas Número de marchas.
     * @throws IllegalArgumentException si el número está fuera del rango permitido.
     */

    public static void validarNumMarchas(int numMarchas) {
        if (numMarchas < 0 || numMarchas > 20) {
            throw new IllegalArgumentException("Error: el número de marchas debe estar comprendido entre 0-20");
        }
    }

    /**
     * Valida que la posición final del movimiento no sea negativa.
     * 
     * @param posicion Posición a validar.
     * @throws IllegalStateException si la posición es negativa.
     */

    public static void validarMovimiento(int posicion) {
        if (posicion < 0) {
            throw new IllegalStateException("Error: la posición final debe ser positiva");
        }
    }

    /**
     * Valida que la cilindrada esté dentro del rango permitido (49-3000 cc).
     * 
     * @param cilindrada Cilindrada del vehículo.
     * @throws IllegalArgumentException si la cilindrada está fuera del rango
     *                                  permitido.
     */

    public static void validarCilindrada(int cilindrada) {
        if (cilindrada < 49 || cilindrada > 3000) {
            throw new IllegalArgumentException("Error: valores no permitidos");
        }
    }

    /**
     * Valida que el número de puertas de un vehículo esté dentro del rango
     * permitido (2-7).
     * 
     * @param numPuertas Número de puertas del vehículo.
     * @throws IllegalArgumentException si el número de puertas es menor que 2 o
     *                                  mayor que 7.
     */

    public static void validarNumPuertas(int numPuertas) {
        if (numPuertas < 2 || numPuertas > 7) {
            throw new IllegalArgumentException("Error: el número de puertas debe de estar entre 2-7");
        }
    }

    /**
     * Valida que el tipo de combustible no sea nulo ni esté vacío.
     * 
     * @param tipoCombustible Tipo de combustible del vehículo (por ejemplo,
     *                        "Gasolina", "Diésel").
     * @throws IllegalArgumentException si el tipo de combustible es nulo o está
     *                                  vacío.
     */

    public static void validarCombustible(String tipoCombustible) {
        if (tipoCombustible == null || tipoCombustible.isBlank()) {
            throw new IllegalArgumentException("Error");
        }
    }

}
