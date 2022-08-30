package classes;

import java.io.Serializable;

/**
 * enum per definire la tipologia di centro vaccinale (gli enum possono essere richiamati staticamente).
 * @author Cristian De Nicola
 * @since 24 /04/2021
 */

public enum Tipologia implements FromString, Serializable {

    /**
     * Tipologia aziendale
     */
    Aziendale,
    /**
     * Tipologia ospedaliero
     */
    Ospedaliero,
    /**
     * Tipologia hub
     */
    Hub;

    @Override
    public Tipologia fromString(String enumString) {
        switch (enumString){
            case "Aziendale":
                return Aziendale;

            case "Ospedaliero":
                return Ospedaliero;

            case "Hub":
                return Hub;

            default :
                return Ospedaliero;
        }
    };

    static final long serialVersionUID = 9L;
}

interface FromString {
    abstract Object fromString(String enumString);
}
