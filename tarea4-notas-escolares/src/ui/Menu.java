package ui;

import java.util.Scanner;

public class Menu {

    public static int menuPrincipal(Scanner entrada) {
        
        while (true) {

        System.out.println();
        System.out.println("==== MENU PRINCIPAL ====");
        System.out.println();
        System.out.println("1. Parte 1: Gestionar calificaciones");
        System.out.println("2. Parte 2: Operaciones con frase");
        System.out.println("3. Salir del programa");

        System.out.println();
        System.out.print("Seleccione una opción: ");

        try {
            int seleccion = entrada.nextInt();
            return seleccion;

        } catch (Exception e) {
            entrada.nextLine();
            System.err.println("Error en la selección");
            System.out.println();
        }
    }
    }

}
