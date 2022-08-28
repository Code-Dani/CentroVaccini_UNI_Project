package classes;

import java.io.Serializable;
import java.util.Locale;

/**
 * Classe utilizzata per creare e salvare utenti vaccinati in database.
 *
 * @author Daniel Satriano
 * @author Claudio Menegotto
 * @since 24 /04/2021
 */
public class UtenteVaccinato implements Serializable {

    private static final long serialVersionUID = 1;
    String nomeCentroVaccinale;
    String nome;
    String cognome;
    String codiceFiscale;
    String dataSomministrazione;
    Vaccini vaccino;
    short idVaccinazione;
    EventoAvverso evento;

    /**
     * Costruttore della classe classes.UtenteVaccinato
     *
     * @param nomeCentroVaccinale  <b>nome del centro vaccinale</b>
     * @param nome                 <b>nome dell'utente vaccinato</b>
     * @param cognome              <b>cognome dell'utente vaccinato</b>
     * @param codiceFiscale        <b>codice fiscale dell'utente vaccinato</b>
     * @param dataSomministrazione <b>data somministrazione del vaccino</b>
     * @param vaccino              <b>tipo vaccino (AstraZeneca, Moderna, etc)</b>
     * @param idVaccinazione       <b>id della vaccinazione</b>
     * @author Daniel Satriano
     * @see Vaccini maggiori informazioni sui tipi di vaccini consentiti
     */
    public UtenteVaccinato(String nomeCentroVaccinale, String nome, String cognome, String codiceFiscale, String dataSomministrazione, Vaccini vaccino, short idVaccinazione){
        this.nomeCentroVaccinale = nomeCentroVaccinale;
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.dataSomministrazione = dataSomministrazione;
        this.vaccino = vaccino;
        this.idVaccinazione = idVaccinazione;
        this.evento = null;
    }

    /**
     * metodo toString per la classe.
     * @return Restituisce in un formato leggibile una stringa con tutti i campi dell'oggetto a cui si è fatto il toString
     */
    @Override
    public String toString() {
        return "classes.UtenteVaccinato{" +
                "nomeCentroVaccinale='" + nomeCentroVaccinale + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", codiceFiscale='" + codiceFiscale + '\'' +
                ", dataSomministrazione=" + dataSomministrazione +
                ", vaccino=" + vaccino +
                ", idVaccinazione=" + idVaccinazione +
                '}';
    }

    /**
     * @return Ritorna il compo {@link #idVaccinazione} dell'oggetto
     */
    public short getIdVaccinazione()
    {
        return idVaccinazione;
    }

    /**
     * @return ritorna una stringa, questo metodo è usato dallo storico
     */
    public String getinformation()
    {
        return nome.toUpperCase(Locale.ROOT).concat(" ").concat(cognome.toUpperCase(Locale.ROOT)).concat(", somministrazione di ").concat(vaccino.toString().toUpperCase(Locale.ROOT)).concat(" nel centro: ").concat(nomeCentroVaccinale.toUpperCase(Locale.ROOT));
    }

    /**
     * @return Ritorna il compo {@link #dataSomministrazione} dell'oggetto
     */
    public String getDataSomministrazione(){return dataSomministrazione;}
}