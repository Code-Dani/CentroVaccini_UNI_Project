package interfaces;

import classes.CentroVaccinale;
import classes.Evento;
import classes.Severita;
import classes.UtenteVaccinato;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

/**
 * Interfaccia usata per definire quali metodi possono essere usati per la comunicazione con il server RMI.
 * @author Cristian De Nicola
 * @since 14/08/2022
 */

public interface ICittadiniMetodi extends Remote {
    /**
     * Metodo utilizzato per comunicare con il server e richiedere di aggiungere un evento avverso al proprio profilo dopo
     * aver fatto il vaccino
     * @param e prende un valore compreso nell'enum "Evento"
     * @param s prende un valore compreso nell'enum "Severita"
     * @param note note opzionali dell'utente se vuole spiegare meglio il suo evento avverso
     * @param CF codice fiscale dell'utente che deve aggiungere l'evento avverso
     * @param data data in cui si è fatta la vaccinazione
     * @throws RemoteException
     * @author Cavallini Francesco
     * @since 14/08/2022
     */
    String AggiungiEventoAvverso(Evento e, Severita s, String note, String CF, String data) throws RemoteException, SQLException;

    /**
     * Metodo utilizzato per comunicare al server la volontà di un utente di fare un login
     * aver fatto il vaccino
     * @param email email dell'utente che vuole effettuare il login
     * @param psw password dell'utente che vuole effettuare il login
     * @param nomeCentro nome del centro vaccinale al quale si vuole fare il login
     * @throws RemoteException
     * @author Cavallini Francesco
     * @since 14/08/2022
     */
    List<UtenteVaccinato> Login(String email, String psw, String nomeCentro) throws RemoteException, SQLException;

    /**
     * Metodo utilizzato per comunicare al server la volontà di un utente di regitrarsi presso un centro vaccinale
     * aver fatto il vaccino
     * @param nome nome anagrafico dell'utente
     * @param cognome cognome anagrafico dell'utente
     * @param nomeCentro nome centro vaccinale al quale si vuole fare la registrazione
     * @param CF codice fiscale dell'utente che si vuole registrare
     * @param psw password dell'utente che vuole effettuare il login
     * @param mail email dell'utente che vuole effettuare la registrazione
     * @throws RemoteException
     * @author Cavallini Francesco
     * @since 14/08/2022
     */
    String Registrazione(String nome, String cognome, String nomeCentro, String CF, String mail, String psw) throws RemoteException, SQLException;

    /**
     * Metodo utilizzato per scaricare dal server una lilsta di tutti i centri vaccinali
     * @throws RemoteException
     * @author Cavallini Francesco
     * @since 14/08/2022
     */
    List<CentroVaccinale> ScaricaCentri() throws RemoteException, SQLException;

    /**
     * Metodo utilizzato per scaricare dal server una lilsta di tutti gli utenti vaccinati da uno specifico centro vaccinale
     * @param nomeCecntroVaccinale nome centro vaccinale dal quale voglio scaricare tutti i rispettivi vaccinati
     * @throws RemoteException
     * @author Cavallini Francesco
     * @since 14/08/2022
     */
    List<UtenteVaccinato> ScaricaVaccinati(String nomeCecntroVaccinale) throws RemoteException, SQLException;

    /**
     * Metodo utilizzato per scaricare dal server una lilsta di tutti gli utenti vaccinati da uno specifico centro vaccinale
     * @param cf codice fiscale dell'utente del quale si vuole scaricare la lista di vaccinazioni
     * @throws RemoteException
     * @author Cavallini Francesco
     * @since 14/08/2022
     */
    List<String> ScaricaVaccinazioni(String cf) throws RemoteException, SQLException;
}

