package clases.menu;

import java.util.Scanner;
import java.util.Set;

import clases.eventos.Evento;
import clases.eventos.GestorEventos;
import clases.participantes.Participante;
import clases.util.Util;

/**
 * Clase que gestiona el menú principal de la aplicación.
 *
 * Permite interactuar con el sistema de gestión de eventos mediante
 * opciones por consola, delegando las operaciones al {@link GestorEventos}.
 * Incluye funcionalidades para:
 * <ul>
 * <li>Crear y gestionar eventos</li>
 * <li>Gestionar participantes y recursos</li>
 * <li>Mostrar estadísticas</li>
 * <li>Realizar operaciones entre eventos (unión, intersección, diferencia)</li>
 * </ul>
 * * @author Gonzalo Hernando
 */
public class Menu {

    // ================
    // ---- MENU ----
    // ================

    /**
     * Muestra el menú principal y gestiona la interacción del usuario.
     *
     * @param entrada Scanner para entrada de datos por consola
     * @param gestor  gestor de eventos que contiene la lógica de negocio
     */

    public static void menuInicial(Scanner entrada, GestorEventos gestor) {

        // VARIABLES DE ENTRADA

        int seleccion = -1;

        // VARIABLES AUXILIARES

        boolean salir = false;

        do {

            System.out.println("\n==== MENÚ ====\n");

            System.out.println("1. Añadir evento");
            System.out.println("2. Añadir participante a un evento.");
            System.out.println("3. Eliminar participante de un evento.");
            System.out.println("4. Añadir recursos a un evento.");
            System.out.println("5. Mostrar participantes de un evento.");
            System.out.println("6. Mostrar recursos de un evento.");
            System.out.println("7. Mostrar estadísticas.");
            System.out.println("8. Mostrar operaciones entre participantes de eventos.");
            System.out.println("0. Salir del programa.");

            seleccion = Util.validarEntero(entrada, "Seleccione una opcion: ");

            switch (seleccion) {

                case 0:
                    salir = true;
                    System.out.println("\n====FIN DE LA APLICACIÓN====\n");

                    break;

                case 1:
                    System.out.println("\n====AÑADIR EVENTO====\n");
                    gestor.añadirEvento(entrada);

                    break;

                case 2:
                    System.out.println("\n====AÑADIR PARTICIPANTE====\n");
                    gestor.añadirParticipanteAEvento(entrada);

                    break;

                case 3:
                    System.out.println("\n====ELIMINAR PARTICIPANTE====\n");
                    Menu.menuEliminar(entrada, gestor);

                    break;

                case 4:
                    System.out.println("\n====AÑADIR RECURSOS====\n");
                    gestor.añadirRecursosAEvento(entrada);

                    break;

                case 5:
                    System.out.println("\n====MOSTRAR PARTICIPANTES====\n");
                    gestor.mostrarParticipantesDeEvento(entrada);

                    break;

                case 6:
                    System.out.println("\n====MOSTRAR RECURSOS====\n");
                    gestor.mostrarRecursosDeEvento(entrada);

                    break;

                case 7:

                    System.out.println("\n====MOSTRAR ESTADÍSTICAS====\n");
                    gestor.mostrarEstadisticas();

                    break;

                case 8:

                    System.out.println("\n====OPERACIONES ENTRE PARTICIPANTES====\n");
                    Menu.menuOperaciones(entrada, gestor);

                    break;

                default:
                    System.err.println("Error en la selección: opción no válida\n");

                    break;
            }

        } while (!salir);

    }

    // ============================
    // ---- MENU OPERACIONES ----
    // ============================

    /**
     * Muestra el menú de operaciones entre dos eventos seleccionados
     * (unión, intersección y diferencia de participantes).
     *
     * @param entrada Scanner para entrada de datos por consola
     * @param gestor  gestor de eventos que permite seleccionar eventos
     */

    public static void menuOperaciones(Scanner entrada, GestorEventos gestor) {

        boolean salir = false;
        int seleccion = -1;
        Set<Participante> resultado = null;

        Evento evento1 = gestor.seleccionarEvento(entrada);

        if (evento1 != null) {

            Evento evento2 = gestor.seleccionarEvento(entrada);

            if (evento2 == evento1) {
                salir = true;
                evento2 = null;
                System.out.println("Operación cancelada: seleccionar diferentes eventos");
            }

            if (evento2 != null) {

                do {

                    resultado = null;

                    System.out.println("\nOPERACIONES ENTRE EVENTOS\n");

                    System.out.println("1. Unión de participantes de dos eventos.");
                    System.out.println("2. Intersección de participantes de dos eventos.");
                    System.out.println("3. Diferencia de participantes de dos eventos.");
                    System.out.println("0. Salir.");

                    seleccion = Util.validarEntero(entrada, "Seleccione operación: ");

                    switch (seleccion) {
                        case 0:
                            System.out.println("Operación cancelada");
                            salir = true;
                            break;

                        case 1:
                            resultado = GestorEventos.union(evento1, evento2);
                            break;

                        case 2:
                            resultado = GestorEventos.interseccion(evento1, evento2);
                            break;

                        case 3:
                            resultado = GestorEventos.diferencia(evento1, evento2);
                            break;

                        default:
                            System.err.println("Error en la selección: opción no válida\n");
                            break;
                    }

                    if (resultado != null) {
                        Util.mostrarColeccion(resultado);
                    }

                } while (!salir);
            }
        }
    }

    // =======================
    // ---- MENU ELIMINAR ----
    // ========================

    /**
     * Muestra el menú de eliminación de participantes de un evento,
     * permitiendo eliminación por listado o por búsqueda.
     *
     * @param entrada Scanner para entrada de datos por consola
     * @param gestor  gestor de eventos
     */

    public static void menuEliminar(Scanner entrada, GestorEventos gestor) {

        boolean salir = false;
        int seleccion = -1;

        do {

            System.out.println("1. Eliminar participante por listado");
            System.out.println("2. Eliminar participante por búsqueda");
            System.out.println("0. Salir");

            seleccion = Util.validarEntero(entrada, "Seleccione opción: ");

            switch (seleccion) {

                case 0:
                    salir = true;
                    System.out.println("Se ha seleccionado: Salir");
                    break;

                case 1:
                    gestor.eliminarParticipanteAEvento(entrada);

                    break;

                case 2:
                    gestor.eliminarParticipanteAEventoBusqueda(entrada);

                    break;

                default:
                    System.err.println("Error en la selección: opción no válida\n");
                    break;
            }

        } while (!salir);
    }
}
