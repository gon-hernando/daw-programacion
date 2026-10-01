package ligadeportesalternativos.clases.deportes;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class GestorDeportes {

    private static Map<Integer, Deporte> deportes = new LinkedHashMap<>();

    // ==========================
    // CONSTRUCTOR
    // ==========================

    static {
        deportes.put(1, new Deporte("Tenis de Mesa", 18, 50, 6));
        deportes.put(2, new Deporte("Tiro con Arco Recreativo", 18, 50, 6));
        deportes.put(3, new Deporte("Ciclismo Urbano", 20, 55, 3));
        deportes.put(4, new Deporte("Carrera de sacos", 18, 50, 6));
        deportes.put(5, new Deporte("Balonmano", 18, 50, 6));
    }

    // ==========================
    // GETTER
    // ==========================

    public static Map<Integer, Deporte> getDeportes() {
        return deportes;
    }

    // ==========================
    // MOSTRAR DEPORTES
    // ==========================

    public static void mostrarDeportes() {

        for (Integer clave : deportes.keySet()) {
            System.out.printf("%d. %s", clave, deportes.get(clave));
        }
    }

    // ==========================
    // ESTADISTICAS
    // ==========================

    public static void estadisticas() {

        System.out.println("\nESTADÍSTICAS\n");

        for (Integer clave : deportes.keySet()) {
            deportes.get(clave).datos();
        }
    }

}
