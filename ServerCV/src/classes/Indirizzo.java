package classes;

import java.io.Serializable;

/**
 * Classe per la creazione di un Indirizzo<br/>
 * Usata oer riuscire ad localizzare correttamente la posizione geografica del centro vaccinazioni.
 * @since 26/04/2021
 * @author De Nicola Cristian
 */
public class Indirizzo implements Serializable
{
    static final long serialVersionUID = 5L;
    public Qualificatore qualificatore;
    public String nome;

    public String comune;
    public String provincia;
    public int numeroCivico;

    public int cap;

    /**
     * Costruttore della classe classes.Indirizzo.
     *
     * @param qualificatore indica il tipo di indirizzo
     * @param nome          nome della strada a cui fa riferimento l'indirizzo
     * @param numeroCivico  numero civico della strada ove situato il centro
     * @param comune        comune dove è situato il centro
     * @param provincia     provincia dove è situato il centro
     * @param cap           codice di avviamento postale del comune
     * @author Cristian De Nicola
     */
    public Indirizzo(Qualificatore qualificatore, String nome, int numeroCivico, String comune, String provincia, int cap)
    {
        this.qualificatore=qualificatore;
        this.nome=nome;
        this.numeroCivico=numeroCivico;
        this.comune=comune;
        this.provincia=provincia;
        this.cap=cap;
    }

    /**
     * Metodo ToString della classe Indirizzo; richiamandolo ritorna le variabili presenti in questa classe
     *
     * @return variabili della classe classes.Indirizzo
     * @author Cristian De Nicola
     */
    @Override
    public String toString() {
        return "classes.Indirizzo{" +
                "qualificatore=" + qualificatore +
                ", nome='" + nome + '\'' +
                ", numeroCivico=" + numeroCivico +
                ", comune='" + comune + '\'' +
                ", provincia='" + provincia + '\'' +
                ", cap=" + cap +
                '}';
    }

}