
package clases.eventos;

/**
 * Clase genérica que representa un recurso asociado a un evento.
 *
 * <p>
 * Un recurso tiene un nombre identificativo y un dato de tipo genérico
 * que puede ser de cualquier tipo (String, Integer, LocalDate, etc.).
 * </p>
 *
 * @param <T> tipo del dato almacenado en el recurso
 * @author Gonzalo Hernando
 */

public class Recurso<T> {

    private String nombre;
    private T dato;

    // =====================
    // ---- CONSTRUCTOR ----
    // =====================

    /**
     * Crea un nuevo recurso con nombre y dato asociados.
     *
     * @param nombre nombre del recurso
     * @param dato   dato asociado al recurso
     */

    public Recurso(String nombre, T dato) {
        this.nombre = nombre;
        this.dato = dato;
    }

    // =================
    // ---- GETTER ----
    // =================

    public String getNombre() {
        return nombre;
    }

    public T getDato() {
        return dato;
    }

    // =================
    // ---- SETTER ----
    // =================

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    // ==================
    // ---- TOSTRING ----
    // ==================

    @Override
    public String toString() {
        return String.format("Nombre: %s, Dato: %s", nombre, dato);
    }

}
