package clases.participantes;

import clases.util.Util;

/**
 * Representa a un participante de tipo estudiante.
 * 
 * Un estudiante es un {@link Participante} que está matriculado
 * en un curso determinado.
 * 
 * Hereda los atributos básicos como nombre y email.
 */

public class Estudiante extends Participante {

    // ===================
    // ---- ATRIBUTOS ----
    // ===================

    private String curso;

    // =====================
    // ---- CONSTRUCTOR ----
    // =====================

    /**
     * Constructor de la clase Estudiante.
     * 
     * @param nombre Nombre del estudiante.
     * @param email  Email del estudiante.
     * @param curso  Curso en el que está matriculado.
     * 
     * @throws IllegalArgumentException si alguno de los parámetros es inválido.
     */

    public Estudiante(String nombre, String email, String curso) {
        super(nombre, email);

        Util.validarString("curso", curso);

        this.curso = curso;
    }

    // =================
    // ---- GETTER ----
    // =================

    public String getCurso() {
        return curso;
    }

    // =================
    // ---- SETTER ----
    // =================

    public void setCurso(String curso) {

        Util.validarString("curso", curso);

        this.curso = curso;
    }

    // ==================
    // ---- TOSTRING ----
    // ==================

    /**
     * Devuelve una representación en forma de texto del estudiante,
     * incluyendo los datos heredados y el curso.
     * 
     * @return cadena con la información del estudiante.
     */

    @Override
    public String toString() {

        return String.format("%s, Curso: %s", super.toString(), curso);
    }

}
