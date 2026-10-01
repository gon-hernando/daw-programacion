/*
 * Registro de empleados - versión 1.0
 * Autor: GonzaloHernando
 * 
 * Esta clase main a traves de un menu switch permite realizar diferentes operaciones. 
 * Las opciones de menu que piden input al usuario permiten salir si se comete un error o continuar con la operacion seleccionada
 */
package empresa.rrhh;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RegistroEmpleados {

    public static void main(String[] args) {

        Empleado empleado1 = null;
        Empleado empleado2 = null;
        Empleado empleado3 = null;
        Empleado empleado4 = null;
        Empleado empleado5 = null;

        // VARIABLES DE ENTRADA
        int menuSeleccion;
        int codigo = 0; // input para buscar y dar de baja un empleado
        double salarioNuevo;
        String entradaSalir; // input de usuario para activar salirSeleccion

        // VARIABLES AUXILIARES
        boolean salir = false;
        boolean salirSeleccion = false; // booleano para salir al menu principal desde una operacion
        boolean entradaValida = true; // booleano para comprobar registro de empleado correcto
        double antiguedadTotal = 0; // para calculo de antigüedadPromedio
        double salarioTotal = 0; // para calculo de salarioPromedio
        boolean maximoEmpleados = false; // para evitar registro de empleado si nº de empleados = 5
        boolean registroCorrecto = false; // para validar el registro de empleados

        // VARIABLES DE SALIDA
        double nominaTotal = 0; // en case 5
        double salarioPromedio = 0;
        double antiguedadPromedio = 0;
        int empleadosRegistrados = 0;
        int empleadosActivos = 0;
        int empleadosBaja = 0;

        Scanner entrada = new Scanner(System.in);

        // MENU DE SELECCION
        do {
            System.out.println("====PROGRAMA DE GESTIÓN DE RRHH=====");
            System.out.println();
            System.out.println("MENU");
            System.out.println("1. Registrar empleado");
            System.out.println("2. Listar todos los empleados");
            System.out.println("3. Dar de baja un empleado");
            System.out.println("4. Ver empleados activos");
            System.out.println("5. Calcular nómina total de todos los empleados");
            System.out.println("6. Buscar por código");
            System.out.println("7. Estadísticas");
            System.out.println("8. Salir");
            System.out.println();

            System.out.print("Seleccione opción: ");
            try {
                menuSeleccion = entrada.nextInt();
            } catch (InputMismatchException e) { // por si usuario no introduce un número
                System.err.println("Error: seleccione 1-8");
                entrada.nextLine();
                System.out.println();
                continue;
            }

            switch (menuSeleccion) {

                case 1:
                    System.out.println();
                    System.out.println("====REGISTRO DE EMPLEADO====");
                    System.out.println();
                    registroCorrecto = false;
                    if (maximoEmpleados == true) { // evitar el resto de código si Nº empleado = 5
                        System.err.println("Error: Máximo de empleados alcanzado");
                    } else {
                        do {
                            entradaValida = true;
                            salirSeleccion = false;

                            try {

                                System.out.println("Introduzca código: ");
                                int codigoNuevo = entrada.nextInt();
                                entrada.nextLine();
                                if ((empleado1 != null && empleado1.getCodigo() == codigoNuevo)
                                        || // validar
                                        // codigoNuevo no sea
                                        // igual que un
                                        // código ya
                                        // utilizado
                                        (empleado2 != null && empleado2.getCodigo() == codigoNuevo)
                                        || (empleado3 != null && empleado3.getCodigo() == codigoNuevo)
                                        || (empleado4 != null && empleado4.getCodigo() == codigoNuevo)
                                        || (empleado5 != null && empleado5.getCodigo() == codigoNuevo)) {

                                    System.err.println("Error: código de empleado ya existe");
                                    System.out.println( // opción de salir a menu principal
                                            "Introduzca SALIR para salir o pulse cualquier tecla para continuar con el proceso");
                                    entradaSalir = entrada.nextLine();
                                    if (entradaSalir.trim().equalsIgnoreCase("SALIR")) {
                                        salirSeleccion = true;
                                    }

                                } else {

                                    System.out.println("Introduzca nombre: ");
                                    String nombreNuevo = entrada.nextLine().toUpperCase();

                                    System.out.println("Introduzca apellido: ");
                                    String apellidoNuevo = entrada.nextLine().toUpperCase();

                                    do {
                                        System.out.println("Introduzca salario: ");
                                        salarioNuevo = entrada.nextDouble();
                                        if (Empleado.validarSalario(salarioNuevo) == false) { // validar salario con
                                            // bucle hasta
                                            // introduccion correcta
                                            System.err.println(
                                                    "Error: Salario no válido. Introduzca un valor entre 1.000 y 10.000");
                                        }
                                        entrada.nextLine();
                                    } while (Empleado.validarSalario(salarioNuevo) == false);

                                    if (empleado1 == null) { // asignacion de valores a 1º objeto empleado que sea null
                                        empleado1 = new Empleado(codigoNuevo, nombreNuevo, apellidoNuevo, salarioNuevo);
                                        registroCorrecto = true; // validacion de operación de registro correcto
                                    } else if (empleado2 == null) {
                                        empleado2 = new Empleado(codigoNuevo, nombreNuevo, apellidoNuevo, salarioNuevo);
                                        registroCorrecto = true;
                                    } else if (empleado3 == null) {
                                        empleado3 = new Empleado(codigoNuevo, nombreNuevo, apellidoNuevo, salarioNuevo);
                                        registroCorrecto = true;
                                    } else if (empleado4 == null) {
                                        empleado4 = new Empleado(codigoNuevo, nombreNuevo, apellidoNuevo, salarioNuevo);
                                        registroCorrecto = true;
                                    } else if (empleado5 == null) {
                                        empleado5 = new Empleado(codigoNuevo, nombreNuevo, apellidoNuevo, salarioNuevo);
                                        registroCorrecto = true;
                                        maximoEmpleados = true;
                                    }

                                    if (registroCorrecto == true) {
                                        System.out.println("Registro realizado con éxito");
                                        salirSeleccion = true;
                                    }
                                }

                            } catch (InputMismatchException e) { // Principalmente para si el usuario no introduce un
                                // código numérico
                                System.err.println("Error en la introducción de datos");
                                entrada.nextLine();
                                System.out.println( // opción de salir a menu principal
                                        "Introduzca SALIR para salir o pulse cualquier tecla para continuar con el proceso");
                                entradaSalir = entrada.nextLine();
                                if (entradaSalir.trim().equalsIgnoreCase("SALIR")) {
                                    salirSeleccion = true;
                                }
                            }

                        } while (salirSeleccion == false);
                    }
                    System.out.println();
                    break;

                case 2:
                    System.out.println();
                    System.out.println("====LISTAR TODOS LOS EMPLEADOS====");
                    System.out.println();

                    if (empleado1 == null && empleado2 == null && empleado3 == null && empleado4 == null
                            && empleado5 == null) {
                        System.out.println("No hay empleados que listar");
                    } else {

                        if (empleado1 != null) { // mostrar todos los empleados != null, se muestra salario con bono
                            // para
                            // activos y no activos (valor de consulta histórica para estos
                            // ultimos)
                            System.out.print(empleado1.toString());
                            System.out.println(" | Salario con bono: " + empleado1.calcularSalarioConBono() + " €");
                        }
                        if (empleado2 != null) {
                            System.out.print(empleado2.toString());
                            System.out.println(" | Salario con bono: " + empleado2.calcularSalarioConBono() + " €");
                        }
                        if (empleado3 != null) {
                            System.out.print(empleado3.toString());
                            System.out.println(" | Salario con bono: " + empleado3.calcularSalarioConBono() + " €");
                        }
                        if (empleado4 != null) {
                            System.out.print(empleado4.toString());
                            System.out.println(" | Salario con bono: " + empleado4.calcularSalarioConBono() + " €");
                        }
                        if (empleado5 != null) {
                            System.out.print(empleado5.toString());
                            System.out.println(" | Salario con bono: " + empleado5.calcularSalarioConBono() + " €");
                        }
                    }
                    System.out.println();
                    break;

                case 3:
                    System.out.println();
                    System.out.println("====DAR DE BAJA A UN EMPLEADO====");
                    System.out.println();

                    do {
                        entradaValida = true;
                        salirSeleccion = false;

                        try {
                            System.out.print("Introduzca código de empleado: ");
                            codigo = entrada.nextInt();
                            entrada.nextLine();

                            if (empleado1 != null && codigo == empleado1.getCodigo()) {
                                empleado1.darDeBaja();
                                System.out.print(empleado1.getNombre() + " " + empleado1.getApellido());
                                System.out.println(" ha sido dado de baja");

                            } else if (empleado2 != null && codigo == empleado2.getCodigo()) {
                                empleado2.darDeBaja();
                                System.out.print(empleado2.getNombre() + " " + empleado2.getApellido());
                                System.out.println(" ha sido dado de baja");

                            } else if (empleado3 != null && codigo == empleado3.getCodigo()) {
                                empleado3.darDeBaja();
                                System.out.print(empleado3.getNombre() + " " + empleado3.getApellido());
                                System.out.println(" ha sido dado de baja");

                            } else if (empleado4 != null && codigo == empleado4.getCodigo()) {
                                empleado4.darDeBaja();
                                System.out.print(empleado4.getNombre() + " " + empleado4.getApellido());
                                System.out.println(" ha sido dado de baja");

                            } else if (empleado5 != null && codigo == empleado5.getCodigo()) {
                                empleado5.darDeBaja();
                                System.out.print(empleado5.getNombre() + " " + empleado5.getApellido());
                                System.out.println(" ha sido dado de baja");

                            } else {
                                System.err.println("Código de empleado no encontrado"); // para codigos no encontrados
                                System.out.println( // opcion de salir a menu principal
                                        "Introduzca SALIR para salir o pulse cualquier tecla para continuar con el proceso");
                                entradaSalir = entrada.nextLine();
                                if (entradaSalir.trim().equalsIgnoreCase("SALIR")) {
                                    salirSeleccion = true;

                                }
                                entradaValida = false;
                            }

                        } catch (Exception e) {
                            entrada.nextLine();
                            System.err.println("Código de empleado no válido"); // Principalmente para códigos no
                            // númericos introducidos por usuario
                            System.out.println( // opcion de salir a menu principal
                                    "Introduzca SALIR para salir o pulse cualquier tecla para continuar con el proceso");
                            entradaSalir = entrada.nextLine();
                            if (entradaSalir.trim().equalsIgnoreCase("SALIR")) {
                                salirSeleccion = true;
                            }
                            entradaValida = false;
                        }

                    } while (!entradaValida && salirSeleccion == false);
                    System.out.println();
                    break;

                case 4:
                    System.out.println();
                    System.out.println("====VER EMPLEADOS ACTIVOS====");
                    System.out.println();

                    // comprobacion inicial para chequear si
                    // todos los empleados no se han creado o no
                    // estan activos
                    if ((empleado1 == null || empleado1.isActivo() != true)
                            && (empleado2 == null || empleado2.isActivo() != true)
                            && (empleado3 == null || empleado3.isActivo() != true)
                            && (empleado4 == null || empleado4.isActivo() != true)
                            && (empleado5 == null || empleado5.isActivo() != true)) {
                        System.out.println("No hay empleados activos");
                    } else {

                        if (empleado1 != null && empleado1.isActivo()) { // empleado no null y activo
                            System.out.print(empleado1.getNombre() + " " + empleado1.getApellido());
                            System.out.print(" | Antigüedad: " + empleado1.calcularAntiguedad()); // para mostrar antigüedad
                            if (empleado1.calcularAntiguedad() == 0) {
                                System.out.println(" año");
                            } else {
                                System.out.println(" años");
                            }
                        }
                        if (empleado2 != null && empleado2.isActivo()) {
                            System.out.print(empleado2.getNombre() + " " + empleado2.getApellido());
                            System.out.print(" | Antigüedad: " + empleado2.calcularAntiguedad());
                            if (empleado2.calcularAntiguedad() == 0) {
                                System.out.println(" año");
                            } else {
                                System.out.println(" años");
                            }
                        }
                        if (empleado3 != null && empleado3.isActivo()) {
                            System.out.print(empleado3.getNombre() + " " + empleado3.getApellido());
                            System.out.println(" | Antigüedad: " + empleado3.calcularAntiguedad() + " años");
                            if (empleado3.calcularAntiguedad() == 0) {
                                System.out.println(" año");
                            } else {
                                System.out.println(" años");
                            }

                        }
                        if (empleado4 != null && empleado4.isActivo()) {
                            System.out.print(empleado4.getNombre() + " " + empleado4.getApellido());
                            System.out.println(" | Antigüedad: " + empleado4.calcularAntiguedad());
                            if (empleado4.calcularAntiguedad() == 0) {
                                System.out.println(" año");
                            } else {
                                System.out.println(" años");
                            }

                        }
                        if (empleado5 != null && empleado5.isActivo()) {
                            System.out.print(empleado5.getNombre() + " " + empleado5.getApellido());
                            System.out.println(" | Antigüedad: " + empleado5.calcularAntiguedad());
                            if (empleado5.calcularAntiguedad() == 0) {
                                System.out.println(" año");
                            } else {
                                System.out.println(" años");
                            }
                        }
                    }

                    System.out.println();

                    break;

                case 5:
                    System.out.println();

                    System.out.println(
                            "====CALCULAR NÓMINA TOTAL====");
                    System.out.println();

                    nominaTotal = 0;

                    if (empleado1 != null && empleado1.isActivo()) {
                        nominaTotal = empleado1.calcularSalarioConBono(); // acumulador de nominas
                    }
                    if (empleado2 != null && empleado2.isActivo()) {
                        nominaTotal += empleado2.calcularSalarioConBono();
                    }
                    if (empleado3 != null && empleado3.isActivo()) {
                        nominaTotal += empleado3.calcularSalarioConBono();
                    }
                    if (empleado4 != null && empleado4.isActivo()) {
                        nominaTotal += empleado4.calcularSalarioConBono();
                    }
                    if (empleado5 != null && empleado5.isActivo()) {
                        nominaTotal += empleado5.calcularSalarioConBono();
                    }

                    System.out.println(
                            "Importe total de nóminas de este mes: " + nominaTotal + " €"); // salida de
                    // resultado
                    System.out.println();

                    break;

                case 6:
                    System.out.println();

                    System.out.println(
                            "====BUSCAR POR CÓDIGO====");
                    System.out.println();

                    do {
                        entradaValida = true;
                        salirSeleccion = false;
                        try {
                            System.out.print("Introduzca código de empleado: ");
                            codigo = entrada.nextInt();
                            entrada.nextLine();
                            if (empleado1 != null && codigo == empleado1.getCodigo()) {
                                System.out.print(empleado1.getNombre() + " " + empleado1.getApellido()); // se muestran unicamente nombre y apellidos, en caso de querer losd atos completos utilizariamos toString igual que en case 2
                                if (empleado1.isActivo()) { // condicional para mostrar salario solo si activo
                                    System.out.println(" | Salario con bono: " + empleado1.calcularSalarioConBono());
                                } else {
                                    System.out.println(" | BAJA"); // si no activo
                                }

                            } else if (empleado2 != null && codigo == empleado2.getCodigo()) {
                                System.out.print(empleado2.getNombre() + " " + empleado2.getApellido());
                                if (empleado2.isActivo()) {
                                    System.out.println(" | Salario con bono: " + empleado2.calcularSalarioConBono());
                                } else {
                                    System.out.println(" | BAJA");
                                }

                            } else if (empleado3 != null && codigo == empleado3.getCodigo()) {
                                System.out.print(empleado3.getNombre() + " " + empleado3.getApellido());
                                if (empleado3.isActivo()) {
                                    System.out.println(" | Salario con bono: " + empleado3.calcularSalarioConBono());
                                } else {
                                    System.out.println(" | BAJA");
                                }

                            } else if (empleado4 != null && codigo == empleado4.getCodigo()) {
                                System.out.print(empleado4.getNombre() + " " + empleado4.getApellido());
                                if (empleado4.isActivo()) {
                                    System.out.println(" | Salario con bono: " + empleado4.calcularSalarioConBono());
                                } else {
                                    System.out.println(" | BAJA");
                                }

                            } else if (empleado5 != null && codigo == empleado5.getCodigo()) {
                                System.out.print(empleado5.getNombre() + " " + empleado5.getApellido());
                                if (empleado5.isActivo()) {
                                    System.out.println(" | Salario con bono: " + empleado5.calcularSalarioConBono());
                                } else {
                                    System.out.println(" | BAJA");
                                }

                            } else { // si codigo no encontrado
                                System.err.println("Código de empleado no encontrado");
                                System.out.println( // opcion de salir al menu principal
                                        "Introduzca SALIR para salir o pulse cualquier tecla para continuar con el proceso");
                                entradaSalir = entrada.nextLine();
                                if (entradaSalir.trim().equalsIgnoreCase("SALIR")) {
                                    salirSeleccion = true;
                                }
                                entradaValida = false;
                            }

                        } catch (Exception e) {
                            entrada.nextLine();
                            System.err.println("Código de empleado no válido");

                            entradaValida = false;

                            System.out.println(
                                    "Introduzca SALIR para salir o pulse cualquier tecla para continuar con el proceso");
                            entradaSalir = entrada.nextLine();
                            if (entradaSalir.trim().equalsIgnoreCase("SALIR")) {
                                salirSeleccion = true;
                            }
                        }
                    } while (!entradaValida && salirSeleccion == false);
                    System.out.println();

                    break;

                case 7:
                    System.out.println();

                    System.out.println(
                            "====ESTADÍSTICAS====");
                    System.out.println();

                    // CALCULO DE EMPLEADOS REGISTRADOS
                    empleadosRegistrados = 0; // Reseteamos valor a 0

                    if (empleado1 != null) {
                        empleadosRegistrados++; // contador de empleados registrados
                    }
                    if (empleado2 != null) {
                        empleadosRegistrados++;
                    }
                    if (empleado3 != null) {
                        empleadosRegistrados++;
                    }
                    if (empleado4 != null) {
                        empleadosRegistrados++;
                    }
                    if (empleado5 != null) {
                        empleadosRegistrados++;
                    }

                    // CALCULO DE EMPLEADOS ACTIVOS
                    empleadosActivos = 0; // Reseteamos valor a 0

                    if (empleado1 != null && empleado1.isActivo()) { // contador de empleados activos
                        empleadosActivos++;
                    }
                    if (empleado2 != null && empleado2.isActivo()) {
                        empleadosActivos++;
                    }
                    if (empleado3 != null && empleado3.isActivo()) {
                        empleadosActivos++;
                    }
                    if (empleado4 != null && empleado4.isActivo()) {
                        empleadosActivos++;
                    }
                    if (empleado5 != null && empleado5.isActivo()) {
                        empleadosActivos++;
                    }

                    // CALCULO DE EMPLEADOS DADOS DE BAJA
                    empleadosBaja = 0; // Reseteamos valor a 0

                    if (empleado1 != null && !empleado1.isActivo()) { // contador de empleados dados de baja
                        empleadosBaja++;
                    }
                    if (empleado2 != null && !empleado2.isActivo()) {
                        empleadosBaja++;
                    }
                    if (empleado3 != null && !empleado3.isActivo()) {
                        empleadosBaja++;
                    }
                    if (empleado4 != null && !empleado4.isActivo()) {
                        empleadosBaja++;
                    }
                    if (empleado5 != null && !empleado5.isActivo()) {
                        empleadosBaja++;
                    }

                    // CALCULO ANTIGÜEDAD PROMEDIO
                    antiguedadTotal = 0; // resetear por si se selecciona varias veces esta operación

                    if (empleado1 != null && empleado1.isActivo()) {
                        antiguedadTotal += empleado1.calcularAntiguedad(); // acumulador de antigüedad
                    }
                    if (empleado2 != null && empleado2.isActivo()) {
                        antiguedadTotal += empleado2.calcularAntiguedad();
                    }
                    if (empleado3 != null && empleado3.isActivo()) {
                        antiguedadTotal += empleado3.calcularAntiguedad();
                    }
                    if (empleado4 != null && empleado4.isActivo()) {
                        antiguedadTotal += empleado4.calcularAntiguedad();
                    }
                    if (empleado5 != null && empleado5.isActivo()) {
                        antiguedadTotal += empleado5.calcularAntiguedad();
                    }

                    // CALCULO SALARIO PROMEDIO
                    salarioTotal = 0;

                    if (empleado1 != null && empleado1.isActivo()) {
                        salarioTotal += empleado1.calcularSalarioConBono(); // acumulador de salarios
                    }
                    if (empleado2 != null && empleado2.isActivo()) {
                        salarioTotal += empleado2.calcularSalarioConBono();
                    }
                    if (empleado3 != null && empleado3.isActivo()) {
                        salarioTotal += empleado3.calcularSalarioConBono();
                    }
                    if (empleado4 != null && empleado4.isActivo()) {
                        salarioTotal += empleado4.calcularSalarioConBono();
                    }
                    if (empleado5 != null && empleado5.isActivo()) {
                        salarioTotal += empleado5.calcularSalarioConBono();
                    }

                    // ESTADISTICAS POR CONSOLA
                    System.out.println(
                            "-Nº de empleados registrados: " + empleadosRegistrados);
                    System.out.println(
                            "-Nº de empleados activos: " + empleadosActivos);
                    System.out.println(
                            "-Nº de empleados dados de baja: " + empleadosBaja);
                    if (empleadosActivos == 0) { // condicional para evitar error NaN
                        System.out.println("-Antigüedad promedio: 0 años");
                        System.out.println("-Salario promedio: 0 €");
                    } else {
                        antiguedadPromedio = antiguedadTotal / empleadosActivos; // calculo de antigüedad promedio
                        salarioPromedio = salarioTotal / empleadosActivos; // calculo de salario promedio
                        if (antiguedadPromedio == 0) {
                            System.out.println("-Antigüedad promedio: " + antiguedadPromedio + " año");
                        } else {
                            System.out.println("-Antigüedad promedio: " + antiguedadPromedio + " años");
                        }
                        System.out.println("-Salario promedio: " + salarioPromedio + " €");
                    }
                    System.out.println();

                    break;

                // SALIR DEL MENU PRINCIPAL Y DEL PROGRAMA
                case 8:
                    System.out.println();
                    salir = true;

                    System.out.println(
                            "FINALIZACIÓN DEL PROGRAMA DE GESTIÓN DE RRHH");
                    break;

                default:
                    System.err.println(
                            "Error: Seleccione 1-8");
                    System.out.println();
            }

        } while (!salir); // referencia a case 8

        entrada.close();
    }
}
