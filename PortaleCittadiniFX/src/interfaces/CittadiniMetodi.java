package interfaces;

import Classes.CentroVaccinale;
import Classes.Evento;
import Classes.Severita;
import Classes.UtenteVaccinato;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Interfaccia usata per definire quali metodi possono essere usati per la comunicazione con il server RMI.
 * @author Cristian De Nicola
 * @since 14/08/2022
 */

public interface CittadiniMetodi extends Remote {
    void AggiungiEventoAvverso(Evento e, Severita s, String note) throws RemoteException;
    UtenteVaccinato Login(String e, String psw, String nome) throws RemoteException;
    String Registrazione(String nome, String cognome, String nomeCentro, String CF, String mail, String psw) throws RemoteException;
    List<CentroVaccinale> ScaricaCentri() throws RemoteException;
    List<UtenteVaccinato> ScaricaVaccinati() throws RemoteException;
    List<UtenteVaccinato> ScaricaVaccinazioni(String cf) throws RemoteException;
}

