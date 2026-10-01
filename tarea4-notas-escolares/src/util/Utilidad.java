package util;

public class Utilidad {

    public static double[][][] generarNotas(int estudiantes, int asignaturas, int trimestres) {

        double[][][] notas = new double[estudiantes][asignaturas][trimestres]; // array de 3 dimensiones

        for (int i = 0; i < estudiantes; i++) { // recorremos el array
            for (int j = 0; j < asignaturas; j++) {
                for (int k = 0; k < trimestres; k++) {
                    notas[i][j][k] = Math.round(Math.random() * 100) / 10.0; // notas aleatorias con 1 decimal
                }
            }
        }
        return notas;
    }

}
