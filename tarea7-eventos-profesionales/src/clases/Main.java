package clases;

import java.util.Scanner;

import clases.eventos.GestorEventos;
import clases.menu.Menu;

/**
 * Clase principal de la aplicación.
 *
 * <p>
 * Inicia el sistema de gestión de eventos, creando el gestor principal
 * y lanzando el menú de interacción por consola.
 * </p>
 *
 * <p>
 * Es el punto de entrada del programa.
 * </p>
 * * @author Gonzalo Hernando
 */

public class Main {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        GestorEventos gestor = new GestorEventos();

        Menu.menuInicial(entrada, gestor);

    }

}
