package classes;

import java.io.Serializable;

/**
 * enum per definire la tipologia di centro vaccinale (gli enum possono essere richiamati staticamente).
 * @author Cristian De Nicola
 * @since 24 /04/2021
 */
public enum Tipologia implements Serializable {
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

    static final long serialVersionUID = 9L;
}
