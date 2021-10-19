package Classes;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

/**
 * Classe utilizzata per creare e salvare utenti vaccinati in database.
 * @since 24/04/2021
 * @author Daniel Satriano
 * @author Claudio Menegotto
 */
public class UtenteVaccinato {
    public String nomeCentroVaccinale;
    public String nome;
    public String cognome;
    public String codiceFiscale;
    public String dataSomministrazione;
    public Vaccini vaccino;

    //va tolto
    public String IDUser;
    //


    public short IDVaccinazione;
    public EventoAvverso evento;

    /**
     * Costruttore della classe classes.UtenteVaccinato
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
    public UtenteVaccinato(String nomeCentroVaccinale, String nome, String cognome, String codiceFiscale, String dataSomministrazione, Vaccini vaccino, short idVaccinazione){
        this.nomeCentroVaccinale = nomeCentroVaccinale;
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.dataSomministrazione = dataSomministrazione;
        this.vaccino = vaccino;
        this.IDVaccinazione = idVaccinazione;

        this.IDUser = nome.charAt(0) + cognome.charAt(0) + "12" ;
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

        IDUser = nome.charAt(0) + cognome.charAt(0) + new Random(100).toString();
    }


    @Override
    public String toString() {
        return "classes.UtenteVaccinato{" +
                "nomeCentroVaccinale='" + nomeCentroVaccinale + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", codiceFiscale='" + codiceFiscale + '\'' +
                ", dataSomministrazione=" + dataSomministrazione +
                ", vaccino=" + vaccino +
                ", idVaccinazione=" + IDVaccinazione +
                '}';
    }

    public short getIdVaccinazione()
    {
        return IDVaccinazione;
    }
    public String getinformation()
    {
        return nome.toUpperCase(Locale.ROOT).concat(" ").concat(cognome.toUpperCase(Locale.ROOT)).concat(", somministrazione di ").concat(vaccino.toString().toUpperCase(Locale.ROOT)).concat(" nel centro: ").concat(nomeCentroVaccinale.toUpperCase(Locale.ROOT));
    }

    public String getDataSomministrazione(){return dataSomministrazione;}
}