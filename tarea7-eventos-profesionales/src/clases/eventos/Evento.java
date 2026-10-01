package clases.eventos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import clases.participantes.Estudiante;
import clases.participantes.Participante;
import clases.participantes.Ponente;
import clases.participantes.Profesional;
import clases.util.Util;

/**
 * Clase que representa un evento que gestiona participantes y recursos
 * asociados.
 * Permite añadir, eliminar, buscar y mostrar participantes de distintos tipos
 * (Estudiante, Ponente y Profesional), así como gestionar recursos genéricos
 * asociados al evento.
 * Internamente mantiene:
 * <ul>
 * <li>Una lista de participantes para iteración y ordenación.</li>
 * <li>Un mapa de email para búsqueda rápida de participantes.</li>
 * <li>Un conjunto de recursos genéricos asociados al evento.</li>
 * </ul>
 * 
 * 
 * @author Gonzalo Hernando
 * @version 1.0
 */

public class Evento {

    // ====================
    // ---- ATRIBUTOS ----
    // ====================

    private String nombreEvento;
    private ArrayList<Participante> participantes = new ArrayList<>();
    private Set<Recurso<?>> recursos = new HashSet<>();
    private Map<String, Participante> mapaEmail = new HashMap<>();

    // =====================
    // ---- CONSTRUCTOR ----
    // =====================

    /**
     * Crea un nuevo evento con el nombre indicado.
     *
     * @param nombreEvento nombre del evento
     * @throws IllegalArgumentException si el nombre no es válido
     */

    public Evento(String nombreEvento) {
        Util.validarString("Nombre del evento", nombreEvento);

        this.nombreEvento = nombreEvento;
    }

    // =================
    // ---- GETTER ----
    // =================

    /**
     * Devuelve el nombre del evento.
     *
     * @return nombre del evento
     */

    public String getNombreEvento() {
        return nombreEvento;
    }

    /**
     * Devuelve la lista de participantes del evento.
     *
     * @return lista de participantes
     */

    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    // =================
    // ---- SETTER ----
    // =================

    /**
     * Establece el nombre del evento.
     *
     * @param nombreEvento nuevo nombre del evento
     * @throws IllegalArgumentException si el nombre no es válido
     */

    public void setNombreEvento(String nombreEvento) {

        Util.validarString("nombre del evento", nombreEvento);

        this.nombreEvento = nombreEvento;
    }

    // ==================
    // ---- TOSTRING ----
    // ==================

    @Override
    public String toString() {
        return String.format("Evento: %s", nombreEvento);
    }

    // =============================
    // ---- AÑADIR PARTICIPANTE ----
    // =============================

    /**
     * Añade un participante al evento a través de entrada por consola,
     * permitiendo seleccionar el tipo de participante.
     *
     * @param entrada Scanner para entrada de datos
     */

    public void añadirParticipante(Scanner entrada) {

        int seleccion = 0;
        boolean salir = false;

        do {

            System.out.println("\nAÑADIR PARTICIPANTES\n");

            System.out.println("1. Estudiante");
            System.out.println("2. Ponente");
            System.out.println("3. Profesional");
            System.out.println("0. Salir");

            seleccion = Util.validarEntero(entrada, "Seleccione tipo de participante: ");

            switch (seleccion) {

                case 0:
                    salir = true;
                    break;

                case 1: {

                    System.out.println("Se ha seleccionado Estudiante\n");

                    String nombre = Util.registro(entrada, "-Nombre: ");
                    String email = Util.registro(entrada, "-Email: ");
                    String curso = Util.registro(entrada, "-Curso: ");

                    try {
                        Util.validarEmail(email, mapaEmail);
                        Participante estudiante = new Estudiante(nombre, email, curso);

                        guardaParticipante(estudiante);

                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                    }
                }

                    break;

                case 2: {

                    System.out.println("Se ha seleccionado Ponente\n");

                    String nombre = Util.registro(entrada, "-Nombre: ");
                    String email = Util.registro(entrada, "-Email: ");
                    String tema = Util.registro(entrada, "-Tema: ");

                    try {
                        Util.validarEmail(email, mapaEmail);
                        Participante ponente = new Ponente(nombre, email, tema);

                        guardaParticipante(ponente);

                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                    }
                }

                    break;

                case 3: {

                    System.out.println("Se ha seleccionado Profesional\n");

                    String nombre = Util.registro(entrada, "-Nombre: ");
                    String email = Util.registro(entrada, "-Email: ");
                    String empresa = Util.registro(entrada, "-Empresa: ");

                    try {
                        Util.validarEmail(email, mapaEmail);
                        Participante profesional = new Profesional(nombre, email, empresa);

                        guardaParticipante(profesional);

                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                    }
                }
                    break;

                default:
                    System.err.println("\nError en la selección: opción no válida");
                    break;

            }

        } while (!salir);
    }

    // =======================================
    // ---- ELIMINAR PARTICIPANTE LISTADO ----
    // =======================================

    /**
     * Elimina un participante seleccionado desde la lista mostrada.
     *
     * @param entrada Scanner para entrada de datos
     */

    public void eliminarParticipante(Scanner entrada) {

        if (Util.comprobarColeccion(participantes, "No hay participantes añadidos")) {

            boolean salir = false;

            do {

                int seleccion = -1;

                System.out.println("\nELIMINAR PARTICIPANTES");

                System.out.println("0. Salir");
                mostrarParticipantes();
                seleccion = Util.validarEntero(entrada, "Seleccione participante a eliminar: ");

                if (seleccion == 0) {
                    salir = true;

                } else if (seleccion > 0 && seleccion <= participantes.size()) {

                    Iterator<Participante> it = participantes.iterator();
                    int indice = 0;

                    while (it.hasNext()) {

                        indice++;
                        Participante participanteEliminar = it.next();

                        if (indice == seleccion) {
                            mapaEmail.remove(participanteEliminar.getEmail());
                            it.remove();
                        }
                    }
                } else {
                    System.err.println("Selección erronea");
                }
            } while (!salir);
        }
    }

    // ========================================
    // ---- ELIMINAR PARTICIPANTE BUSQUEDA ----
    // ========================================

    /**
     * Elimina participantes cuyo email coincida parcialmente con una búsqueda.
     *
     * @param entrada Scanner para entrada de datos
     */

    public void eliminarPorBusqueda(Scanner entrada) {

        if (Util.comprobarColeccion(participantes, "No hay participantes añadidos")) {

            System.out.println("\nELIMINAR PARTICIPANTES POR BÚSQUEDA");

            boolean encontrado = false;

            String textoBusqueda = Util.registro(entrada, "Introduzca Email: ");

            Iterator<String> it = mapaEmail.keySet().iterator();

            while (it.hasNext()) {

                String emailListado = it.next();

                if (emailListado.contains(textoBusqueda)) {

                    encontrado = true;
                    Participante participanteEliminar = mapaEmail.get(emailListado);

                    System.out.printf("\nParticipante encontrado: %s", participanteEliminar);

                    String confirmarEliminacion = Util.registro(entrada, "Confirmar eliminar(S/N): ");

                    if (confirmarEliminacion.equalsIgnoreCase("S")) {

                        System.out.printf("%s \n Eliminado correctamente\n", participanteEliminar);
                        participantes.remove(participanteEliminar);
                        it.remove();

                    } else {
                        System.out.println("Eliminación cancelada\n");
                    }
                }
            }
            if (!encontrado) {
                System.out.println("No se han encontrado coincidencias");
            }
        }
    }

    // =============================
    // ---- BUSCAR PARTICIPANTE ----
    // =============================

    /**
     * Busca un participante por su email.
     *
     * @param entrada Scanner para entrada de datos
     * @return participante encontrado o null si no existe
     */

    public Participante buscarParticipante(Scanner entrada) {

        String email = Util.registro(entrada, "Introduzca Email: ");

        Participante participante = mapaEmail.get(email);

        if (participante == null) {
            System.out.println("Participante no encontrado");
        }

        return participante;
    }

    // =============================
    // ---- MOSTRAR PARTICIPANTE ----
    // =============================

    /**
     * Muestra todos los participantes del evento ordenados.
     */

    public void mostrarParticipantes() {

        if (Util.comprobarColeccion(participantes, "No hay participantes añadidos")) {

            int contador = 0;
            TreeSet<Participante> participantesOrdenados = new TreeSet<>(participantes);

            for (Participante participante : participantesOrdenados) {
                contador++;
                System.out.printf("%d. %s\n", contador, participante);
            }
        }
    }

    // =========================
    // ---- AÑADIR RECURSO ----
    // =========================

    /**
     * Añade un recurso al evento según el tipo indicado por consola.
     *
     * @param entrada Scanner para entrada de datos
     */

    public void añadirRecurso(Scanner entrada) {

        int seleccion = -1;
        boolean salir = false;
        String nombreRecurso;

        do {

            System.out.println("\n1. Cadena de texto");
            System.out.println("2. Número entero");
            System.out.println("3. Fecha");
            System.out.println("0. Salir");

            seleccion = Util.validarEntero(entrada, "Seleccione tipo de recurso: ");

            switch (seleccion) {

                case 0:
                    salir = true;
                    break;

                case 1:

                    System.out.println("Se ha seleccionado recurso de tipo cadena de texto\n");

                    nombreRecurso = Util.registro(entrada, "Indicar nombre del recurso: ");
                    System.out.print("Introduzca cadena de texto: ");
                    String cadena = entrada.nextLine();

                    Recurso<String> recursoTexto = new Recurso<>(nombreRecurso, cadena);
                    recursos.add(recursoTexto);
                    System.out.println("\nRecurso añadido con éxito");

                    break;

                case 2:

                    System.out.println("Se ha seleccionado recurso de tipo número entero\n");

                    nombreRecurso = Util.registro(entrada, "Indicar nombre del recurso: ");
                    Integer numero = Util.validarEntero(entrada, "Introduzca número: ");

                    Recurso<Integer> recursoNumero = new Recurso<>(nombreRecurso, numero);
                    recursos.add(recursoNumero);
                    System.out.println("\nRecurso añadido con éxito");

                    break;

                case 3:

                    System.out.println("Se ha seleccionado recurso de tipo fecha\n");

                    nombreRecurso = Util.registro(entrada, "Indicar nombre del recurso: ");
                    System.out.print("Introduzca fecha (YYYY-MM-DD): ");
                    String fechaString = entrada.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    try {
                        LocalDate fecha = LocalDate.parse(fechaString, formatter);

                        Recurso<LocalDate> recursoFecha = new Recurso<>(nombreRecurso, fecha);
                        recursos.add(recursoFecha);
                        System.out.println("\nRecurso añadido con éxito");

                    } catch (Exception e) {
                        System.err.println("\nFormato de fecha incorrecto");
                    }

                    break;

                default:

                    System.err.println("Error en la seleccion");

                    break;
            }

        } while (!salir);

    }

    // ==========================
    // ---- MOSTRAR RECURSOS ----
    // ==========================

    /**
     * Muestra todos los recursos asociados al evento.
     */

    public void mostrarRecursos() {

        Util.mostrarColeccion(recursos);
    }

    // ==============================
    // ---- GUARDAR PARTICIPANTE ----
    // ==============================

    /**
     * Guarda un participante en las estructuras internas del evento.
     *
     * @param participante participante a guardar
     */

    public void guardaParticipante(Participante participante) {
        participantes.add(participante);
        mapaEmail.put(participante.getEmail(), participante);
        System.out.printf("\n %s añadido correctamente\n", participante.getClass().getSimpleName());
    }

}
