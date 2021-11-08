package Classes;

/**classe per la definizione dell'istanza del nuovo utente registrato
 * @author Cavallini Francesco
 * */
public class UtenteCredenziali
{
    public String userID;
    public String indirizzoEmail;
    public String password;
    public short IDVaccinazione;

    /**
     * Costruttore della classe UtenteCredenziali.
     * @param IDVaccinazione short creato autonomamente dal sistema per legare l'utente registrato con il resto del database.
     * @param indirizzoEmail indirizzo mail dell'utente usato in fase di login per accedere al sistema.
     * @param password password scelta dall'utente per accedere al sistema.
     * @author De Nicola Cristian
     */
    public UtenteCredenziali(short IDVaccinazione, String indirizzoEmail, String password)
    {
        this.userID = "12"; ///PLACE HOLDER
        this.IDVaccinazione = IDVaccinazione;
        this.indirizzoEmail = indirizzoEmail;
        this.password = password;
    }
}

