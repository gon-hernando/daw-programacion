package empresa.rrhh;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Clase que representa un empleado de la empresa.
 * Gestiona la informaciÃ³n personal, salarial y estado laboral del empleado.
 * 
 * @author Sistema de RRHH
 * @version 1.0
 */
class Empleado {
    private final int codigo;
    private String nombre;
    private String apellido;
    private double salarioBase;
    private final LocalDate fechaContratacion;
    private boolean activo;
    private LocalDate fechaBaja;

    /**
     * Constructor que crea un empleado con validaciones.
     * 
     * @param codigo      Código Ãºnico del empleado
     * @param nombre      Nombre del empleado
     * @param apellido    Apellido del empleado
     * @param salarioBase Salario base mensual en euros
     * @throws IllegalArgumentException si algÃºn parÃ¡metro no es vÃ¡lido
     */
    public Empleado(int codigo, String nombre, String apellido, double salarioBase) {
        // Validar código
        if (codigo <= 0) {
            throw new IllegalArgumentException("El código debe ser positivo");
        }

        // Validar nombre
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vací­o");
        }

        // Validar apellido
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        }

        // Validar salario base
        if (salarioBase <= 0) {
            throw new IllegalArgumentException("El salario base debe ser mayor que 0");
        }

        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.salarioBase = salarioBase;
        this.fechaContratacion = LocalDate.now();
        this.activo = true;
        this.fechaBaja = null;
    }

    /**
     * Da de baja al empleado y registra la fecha.
     * 
     * @throws IllegalStateException si el empleado ya estÃ¡ dado de baja
     */
    public void darDeBaja() {
        if (!activo) {
            throw new IllegalStateException("El empleado ya está dado de baja");
        }
        this.activo = false;
        this.fechaBaja = LocalDate.now();
    }

    /**
     * Calcula la antigÃ¼edad del empleado en aÃ±os completos.
     * Si está de baja, calcula hasta la fecha de baja.
     * Si está activo, calcula hasta hoy.
     * 
     * @return número de aÃ±os completos de antigÃ¼edad
     */
    public long calcularAntiguedad() {
        LocalDate fechaFin;
        if (activo) {
            fechaFin = LocalDate.now();
        } else {
            fechaFin = fechaBaja;
        }
        return ChronoUnit.YEARS.between(fechaContratacion, fechaFin);
    }

    /**
     * Calcula el salario total incluyendo el bono por antigÃ¼edad.
     * Bono: 100â‚¬ por cada aÃ±o de antigÃ¼edad.
     * 
     * @return salario base mÃ¡s bono por antigÃ¼edad
     */
    public double calcularSalarioConBono() {
        long antiguedad = calcularAntiguedad();
        double bono = antiguedad * 100.0;
        return salarioBase + bono;
    }

    /**
     * Valida si un salario estÃ¡ dentro del rango permitido.
     * 
     * @param salario Salario a validar
     * @return true si estÃ¡ entre 1000â‚¬ y 10000â‚¬, false en caso contrario
     */
    public static boolean validarSalario(double salario) {
        return salario >= 1000.0 && salario <= 10000.0;
    }

    /**
     * Obtiene el cÃ³digo del empleado.
     * 
     * @return cÃ³digo del empleado
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Obtiene el nombre del empleado.
     * 
     * @return nombre del empleado
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el apellido del empleado.
     * 
     * @return apellido del empleado
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Obtiene el salario base del empleado.
     * 
     * @return salario base
     */
    public double getSalarioBase() {
        return salarioBase;
    }

    /**
     * Verifica si el empleado está activo.
     * 
     * @return true si está activo, false si está de baja
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * Representación textual del empleado.
     * 
     * @return String formateado con la información del empleado
     */
    @Override // sobrescribiendo (reemplazando) un método de la clase padre o de una interfaz
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        sb.append("[").append(codigo).append("] ");
        sb.append(nombre).append(" ").append(apellido).append("\n");

        sb.append("   Salario base: ").append(String.format("%.2f€", salarioBase));
        sb.append(" | Antigüedad: ").append(calcularAntiguedad()).append(" año");
        if (calcularAntiguedad() != 1) {
            sb.append("s");
        }
        sb.append("\n");

        if (activo) {
            sb.append("   Estado: Activo");
            sb.append(" | Contratado: ").append(fechaContratacion.format(formato));
        } else {
            sb.append("   Estado: Baja desde ").append(fechaBaja.format(formato));
        }

        return sb.toString();
    }

    /**
     * Compara dos empleados por su código.
     * 
     * @param obj Objeto a comparar
     * @return true si tienen el mismo código, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Empleado empleado = (Empleado) obj;
        return codigo == empleado.codigo;
    }
}