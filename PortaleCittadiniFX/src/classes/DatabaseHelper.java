package classes;

import interfaces.ICittadiniMetodi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.List;

public class DatabaseHelper implements ICittadiniMetodi {

    private final int PORT = 7272;
    private final String ADDRESS = "localhost";
    private final Registry registry;
    private final ICittadiniMetodi skeleton;

    /**
     * Utilizza la porta e l'indirizzo default. {@link #PORT}, {@link #ADDRESS} <br\>
     * Inizializza l'oggetto per poter comunicare con il server RMI remoto. <br\>
     * Inizializza registry e skeleton
     * @throws RemoteException Il programma non è stato in grado di accedere al registry indicato da porta e address
     * @throws NotBoundException Il programma non è stato in grado di trovare l'oggetto "ServerRMI"
     * @author Cristian De Nicola
     * @since 14/08/2022
     */
    public DatabaseHelper() throws RemoteException, NotBoundException {
        registry = LocateRegistry.getRegistry(ADDRESS,PORT);
        skeleton = (ICittadiniMetodi) registry.lookup("CittadiniServer");
    }

    /**
     * Inizializza l'oggetto per poter comunicare con il server RMI remoto. <br\>
     * Inizializza registry e skeleton
     * @param port porta del server RMI remoto
     * @param address indirizzo al server RMI remoto
     * @throws RemoteException Il programma non è stato in grado di accedere al registry indicato da porta e address
     * @throws NotBoundException Il programma non è stato in grado di trovare l'oggetto "ServerRMI"
     * @author Cristian De Nicola
     * @since 14/08/2022
     */
    DatabaseHelper(int port, String address) throws RemoteException, NotBoundException {
        registry = LocateRegistry.getRegistry(address,port);
        skeleton = (ICittadiniMetodi) registry.lookup("CittadiniServer");
    }

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
    @Override
    public String AggiungiEventoAvverso(Evento e, Severita s, String note, String CF, String data) throws RemoteException, SQLException {
        return skeleton.AggiungiEventoAvverso(e,s,note,CF, data);
    }

    /**
     * Metodo utilizzato per comunicare al server la volontà di un utente di fare un login
     * aver fatto il vaccino
     * @param e email dell'utente che vuole effettuare il login
     * @param psw password dell'utente che vuole effettuare il login
     * @param nome nome del centro vaccinale al quale si vuole fare il login
     * @throws RemoteException
     * @author Cavallini Francesco
     * @since 14/08/2022
     */
    @Override
    public List<UtenteVaccinato> Login(String e, String psw, String nome) throws RemoteException, SQLException {
        return skeleton.Login(e,psw,nome);
    }

    /**
     * Metodo utilizzato per comunicare al server la volontà di un utente di regitrarsi presso un centro vaccinale
     * aver fatto il vaccino
     * @param nome nome anagrafico dell'utente
     * @param cognome cognome anagrafico dell'utente
     * @param NC nome centro vaccinale al quale si vuole fare la registrazione
     * @param CF codice fiscale dell'utente che si vuole registrare
     * @param psw password dell'utente che vuole effettuare il login
     * @param mail email dell'utente che vuole effettuare la registrazione
     * @throws RemoteException
     * @author Cavallini Francesco
     * @since 14/08/2022
     */
    @Override
    public String Registrazione(String nome, String cognome, String NC, String CF, String mail, String psw) throws RemoteException, SQLException {
        return skeleton.Registrazione(nome,cognome,NC,CF,mail,psw);
    }

    /**
     * Metodo utilizzato per scaricare dal server una lilsta di tutti i centri vaccinali
     * @throws RemoteException
     * @author Cavallini Francesco
     * @since 14/08/2022
     */
    @Override
    public List<CentroVaccinale> ScaricaCentri() throws RemoteException, SQLException {
        return skeleton.ScaricaCentri();
    }

    /**
     * Metodo utilizzato per scaricare dal server una lilsta di tutti gli utenti vaccinati da uno specifico centro vaccinale
     * @param nomeCecntroVaccinale nome centro vaccinale dal quale voglio scaricare tutti i rispettivi vaccinati
     * @throws RemoteException
     * @author Cavallini Francesco
     * @since 14/08/2022
     */
    @Override
    public List<UtenteVaccinato> ScaricaVaccinati(String nomeCecntroVaccinale) throws RemoteException, SQLException {
        return skeleton.ScaricaVaccinati(nomeCecntroVaccinale);
    }

    /**
     * Metodo utilizzato per scaricare dal server una lilsta di tutti gli utenti vaccinati da uno specifico centro vaccinale
     * @param CF codice fiscale dell'utente del quale si vuole scaricare la lista di vaccinazioni
     * @throws RemoteException
     * @author Cavallini Francesco
     * @since 14/08/2022
     */
    @Override
    public List<String> ScaricaVaccinazioni(String CF) throws RemoteException, SQLException {
        return skeleton.ScaricaVaccinazioni(CF);
    }

}
