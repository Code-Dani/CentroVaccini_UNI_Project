package sample;
import java.util.LinkedList;
/**
 * classe che desrive gli utenti al quale è dedicata l'applicazione memorizzandone le informazioni
 * @since 30/04/2021
 * @author Cavallini Francesco
 */
public class OperatoreVaccinale
{
    LinkedList<CentroVaccinale> centri;

    /**
     * metodo costruttore
     */
    public OperatoreVaccinale()
    {
        centri = new LinkedList<CentroVaccinale>();
    }

    /**
     * metodo per aggiungere centro vaccinale tramite oggetto già istanziato
     * @param centro oggetto centro vaccinale
     */
    public void addCentroVaccinale(CentroVaccinale centro)
    {
        centri.add(centro);
    }

    /**
     * metodo per aggiungere centro vaccinale istanziando oggetto
     * @param nome nome centro
     * @param indirizzo indirizzo centro
     * @param tipo tipo centro
     */
    public void addCentroVaccinale(String nome, Indirizzo indirizzo, CentroVaccinale.Tipologia tipo)
    {
        CentroVaccinale centro = new CentroVaccinale(nome, indirizzo, tipo);
        centri.add(centro);
    }

    @Override
    public String toString() {
        return "OperatoreVaccinale{" +
                "centri=" + centri +
                '}';
    }
}
