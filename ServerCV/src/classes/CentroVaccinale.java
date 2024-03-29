package classes;


import java.io.Serial;
import java.io.Serializable;


//import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

import java.util.LinkedList;

/**
 * Classe per la creazione di un nuovo Centro Vaccinale.
 * @author Cavallini Francesco
 * @since 26/04/2021
 */
public class CentroVaccinale implements Serializable
{
    @Serial
    static final long serialVersionUID = 42L;


    public String nome;
    public Indirizzo indirizzo;
    public Tipologia tipologia;
    public LinkedList<Short> IDVaccinazioni;

    /*
    public StringProperty nome2;
    public StringProperty indirizzo2;
    public StringProperty tipologia2;
    */

    /**
     * Costruttore della classe CentroVaccinale
     * @param nome nome del Centro Vaccinale
     * @param indirizzo indirizzo di dove è situato il Centro Vaccinale
     * @param tipologia tipologia del Centro Vaccinale
     * @param idVaccinazione chiave esterna per poter cercare le vaccinazioni effettuate ai clienti registrati al centro
     * @author Cavallini Francesco
     * @since 26/04/2021
     */
    public CentroVaccinale(String nome, Indirizzo indirizzo, Tipologia tipologia, LinkedList<Short> idVaccinazione) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.tipologia = tipologia;
        this.IDVaccinazioni = idVaccinazione;

        /*
        nome2 = new SimpleStringProperty((String) nome);
        indirizzo2 = new SimpleStringProperty((String) indirizzo.toString());
        tipologia2 = new SimpleStringProperty((String) tipologia.toString());
         */
    }

    /**
     * Metodo costruttore classe centri vaccinali
     *
     * @param nome      <b>nome del centro vaccinale</b>
     * @param indirizzo <b>posizione del centro vaccinale (richiede un oggetto classes.Indirizzo)</b>
     * @param tipologia <b>scegli un possile tipo (gli enum possono essere richiamati staticamente)</b>
     */
    public CentroVaccinale(String nome, Indirizzo indirizzo, Tipologia tipologia)
    {
        this.IDVaccinazioni = new LinkedList<>();
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.tipologia = tipologia;
    }

    /**
     * Metodo ToString della classe Centro Vaccinale; richiamandolo ritorna le variabili presenti in questa classe
     * @return Variabili della classe Centro Vaccinale
     * @author Cavallini Francesco
     * @since 26/04/2021
     */
    @Override
    public String toString() {
        return "CentroVaccinale{" +
                "nome='" + nome + '\'' +
                ", indirizzo=" + indirizzo +
                ", tipologia=" + tipologia +
                ", idVaccinazione=" + IDVaccinazioni +
                '}';
    }
}
