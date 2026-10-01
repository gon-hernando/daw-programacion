package clases.participantes;

import clases.util.Util;

/**
 * Representa a un participante de tipo profesional.
 * 
 * Un profesional es un {@link Participante} que está asociado
 * a una empresa.
 * 
 * Hereda los atributos básicos como nombre y email.
 * 
 * @author tarde
 */

public class Profesional extends Participante {

    // ===================
    // ---- ATRIBUTOS ----
    // ===================

    String empresa;

    // =====================
    // ---- CONSTRUCTOR ----
    // =====================

    /**
     * Constructor de la clase Profesional.
     * 
     * @param nombre  Nombre del profesional.
     * @param email   Email del profesional.
     * @param empresa Empresa a la que pertenece.
     * 
     * @throws IllegalArgumentException si alguno de los parámetros es inválido.
     */

    public Profesional(String nombre, String email, String empresa) {
        super(nombre, email);

        Util.validarString("empresa", empresa);

        this.empresa = empresa;
    }

    // =================
    // ---- GETTER ----
    // =================

    public String getEmpresa() {
        return empresa;
    }

    // =================
    // ---- SETTER ----
    // =================

    public void setEmpresa(String empresa) {

        Util.validarString("empresa", empresa);

        this.empresa = empresa;
    }

    // ==================
    // ---- TOSTRING ----
    // ==================

    /**
     * Devuelve una representación en forma de texto del profesional,
     * incluyendo los datos heredados y la empresa.
     * 
     * @return cadena con la información del profesional.
     */

    @Override
    public String toString() {

        return String.format("%s, Empresa: %s", super.toString(), empresa);
    }

}
