/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases.participantes;

import clases.util.Util;

/**
 * Representa a un ponente dentro de un evento.
 * Un ponente es un tipo de {@link Participante} que tiene asignado un tema
 * sobre el que realizará su intervención.
 * 
 * Hereda los atributos básicos como nombre y email.
 * 
 * @author Gonzalo Hernando
 */
public class Ponente extends Participante {

    // ===================
    // ---- ATRIBUTOS ----
    // ===================

    String tema;

    // =====================
    // ---- CONSTRUCTOR ----
    // =====================

    /**
     * Constructor de la clase Ponente.
     * 
     * @param nombre Nombre del ponente.
     * @param email  Email del ponente.
     * @param tema   Tema de la ponencia.
     * 
     * @throws IllegalArgumentException si alguno de los parámetros es inválido.
     */

    public Ponente(String nombre, String email, String tema) {

        super(nombre, email);

        Util.validarString("tema", tema);

        this.tema = tema;
    }

    // =================
    // ---- GETTER ----
    // =================

    public String getTema() {
        return tema;
    }

    // =================
    // ---- SETTER ----
    // =================

    public void setTema(String tema) {

        Util.validarString("tema", tema);

        this.tema = tema;
    }

    // ==================
    // ---- TOSTRING ----
    // ==================

    /**
     * Devuelve una representación en forma de texto del ponente,
     * incluyendo los datos heredados y el tema.
     * 
     * @return cadena con la información del ponente.
     */

    @Override
    public String toString() {

        return String.format("%s, Tema: %s", super.toString(), tema);
    }

}
