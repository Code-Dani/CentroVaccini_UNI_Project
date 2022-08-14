package interfaces;

import classes.CentroVaccinale;
import classes.UtenteVaccinato;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaccia usata per definire quali metodi possono essere usati per la comunicazione con il server RMI.
 * @author Daniel Satriano
 * @since 6/08/2022
 */
public interface OperatoriMethods extends Remote {

    void registraCentroVaccinale(CentroVaccinale nuovoCentro) throws RemoteException;

    void registraVaccinato(UtenteVaccinato utenteVaccinato) throws RemoteException;

}