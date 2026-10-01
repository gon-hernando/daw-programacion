package ligadeportesalternativos.clases.deportes;

import java.util.Set;
import java.util.TreeSet;

import ligadeportesalternativos.clases.Inscrito;
import ligadeportesalternativos.utils.Util;

public class Deporte implements Comparable<Deporte> {

    private String nombreDeporte;
    private int edadMinima;
    private int edadMaxima;
    private int usuariosMaximos;
    Set<Inscrito> inscritos = new TreeSet<>();

    // ==========================
    // CONSTRUCTOR
    // ==========================

    public Deporte(String nombreDeporte, int edadMinima, int edadMaxima, int usuariosMaximos) {

        Util.validarString(nombreDeporte, "Nombre del deporte");

        this.nombreDeporte = nombreDeporte;
        this.edadMinima = edadMinima;
        this.edadMaxima = edadMaxima;
        this.usuariosMaximos = usuariosMaximos;
    }

    // ==========================
    // GETTER
    // ==========================

    public String getNombreDeporte() {
        return nombreDeporte;
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    public int getEdadMaxima() {
        return edadMaxima;
    }

    public int getUsuariosMaximos() {
        return usuariosMaximos;
    }

    // ==========================
    // OBTENER NUM INSCRITOS
    // ==========================

    public int obtenerNumInscritos() {
        return inscritos.size();
    }

    // ==========================
    // ---- INSCRIBIR
    // ==========================

    public void inscribir(Inscrito i) {

        if (plazasVacantes() == 0) {
            throw new IllegalStateException("Error: no quedan plazas en el deporte seleccioando");
        }
        inscritos.add(i);
    }

    // ==========================
    // COMPROBAR EDAD
    // ==========================

    public void comprobarEdad(int edad) {
        if (edad < this.edadMinima || edad > this.edadMaxima) {
            throw new IllegalArgumentException("Error: edad fuera de rango");
        }
    }

    // ==========================
    // DATOS
    // ==========================

    public void datos() {

        double mediaEdad = 0;
        int sumatorioEdad = 0;

        System.out.println(getNombreDeporte().toUpperCase());

        if (obtenerNumInscritos() == 0) {
            System.out.println("No se han solicitado plazas\n");
        } else {

            for (Inscrito i : inscritos) {
                sumatorioEdad += i.getEdad();
            }

            mediaEdad = (double) sumatorioEdad / obtenerNumInscritos();

            System.out.printf("%-22s | %d\n", "Plazas solicitadas", obtenerNumInscritos());
            System.out.printf("%-22s | %d\n", "Plazas vacantes", plazasVacantes());
            System.out.printf("%-22s | %.2f años\n\n", "Media de edad", mediaEdad);
        }
    }

    public int plazasVacantes() {
        int plazasRestantes = usuariosMaximos - inscritos.size();
        return plazasRestantes;
    }

    @Override
    public String toString() {
        return String.format("%-30s | Plazas vacantes: %d\n", this.nombreDeporte, plazasVacantes());
    }

    @Override
    public int compareTo(Deporte otro) {
        return this.nombreDeporte.compareTo(otro.nombreDeporte);
    }

}
