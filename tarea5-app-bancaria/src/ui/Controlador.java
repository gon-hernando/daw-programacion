package ui;

import java.util.Scanner;

import clases.CuentaBancaria;
import util.Util;

public class Controlador {

    // ===================================
    // ====CREAR CUENTA ====
    // ===================================

    public static CuentaBancaria crearCuenta(Scanner entrada) {

        while (true) {

            String nifONie = Util.registro(entrada, "Ingrese NIF o NIE del titular: ", "NIF o NIE");

            String nombreTitularCuenta = Util.registro(entrada, "Ingrese nombre del titular: ", "Nombre del titular")
                    .toUpperCase();
            String codigoCuentaCliente = Util.registro(entrada, "Ingrese CCC: ", "CCC");
            try {
                return new CuentaBancaria(codigoCuentaCliente, nifONie, nombreTitularCuenta);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        }
    }

    // ===================================
    // ====gestionarMenuCuenta ====
    // ===================================

    public static void gestionarMenuCuenta(Scanner entrada, CuentaBancaria cuenta) {

        boolean salir = false; // variable para menú

        do {

            int seleccion = Menu.menuCuenta(entrada);

            switch (seleccion) {

                case 1:
                    System.out.println("\n==== CONSULTAR CÓDIGO DE CUENTA ====\n");
                    System.out.println(cuenta);

                    break;

                case 2:
                    System.out.println("\n==== CONSULTAR TITULAR DE CUENTA ====\n");
                    cuenta.mostrarDato("Titular de la cuenta: ", cuenta.getNombreTitularCuenta());
                    break;

                case 3:
                    System.out.println("\n==== CONSULTAR CÓDIGO DE ENTIDAD ====\n");
                    cuenta.mostrarDato("Código de la entidad: ", cuenta.getEntidad());
                    break;

                case 4:
                    System.out.println("\n==== CONSULTAR CÓDIGO DE OFICINA ====\n");
                    cuenta.mostrarDato("Código de la oficina: ", cuenta.getCodigoOficina());
                    break;

                case 5:
                    System.out.println("\n==== CONSULTAR NÚMERO DE CUENTA ====\n");
                    cuenta.mostrarDato("Número de cuenta: ", cuenta.getNumeroCuenta());
                    break;

                case 6:
                    System.out.println("\n==== CONSULTAR DIGITOS DE CONTROL ====\n");
                    cuenta.mostrarDato("Digitos de control: ", cuenta.getDigitosControl());

                    break;

                case 7:
                    System.out.println("\n==== CONSULTAR IBAN ====\n");

                    System.out.printf("IBAN: %s %s", cuenta.mostrarIban().substring(0, 4),
                            cuenta.getCodigoCuentaCliente());
                    break;

                case 8:
                    System.out.println("\n==== REALIZAR INGRESO ====\n");

                    double cantidad = Menu.menuPedirCantidad(entrada, "Cantidad a ingresar: ");
                    cuenta.ingresar(cantidad);

                    break;

                case 9:
                    System.out.println("\n==== RETIRAR EFECTIVO ====\n");

                    cantidad = Menu.menuPedirCantidad(entrada, "Cantidad a retirar: ");
                    cuenta.retirar(cantidad);

                    break;

                case 10:
                    System.out.println("\n==== CONSULTAR SALDO ====\n");

                    System.out.printf("Saldo disponible: %.2f€", cuenta.getSaldo());

                    break;

                case 0:

                    salir = true;
                    System.out.println("\n==== FIN DE LA APLICACIÓN ====\n");

                    break;
            }

        } while (!salir);
    }

}
