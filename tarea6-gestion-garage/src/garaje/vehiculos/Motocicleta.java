package garaje.vehiculos;

import garaje.interfaces.Arrancable;
import garaje.util.Util;

/**
 * Representa una motocicleta dentro del sistema de gestión de vehículos.
 * Extiende de {@link Vehiculo} y implementa la interfaz {@link Arrancable}.
 * Permite mover, arrancar y detener la motocicleta, además de acceder y
 * modificar sus atributos específicos como cilindrada y tipo de manillar.
 * <p>
 * Incluye validaciones de entrada para asegurar que la cilindrada y el tipo de
 * manillar sean correctos.
 * </p>
 * 
 * @author Gonzalo Hernando Llorente
 * @version
 */
public class Motocicleta extends Vehiculo implements Arrancable {

    // ===================
    // ---- ATRIBUTOS ----
    // ===================

    /**
     * La cilindrada de la motocicleta en centímetros cúbicos (cc).
     */
    private int cilindrada;

    /**
     * Tipo de manillar de la motocicleta (por ejemplo, "deportivo", "clásico").
     */
    private String tipoManillar;

    // =====================
    // ---- CONSTRUCTOR ----
    // =====================

    /**
     * Construye una nueva motocicleta con los datos especificados.
     * 
     * @param marca        Marca de la motocicleta.
     * @param modelo       Modelo de la motocicleta.
     * @param matricula    Matrícula de la motocicleta.
     * @param cilindrada   Cilindrada de la motocicleta (en cc).
     * @param tipoManillar Tipo de manillar.
     * @throws IllegalArgumentException si la cilindrada o el tipo de manillar no
     *                                  son válidos.
     */
    public Motocicleta(String marca, String modelo, String matricula, int cilindrada, String tipoManillar) {

        super(marca, modelo, matricula);

        Util.validarCilindrada(cilindrada);
        Util.validarManillar(tipoManillar);

        this.cilindrada = cilindrada;
        this.tipoManillar = tipoManillar;
    }

    // =================
    // ---- GETTER ----
    // =================

    public int getCilindrada() {
        return cilindrada;
    }

    public String getTipoManillar() {
        return tipoManillar;
    }

    // =================
    // ---- SETTER ----
    // =================

    /**
     * Establece la cilindrada de la motocicleta.
     * 
     * @param cilindrada Nueva cilindrada en cc.
     * @throws IllegalArgumentException si la cilindrada no es válida.
     */

    public void setCilindrada(int cilindrada) {

        Util.validarCilindrada(cilindrada);
        this.cilindrada = cilindrada;
    }

    /**
     * Establece el tipo de manillar de la motocicleta.
     * 
     * @param tipoManillar Nuevo tipo de manillar.
     * @throws IllegalArgumentException si el tipo de manillar no es válido.
     */

    public void setTipoManillar(String tipoManillar) {

        Util.validarManillar(tipoManillar);
        this.tipoManillar = tipoManillar;
    }

    // ====================
    // ---- INTERFACES ----
    // ====================

    /**
     * Mueve la motocicleta a la posición especificada.
     * 
     * @param x Avance en eje X.
     * @param y Avance en eje Y.
     * @throws IllegalStateException si la motocicleta no está arrancada.
     */

    @Override
    public void mover(int x, int y) {

        if (!getArrancado()) {
            throw new IllegalStateException("Error: La motocicleta no está arrancada");
        }
        super.mover(x, y);
        System.out.printf("La motocicleta se mueve a X:%d, Y:%d\n", getPosicionX(), getPosicionY());
    }

    /**
     * Verifica que la posición final de la motocicleta sea válida (positiva).
     * <p>
     * Este método se llama antes de mover la motocicleta para asegurar
     * que no se intente colocar en coordenadas negativas.
     * </p>
     * 
     * @param x Posición final en el eje X.
     * @param y Posición final en el eje Y.
     * @throws IllegalStateException si alguna coordenada es negativa.
     */

    @Override
    public void esMovible(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalStateException("Error: la posición final de la motocicleta debe ser positiva");
        }
    }

    /**
     * Devuelve el estado actual de la motocicleta.
     * 
     * @return String indicando si la motocicleta está arrancada o detenida.
     */

    @Override
    public String getEstado() {
        return getArrancado() ? "La motocicleta está arrancada" : "La motocicleta está detenida";
    }

    /**
     * Arranca la motocicleta.
     * 
     * @throws IllegalStateException si la motocicleta ya está arrancada.
     */

    @Override
    public void arrancar() {
        if (getArrancado()) {
            throw new IllegalStateException("Error: La motocicleta ya está arrancada");
        }

        setArrancado(true);
        System.out.println("La motocicleta ha arrancado");
    }

    /**
     * Detiene la motocicleta.
     * 
     * @throws IllegalStateException si la motocicleta ya está detenida.
     */

    @Override
    public void detener() {

        if (!getArrancado()) {
            throw new IllegalStateException("Error: La motocicleta ya está detenida");
        }

        setArrancado(false);
        System.out.println("La motocicleta se ha detenido");
    }

    // ==================
    // ---- TOSTRING ----
    // ==================

    /**
     * Devuelve una representación en cadena de la motocicleta,
     * incluyendo los atributos heredados y propios.
     * 
     * @return String con información de la motocicleta.
     */

    public String toString() {
        return String.format("%s, cilindrada: %d, tipo de manillar: %s",
                super.toString(), cilindrada, tipoManillar);
    }

}
