
import java.util.Scanner;

import clases.CuentaBancaria;
import ui.Controlador;

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

        // ===================================
        // ====GESTIÓN DE CUENTA BANCARIA ====
        // ===================================

        System.out.println("==== GESTIÓN DE CUENTA BANCARIA ====");
        System.out.println();

        CuentaBancaria cuentaBancaria1 = Controlador.crearCuenta(entrada);

        Controlador.gestionarMenuCuenta(entrada, cuentaBancaria1);

        entrada.close();
    }

}
