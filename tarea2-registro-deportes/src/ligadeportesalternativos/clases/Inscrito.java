package ligadeportesalternativos.clases;

import ligadeportesalternativos.utils.Util;

public class Inscrito implements Comparable<Inscrito> {

    private String nombre;
    private String apellidos;
    private int edad;

    // ==========================
    // CONSTRUCTOR
    // ==========================

    public Inscrito(String nombre, String apellidos, int edad) {

        Util.validarString(nombre, "nombre");
        Util.validarString(apellidos, "apellidos");

        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }

    // ==========================
    // GETTER
    // ==========================

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getEdad() {
        return edad;
    }

    // ==========================
    // TO STRING
    // ==========================

    @Override
    public String toString() {
        return String.format("Nombre y apellidos: %s %s, edad: %d", this.nombre, this.apellidos, this.edad);
    }

    // ==========================
    // COMPARE TO
    // ==========================

    @Override
    public int compareTo(Inscrito otro) {
        return this.nombre.compareTo(otro.nombre);
    }

}
