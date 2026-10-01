package garaje;

import java.util.Scanner;

import garaje.ui.Controlador;

/**
 * Clase principal de la aplicación del sistema de gestión de garaje.
 * <p>
 * Contiene el método{@code main} que inicia la ejecución del programa,
 * mostrando un menú interactivo por consola que permite:
 * </p>
 * <ul>
 * <li>Añadir nuevos vehículos al garaje</li>
 * <li>Mostrar información de los vehículos almacenados</li>
 * <li>Usar vehículos (realizar acciones sobre ellos)</li>
 * <li>Salir de la aplicación</li>
 * </ul>
 * <p>
 * La interacción con el usuario se realiza mediante entrada estándar
 * utilizando la clase {@link Scanner}.
 * </p>
 * 
 * @author Gonzalo Hernando Llorente
 * @version
 */
public class Main {

    /**
     * Punto de entrada de la aplicación.
     * <p>
     * Controla el flujo principal del programa mediante un menú repetitivo
     * que se ejecuta hasta que el usuario decide salir.
     * </p>
     * 
     * @author
     * @version
     */

    public static void main(String[] args) {

        // SCANNER

        Scanner entrada = new Scanner(System.in);

        Controlador.iniciar(entrada);

        entrada.close();

    }

}
