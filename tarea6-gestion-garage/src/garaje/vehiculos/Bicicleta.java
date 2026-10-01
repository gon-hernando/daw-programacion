package garaje.vehiculos;

import garaje.util.Util;

/**
 * Representa una bicicleta dentro del sistema de gestión de garaje.
 * Extiende de {@link Vehiculo}.
 * 
 * <p>
 * Incluye atributos específicos de una bicicleta, como tipo de manillar
 * y número de marchas. Proporciona métodos para mover el vehículo y validar
 * posiciones.
 * </p>
 * <p>
 * Todos los setters que modifican atributos críticos realizan validación
 * mediante la clase {@link Util}.
 * </p>
 * 
 * @author Gonzalo
 * @version 1.0
 */

public class Bicicleta extends Vehiculo {

    // ===================
    // ---- ATRIBUTOS ----
    // ===================

    /** Tipo de manillar de la bicicleta. */
    private String tipoManillar;

    /** Número de marchas de la bicicleta (0-20). */
    private int numMarchas;

    // =====================
    // ---- CONSTRUCTOR ----
    // =====================

    /**
     * Construye una nueva bicicleta con los datos especificados.
     * 
     * @param marca        Marca de la bicicleta.
     * @param modelo       Modelo de la bicicleta.
     * @param matricula    Matrícula de la bicicleta.
     * @param tipoManillar Tipo de manillar.
     * @param numMarchas   Número de marchas (0-20).
     * @throws IllegalArgumentException si alguno de los datos no es válido.
     */

    public Bicicleta(String marca, String modelo, String matricula, String tipoManillar, int numMarchas) {

        super(marca, modelo, matricula);

        Util.validarManillar(tipoManillar);
        Util.validarNumMarchas(numMarchas);

        this.tipoManillar = tipoManillar;
        this.numMarchas = numMarchas;
    }

    // =================
    // ---- GETTER ----
    // =================

    public String getTipoManillar() {
        return tipoManillar;
    }

    public int getNumMarchas() {
        return numMarchas;
    }

    // =================
    // ---- SETTER ----
    // =================

    /**
     * Establece el tipo de manillar de la bicicleta.
     * 
     * @param tipoManillar Tipo de manillar.
     * @throws IllegalArgumentException si es nulo o vacío.
     */

    public void setTipoManillar(String tipoManillar) {

        Util.validarManillar(tipoManillar);
        this.tipoManillar = tipoManillar;
    }

    /**
     * Establece el número de marchas de la bicicleta.
     * 
     * @param numMarchas Número de marchas (0-20).
     * @throws IllegalArgumentException si el valor no está en el rango permitido.
     */

    public void setnumMarchas(int numMarchas) {

        Util.validarNumMarchas(numMarchas);
        this.numMarchas = numMarchas;
    }

    // ====================
    // ---- INTERFACES ----
    // ====================

    /**
     * Mueve la bicicleta a una nueva posición sumando los desplazamientos X e Y.
     * 
     * @param x Desplazamiento en X.
     * @param y Desplazamiento en Y.
     */

    @Override
    public void mover(int x, int y) {

        super.mover(x, y);
        System.out.printf("La bicicleta se mueve a X:%d, Y:%d\n", getPosicionX(), getPosicionY());
    }

    /**
     * Verifica que la posición final de la bicicleta sea válida (positiva).
     * 
     * @param x Posición final en el eje X.
     * @param y Posición final en el eje Y.
     * @throws IllegalStateException si alguna coordenada es negativa.
     */

    @Override
    public void esMovible(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalStateException("Error: la posición final de la bicicleta debe ser positiva");
        }
    }

    // ==================
    // ---- TOSTRING ----
    // ==================

    /**
     * Devuelve una representación en cadena de la bicicleta, incluyendo los
     * atributos heredados
     * y propios (tipo de manillar y número de marchas).
     * 
     * @return Cadena con información de la bicicleta.
     */

    @Override
    public String toString() {
        return String.format("%s, tipo de manillar: %s, número de marchar: %d",
                super.toString(), tipoManillar, numMarchas);
    }

}
