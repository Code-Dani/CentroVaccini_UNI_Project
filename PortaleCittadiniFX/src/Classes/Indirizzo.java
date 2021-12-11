package Classes;

/**
 * Classe per la creazione di un Indirizzo<br/>
 * Usata oer riuscire ad localizzare correttamente la posizione geografica del centro vaccinazioni.
 * @since 26/04/2021
 * @author De Nicola Cristian
 */
public class Indirizzo
{
    public Qualificatore qualificatore;
    public String nome;
    public String comune;
    public String provincia;
    public int numeroCivico;
    public int cap;

    /**
     * Costruttore della classe Indirizzo.
     * @param qualificatore indica il tipo di indirizzo
     * @param nome nome della strada a cui fa riferimento l'indirizzo
     * @param numeroCivico numero civico della strada ove situato il centro
     * @param comune comune dove è situato il centro
     * @param provincia provincia dove è situato il centro
     * @param cap codice di avviamento postale del comune
     * @author De Nicola Cristian
     * @since 08/11/2021
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
     * @return Variabili della classe Indirizzo.
     * @author De Nicola Cristian
     * @since 26/04/2021
     */
    @Override
    public String toString() {
        return  comune +"(" +provincia +"), "
                + qualificatore + " "
                + nome + ", \'"
                + numeroCivico
                +"\', cap:" + cap;
    }
}

