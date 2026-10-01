package ui;

import java.util.Scanner;

import util.Util;

public class Menu {

    public static int menuCuenta(Scanner entrada) {

        while (true) {

            System.out.println();
            System.out.println("\n==== MENÚ DE OPCIONES ====\n");

            System.out.println("[1] Ver número de cuenta completo (CCC)");
            System.out.println("[2] Ver titular de la cuenta");
            System.out.println("[3] Ver código de entidad de la cuenta");
            System.out.println("[4] Ver código de oficina de la cuenta");
            System.out.println("[5] Ver número de la cuenta");
            System.out.println("[6] Ver dígitos de control de la cuenta");
            System.out.println("[7] Ver IBAN de la cuenta");
            System.out.println("[8] Realizar un ingreso");
            System.out.println("[9] Retirar efectivo");
            System.out.println("[10] Consultar saldo");
            System.out.println("[0] Salir");

            System.out.print("Seleccione opción: ");

            try {
                int seleccion = entrada.nextInt();

                if (seleccion >= 0 && seleccion <= 10) {
                    return seleccion;

                } else {
                    System.err.println("Error: Opción del menú incorrecta"); // mensaje de error
                }

            } catch (Exception e) {
                System.err.println("Selección erronea");
                entrada.nextLine(); // Limpieza de buffer

            }
        }
    }

    public static double menuPedirCantidad(Scanner entrada, String frase) {

        while (true) {

            System.out.print(frase);

            try {
                double cantidad = entrada.nextDouble();
                Util.validarCantidad(cantidad);
                return cantidad;

            } catch (IllegalArgumentException e) { // atrapamos el mensaje de error desde clase CuentaBancaria
                System.err.println(e.getMessage());

            } catch (Exception e) {
                System.err.println("Cantidad no válida");
                entrada.nextLine();
            }
        }
    }
}
