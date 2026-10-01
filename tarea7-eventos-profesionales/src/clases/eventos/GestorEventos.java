package clases.eventos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import clases.participantes.Estudiante;
import clases.participantes.Participante;
import clases.participantes.Ponente;
import clases.participantes.Profesional;
import clases.util.Util;

/**
 * Clase que gestiona una colección de eventos y proporciona operaciones
 * de alto nivel sobre ellos, como creación, selección, modificación
 * y análisis de participantes.
 * 
 * <p>
 * Permite además realizar operaciones de teoría de conjuntos
 * entre eventos (unión, intersección y diferencia de participantes)
 * y obtener estadísticas globales.
 * </p>
 * 
 * @author Gonzalo Hernando
 */

public class GestorEventos {

    /**
     * Lista de eventos gestionados.
     */

    private List<Evento> eventos = new ArrayList<>();

    // ===========================
    // ---- GET LISTA EVENTOS ----
    // ===========================

    /**
     * Devuelve la
     * lista de
     * eventos gestionados.
     * 
     * @return lista de eventos
     */

    public List<Evento> getEventos() {
        return eventos;
    }

    // ========================
    // ---- AÑADIR EVENTOS ----
    // ========================

    /**
     * Añade un nuevo evento introducido por consola.
     *
     * @param entrada Scanner para entrada de datos
     */

    public void añadirEvento(Scanner entrada) {

        String nombreEvento = Util.registro(entrada, "Introduzca nombre del evento: ");

        Evento evento = new Evento(nombreEvento);
        eventos.add(evento);
        System.out.printf("Evento \"%s\" añadido correctamente\n", nombreEvento);
    }

    // ============================
    // ---- SELECCIONAR EVENTO ----
    // ============================

    /**
     * Permite seleccionar un evento de la lista mediante consola.
     *
     * @param entrada Scanner para entrada de datos
     * @return evento seleccionado o null si se cancela o no hay eventos
     */

    public Evento seleccionarEvento(Scanner entrada) {

        if (!Util.comprobarColeccion(eventos, "No hay eventos que mostrar")) {

            return null;

        } else {

            int seleccion = -1;
            Evento eventoSeleccionado = null;

            do {

                Util.mostrarColeccion(eventos);

                seleccion = Util.validarEntero(entrada, "Seleccione un evento: ");

                if (seleccion == 0) {
                    System.out.println("\nSe ha seleccionado: Salir");
                    return null;
                }

                else if (seleccion < 1 || seleccion > eventos.size()) {
                    System.err.println("\nError en la selección\n");
                    eventoSeleccionado = null;

                } else {
                    eventoSeleccionado = eventos.get(seleccion - 1);
                    System.out.printf("\nSe ha seleccionado correctamente evento \"%s\"\n",
                            eventoSeleccionado.getNombreEvento());
                }

            } while (eventoSeleccionado == null);

            return eventoSeleccionado;
        }
    }

    // ============================
    // ---- AÑADIR PARTICIPANTE ----
    // ============================

    /**
     * Añade un participante a un evento seleccionado.
     *
     * @param entrada Scanner para entrada de datos
     */

    public void añadirParticipanteAEvento(Scanner entrada) {
        Evento evento = seleccionarEvento(entrada);

        if (evento != null) {
            evento.añadirParticipante(entrada);
        }
    }

    // ============================
    // ---- ELIMINAR PARTICIPANTE ----
    // ============================

    /**
     * Elimina un participante de un evento seleccionado.
     *
     * @param entrada Scanner para entrada de datos
     */

    public void eliminarParticipanteAEvento(Scanner entrada) {
        Evento evento = seleccionarEvento(entrada);

        if (evento != null) {
            evento.eliminarParticipante(entrada);
        }
    }

    // ============================
    // ---- ELIMINAR PARTICIPANTE BUSQUEDA ----
    // ============================

    /**
     * Elimina un participante de un evento seleccionado.
     *
     * @param entrada Scanner para entrada de datos
     */

    public void eliminarParticipanteAEventoBusqueda(Scanner entrada) {
        Evento evento = seleccionarEvento(entrada);

        if (evento != null) {
            evento.eliminarPorBusqueda(entrada);
        }
    }

    // ============================
    // ---- AÑADIR RECURSO ----
    // ============================

    /**
     * Añade un recurso a un evento seleccionado.
     *
     * @param entrada Scanner para entrada de datos
     */

    public void añadirRecursosAEvento(Scanner entrada) {
        Evento evento = seleccionarEvento(entrada);

        if (evento != null) {
            evento.añadirRecurso(entrada);
        }
    }

    // ============================
    // ---- MOSTRAR PARTICIPANTE ----
    // ============================

    /**
     * Muestra los participantes de un evento seleccionado.
     *
     * @param entrada Scanner para entrada de datos
     */

    public void mostrarParticipantesDeEvento(Scanner entrada) {
        Evento evento = seleccionarEvento(entrada);

        if (evento != null) {
            evento.mostrarParticipantes();
        }
    }

    // ============================
    // ---- MOSTRAR RECURSO ----
    // ============================

    /**
     * Muestra los recursos de un evento seleccionado.
     *
     * @param entrada Scanner para entrada de datos
     */

    public void mostrarRecursosDeEvento(Scanner entrada) {
        Evento evento = seleccionarEvento(entrada);

        if (evento != null) {
            evento.mostrarRecursos();
        }
    }

    // ==========================
    // ---- UNION DE EVENTOS ----
    // ==========================

    /**
     * Calcula la unión de participantes entre dos eventos.
     *
     * @param e1 primer evento
     * @param e2 segundo evento
     * @return conjunto con todos los participantes sin duplicados
     */

    public static Set<Participante> union(Evento e1, Evento e2) {

        Set<Participante> resultado = new HashSet<>(e1.getParticipantes());
        resultado.addAll(e2.getParticipantes());

        return resultado;
    }

    // =================================
    // ---- INTERSECCIÓN DE EVENTOS ----
    // =================================

    /**
     * Calcula la intersección de participantes entre dos eventos.
     *
     * @param e1 primer evento
     * @param e2 segundo evento
     * @return conjunto con los participantes comunes
     */

    public static Set<Participante> interseccion(Evento e1, Evento e2) {

        Set<Participante> resultado = new HashSet<>(e1.getParticipantes());
        resultado.retainAll(e2.getParticipantes());

        return resultado;
    }

    // ===============================
    // ---- DIFERENCIA DE EVENTOS ----
    // ===============================

    /**
     * Calcula la diferencia de participantes entre dos eventos.
     *
     * @param e1 evento base
     * @param e2 evento a restar
     * @return participantes que están en e1 pero no en e2
     */

    public static Set<Participante> diferencia(Evento e1, Evento e2) {

        Set<Participante> resultado = new HashSet<>(e1.getParticipantes());
        resultado.removeAll(e2.getParticipantes());

        return resultado;
    }

    // =======================
    // ---- ESTADÍSTICAS ----
    // =======================

    /**
     * Muestra estadísticas globales de los eventos gestionados.
     * 
     * Calcula:
     * - Número total de eventos.
     * - Número de participantes únicos (sin duplicados entre eventos).
     * - Distribución de participantes por tipo (Estudiante, Ponente y Profesional)
     * 
     */

    public void mostrarEstadisticas() {

        int numeroEventos = eventos.size();
        int numeroEstudiantes = 0;
        int numeroPonentes = 0;
        int numeroProfesionales = 0;

        Set<Participante> participantesUnicos = new HashSet<>();

        for (Evento evento : eventos) {
            participantesUnicos.addAll(evento.getParticipantes());
        }

        int numeroParticipantes = participantesUnicos.size();

        for (Participante participante : participantesUnicos) {

            if (participante instanceof Estudiante) {
                numeroEstudiantes++;
            }

            else if (participante instanceof Ponente) {
                numeroPonentes++;
            }

            else if (participante instanceof Profesional) {
                numeroProfesionales++;
            }

        }

        System.out.printf("-Número de eventos: %d\n", numeroEventos);
        System.out.printf("-Número de participantes totales: %d\n", numeroParticipantes);
        System.out.printf("\t-Número de estudiantes: %d\n", numeroEstudiantes);
        System.out.printf("\t-Número de ponentes: %d\n", numeroPonentes);
        System.out.printf("\t-Número de profesionales: %d\n", numeroProfesionales);
    }
}
