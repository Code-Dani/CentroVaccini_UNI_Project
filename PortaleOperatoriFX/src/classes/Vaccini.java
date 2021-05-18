package classes;

/**
 *  enum contenente tutti i possibili vaccini attualmente in circolazione. <br/>
 *  Contiene al suo interno i seguenti campi: "Pfizer", "AstraZeneca", "Moderna", "J&J"
 * @since 24/04/2021
 * @Author Satriano Daniel
 * @Author Menegotto Claudio
 */
public enum Vaccini {
    Pfizer,
    AstraZeneca,
    Moderna,
    JeJ{
        public String toString(){
            return "J&J";
        }
    }
}