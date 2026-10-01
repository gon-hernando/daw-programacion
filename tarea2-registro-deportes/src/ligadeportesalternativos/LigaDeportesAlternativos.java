/**
 * 
 * @author GonzaloHernando
 */

package ligadeportesalternativos;

import java.util.Scanner;

import ligadeportesalternativos.interfaz.Menu;

public class LigaDeportesAlternativos {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        System.out.println("===================================================");
        System.out.println("----APP DE REGISTRO LIGA DEPORTES ALTERNATIVOS----");
        System.out.println("===================================================");

        Menu.inicio(entrada);

        entrada.close();

    }
}
