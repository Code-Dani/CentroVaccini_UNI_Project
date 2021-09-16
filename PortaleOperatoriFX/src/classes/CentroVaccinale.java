package classes;

import java.util.LinkedList;

/**
 * Classe che identifica i centri vaccinali e le loro funzioni
 * @since 30/04/2021
 * @author Cristian De Nicola
 */
public class CentroVaccinale
{
    LinkedList<Short> IDVaccinazioni;
    String nome;
    Indirizzo indirizzo;
    Tipologia tipologia;

    /**
     * Metodo costruttore classe centri vaccinali
     * @param nome nome del centro vaccinale
     * @param indirizzo posizione del centro vaccinale (richiede un oggetto classes.Indirizzo)
     * @param tipologia scegli un possile tipo (gli enum possono essere richiamati staticamente)
     */
    public CentroVaccinale(String nome, Indirizzo indirizzo, Tipologia tipologia)
    {
        this.IDVaccinazioni = new LinkedList<Short>();
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.tipologia = tipologia;
    }

    @Override
    public String toString() {
        return "classes.CentroVaccinale{" +
                "IDVaccinazioni=" + IDVaccinazioni +
                ", nome='" + nome + '\'' +
                ", indirizzo=" + indirizzo +
                ", tipologia=" + tipologia +
                '}';
    }

    public String getNome(){
        return nome;
    }
}
