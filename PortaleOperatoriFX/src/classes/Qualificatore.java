package classes;

import java.io.Serializable;

/**
 * enum contenente tutti i possibili tipi di qualificatore di un classes.Indirizzo.<br/>
 * Contiene al suo interno i seguenti campi: {@link #Via}, {@link #Viale}, {@link #Piazza}, {@link #Corso}.
 * @author Cristian De Nicola
 */
public enum Qualificatore implements Serializable {
    /**
     * Qualificatore via
     */
    Via,
    /**
     * Qualificatore viale
     */
    Viale,
    /**
     * Qualificatore piazza
     */
    Piazza,
    /**
     * Qualifica corso
     */
    Corso;
    private static final long serialVersionUID = 7L;
}
