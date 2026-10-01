package clases;

import java.util.HashMap;

public class Estudiante {

    private int id;
    private HashMap<Asignatura, Matricula> matriculas;

    public Estudiante(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public HashMap<Asignatura, Matricula> getMatriculas() {
        return matriculas;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double obtenerMedia() {
        double media;

        for (Matricula matricula : matriculas.values()) {
            media += matricula;
        }
    }

}
