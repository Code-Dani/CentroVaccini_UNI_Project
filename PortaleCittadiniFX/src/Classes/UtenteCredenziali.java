package Classes;

/**classe per la definizione dell'utente registrato
 * @author Cavallini Francesco
 * */
public class UtenteCredenziali
{
    public String userID;
    public String indirizzoEmail;
    public String password;

    public UtenteCredenziali(String a, String b, String c)
    {
        userID = a;
        indirizzoEmail = b;
        password = c;
    }
}

