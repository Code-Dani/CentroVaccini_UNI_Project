package classes;

import interfaces.intOperators;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

/**
 * Classe utility per la connessione al server RMI remoto
 * @author Daniel Satriano
 * @since 6/08/2022
 */
public class DatabaseHelper implements intOperators {

    public static int PORT = 1099;
    public static String ADDRESS = "localhost";
    private final Registry registry;
    private final intOperators skeleton;

    /**
     * Utilizza la porta e l'indirizzo default. {@link #PORT}, {@link #ADDRESS}
     * Inizializza l'oggetto per poter comunicare con il server RMI remoto.
     * Inizializza registry e skeleton
     * @throws RemoteException Il programma non è stato in grado di accedere al registry indicato da porta e address
     * @throws NotBoundException Il programma non è stato in grado di trovare l'oggetto "ServerRMI"
     * @author Daniel Satriano
     * @since 6/08/2022
     */
    public DatabaseHelper() throws RemoteException, NotBoundException {
        registry = LocateRegistry.getRegistry(ADDRESS,PORT);
        skeleton = (intOperators) registry.lookup("operatoriCV");
    }

    /**
     * Inizializza l'oggetto per poter comunicare con il server RMI remoto.
     * Inizializza registry e skeleton
     * @param port porta del server RMI remoto
     * @param address indirizzo al server RMI remoto
     * @throws RemoteException Il programma non è stato in grado di accedere al registry indicato da porta e address
     * @throws NotBoundException Il programma non è stato in grado di trovare l'oggetto "ServerRMI"
     * @author Daniel Satriano
     * @since 6/08/2022
     */
    public DatabaseHelper(int port, String address) throws RemoteException, NotBoundException {
        registry = LocateRegistry.getRegistry(address,port);
        skeleton = (intOperators) registry.lookup("operatoriCV");
    }


    /**
     * Metodo utilizzato per comunicare con il server e richiedere l'aggiunta di un nuovo centro vaccinale
     * @author Daniel Satriano
     * @param nuovoCentro È l'oggetto di tipo {@link CentroVaccinale} che verrà usato per prelevare tutti i dati necessari
     * @since 8/08/2022
     */
    @Override
    public String registraCentroVaccinale(CentroVaccinale nuovoCentro) throws RemoteException {
        //TODO("Implementare Thread?")
        return skeleton.registraCentroVaccinale(nuovoCentro);
    }

    /**
     * Metodo utilizzato per comunicare con il server e richiedere l'aggiunta di un nuovo utente vaccinato nella tabella "Vaccinati_NomeCentroVaccinale"
     * @author Daniel Satriano
     * @param utenteVaccinato E' l'oggetto di tipo {@link UtenteVaccinato} che verrà usato per prelevare tutti i dati necessari
     * @since 8/08/2022
     */
    @Override
    public String registraVaccinato(UtenteVaccinato utenteVaccinato) throws RemoteException {
        //TODO("Implementare Thread?")
        return skeleton.registraVaccinato(utenteVaccinato);
    }

    /**
     * Metodo utilizzato per recuperare tutti i centri vaccinali registrati
     * @author Daniel Satriano
     * @since 22/08/2022
     * @return lista di centri vaccinali List CentroVaccinale
     * @throws RemoteException eccezione sollevata nel caso la connessione con il server RMI non dovesse andare a buon fine
     */
    @Override
    public List<CentroVaccinale> pullCentriVaccinali() throws RemoteException {
        //TODO("Implementare Thread?")
        return skeleton.pullCentriVaccinali();
    }


    /**
     * Metodo utilizzato per recuperare tutti gli utenti vaccinati, utilizzato per lo storico
     * @author Daniel Satriano
     * @since 22/08/2022
     * @return Ritorna una lista di utenti vaccinati
     * @throws RemoteException eccezione sollevata nel caso la connessione con il server RMI non dovesse andare a buon fine
     */
    @Override
    public List<UtenteVaccinato> pullUtentiVaccinati() throws RemoteException {
        //TODO("Implementare Thread?")
        return skeleton.pullUtentiVaccinati();
    }
}
