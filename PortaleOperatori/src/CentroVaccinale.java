import java.util.LinkedList;

/**
 * Classe che identifica i centri vaccinali e le loro funzioni
 * @since 30/04/2021
 */
public class CentroVaccinale
{
    LinkedList<Short> IDVaccinazioni;
    String nome;
    Indirizzo indirizzo;

    /**
     * enum per definire la tipologia di centro vaccinale (gli enum possono essere richiamati staticamente)
     */
    enum Tipologia { ospedaliero, aziendale, hub };
    Tipologia tipologia;

    /**
     * Metodo costruttore classe centri vaccinali
     * @param nome nome del centro vaccinale
     * @param indirizzo posizione del centro vaccinale (richiede un oggetto Indirizzo)
     * @param tipo scegli un possile tipo (gli enum possono essere richiamati staticamente)
     */
    public CentroVaccinale(String nome, Indirizzo indirizzo, Tipologia tipo)
    {
        IDVaccinazioni = new LinkedList<Short>();
        this.nome = nome;
        this.indirizzo = indirizzo;
        tipologia = tipo;
    }

    @Override
    public String toString() {
        return "CentroVaccinale{" +
                "IDVaccinazioni=" + IDVaccinazioni +
                ", nome='" + nome + '\'' +
                ", indirizzo=" + indirizzo +
                ", tipologia=" + tipologia +
                '}';
    }
}
