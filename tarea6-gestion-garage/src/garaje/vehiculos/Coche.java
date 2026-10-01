package garaje.vehiculos;

import garaje.interfaces.Arrancable;
import garaje.util.Util;

/**
 * Representa un coche dentro del sistema de gestión de garaje.
 * Extiende de {@link Vehiculo} e implementa la interfaz {@link Arrancable}.
 * <p>
 * Incluye atributos específicos de un coche como el número de puertas
 * y el tipo de combustible. Proporciona métodos para mover, arrancar y detener
 * el vehículo.
 * </p>
 * <p>
 * Todos los setters que modifican atributos críticos realizan validación
 * mediante la clase {@link Util}.
 * </p>
 * 
 * @author Gonzalo Hernando Llorente
 * @version
 */

public class Coche extends Vehiculo implements Arrancable {

    // ===================
    // ---- ATRIBUTOS ----
    // ===================

    private int numPuertas;
    private String tipoCombustible;

    // =====================
    // ---- CONSTRUCTOR ----
    // =====================

    /**
     * Construye un nuevo coche con los datos especificados.
     * 
     * @param marca           Marca del coche.
     * @param modelo          Modelo del coche.
     * @param matricula       Matrícula del coche.
     * @param numPuertas      Número de puertas (2-7).
     * @param tipoCombustible Tipo de combustible.
     * @throws IllegalArgumentException si alguno de los datos no es válido.
     */

    public Coche(String marca, String modelo, String matricula, int numPuertas, String tipoCombustible) {

        super(marca, modelo, matricula);

        Util.validarNumPuertas(numPuertas);
        Util.validarCombustible(tipoCombustible);

        this.numPuertas = numPuertas;
        this.tipoCombustible = tipoCombustible;
    }

    // =================
    // ---- GETTER ----
    // =================

    public int getNumPuertas() {
        return numPuertas;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    // =================
    // ---- SETTER ----
    // =================

    /**
     * Establece el número de puertas del coche.
     * 
     * @param numPuertas Número de puertas (2-7).
     * @throws IllegalArgumentException si el valor no está en el rango permitido.
     */

    public void setNumPuertas(int numPuertas) {

        Util.validarNumPuertas(numPuertas);
        this.numPuertas = numPuertas;
    }

    /**
     * Establece el tipo de combustible del coche.
     * 
     * @param tipoCombustible Tipo de combustible.
     * @throws IllegalArgumentException si es nulo o vacío.
     */

    public void setTipoCombustible(String tipoCombustible) {

        Util.validarCombustible(tipoCombustible);
        this.tipoCombustible = tipoCombustible;
    }

    // ====================
    // ---- INTERFACES ----
    // ====================

    /**
     * Mueve el coche a una nueva posición sumando los desplazamientos X e Y.
     * 
     * @param x Desplazamiento en X.
     * @param y Desplazamiento en Y.
     * @throws IllegalStateException si el coche no está arrancado.
     */

    @Override
    public void mover(int x, int y) {

        if (!getArrancado()) {
            throw new IllegalStateException("Error: El coche no está arrancado");
        }

        super.mover(x, y);

        System.out.printf("El coche se mueve a X:%d, Y:%d\n", getPosicionX(), getPosicionY());
    }

    /**
     * Verifica que la posición final del coche sea válida (positiva).
     * 
     * @param x Posición final X.
     * @param y Posición final Y.
     * @throws IllegalStateException si alguna coordenada es negativa.
     */

    @Override
    public void esMovible(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalStateException("Error: la posición final del coche debe ser positiva");
        }
    }

    /**
     * Devuelve el estado actual del coche (arrancado o detenido).
     * 
     * @return Cadena con el estado del coche.
     */

    @Override
    public String getEstado() {
        return getArrancado() ? "El coche está arrancado" : "El coche está detenido";
    }

    /**
     * Arranca el coche.
     * 
     * @throws IllegalStateException si el coche ya está arrancado.
     */

    @Override
    public void arrancar() {

        if (getArrancado()) {
            throw new IllegalStateException("Error: El coche ya está arrancado");
        }

        setArrancado(true);
        System.out.println("El coche ha arrancado");
    }

    /**
     * Detiene el coche.
     * 
     * @throws IllegalStateException si el coche ya está detenido.
     */

    @Override
    public void detener() {
        if (!getArrancado()) {
            throw new IllegalStateException("Error: El coche ya está detenido");
        }
        setArrancado(false);
        System.out.println("El coche se ha detenido");

    }

    // ==================
    // ---- TOSTRING ----
    // ==================

    /**
     * Devuelve una representación en cadena del coche, incluyendo los atributos
     * heredados
     * y propios (número de puertas y tipo de combustible).
     * 
     * @return Cadena con información del coche.
     */

    public String toString() {
        return String.format("%s, número de puertas: %d, tipo de combustible: %s",
                super.toString(), numPuertas, tipoCombustible);
    }

}
