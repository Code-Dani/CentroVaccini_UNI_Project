package Classes;

import interfaces.CittadiniMetodi;
import javafx.collections.ObservableList;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DatabaseHelper implements CittadiniMetodi {

    private final int PORT = 8080;
    private final String ADDRESS = "localhost";
    private final Registry registry;
    private final CittadiniMetodi skeleton;

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
        skeleton = (CittadiniMetodi) registry.lookup("ServerRMI");
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
        skeleton = (CittadiniMetodi) registry.lookup("ServerRMI");
    }

    /**
     * Metodo utilizzato per comunicare con il server e richiedere di aggiungere un evento avverso al proprio profilo dopo
     * aver fatto il vaccino
     * @param evento prende un valore compreso nell'enum "Evento"
     * @param severita prende un valore compreso nell'enum "Severita"
     * @param id usato come chiave esterna per poter collegare l'evento avverso al profilo giusto
     * @param note note opzionali dell'utente se vuole spiegare meglio il suo evento avverso
     * @throws RemoteException
     * @author Cristian De Nicola
     * @since 14/08/2022
     */
    @Override
    public void AggiungiEventoAvverso(String evento, Double severita, short id, String note) throws RemoteException {
        skeleton.AggiungiEventoAvverso(evento,severita,id,note);
    }

    @Override
    public void Login(String e, String psw, String nome) throws RemoteException {
        skeleton.Login(e,psw,nome);
    }

    @Override
    public void Registrazione(String nome, String cognome, String CF, String mail, String psw) throws RemoteException {
        skeleton.Registrazione(nome,cognome,CF,mail,psw);
    }

    @Override
    public ObservableList<CentroVaccinale> ScaricaCentri() throws RemoteException {
        return skeleton.ScaricaCentri();
    }

}
