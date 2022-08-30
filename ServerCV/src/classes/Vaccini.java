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
public enum Vaccini implements FromString, Serializable {
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
    };

    @Override
    public Vaccini fromString(String enumString) {
        switch (enumString){
            case "Pfizer":
                return Pfizer;

            case "AstraZeneca":
                return AstraZeneca;

            case "Moderna":
                return Moderna;

            case "J&J":
                return JeJ;

            default :
                return Pfizer;
        }

    };

    static final long serialVersionUID = 12L;

}