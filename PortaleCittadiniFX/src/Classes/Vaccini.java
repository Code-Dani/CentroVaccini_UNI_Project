package Classes;

/**
 * Classe contentente enum per la gestione dei tipi di vaccino disponibili per la somministrazione.
 * contiene: Pfrizer, AstraZeneca, Moderna, JeJ.
 * @author De Nicola Cristian
 */
public enum Vaccini
{
    Pfizer,
    AstraZeneca,
    Moderna,
    JeJ {
        public String toString(){
            return "J&J";
        }
    }
}

