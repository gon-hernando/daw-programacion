package ligadeportesalternativos.interfaz;

import java.util.Scanner;

import ligadeportesalternativos.clases.Inscrito;
import ligadeportesalternativos.clases.deportes.Deporte;
import ligadeportesalternativos.clases.deportes.GestorDeportes;
import ligadeportesalternativos.utils.ConsoleUtils;
import ligadeportesalternativos.utils.Util;

public class Registro {

    // ==========================
    // REGISTRO USUARIO
    // ==========================

    public static void registroUsuario(Scanner entrada, Deporte deporte) {

        System.out.println("\nREGISTRO DE USUARIOS\n");

        System.out.printf("Se ha seleccionado: %s\n", deporte.getNombreDeporte());
        System.out.printf("Rango de edad = %d-%d\n", deporte.getEdadMinima(), deporte.getEdadMaxima());
        System.out.printf("Número máximo de usuarios = %d\n", deporte.getUsuariosMaximos());
        System.out.printf("Plazas vacantes: %d\n", deporte.plazasVacantes());

        boolean finalizarRegistro = false;

        do {

            try {

                String nombre = Util.registro(entrada, "Introduzca nombre: ");
                String apellidos = Util.registro(entrada, "Introduzca apellidos: ");
                int edadUsuario = Util.validarEntero(Util.registro(entrada, "Introduzca edad: "));

                deporte.comprobarEdad(edadUsuario);

                Inscrito i = new Inscrito(nombre, apellidos, edadUsuario);
                deporte.inscribir(i);

                System.out.printf("\n%s %s ha sido inscrito en %s\n", nombre, apellidos, deporte.getNombreDeporte());

            } catch (IllegalArgumentException | IllegalStateException e) {
                System.err.println(e.getMessage());
            }

            if (deporte.plazasVacantes() > 0) {

                String respuesta = Util.registro(entrada, "\n¿Continuar con el registro de otro usuario? (s/n): ");

                finalizarRegistro = !respuesta.equalsIgnoreCase("s");

            } else {
                System.out.println("\nNo quedan plazas. Finalizado registro");
                finalizarRegistro = true;
            }

        } while (!finalizarRegistro);

    }

    // ==========================
    // SELECCIONAR DEPORTE
    // ==========================

    public static Deporte seleccionarDeporte(Scanner entrada) {

        System.out.println("\nSELECCIÓN DE DEPORTE\n");
        System.out.println("----Opciones disponibles----\n");
        GestorDeportes.mostrarDeportes();
        System.out.println("0. Salir");

        while (true) {

            int seleccion = ConsoleUtils.validarSeleccion(entrada, "\nSeleccione deporte: ");

            if (seleccion == 0) {
                return null;
            }

            Deporte deporte = GestorDeportes.getDeportes().get(seleccion);

            if (deporte != null) {
                return deporte;
            }

            System.err.println("Selección fuera de rango");

        }

    }

}
