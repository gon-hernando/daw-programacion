package clases.participantes;

import clases.util.Util;

/**
 * Representa un participante genérico dentro de un evento.
 * 
 * Un participante tiene un nombre y un email únicos. Esta clase
 * implementa {@link Comparable} para permitir la ordenación
 * alfabética por nombre.
 * 
 * Además, dos participantes se consideran iguales si tienen
 * el mismo email.
 * 
 * @author Gonzalo Hernando
 */
public class Participante implements Comparable<Participante> {

    // ===================
    // ---- ATRIBUTOS ----
    // ===================

    private String nombre;
    private String email;

    // =====================
    // ---- CONSTRUCTOR ----
    // =====================

    /**
     * Constructor de la clase Participante.
     * 
     * @param nombre Nombre del participante.
     * @param email  Email del participante.
     * 
     * @throws IllegalArgumentException si alguno de los parámetros es inválido.
     */

    public Participante(String nombre, String email) {

        Util.validarString("nombre", nombre);
        Util.validarString("email", email);

        this.nombre = nombre;
        this.email = email;
    }

    // =================
    // ---- GETTER ----
    // =================

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    // =================
    // ---- SETTER ----
    // =================

    public void setNombre(String nombre) {

        Util.validarString("nombre", nombre);

        this.nombre = nombre;

    }

    public void setEmail(String email) {

        Util.validarString("email", email);

        this.email = email;
    }

    // ==================
    // ---- TOSTRING ----
    // ==================

    /**
     * Devuelve una representación en forma de texto del participante.
     * Incluye el tipo de clase, nombre y email.
     * 
     * @return cadena con la información del participante.
     */

    @Override
    public String toString() {
        return String.format("%s | Nombre: %s, email: %s", getClass().getSimpleName(), nombre, email);
    }

    // ====================
    // ---- COMPARE TO ----
    // ====================

    /**
     * Compara este participante con otro para ordenación.
     * 
     * La comparación se realiza alfabéticamente por nombre.
     * 
     * @param p Participante a comparar.
     * @return valor negativo, cero o positivo según el orden.
     */

    @Override
    public int compareTo(Participante p) {
        return nombre.compareTo(p.nombre);
    }

    // ====================
    // ---- EQUALS ----
    // ====================

    /**
     * Comprueba si dos participantes son iguales.
     * 
     * Dos participantes son iguales si tienen el mismo email.
     * 
     * @param otro Objeto a comparar.
     * @return true si son iguales, false en caso contrario.
     */

    @Override
    public boolean equals(Object otro) {
        if (this == otro)
            return true;
        if (!(otro instanceof Participante))
            return false;
        Participante p = (Participante) otro;
        return email.equals(p.email);
    }

    // ====================
    // ---- HASH CODE ----
    // ====================

    /**
     * Devuelve el código hash del participante.
     * 
     * Se basa en el email para mantener consistencia con equals().
     * 
     * @return código hash.
     */

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}
