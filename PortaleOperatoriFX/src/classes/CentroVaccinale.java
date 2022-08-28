package classes;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Classe che identifica i centri vaccinali e le loro funzioni
 *
 * @author Cristian De Nicola
 * @since 30 /04/2021
 */
public class CentroVaccinale implements Serializable
{
    private static final long serialVersionUID = 1;
    String nome;
    Indirizzo indirizzo;
    Tipologia tipologia;

    /**
     * Metodo costruttore classe centri vaccinali
     *
     * @param nome      <b>nome del centro vaccinale</b>
     * @param indirizzo <b>posizione del centro vaccinale (richiede un oggetto classes.Indirizzo)</b>
     * @param tipologia <b>scegli un possile tipo (gli enum possono essere richiamati staticamente)</b>
     */
    public CentroVaccinale(String nome, Indirizzo indirizzo, Tipologia tipologia)
    {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.tipologia = tipologia;
    }

    @Override
    public String toString() {
        return "classes.CentroVaccinale{" +
                ", nome='" + nome + '\'' +
                ", indirizzo=" + indirizzo +
                ", tipologia=" + tipologia +
                '}';
    }

    /**
     * @return Ritorna il campo {@link #nome} presente nell'oggetto
     */
    public String getNome(){
        return nome;
    }
}
