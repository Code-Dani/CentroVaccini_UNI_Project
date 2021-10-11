package classes;

import java.util.Locale;

/**
 * Classe utilizzata per creare e salvare utenti vaccinati in database.
 * @since 24/04/2021
 * @author Daniel Satriano
 * @author Claudio Menegotto
 */
public class UtenteVaccinato {
    String nomeCentroVaccinale;
    String nome;
    String cognome;
    String codiceFiscale;
    String dataSomministrazione;
    Vaccini vaccino;
    short idVaccinazione;

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
        this.idVaccinazione = idVaccinazione;
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
                ", idVaccinazione=" + idVaccinazione +
                '}';
    }

    public short getIdVaccinazione()
    {
        return idVaccinazione;
    }
    public String getinformation()
    {
        return nome.toUpperCase(Locale.ROOT).concat(" ").concat(cognome.toUpperCase(Locale.ROOT)).concat(", somministrazione di ").concat(vaccino.toString().toUpperCase(Locale.ROOT)).concat(" nel centro: ").concat(nomeCentroVaccinale.toUpperCase(Locale.ROOT));
    }

    public String getDataSomministrazione(){return dataSomministrazione;}
}