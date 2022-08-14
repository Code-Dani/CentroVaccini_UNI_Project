package interfaces;

import Classes.Evento;
import Classes.Severita;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaccia usata per definire quali metodi possono essere usati per la comunicazione con il server RMI.
 * @author Cristian De Nicola
 * @since 14/08/2022
 */

public interface CittadiniMetodi extends Remote {
    void AggiungiEventoAvverso(Evento e, Severita s, short id, String note) throws RemoteException;
}
