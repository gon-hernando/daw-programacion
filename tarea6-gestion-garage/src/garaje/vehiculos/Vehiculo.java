package garaje.vehiculos;

import java.util.Scanner;

import garaje.interfaces.Movible;
import garaje.util.Util;

/**
 * Representa un vehículo genérico dentro del sistema de gestión de garaje.
 * <p>
 * Esta clase incluye atributos comunes a todos los vehículos, como marca,
 * modelo y matrícula,así como información de posición (X, Y) y estado de
 * arranque. Implementa la interfaz {@link Movible} para permitir movimiento
 * controlado en un espacio
 * 2D.
 * </p>
 * <p>
 * Además, proporciona métodos estáticos auxiliares para el registro seguro de
 * datos mediante la consola.
 * </p>
 * 
 * @author Gonzalo Hernando Llorente
 * @version
 */

public class Vehiculo implements Movible {

    private String marca;
    private String modelo;
    private String matricula;

    private int posicionX = 0;
    private int posicionY = 0;

    private boolean arrancado = false;

    // =====================
    // ---- CONSTRUCTOR ----
    // =====================

    /**
     * Construye un nuevo vehículo con los datos especificados.
     * 
     * @param marca     Marca del vehículo.
     * @param modelo    Modelo del vehículo.
     * @param matricula Matrícula del vehículo.
     * @throws IllegalArgumentException si alguno de los datos no es válido.
     */

    public Vehiculo(String marca, String modelo, String matricula) {

        Util.validarMarca(marca);

        Util.validarModelo(modelo);

        Util.validarMatricula(matricula);

        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
    }

    // ================
    // ---- GETTER ----
    // ================

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public boolean getArrancado() {
        return arrancado;
    }

    // ================
    // ---- SETTER ----
    // ================

    // MARCA //

    public void setMarca(String marca) {

        Util.validarMarca(marca);

        this.marca = marca;
    }

    // MODELO //

    public void setModelo(String modelo) {

        Util.validarModelo(modelo);

        this.modelo = modelo;
    }

    // MATRICULA //

    public void setMatricula(String matricula) {

        Util.validarMatricula(matricula);

        this.matricula = matricula;
    }

    // POSICIONES //

    public void setPosicionX(int posicionX) {

        Util.validarMovimiento(posicionX);
        this.posicionX = posicionX;
    }

    public void setPosicionY(int posicionY) {

        Util.validarMovimiento(posicionY);
        this.posicionY = posicionY;
    }

    public void setArrancado(boolean arrancado) {
        this.arrancado = arrancado;
    }

    // ==================
    // ---- TOSTRING ----
    // ==================

    /**
     * Devuelve una representación en cadena del vehículo, incluyendo marca, modelo
     * y matrícula.
     * 
     * @return String con información del vehículo.
     */

    @Override
    public String toString() {
        return String.format("Marca: %s, Modelo: %s, Matrícula: %s", marca, modelo, matricula);
    }

    // ==================
    // ---- MOVER() ----
    // ==================

    /**
     * Mueve el vehículo a una nueva posición sumando los desplazamientos X e Y.
     * 
     * @param x Desplazamiento en el eje X.
     * @param y Desplazamiento en el eje Y.
     * @throws IllegalStateException si la posición final es negativa.
     */

    @Override
    public void mover(int x, int y) {

        int posicionFinalX = posicionX + x;
        int posicionFinalY = posicionY + y;

        esMovible(posicionFinalX, posicionFinalY);

        // Validacion.validarMovimiento(posicionFinalX);
        // Validacion.validarMovimiento(posicionFinalY);

        posicionX = posicionFinalX;
        posicionY = posicionFinalY;
    }

    // ==================
    // ---- ESMOVIBLE() ----
    // ==================

    /**
     * Verifica que la posición final del vehículo sea válida (positiva).
     * 
     * @param x Posición final en X.
     * @param y Posición final en Y.
     * @throws IllegalStateException si alguna coordenada es negativa.
     */

    @Override
    public void esMovible(int x, int y) {

        if (x < 0 || y < 0) {
            throw new IllegalStateException("Error: la posición final del vehículo debe ser positiva");
        }
    }

    // ==================
    // ---- REGISTRO ----
    // ==================

    /**
     * Solicita al usuario que ingrese un dato de tipo cadena por consola.
     * 
     * @param entrada Objeto {@link Scanner} para leer la entrada.
     * @param texto   Mensaje a mostrar al usuario.
     * @return Cadena ingresada por el usuario (no vacía).
     */

    public static String registro(Scanner entrada, String texto) {

        String dato;

        do {
            System.out.print(texto);
            dato = entrada.nextLine();

            if (dato.isEmpty()) {
                System.err.println("El dato no puede estar vacío");
            }

        } while (dato.isEmpty());

        return dato;
    }

    /**
     * Solicita al usuario que ingrese un dato numérico por consola, dentro de un
     * rango.
     * 
     * @param entrada Objeto {@link Scanner} para leer la entrada.
     * @param texto   Mensaje a mostrar al usuario.
     * @param min     Valor mínimo permitido.
     * @param max     Valor máximo permitido.
     * @return Entero ingresado por el usuario dentro del rango.
     */

    public static int registroInt(Scanner entrada, String texto, int min, int max) {

        int dato;
        do {
            System.out.print(texto);

            try {
                dato = entrada.nextInt();
                entrada.nextLine();

                if (dato < min || dato > max) {
                    System.err.println("Valor fuera de rango");
                } else {
                    return dato;
                }

            } catch (Exception e) {
                entrada.nextLine();
                System.err.println("Error: debe indicar un número");
            }
        } while (true);
    }
}
