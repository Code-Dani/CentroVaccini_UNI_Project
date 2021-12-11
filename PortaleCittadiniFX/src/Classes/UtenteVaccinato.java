package Classes;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

/**
 * Classe utilizzata per creare e salvare utenti vaccinati nel database.
 * @since 24/04/2021
 * @author Daniel Satriano
 * @author Claudio Menegotto
 * @author Cavallini Francesco
 */
public class UtenteVaccinato {
    public String nomeCentroVaccinale;
    public String nome;
    public String cognome;
    public String codiceFiscale;
    public String dataSomministrazione;
    public Vaccini vaccino;

    public short idVaccinazione;
    public EventoAvverso evento;

    /**
     * Costruttore della classe UtenteVaccinato
     * @param nomeCentroVaccinale <b>nome del centro vaccinale</b>
     * @param nome <b>nome dell'utente vaccinato</b>
     * @param cognome <b>cognome dell'utente vaccinato</b>
     * @param codiceFiscale <b>codice fiscale dell'utente vaccinato</b>
     * @param dataSomministrazione <b>data somministrazione del vaccino</b>
     * @param vaccino <b>tipo vaccino (AstraZeneca, Moderna, etc)</b>
     * @param idVaccinazione <b>id della vaccinazione</b>
     * @see Vaccini per maggiori informazioni sui tipi di vaccini consentiti
     * @author Daniel Satriano
     */
    public UtenteVaccinato(String nomeCentroVaccinale, String nome, String cognome, String codiceFiscale, String dataSomministrazione, Vaccini vaccino, short idVaccinazione)
    {
        this.nomeCentroVaccinale = nomeCentroVaccinale;
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.dataSomministrazione = dataSomministrazione;
        this.vaccino = vaccino;
        this.idVaccinazione = idVaccinazione;
    }

    /**
     * Costruttore della classe classes.UtenteVaccinato
     * @param nome <b>nome dell'utente vaccinato</b>
     * @param cognome <b>cognome dell'utente vaccinato</b>
     * @param codiceFiscale <b>codice fiscale dell'utente vaccinato</b>
     * @see Vaccini per maggiori informazioni sui tipi di vaccini consentiti
     * @author Cavallini Francesco
     */
    public UtenteVaccinato(String nomeCentro, String nome, String cognome, String codiceFiscale)
    {
        this.nomeCentroVaccinale = nomeCentro;
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.dataSomministrazione = null;
        this.vaccino = null;
    }

    /**
     * metodo ToString della classe Utente vaccinato. Richiamandolo ritorna le variabili presenti in questa classe.
     * @return variabili presenti in questa classe.
     *
     */
    @Override
    public String toString()
    {
        return "classes.UtenteVaccinato{" +
                "nomeCentroVaccinale='" + nomeCentroVaccinale + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", codiceFiscale='" + codiceFiscale + '\'' +
                ", dataSomministrazione=" + dataSomministrazione +
                ", vaccino=" + vaccino +
                ", idVaccinazione=" + idVaccinazione + '\'' +
                ", EventoAvverso=" + evento +
                '}';
    }

    /**
     * Metodo usato per la lettura dell'ID di vaccinazione dell'utente.
     * @return IDvaccinazione dell'utente.
     * @author Cavallini Francesco
     */
    public short getIdVaccinazione()
    {
        return idVaccinazione;
    }

    /**
     * metodo usato per ottenere informazioni riguardanti l'utente e il vaccino che ha fatto e dove.
     * @return una stringa di più string concatenate con tutti i dati dell'utente, del vaccino e dove.
     * @since 08/11/2021
     */
    public String getinformation()
    {
        return nome.toUpperCase(Locale.ROOT).concat(" ").concat(cognome.toUpperCase(Locale.ROOT)).concat(", somministrazione di ").concat(vaccino.toString().toUpperCase(Locale.ROOT)).concat(" nel centro: ").concat(nomeCentroVaccinale.toUpperCase(Locale.ROOT));
    }

    /**
     * metodo utilizzato per la lettura della data di somministrazione del vaccino all'utente.
     * @return la data di somministrazione del vaccino.
     */
    public String getDataSomministrazione(){return dataSomministrazione;}
}