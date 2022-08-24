package interfaces;

import classes.CentroVaccinale;
import classes.UtenteVaccinato;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Interfaccia usata per definire quali metodi possono essere usati per la comunicazione con il server RMI.
 * @author Daniel Satriano
 * @since 6/08/2022
 */
public interface OperatoriMethods extends Remote {

    /**
     * Metodo utilizzato per la registrazione di un nuovo centro vaccinale
     * @author Daniel Satriano
     * @since 8/08/2022
     * @param nuovoCentro il nuovo centro di tipo {@link CentroVaccinale} da salvare sul database
     * @throws RemoteException eccezione sollevata nel caso la connessione con il server RMI non dovesse andare a buon fine
     */
    void registraCentroVaccinale(CentroVaccinale nuovoCentro) throws RemoteException;

    /**
     * Metodo utilizzato per la registrazione di un nuovo utente vaccinato
     * @author Daniel Satriano
     * @since 8/08/2022
     * @param utenteVaccinato il nuovo utente vaccinato di tipo {@link UtenteVaccinato} da salvare sul database
     * @throws RemoteException eccezione sollevata nel caso la connessione con il server RMI non dovesse andare a buon fine
     */
    void registraVaccinato(UtenteVaccinato utenteVaccinato) throws RemoteException;

    /**
     * Metodo utilizzato per recuperare tutti i centri vaccinali registrati
     * @author Daniel Satriano
     * @since 22/08/2022
     * @return lista di centri vaccinali List<CentroVaccinale>
     * @throws RemoteException eccezione sollevata nel caso la connessione con il server RMI non dovesse andare a buon fine
     */
    List<CentroVaccinale> pullCentriVaccinali() throws RemoteException;

    /**
     * Metodo utilizzato per recuperare tutti gli utenti vaccinati, utilizzato per lo storico
     * @author Daniel Satriano
     * @since 22/08/2022
     * @return Ritorna una lista di utenti vaccinati
     * @throws RemoteException eccezione sollevata nel caso la connessione con il server RMI non dovesse andare a buon fine
     */
    List<UtenteVaccinato> pullUtentiVaccinati() throws RemoteException;

}
