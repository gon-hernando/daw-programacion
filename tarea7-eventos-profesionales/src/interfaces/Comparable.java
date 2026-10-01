
package interfaces;

import clases.participantes.Participante;

/**
 * Interfaz que define un criterio de comparación entre objetos
 * de tipo {@link Participante}.
 * 
 * Las clases que implementen esta interfaz podrán ser comparadas
 * entre sí, permitiendo su ordenación según un criterio definido.
 * 
 * @author Gonzalo Hernando
 */

public interface Comparable {

    /**
     * Compara el objeto actual con otro participante.
     * 
     * @param p Participante con el que se realiza la comparación.
     * @return un valor negativo si este objeto es menor que {@code p},
     *         cero si son iguales, o un valor positivo si es mayor.
     */

    public int compareTo(Participante p);

}
