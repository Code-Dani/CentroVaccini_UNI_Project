import java.time.LocalDate;

/**
 * Classe utilizzata per creare e salvare utenti vaccinati in database.
 * @since 24/04/2021
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
     * @param nomeCentroVaccinale
     * @param nome
     * @param cognome
     * @param codiceFiscale
     * @param dataSomministrazione
     * @param vaccino
     * @param idVaccinazione
     *
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
