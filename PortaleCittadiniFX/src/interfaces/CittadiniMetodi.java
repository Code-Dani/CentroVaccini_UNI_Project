package interfaces;

import Classes.CentroVaccinale;
import Classes.Evento;
import Classes.Severita;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaccia usata per definire quali metodi possono essere usati per la comunicazione con il server RMI.
 * @author Cristian De Nicola
 * @since 14/08/2022
 */

public interface CittadiniMetodi extends Remote {
    void AggiungiEventoAvverso(String e, Double s, short id, String note) throws RemoteException;
    void Login(String e, String psw, String nome) throws RemoteException;
    void Registrazione(String nome, String cognome, String CF, String mail, String psw) throws RemoteException;
    ObservableList<CentroVaccinale> ScaricaCentri() throws RemoteException;
}

