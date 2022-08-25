package classes;

import java.io.Serializable;

/**
 * enum contenente tutti i possibili vaccini attualmente in circolazione. <br/>
 * Contiene al suo interno i seguenti campi: {@link #Pfizer}, {@link #AstraZeneca}, {@link #Moderna}, {@link #JeJ}
 *
 * @author Daniel Satriano
 * @author Claudio Menegotto
 * @since 24/04/2021
 */
public enum Vaccini implements Serializable {
    /**
     * Vaccino Pfizer
     */
    Pfizer,
    /**
     * Vaccino AstraZeneca
     */
    AstraZeneca,
    /**
     * Vaccino Moderna
     */
    Moderna,
    /**
     * Vaccino JeJ
     */
    JeJ{
        public String toString(){
            return "J&J";
        }
    }
}