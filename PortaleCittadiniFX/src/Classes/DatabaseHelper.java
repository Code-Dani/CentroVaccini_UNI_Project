package Classes;

import interfaces.CittadiniMetodi;
import javafx.collections.ObservableList;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

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
     * @param e prende un valore compreso nell'enum "Evento"
     * @param s prende un valore compreso nell'enum "Severita"
     * @param note note opzionali dell'utente se vuole spiegare meglio il suo evento avverso
     * @throws RemoteException
     * @author Cristian De Nicola
     * @since 14/08/2022
     */
    @Override
    public void AggiungiEventoAvverso(Evento e, Severita s, String note) throws RemoteException {
        skeleton.AggiungiEventoAvverso(e,s,note);
    }

    @Override
    public UtenteVaccinato Login(String e, String psw, String nome) throws RemoteException {
        return skeleton.Login(e,psw,nome);
    }

    @Override
    public String Registrazione(String nome, String cognome, String NC, String CF, String mail, String psw) throws RemoteException {
        return skeleton.Registrazione(nome,cognome,NC,CF,mail,psw);
    }

    @Override
    public List<CentroVaccinale> ScaricaCentri() throws RemoteException {
        return skeleton.ScaricaCentri();
    }

    @Override
    public List<UtenteVaccinato> ScaricaVaccinati() throws RemoteException {
        return skeleton.ScaricaVaccinati();
    }

}
