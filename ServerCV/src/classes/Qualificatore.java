package classes;

import java.io.Serializable;

/**
 * enum contenente tutti i possibili tipi di qualificatore di un classes.Indirizzo.<br/>
 * Contiene al suo interno i seguenti campi: {@link #Via}, {@link #Viale}, {@link #Piazza}, {@link #Corso}.
 * @author Cristian De Nicola
 */

public enum Qualificatore implements FromString, Serializable {
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

    @Override
    public Qualificatore fromString(String enumString) {
        switch (enumString){
            case "Via":
                return Via;

            case "Viale":
                return Viale;

            case "Piazza":
                return Piazza;

            case "Corso":
                return Corso;

            default :
                return Via;
        }
    };

    static final long serialVersionUID = 7L;

}
