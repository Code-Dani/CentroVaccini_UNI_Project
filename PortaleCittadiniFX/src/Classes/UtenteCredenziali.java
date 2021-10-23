package Classes;

/**classe per la definizione dell'utente registrato
 * @author Cavallini Francesco
 * */
public class UtenteCredenziali
{
    public String userID;
    public String indirizzoEmail;
    public String password;
    public short IDVaccinazione;

    public UtenteCredenziali(short IDVaccinazione, String indirizzoEmail, String password)
    {
        this.userID = "12"; ///PLACE HOLDER
        this.IDVaccinazione = IDVaccinazione;
        this.indirizzoEmail = indirizzoEmail;
        this.password = password;
    }
}

