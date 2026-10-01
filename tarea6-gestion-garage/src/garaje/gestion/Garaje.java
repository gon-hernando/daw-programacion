package garaje.gestion;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import garaje.interfaces.Arrancable;
import garaje.interfaces.Movible;
import garaje.ui.Menu;
import garaje.util.Util;
import garaje.vehiculos.Bicicleta;
import garaje.vehiculos.Coche;
import garaje.vehiculos.Motocicleta;
import garaje.vehiculos.Vehiculo;

/**
 * Clase que gestiona un garaje de vehículos.
 * <p>
 * Permite añadir vehículos de diferentes tipos, listar los vehículos
 * existentes y ejecutar acciones sobre ellos como arrancar, mover,
 * detener o consultar estado.
 * </p>
 * <p>
 * Mantiene un listado interno de vehículos mediante un {@link ArrayList}.
 * </p>
 * 
 * @author Gonzalo Hernando Llorente
 * @version
 */
public class Garaje {

    /** Lista de vehículos gestionados por el garaje */
    private static ArrayList<Vehiculo> misVehiculos = new ArrayList<>();

    // ================
    // ---- GETTER ----
    // ================

    public static ArrayList<Vehiculo> getVehiculos() { // static
        return misVehiculos;
    }

    // ==========================
    // ---- AÑADIR VEHICULOS ----
    // ==========================

    /**
     * Permite añadir un vehículo al garaje mediante interacción con el usuario.
     * <p>
     * Se solicitan los datos necesarios según el tipo de vehículo seleccionado.
     * Se valida cada dato mediante la clase {@link Vehiculo} y {@link Util}.
     * </p>
     * 
     * @param entrada {@link Scanner} para leer datos de usuario.
     */

    public static void añadirVehiculo(Scanner entrada) {

        // ===================
        // ---- VARIABLES ----
        // ===================

        boolean salir = false;

        do {

            int seleccion = Menu.menuSeleccionarVehiculo(entrada);

            switch (seleccion) {

                // AÑADIR BICICLETA

                case 1: {
                    System.out.println("Se ha seleccionado Bicicleta\n");

                    String marca = Vehiculo.registro(entrada, "-Marca: ");

                    String modelo = Vehiculo.registro(entrada, "-Modelo: ");

                    String matricula = Vehiculo.registro(entrada, "-Matricula: ").toUpperCase();

                    String tipoManillar = Vehiculo.registro(entrada, "-Tipo de manillar: ");

                    int numMarchas = Vehiculo.registroInt(entrada, "-Número de marchas: ", 0, 10);

                    try {
                        Vehiculo bicicleta = new Bicicleta(marca, modelo, matricula, tipoManillar, numMarchas); // Ligadura
                                                                                                                // dinámica
                        misVehiculos.add(bicicleta);
                        System.out.println("\nBicicleta añadida correctamente\n");

                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                    }
                }
                    break;

                // AÑADIR COCHE

                case 2: {

                    System.out.println("Se ha seleccionado Coche\n");

                    String marca = Vehiculo.registro(entrada, "-Marca: ");

                    String modelo = Vehiculo.registro(entrada, "-Modelo: ");

                    String matricula = Vehiculo.registro(entrada, "-Matricula: ").toUpperCase();

                    int numPuertas = Vehiculo.registroInt(entrada, "-Número de puertas: ", 2, 5);

                    String tipoCombustible = Vehiculo.registro(entrada, "-Tipo de combustible: ");

                    try {
                        Vehiculo coche = new Coche(marca, modelo, matricula, numPuertas, tipoCombustible);

                        misVehiculos.add(coche);
                        System.out.println("\nCoche añadido correctamente\n");

                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                    }

                }
                    break;

                // AÑADIR MOTOCICLETA

                case 3: {

                    System.out.println("Se ha seleccionado motocicleta\n");

                    String marca = Vehiculo.registro(entrada, "-Marca: ");

                    String modelo = Vehiculo.registro(entrada, "-Modelo: ");

                    String matricula = Vehiculo.registro(entrada, "-Matricula: ").toUpperCase();

                    int cilindrada = Vehiculo.registroInt(entrada, "-Cilindrada: ", 49, 3000);

                    String tipoCombustible = Vehiculo.registro(entrada, "-Tipo de combustible: ");

                    try {
                        Vehiculo moto = new Motocicleta(marca, modelo, matricula, cilindrada, tipoCombustible);

                        misVehiculos.add(moto);
                        System.out.println("\nMotocicleta añadida correctamente\n");

                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                    }

                }
                    break;

                case 4:
                    salir = true;
                    break;

                default:
                    System.err.println("Error en la selección: opción no válida");
                    break;
            }

        } while (!salir);

    };

    // ==========================
    // ---- LISTAR VEHICULOS ----
    // ==========================

    /**
     * Muestra por consola la información de todos los vehículos añadidos al garaje.
     * <p>
     * Si no hay vehículos, se muestra un mensaje informativo.
     * </p>
     */

    public static void listarVehiculo() {

        if (misVehiculos.size() == 0) {
            System.out.println("No hay vehículos que mostrar");

        } else {

            int contador = 1;

            for (Vehiculo vehiculo : misVehiculos) {

                System.out.printf("%d. %s %s\n", contador, vehiculo.getClass().getSimpleName(), vehiculo);
                contador++;
            }
        }
    }

    // =========================
    // ---- EJECUTAR ACCIÓN ----
    // =========================

    /**
     * Permite ejecutar acciones sobre un vehículo seleccionado.
     * Se pueden realizar las siguientes acciones:
     * <ul>
     * <li>Salir
     * <li>Arrancar (si es {@link Arrancable})</li>
     * <li>Mover (si es {@link Movible})</li>
     * <li>Detener (si es {@link Arrancable})</li>
     * <li>Comprobar estado (si es {@link Arrancable})</li>
     * </ul>
     * 
     * @param entrada {@link Scanner} para leer la selección y datos de movimiento.
     */

    public static void ejecutarAccion(Scanner entrada) {

        if (misVehiculos.size() > 0) {

            boolean salir = false;
            Vehiculo vehiculo = null;

            // SELECCIONAR VEHICULO

            int seleccionVehiculo = 0;

            do {
                System.out.println("\nSeleccione vehiculo: ");

                try {
                    seleccionVehiculo = entrada.nextInt();
                    entrada.nextLine();

                    if (seleccionVehiculo == 0) {
                        salir = true;

                    } else if (seleccionVehiculo >= 1 && seleccionVehiculo <= misVehiculos.size()) {
                        vehiculo = misVehiculos.get(seleccionVehiculo - 1);

                    } else {
                        throw new IllegalArgumentException("Error: selección fuera de rango");
                    }

                } catch (InputMismatchException e) { // atrapar excepcion por no introducir numero
                    System.err.println("Debe introducir un número válido");
                    vehiculo = null;
                    entrada.nextLine();
                } catch (Exception e) { // resto de excepciones
                    System.err.println(e.getMessage());
                    vehiculo = null;
                }

            } while (vehiculo == null && !salir);

            // SELECCIONAR ACCION

            salir = false;
            int seleccion = 0;

            if (seleccionVehiculo > 0) {

                do {

                    seleccion = Menu.menuAcciones(entrada);

                    switch (seleccion) {

                        case 1: // Arrancar

                            if (vehiculo instanceof Arrancable) {
                                ((Arrancable) vehiculo).arrancar();
                            } else {
                                System.err.println("Este vehiculo no puede arrancar");
                            }

                            break;

                        case 2: // mover

                            // Obtener estado del vehículo (si arrancable/no arrancable)

                            if (vehiculo instanceof Arrancable) {
                                System.out.println(((Arrancable) vehiculo).getEstado());
                            }

                            // Solicitud de movimiento

                            if (vehiculo instanceof Movible) {

                                int x = 0;
                                int y = 0;
                                boolean valido = false;

                                do {

                                    try {
                                        System.out.print("Avance en X: ");
                                        x = entrada.nextInt();
                                        System.out.print("Avance en Y: ");
                                        y = entrada.nextInt();
                                        entrada.nextLine();

                                        valido = true;

                                    } catch (Exception e) {
                                        System.err.println("Debe introducir un número válido");
                                        entrada.nextLine();
                                    }
                                } while (!valido);

                                try {
                                    ((Movible) vehiculo).mover(x, y);
                                } catch (Exception e) {
                                    System.err.println(e.getMessage());
                                }
                            } else {
                                System.err.println("Este vehiculo no se puede mover");
                            }
                            break;

                        case 3: // detener

                            if (vehiculo instanceof Arrancable) {
                                ((Arrancable) vehiculo).detener();

                            } else {
                                System.err.println("Este vehiculo no puede detenerse");
                            }
                            break;

                        case 4: // ver estado
                            if (vehiculo instanceof Arrancable) {
                                System.out.println(((Arrancable) vehiculo).getEstado());

                            } else {
                                System.out.println("El vehículo no puede arrancar/detenerse");
                            }
                            break;

                        case 5:
                            salir = true;
                            break;

                        default:
                            System.err.println("Selección no válida");
                            break;
                    }

                } while (!salir);

            }
        }
    }
}
