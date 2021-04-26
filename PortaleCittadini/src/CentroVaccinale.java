import java.util.ArrayList;

/**
 * Classe per la creazione di un nuovo Centro Vaccinale.
 * @since 26/04/2021
 */

public class CentroVaccinale
{
    public String nome;
    public Indirizzo indirizzo;

    /**
     * enum contenente tutti i possibili tipi di centro vaccinale.
     * contiene al suo interno i seguenti campi: Ospedaliero, Aziendale, Hub.
     *
     * @since 26/04/2021
     */
    public enum Tipologia
    {
        Ospedaliero,
        Aziendale,
        Hub
    }
    public Tipologia tipologia;

    public ArrayList<Short> idVaccinazione;

    /**
     * Costruttore della classe CentroVaccinale
     * @param nome nome del Centro Vaccinale
     * @param indirizzo indirizzo di dove è situato il Centro Vaccinale
     * @param tipologia tipologia del Centro Vaccinale
     * @param idVaccinazione chiave esterna per poter cercare le vaccinazioni effettuate ai clienti registrati al centro
     */
    public CentroVaccinale(String nome, Indirizzo indirizzo,Tipologia tipologia, ArrayList<Short> idVaccinazione)
    {
        this.nome=nome;
        this.indirizzo=indirizzo;
        this.tipologia=tipologia;
        this.idVaccinazione=idVaccinazione;
    }

    /**
     *
     * @return Variabili della classe Centro Vaccinale
     */
    @Override
    public String toString() {
        return "CentroVaccinale{" +
                "nome='" + nome + '\'' +
                ", indirizzo=" + indirizzo +
                ", tipologia=" + tipologia +
                ", idVaccinazione=" + idVaccinazione +
                '}';
    }
}

