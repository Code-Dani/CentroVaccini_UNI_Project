package classes;

import interfaces.OperatoriMethods;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Classe utility per la connessione al server RMI remoto
 * @author Daniel Satriano
 * @since 6/08/2022
 */
public class DatabaseHelper implements OperatoriMethods {

    private final int PORT = 8080;
    private final String ADDRESS = "localhost";
    private final Registry registry;
    private final OperatoriMethods skeleton;

    /**
     * Utilizza la porta e l'indirizzo default. {@link #PORT}, {@link #ADDRESS} <br\>
     * Inizializza l'oggetto per poter comunicare con il server RMI remoto. <br\>
     * Inizializza registry e skeleton
     * @throws RemoteException Il programma non è stato in grado di accedere al registry indicato da porta e address
     * @throws NotBoundException Il programma non è stato in grado di trovare l'oggetto "ServerRMI"
     * @author Daniel Satriano
     * @since 6/08/2022
     */
    DatabaseHelper() throws RemoteException, NotBoundException {
        registry = LocateRegistry.getRegistry(ADDRESS,PORT);
        skeleton = (OperatoriMethods) registry.lookup("ServerRMI");
    }

    /**
     * Inizializza l'oggetto per poter comunicare con il server RMI remoto. <br\>
     * Inizializza registry e skeleton
     * @param port porta del server RMI remoto
     * @param address indirizzo al server RMI remoto
     * @throws RemoteException Il programma non è stato in grado di accedere al registry indicato da porta e address
     * @throws NotBoundException Il programma non è stato in grado di trovare l'oggetto "ServerRMI"
     * @author Daniel Satriano
     * @since 6/08/2022
     */
    DatabaseHelper(int port, String address) throws RemoteException, NotBoundException {
        registry = LocateRegistry.getRegistry(address,port);
        skeleton = (OperatoriMethods) registry.lookup("ServerRMI");
    }


    /**
     * Metodo utilizzato per comunicare con il server e richiedere l'aggiunta di un nuovo centro vaccinale
     * @author Daniel Satriano
     * @param nuovoCentro E' l'oggetto di tipo {@link CentroVaccinale} che verrà usato per prelevare tutti i dati necessari
     * @since 8/08/2022
     */
    @Override
    public void registraCentroVaccinale(CentroVaccinale nuovoCentro) throws RemoteException {
        //TODO("Implementare Thread?")
        skeleton.registraCentroVaccinale(nuovoCentro);
    }

    /**
     * Metodo utilizzato per comunicare con il server e richiedere l'aggiunta di un nuovo utente vaccinato nella tabella "Vaccinati_NomeCentroVaccinale"
     * @author Daniel Satriano
     * @param utenteVaccinato E' l'oggetto di tipo {@link UtenteVaccinato} che verrà usato per prelevare tutti i dati necessari
     * @since 8/08/2022
     */
    @Override
    public void registraVaccinato(UtenteVaccinato utenteVaccinato) throws RemoteException {
        //TODO("Implementare Thread?")
        skeleton.registraVaccinato(utenteVaccinato);
    }
}
