package interfaces;

import Classes.CentroVaccinale;
import Classes.Evento;
import Classes.Severita;
import Classes.UtenteVaccinato;

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
    void AggiungiEventoAvverso(Evento e, Severita s, String note, String CF, String data) throws RemoteException, SQLException;
    List<UtenteVaccinato> Login(String email, String psw, String nomeCentro) throws RemoteException, SQLException;
    String Registrazione(String nome, String cognome, String nomeCentro, String CF, String mail, String psw) throws RemoteException, SQLException;
    List<CentroVaccinale> ScaricaCentri() throws RemoteException, SQLException;
    List<UtenteVaccinato> ScaricaVaccinati(String nomeCecntroVaccinale) throws RemoteException, SQLException;
    List<String> ScaricaVaccinazioni(String cf) throws RemoteException, SQLException;
}

