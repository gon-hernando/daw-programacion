
import java.util.Scanner;

/**
 * Clase principal de la aplicación de gestión de cuentas bancarias.
 * <p>
 * Permite introducir los datos iniciales de una cuenta bancaria y muestra
 * un menú interactivo desde el que se pueden realizar distintas operaciones
 * como consultar datos de la cuenta, ingresar o retirar dinero y consultar el
 * saldo.
 *
 * @author gonzaloHernando
 */
public class AplicacionCuentaBancaria {

    /**
     * Método principal de la aplicación.
     * <p>
     * Gestiona la entrada de datos por teclado, crea una cuenta bancaria
     * y muestra un menú con las distintas opciones disponibles para operar
     * con la cuenta.
     *
     * @param args argumentos de la línea de comandos (no utilizados)
     */

    public static void main(String[] args) {

        // CLASE SCANNER

        Scanner entrada = new Scanner(System.in);

        // PARAMETROS DE ENTRADA

        int menuSeleccion = -1;

        String nifONie;
        String nombreTitularCuenta;
        String codigoCuentaCliente;

        double cantidad; // ingreso y retirada

        // PARÁMETROS AUXILARES

        boolean salir = false; // variable para menú
        boolean valido = false; // variable ingreso de datos

        // ===================================
        // ====GESTIÓN DE CUENTA BANCARIA ====
        // ===================================

        System.out.println("==== GESTIÓN DE CUENTA BANCARIA ====");
        System.out.println();

        // ========================================
        // ====PROCESO DE INGRESO DE NIF O NIE ====
        // ========================================

        do {
            System.out.print("Ingrese NIF o NIE del titular: ");
            nifONie = entrada.nextLine();

            try {
                Validar.esValidoNifONie(nifONie);
                System.out.println("NIF o NIE correcto");
                valido = true;

            } catch (IllegalArgumentException e) { // atrapamos el mensaje de error desde clase Validar
                System.err.println(e.getMessage());
                valido = false;

            }

        } while (!valido);

        // ==================================================
        // ==== PROCESO DE INGRESO DE NOMBRE DEL TITULAR ====
        // ==================================================

        do {
            System.out.print("Ingrese nombre del titular: ");
            nombreTitularCuenta = entrada.nextLine();
            nombreTitularCuenta = nombreTitularCuenta.toUpperCase(); // estandarizamos en mayúsculas
            valido = false;

            try {
                Validar.esValidoNombre(nombreTitularCuenta);
                System.out.println("Nombre del titular correcto");
                valido = true;

            } catch (IllegalArgumentException e) { // atrapamos el mensaje de error desde clase Validar
                System.err.println(e.getMessage());
                valido = false;
            }

        } while (!valido);

        // ==============================================
        // ==== PROCESO DE INGRESO DE CUENTA CLIENTE ====
        // ==============================================

        do {
            System.out.print("Ingrese CCC: ");
            codigoCuentaCliente = entrada.nextLine();

            try {
                CuentaBancaria.esCuentaValida(codigoCuentaCliente);
                System.out.println("CCC correcto");
                valido = true;

            } catch (IllegalArgumentException e) { // atrapamos el mensaje de error desde clase Validar
                System.err.println(e.getMessage());
                valido = false;
            }

        } while (!valido);

        // ============================
        // ==== CREACIÓN DE OBJETO ====
        // ============================

        CuentaBancaria cuentaBancaria1 = new CuentaBancaria(codigoCuentaCliente, nifONie, nombreTitularCuenta);

        // ==========================
        // ==== MENU DE OPCIONES ====
        // ==========================

        do {

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

            do {
                System.out.print("Seleccione opción: ");
                valido = false;
                try {
                    menuSeleccion = entrada.nextInt();

                    if (menuSeleccion >= 0 && menuSeleccion <= 10) {
                        valido = true;

                    } else {
                        System.err.println("Error: Opción del menú incorrecta"); // mensaje de error
                    }

                } catch (Exception e) {
                    System.err.println("Selección erronea");
                    entrada.nextLine(); // Limpieza de buffer

                } finally {
                    entrada.nextLine(); // limpiar buffer
                }
            } while (!valido);

            switch (menuSeleccion) {

                case 0:
                    salir = true;
                    System.out.println("\n==== FIN DE LA APLICACIÓN ====\n");
                    break;

                case 1:
                    System.out.println("\n==== CONSULTAR CÓDIGO DE CUENTA ====\n");

                    System.out.printf("-Número de cuenta completo (CCC): %s-%s-%s-%s", cuentaBancaria1.mostrarEntidad(),
                            cuentaBancaria1.mostrarOficina(), cuentaBancaria1.mostrarDigitosControl(),
                            cuentaBancaria1.mostrarNumeroCuenta());

                    break;

                case 2:
                    System.out.println("\n==== CONSULTAR TITULAR DE CUENTA ====\n");
                    System.out.println(cuentaBancaria1.getNombreTitularCuenta());
                    break;

                case 3:
                    System.out.println("\n==== CONSULTAR CÓDIGO DE ENTIDAD ====\n");
                    System.out.printf("Código de entidad: %s\n", cuentaBancaria1.mostrarEntidad());
                    break;

                case 4:
                    System.out.println("\n==== CONSULTAR CÓDIGO DE OFICINA ====\n");
                    System.out.printf("Código de oficina: %s\n", cuentaBancaria1.mostrarOficina());
                    break;

                case 5:
                    System.out.println("\n==== CONSULTAR NÚMERO DE CUENTA ====\n");
                    System.out.printf("Número de cuenta: %s\n", cuentaBancaria1.mostrarNumeroCuenta());
                    break;

                case 6:
                    System.out.println("\n==== CONSULTAR DIGITOS DE CONTROL ====\n");
                    System.out.printf("Digitos de control: %s\n", cuentaBancaria1.mostrarDigitosControl());

                    break;

                case 7:
                    System.out.println("\n==== CONSULTAR IBAN ====\n");
                    System.out.printf("IBAN: %s %s", cuentaBancaria1.mostrarIban().substring(0, 4),
                            cuentaBancaria1.getCodigoCuentaCliente());
                    break;

                case 8:
                    System.out.println("\n==== REALIZAR INGRESO ====\n");
                    System.out.print("Cantidad a ingresar: ");

                    try {
                        cantidad = entrada.nextDouble();
                        Validar.validarCantidad(cantidad);
                        cuentaBancaria1.ingresar(cantidad);

                    } catch (IllegalArgumentException e) { // atrapamos el mensaje de error desde clase CuentaBancaria
                        System.err.println(e.getMessage());

                    } catch (Exception e) {
                        System.err.println("Cantidad no válida");
                        entrada.nextLine();
                    }

                    break;

                case 9:
                    System.out.println("\n==== RETIRAR EFECTIVO ====\n");
                    System.out.print("Cantidad a retirar: ");

                    try {
                        cantidad = entrada.nextDouble();
                        Validar.validarCantidad(cantidad);
                        cuentaBancaria1.retirar(cantidad);

                    } catch (IllegalArgumentException e) { // atrapamos el mensaje de error desde clase CuentaBancaria
                        System.err.println(e.getMessage());

                    } catch (Exception e) {
                        System.err.println("Cantidad no válida");
                        entrada.nextLine();
                    }

                    break;

                case 10:
                    System.out.println("\n==== CONSULTAR SALDO ====\n");

                    System.out.printf("Saldo disponible: %.2f€", cuentaBancaria1.getSaldo());
                    break;

            }

        } while (!salir);

        entrada.close();
    }

}
