
import java.util.Scanner;

/**
 * Clase para registro de estudiantes, asignaturas y trimestres cuya función es
 * solicitar al usuario un número que válida entre las
 * constantes mínimas y máximas definidas en el método main y devuelve el dato
 * numérico
 * 
 * @author gonzaloHernando
 * @version 2
 */

public class Datos {

    // ATRIBUTOS

    private String textoVariable; // estudiantes/asignaturas/trimestres
    private int minimo; // referencia a constante mínima de cada sección
    private int maximo; // referencia a constante máxima de cada sección

    // CONSTRUCTOR

    /**
     * Constructor que inicializa la instancia con los parámetros necesarios
     * para la validación de la entrada del usuario.
     * 
     * @param textoVariable Nombre descriptivo del dato solicitado
     *                      (por ejemplo: estudiantes, asignaturas, trimestres).
     * @param minimo        Valor mínimo permitido.
     * @param maximo        Valor máximo permitido.
     */

    public Datos(String textoVariable, int minimo, int maximo) {
        this.textoVariable = textoVariable;
        this.minimo = minimo;
        this.maximo = maximo;
    }

    /**
     * Solicita al usuario un valor numérico y lo valida dentro del rango
     * permitido.
     * 
     * El método repite la solicitud hasta que el usuario introduce un valor
     * correcto. También maneja excepciones para evitar errores en la entrada.
     * 
     * @param entrada Scanner utilizado para leer la entrada del usuario.
     * @return Valor numérico validado dentro del rango permitido.
     */

    public int registroValores(Scanner entrada) { // método con parámetro Scanner desde método main

        int datoSolicitado = 0; // dato solicitado al usuario por teclado que será devuelto desde la clase

        do {
            System.out.println();
            System.out.println("REGISTRO DE NÚMERO DE " + textoVariable.toUpperCase()); // texto explicativo
            System.out.println("Introduzca número de " + textoVariable + " (" + minimo + "-" + maximo + "): ");

            try {
                datoSolicitado = entrada.nextInt();
                entrada.nextLine(); // limpiar buffer tras nextInt

                if (datoSolicitado < minimo || datoSolicitado > maximo) { // validar número entre constantes mínimas y
                                                                          // máximas de cada sección
                    System.err.println("Número de " + textoVariable + " introducido no válido");
                    System.err.println("Introduzca un número entre " + minimo + " y " + maximo);
                } else {
                    System.out.println("Registro de " + textoVariable + " finalizado con éxito"); // mensaje de
                                                                                                  // confirmación

                }

            } catch (Exception e) {
                entrada.nextLine();
                System.err.println("Error en la introducción de datos");
            }

        } while (datoSolicitado < minimo || datoSolicitado > maximo);

        return datoSolicitado;

    }

}
