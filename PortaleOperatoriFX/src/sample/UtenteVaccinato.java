package sample;
import java.time.LocalDate;

/**
 * Classe utilizzata per creare e salvare utenti vaccinati in database.
 * @since 24/04/2021
 * @author Satriano Daniel
 */
public class UtenteVaccinato {
    String nomeCentroVaccinale;
    String nome;
    String cognome;
    String codiceFiscale;
    LocalDate dataSomministrazione;
    Vaccino vaccino;

    /**
     *  enum contenente tutti i possibili vaccini attualmente in circolazione.
     *  Contiene al suo interno i seguenti campi: "Pfizer", "AstraZeneca", "Moderna", "J&J"
     *
     * @since 24/04/2021
     */
    enum Vaccino {
        Pfizer,
        AstraZeneca,
        Moderna,
        JeJ{
            public String toString(){
                return "J&J";
            }
        }
    }
    short idVaccinazione;

    /**
     * Costruttore della classe UtenteVaccinato
     * @param nomeCentroVaccinale nome del centro vaccinale
     * @param nome nome dell'utente vaccinato
     * @param cognome cognome dell'utente vaccinato
     * @param codiceFiscale codice fiscale dell'utente vaccinato
     * @param dataSomministrazione data somministrazione del vaccino
     * @param vaccino tipo vaccino (AstraZeneca, Moderna, etc)
     * @param idVaccinazione id della vaccinazione
     *
     * @see Vaccino per maggiori informazioni sui tipi di vaccini consentiti
     */
    public UtenteVaccinato(String nomeCentroVaccinale, String nome, String cognome, String codiceFiscale, LocalDate dataSomministrazione, Vaccino vaccino, short idVaccinazione){
        this.nomeCentroVaccinale = nomeCentroVaccinale;
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.dataSomministrazione = dataSomministrazione;
        this.vaccino = vaccino;
        this.idVaccinazione = idVaccinazione;
    }


    @Override
    public String toString() {
        return "UtenteVaccinato{" +
                "nomeCentroVaccinale='" + nomeCentroVaccinale + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", codiceFiscale='" + codiceFiscale + '\'' +
                ", dataSomministrazione=" + dataSomministrazione +
                ", vaccino=" + vaccino +
                ", idVaccinazione=" + idVaccinazione +
                '}';
    }
}
