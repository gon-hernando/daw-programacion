
import java.math.BigInteger; // necesario para IBAN 

/**
 * Clase {@code CuentaBancaria} que representa una cuenta bancaria de un
 * cliente, incluyendo los datos del titular, CCC (Código Cuenta Cliente), saldo
 * y métodos para operaciones básicas como ingreso, retirada y cálculo de
 * dígitos de control.
 * <p>
 * Esta clase valida los datos de entrada mediante la clase {@code Validar} y
 * permite consultar los distintos componentes del CCC: entidad, oficina,
 * dígitos de control y número de cuenta.
 * 
 * @author gonzaloHernando
 * @version 1.0
 * @since 2026-01-26
 */

public class CuentaBancaria {

    // ===============================
    // ==== ATRIBUTOS PRINCIPALES ====
    // ===============================

    private String codigoCuentaCliente;// String de 20 dígitos.
    private String nombreTitularCuenta; // máximo 36 caracteres
    private String nifONieDelTitular;
    private double saldo;

    // =====================================
    // ==== ATRIBUTOS DIGITOS DERIVADOS ====
    // =====================================

    // =======================
    // ==== CONSTRUCTORES ====
    // =======================

    // Constructor vacío

    /**
     * Constructor vacío. Inicializa una cuenta sin datos.
     */

    public CuentaBancaria() {
    }

    // Constructor 1

    /**
     * Constructor que inicializa la cuenta bancaria con el CCC completo,
     * NIF/NIE y nombre del titular. El saldo inicial de la cuenta es 0
     * 
     * @param codigoCuentaCliente CCC de 20 dígitos
     * @param nifONieDelTitular   NIF o NIE del titular
     * @param nombreTitularCuenta Nombre completo del titular. Máximo 36 caracteres
     * @throws IllegalArgumentException si los datos no son válidos
     */

    public CuentaBancaria(String codigoCuentaCliente, String nifONieDelTitular, String nombreTitularCuenta) {

        CuentaBancaria.esCuentaValida(codigoCuentaCliente); // validación CCC

        Validar.esValidoNifONie(nifONieDelTitular); // validación NIF o NIE

        Validar.esValidoNombre(nombreTitularCuenta); // validacion nombre

        this.codigoCuentaCliente = codigoCuentaCliente;
        this.nifONieDelTitular = nifONieDelTitular;
        this.nombreTitularCuenta = nombreTitularCuenta;

        System.out.println("\nPROCESO DE CREACIÓN DE NUEVA CUENTA BANCARIA FINALIZADO");
    }

    // Constructor 2

    /**
     * Constructor que inicializa la cuenta bancaria a partir de los componentes
     * del CCC, NIF/NIE y nombre del titular.
     * 
     * @param entidad             Código de la entidad (4 dígitos)
     * @param oficina             Código de la oficina (4 dígitos)
     * @param numeroCuenta        Número de cuenta (10 dígitos)
     * @param nifONieDelTitular   NIF o NIE del titular
     * @param nombreTitularCuenta Nombre completo del titular (máximo 36 caracteres)
     * @throws IllegalArgumentException si los datos no son válidos
     */

    public CuentaBancaria(String entidad, String oficina, String numeroCuenta, String nifONieDelTitular,
            String nombreTitularCuenta) {

        if (entidad == null || !entidad.matches("[0-9]{4}")) { // validación entidad
            throw new IllegalArgumentException("El código de la entidad debe estar compuesto por 4 dígitos");
        }

        if (oficina == null || !oficina.matches("[0-9]{4}")) { // validación oficina
            throw new IllegalArgumentException("El código de la oficina debe estar compuesto por 4 dígitos");
        }

        if (numeroCuenta == null || !numeroCuenta.matches("[0-9]{10}")) { // validación número de cuenta
            throw new IllegalArgumentException("El código del número de cuenta debe estar compuesto por 10 dígitos");
        }

        Validar.esValidoNifONie(nifONieDelTitular); // validación NIF o NIE

        Validar.esValidoNombre(nombreTitularCuenta);

        this.nifONieDelTitular = nifONieDelTitular;
        this.nombreTitularCuenta = nombreTitularCuenta;
        this.codigoCuentaCliente = entidad + oficina + calcularDigitos(entidad, oficina, numeroCuenta) + numeroCuenta; //
    }

    // ==============
    // ====SETTER====
    // ==============

    // CCC

    /**
     * Establece el código de cuenta cliente (CCC).
     * 
     * @param codigoCuentaCliente CCC de 20 dígitos
     * @throws IllegalArgumentException si el CCC no es válido
     */

    public void setCodigoCuentaCliente(String codigoCuentaCliente) {

        CuentaBancaria.esCuentaValida(codigoCuentaCliente);

        this.codigoCuentaCliente = codigoCuentaCliente;
    }

    // NOMBRE

    /**
     * Establece el nombre del titular de la cuenta.
     * 
     * @param nombreTitularCuenta Nombre completo del titular (máximo 36 caracteres)
     * @throws IllegalArgumentException si el nombre no es válido
     */

    public void setNombreTitularCuenta(String nombreTitularCuenta) {

        Validar.esValidoNombre(nombreTitularCuenta);

        this.nombreTitularCuenta = nombreTitularCuenta;
    }

    // NIF o NIE

    /**
     * Establece el NIF o NIE del titular.
     * 
     * @param nifONieDelTitular NIF o NIE válido
     * @throws IllegalArgumentException si el NIF/NIE no es válido
     */

    public void setNifONieDelTitular(String nifONieDelTitular) {

        Validar.esValidoNifONie(nifONieDelTitular); // validación NIF o NIE

        this.nifONieDelTitular = nifONieDelTitular;
    }

    // SALDO

    /**
     * Establece el saldo de la cuenta.
     * 
     * @param saldo Saldo mayor o igual a cero
     * @throws IllegalArgumentException si el saldo es negativo
     */

    public void setSaldo(double saldo) {
        if (saldo < 0) {
            throw new IllegalArgumentException("Error en la introducción del saldo");
        }
        this.saldo = saldo;
    }

    // ==============
    // ====GETTER====
    // ==============

    // CCC

    /**
     * Devuelve el código de cuenta cliente (CCC).
     * 
     * @return CCC de 20 dígitos
     */

    public String getCodigoCuentaCliente() {
        return codigoCuentaCliente;
    }

    // NOMBRE

    /**
     * Devuelve el nombre del titular de la cuenta.
     * 
     * @return nombre del titular
     */

    public String getNombreTitularCuenta() {
        return nombreTitularCuenta;
    }

    // NIF o NIE

    /**
     * Devuelve el NIF o NIE del titular de la cuenta.
     * 
     * @return NIF o NIE
     */

    public String getNifONieDelTitular() {
        return nifONieDelTitular;
    }

    /**
     * Devuelve el saldo actual de la cuenta.
     *
     * @return saldo disponible
     */

    public double getSaldo() {
        return saldo;
    }

    // ======================
    // ===MÉTODOS MOSTRAR====
    // ======================

    /**
     * Devuelve el código de la entidad bancaria (primeros 4 dígitos del CCC).
     * 
     * @return código de la entidad
     */

    public String mostrarEntidad() {
        return codigoCuentaCliente.substring(0, 4);
    }

    // OFICINA

    /**
     * Devuelve el código de la ofifina bancaria (primeros 4 dígitos del CCC).
     * 
     * @return código de la oficina
     */

    public String mostrarOficina() { // posicion 5-8 del CCC
        return codigoCuentaCliente.substring(4, 8);
    }

    // DIGITOS CONTROL

    /**
     * Devuelve los digitos de control de la CCC (primeros 4 dígitos del CCC).
     * 
     * @return digitos de control
     */

    public String mostrarDigitosControl() { // posicion 9-10 del CCC
        return codigoCuentaCliente.substring(8, 10);
    }

    // NUMERO DE CUENTA

    /**
     * Devuelve el número de cuenta de la CCC (primeros 4 dígitos del CCC)
     * 
     * @return número de cuenta
     */

    public String mostrarNumeroCuenta() { // posicion 11-20 del CCC
        return codigoCuentaCliente.substring(10, 20);
    }

    // IBAN

    /**
     * Calcula el IBAN completo a partir de un CCC válido.
     * 
     * @return IBAN completo con código de país y dígitos de control
     */

    public String mostrarIban() {

        String bloque = codigoCuentaCliente + "142800";

        BigInteger numero = new BigInteger(bloque);

        BigInteger resto = numero.mod(BigInteger.valueOf(97)); // Calcular resto

        int digitosIbanInt = 98 - resto.intValue(); // Calcular dígitos IBAN

        String digitosIban;

        // Asegurar que tenga 2 dígitos

        if (digitosIbanInt < 10) {
            digitosIban = "0" + digitosIbanInt;

        } else {
            digitosIban = String.valueOf(digitosIbanInt);
        }
        return ("ES" + digitosIban + codigoCuentaCliente);

    }

    // ================================
    // ==== MÉTODOS DE OPERACIONES ====
    // ================================

    // ==== INGRESAR ====

    /**
     * Ingresa una cantidad en la cuenta.
     * 
     * @param cantidad Cantidad a ingresar (>= 0)
     * @throws IllegalArgumentException si la cantidad es negativa
     */

    public void ingresar(double cantidad) {

        if (cantidad >= 0) {
            saldo += cantidad;
            System.out.printf("Se ha realizado el ingreso de %.2f€ \n", cantidad);

        } else {
            throw new IllegalArgumentException("Error en la cantidad a ingresar");
        }
    }

    // ==== RETIRAR ====

    /**
     * Retira una cantidad de la cuenta.
     * 
     * @param cantidad Cantidad a retirar (>= 0)
     * @throws IllegalArgumentException si la cantidad es negativa
     * @throws IllegalArgumentException si la cantidad es superior al saldo
     *                                  disponible
     */

    public void retirar(double cantidad) {

        if (cantidad < 0) { // validamos cantidad a retirar mayor a 0
            throw new IllegalArgumentException("Error: cantidad a retirar negativa");
        }

        if (cantidad > saldo) { // validamos cantidad a retirar no mayor que saldo
            throw new IllegalArgumentException("Error: saldo insuficiente");
        }

        saldo -= cantidad;
        System.out.printf("Se ha realizado la retirada de %.2f€ \n", cantidad);

    }

    // ======================================
    // ==== CALCULAR DIGITOS CONTROL CCC ====
    // ======================================

    /**
     * Calcula los dígitos de control de un CCC a partir de entidad, oficina
     * y número de cuenta.
     * 
     * @param entidad      Código de la entidad (4 dígitos)
     * @param oficina      Código de la oficina
     * @param numeroCuenta Número de cuenta
     * @return String con los dos dígitos de control calculados
     * @see #calcularDigito(String, int[]) Método auxiliar para calcular cada dígito
     */

    public static String calcularDigitos(String entidad, String oficina, String numeroCuenta) {
        int[] valores = { 1, 2, 4, 8, 5, 10, 9, 7, 3, 6 };

        String bloque1 = "00" + entidad + oficina;
        int digito1 = calcularDigito(bloque1, valores);

        // Segundo dígito: número de cuenta
        int digito2 = calcularDigito(numeroCuenta, valores);

        return String.valueOf(digito1) + String.valueOf(digito2);

    }

    /**
     * Calcula un dígito de control a partir de un bloque de 10 dígitos
     * y un arreglo de valores.
     * 
     * @param bloque  String de 10 dígitos
     * @param valores Valores para multiplicar
     * @return Dígito de control calculado
     */

    private static int calcularDigito(String bloque, int[] valores) {
        int suma = 0;

        for (int i = 0; i < 10; i++) {
            int digito = Character.getNumericValue(bloque.charAt(i));
            suma += digito * valores[i];
        }

        int resto = suma % 11;
        int dc = 11 - resto;

        if (dc == 11)
            return 0;
        if (dc == 10)
            return 1;
        return dc;
    }

    // ========================
    // ==== VALIDACIÓN CCC ====
    // ========================

    /**
     * Valida el CCC de la cuenta (longitud y digitos de control)
     * 
     * @param codigoCuentaCliente CCC a validar
     * @throws IllegalArgumentException si CCC es nulo
     * @throws IllegalArgumentException si CCC no cumple formato
     * @throws IllegalArgumentException si CCC contiene digitos de control erroneos
     * 
     * @see Validar#validarDigitos(String) Método que valida los dígitos de control
     */

    public static void esCuentaValida(String codigoCuentaCliente) {

        if (codigoCuentaCliente == null) {
            throw new IllegalArgumentException("Error: El CCC no puede ser nulo");
        }
        if (!codigoCuentaCliente.matches("\\d{20}")) {
            throw new IllegalArgumentException("Error: El CCC no cumple el formato necesario");
        }
        if (!Validar.validarDigitos(codigoCuentaCliente)) {
            throw new IllegalArgumentException("Error: Digitos de control incorrectos");
        }

    }

}
