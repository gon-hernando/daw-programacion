
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Programa con 2 métodos seleccionables mediante menú
 * 
 * PARTE 1: Programa para generar notas aleatorias y obtencion de datos en
 * funcion de nº
 * de estudiantes, nº asignaturas y nº de trimestres introducidos por el
 * usuario.
 * Consideraciones:
 * -Todas las medias se han redondeado a un decimal antes de operar y
 * compararlas
 * -El programa contempla empates en mejor y peor nota y mostrará todos los
 * empates si se producen
 * 
 * 
 * PARTE 2: Programa para realizar diferentes operaciones con una frase
 * introducida por el usuario
 * 
 * @author gonzaloHernando
 */
public class Main {

    static Scanner entrada = new Scanner(System.in);

    /**
     * Punto de entrada del programa.
     * 
     * Muestra un menú interactivo que permite seleccionar entre:
     * 
     * <ul>
     * <li>Gestión de calificaciones.</li>
     * <li>Operaciones con frases.</li>
     * <li>Salida del programa.</li>
     * </ul>
     * 
     * Controla la entrada del usuario mediante excepciones para evitar errores.
     * 
     */

    public static void main(String[] args) {

        // VARIABLE DE ENTRADA
        int opcionMenu = 0;

        // VARIABLES AUXILIARES
        boolean salir = false;

        do {

            System.out.println();
            System.out.println("==== MENU PRINCIPAL ====");
            System.out.println();
            System.out.println("1. Parte 1: Gestionar calificaciones");
            System.out.println("2. Parte 2: Operaciones con frase");
            System.out.println("3. Salir del programa");

            System.out.println();
            System.out.print("Seleccione una opción: ");

            try {

                opcionMenu = entrada.nextInt();

                switch (opcionMenu) {

                    case 1:
                        parteUno();
                        break;

                    case 2:
                        entrada.nextLine();
                        parteDos();
                        break;

                    case 3:
                        salir = true;
                        entrada.close();
                        System.out.println("====FIN DEL PRGRAMA====");
                        break;

                    default:
                        System.out.println("Opción no válida. Seleccione 1-3");
                        System.out.println();
                        break;
                }

            } catch (Exception e) {
                entrada.nextLine();
                System.err.println("Error en la selección");
                System.out.println();
            }

        } while (salir == false);

    }

    // =============================
    // PARTE UNO DEL PROGRAMA
    // ==============================

    /**
     * Realiza la primera parte del programa: gestión de calificaciones.
     * 
     * El usuario introduce:
     * <ul>
     * <li>Número de estudiantes (2-20).</li>
     * <li>Número de asignaturas (3-8).</li>
     * <li>Número de trimestres (2-3).</li>
     * </ul>
     * 
     * Se generan notas aleatorias y se calculan:
     * <ul>
     * <li>Medias por estudiante.</li>
     * <li>Mejor media global.</li>
     * <li>Medias por asignatura.</li>
     * <li>Asignaturas con peor media.</li>
     * </ul>
     */

    public static void parteUno() {

        // VARIABLES DE ENTRADA
        int estudiantes = 0;
        int asignaturas = 0;
        int trimestres = 0;

        // CONSTANTES
        final int ESTUDIANTESMIN = 2;
        final int ESTUDIANTESMAX = 20;

        final int ASIGNATURASMIN = 3;
        final int ASIGNATURASMAX = 8;

        final int TRIMESTRESMIN = 2;
        final int TRIMESTRESMAX = 3;

        // VARIABLES AUXILIARES
        double sumatorioAsignatura = 0;
        double sumatorioCurso = 0;
        double sumatorioAsignaturaTotal = 0;
        double peorNota = 10;
        double mejorNota = -1;

        // VARIABLES DE SALIDA
        double notaMediaAsignatura = 0;
        double notaMediaCurso = 0;
        double mediaAsignatura = 0;

        System.out.println("==== PROGRAMA: PARTE 1====");

        // ==== REGISTRO DE ESTUDIANTES ====

        // Uso de la clase Datos

        Datos registroEstudiantes = new Datos("estudiantes", ESTUDIANTESMIN, ESTUDIANTESMAX);
        estudiantes = registroEstudiantes.registroValores(entrada);

        // ==== REGISTRO DE ASIGNATURAS ====

        Datos registroAsignaturas = new Datos("asignaturas", ASIGNATURASMIN, ASIGNATURASMAX);
        asignaturas = registroAsignaturas.registroValores(entrada);

        // ==== REGISTRO DE TRIMESTRES ====

        Datos registroTrimestres = new Datos("trimestres", TRIMESTRESMIN, TRIMESTRESMAX);
        trimestres = registroTrimestres.registroValores(entrada);

        // ==== GENERACIÓN DE NOTAS DE FORMA ALEATORIA ====

        double[][][] notas = new double[estudiantes][asignaturas][trimestres]; // array de 3 dimensiones

        for (int i = 0; i < estudiantes; i++) { // recorremos el array
            for (int j = 0; j < asignaturas; j++) {
                for (int k = 0; k < trimestres; k++) {
                    notas[i][j][k] = Math.round(Math.random() * 100) / 10.0; // notas aleatorias con 1 decimal
                }
            }
        }

        // ==== ARRAYS PARA GUARDAR MEDIAS ====
        // (tamaño definido en funcion del nº de estudiantes y asignaturas)

        double[] mediaCursoEstudiante = new double[estudiantes];
        double[] mediaAsignaturas = new double[asignaturas];

        // ==== CALCULAR MEDIAS POR ESTUDIANTE ====

        System.out.println();
        System.out.println("NOTAS POR ESTUDIANTE");

        for (int i = 0; i < estudiantes; i++) {

            sumatorioCurso = 0; // reseteamos variable por cada estudiante
            System.out.println();
            System.out.println("Estudiante Nº: " + (i + 1));

            for (int j = 0; j < asignaturas; j++) {

                sumatorioAsignatura = 0; // reseteamos variable por cada asignatura

                for (int k = 0; k < trimestres; k++) {
                    sumatorioAsignatura += notas[i][j][k]; // acumulador de notas de los trimestres
                }

                notaMediaAsignatura = sumatorioAsignatura / trimestres; // cálculo de nota media en función de
                                                                        // trimestres
                notaMediaAsignatura = Math.round(notaMediaAsignatura * 10.0) / 10.0; // redondeo a 1 decimal

                sumatorioCurso += notaMediaAsignatura; // acumulador de notas medias de las diferentes asignaturas

                System.out.printf("Asignatura Nº: " + (j + 1) + " | Nota media: %.1f \n", notaMediaAsignatura);

            }
            notaMediaCurso = sumatorioCurso / asignaturas; // cálculo de nota media en función de asignaturas
            notaMediaCurso = Math.round(notaMediaCurso * 10.0) / 10.0; // redondeo a 1 decimal

            mediaCursoEstudiante[i] = notaMediaCurso; // guardamos las medias en el array correspondiente

            System.out.printf("Nota media del curso: %.1f \n", notaMediaCurso);

            if (notaMediaCurso > mejorNota) { // Obtención de estudiante con mejor nota media
                mejorNota = notaMediaCurso; // variable auxiliar para comparar notas
            }
        }

        // ==== MOSTRAR ESTUDIANTE CON MEJOR MEDIA GLOBAL ====

        System.out.println();
        System.out.printf("Mejor nota media global: %.1f\n", mejorNota); // mostramos la mejor nota

        System.out.println("Estudiantes con esa nota:");

        for (int i = 0; i < estudiantes; i++) { // recorremos el array para buscar estudiantes con la mejor nota
            if (mediaCursoEstudiante[i] == mejorNota) {
                System.out.println("Estudiante Nº: " + (i + 1));
            }
        }

        // ==== NOTA MEDIA POR ASIGNATURAS ====

        System.out.println();
        System.out.println("NOTA MEDIA POR ASIGNATURAS");

        for (int i = 0; i < asignaturas; i++) { // recorremos el array de forma diferente al punto anterior

            sumatorioAsignaturaTotal = 0;
            sumatorioAsignatura = 0;

            System.out.println("Asigatura Nº: " + (i + 1));

            for (int j = 0; j < estudiantes; j++) {
                sumatorioAsignatura = 0; // reseteamos la variable por estudiante

                for (int k = 0; k < trimestres; k++) {
                    sumatorioAsignatura += notas[j][i][k]; // acumulador de notas de diferentes trimestres por alumno

                }
                notaMediaAsignatura = sumatorioAsignatura / trimestres; // cálculo de nota media del alumno en la
                                                                        // asignatura
                notaMediaAsignatura = Math.round(notaMediaAsignatura * 10.0) / 10.0; // redondeo a 1 decimal

                sumatorioAsignaturaTotal += notaMediaAsignatura; // acumulador de notas media de la asignatura para
                                                                 // todos los alumnos
            }

            mediaAsignatura = sumatorioAsignaturaTotal / estudiantes; // cálculo de la media en función del nº de
                                                                      // alumnos

            mediaAsignaturas[i] = mediaAsignatura; // guardamos las medias en el array correspondiente

            System.out.printf("%.1f \n", mediaAsignatura);

            // ==== OBTENCION DE LA ASIGNATURA CON PEOR MEDIA ====

            if (mediaAsignatura < peorNota) {
                peorNota = mediaAsignatura; // almacenamos la peor nota
            }

        }

        // ==== MOSTRAR ASIGNATURA CON PEOR MEDIA GLOBAL ====

        System.out.println();
        System.out.printf("Peor nota media de una asignatura: %.1f\n", peorNota); // mostramos la mejor nota

        System.out.println("Asignaturas con esa nota:");

        for (int i = 0; i < asignaturas; i++) { // recorremos el array para buscar estudiantes con la mejor nora
            if (mediaAsignaturas[i] == peorNota) {
                System.out.println("Asignatura Nº: " + (i + 1));
            }
        }

        System.out.println();
        System.out.println("====FIN DE LA EJECUCIÓN DE LA PARTE UNO====");
        System.out.println();
    }

    // =============================
    // PARTE DOS DEL PROGRAMA
    // ==============================

    /**
     * Realiza la segunda parte del programa: operaciones con frases.
     * 
     * Permite al usuario introducir una frase y realiza:
     * 
     * <ul>
     * <li>Conteo de caracteres.</li>
     * <li>Conteo de palabras.</li>
     * <li>Inversión del orden de las palabras.</li>
     * <li>Reemplazo de vocales por '*'.</li>
     * <li>Conversión de números encontrados en la frase.</li>
     * </ul>
     * 
     * Se emplean expresiones regulares para la detección de números.
     */

    public static void parteDos() {

        // VARIABLES DE ENTRADA

        String frase;

        // VARIABLES AUXILIARES
        int palabrasIndice; // variable auxiliar para invertir palabras en la frase
        boolean numeroEncontrado = false;
        int contadorNumero = 0;

        // VARIABLES DE SALIDA

        int caracteresNum;
        int palabrasNum;
        String fraseReemplazada;
        String numero;
        int numeroInt;
        float numeroFloat;

        System.out.println();
        System.out.println("====PROGRAMA PARTE 2====");
        System.out.println();

        System.out.print("Introduzca frase: ");

        frase = entrada.nextLine();

        // ==== CONTAR CARACTERES ====

        System.out.println();
        System.out.println("CONTAR CARACTERES");

        caracteresNum = frase.length(); // metodo length para contar caracteres incluyendo espacios
        System.out.println(caracteresNum);

        // ==== CONTAR PALABRAS ====

        System.out.println();
        System.out.println("CONTAR PALABRAS");

        String[] palabras = frase.split("\\s+"); // separamos palabras por espacios e incluimos en array de strings

        palabrasNum = palabras.length; // obtenemos la longitud del array (= número de string que lo forman)
        System.out.println(palabrasNum);

        // ==== INVERTIR PALABRAS ====

        System.out.println();
        System.out.println("INVERSIÓN DE PALABRAS");
        palabrasIndice = palabrasNum - 1; // convertimos numero de palabras en un índice (restamos para empezar por 0)

        for (int i = 0; i < palabrasNum; i++) { // recorremos el array

            System.out.print(palabras[palabrasIndice]);
            palabrasIndice--;

            if (i < palabrasNum - 1) { // incluimos espacio entre los strings
                System.out.print(" ");
            }
        }

        System.out.println();

        // ==== REEMPLAZAR VOCALES ====

        System.out.println();
        System.out.println("REEMPLAZAR VOCALES");

        // usamos metodo replaceAll para a traves de una expreison regular remplazar
        // vocales
        fraseReemplazada = frase.replaceAll("[AÁaáEÉeéIÍiíOÓoóUÚÜuúü]", "*");
        System.out.println(fraseReemplazada);

        // ==== CONVERSION NÚMERO ====

        System.out.println();
        System.out.println("CONVERSIÓN DE NÚMERO");

        Pattern patron = Pattern.compile("[1234567890]+"); // crear patrón de búsqueda
        Matcher comparador = patron.matcher(frase); // buscador de patrón

        while (comparador.find()) { // si encuentra patrón == true

            contadorNumero++;

            if (contadorNumero > 1) { // condicion para incluir observacion si varios números encontrados
                System.out.println("Se han encontrado números adicionales:");
                System.out.println();
            }

            numeroEncontrado = true;

            numero = comparador.group(); // devuelve el patrón localizado

            numeroInt = Integer.parseInt(numero); // conversión a entero

            System.out.println("El cuadrado del número encontrado es: " + numeroInt * numeroInt);

            numeroFloat = (float) numeroInt;
            System.out.println("La raiz cuadrada del número encontrado es: " + Math.sqrt(numeroFloat));

            System.out.println(Integer.toString(numeroInt) + " es el número procesado");

            System.out.println();

        } // condicion si no se encuentran números
        if (numeroEncontrado == false) {
            System.out.println("No se encontró ningún número");
        }

        System.out.println();
        System.out.println("====FIN DE LA EJECUCIÓN DE LA PARTE 2====");
    }

}
