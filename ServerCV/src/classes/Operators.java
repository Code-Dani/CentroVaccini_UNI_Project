package classes;

import interfaces.intOperators;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * classe per la connessione remota RMI
 * @author Claudio
 */
public class Operators extends UnicastRemoteObject implements intOperators, Serializable {

    private final PostgressConnection dbConnection;
    public Operators(PostgressConnection connection) throws RemoteException {
        super();
        dbConnection = connection;
    }

    /**
     * metodo per la registrazione e l'inserimento
     * dei nuovi centri vaccinali nel database
     * @author Claudio
     */
    @Override
    public String registraCentroVaccinale(CentroVaccinale centroVaccinale) throws RemoteException {
        try {
            ResultSet result = dbConnection.SQLquery("SELECT nomecentro FROM centrivaccinali WHERE nomecentro = '" + centroVaccinale.nome + "'");
            if (!result.next()) {
                dbConnection.SQLquery("INSERT INTO Indirizzo (qualificatore, nome, numerocivico, comune, provincia, cap) VALUES ('" + centroVaccinale.indirizzo.qualificatore.toString() + "', '" + centroVaccinale.indirizzo.nome + "', " + centroVaccinale.indirizzo.numeroCivico + ", '" + centroVaccinale.indirizzo.comune + "', '" + centroVaccinale.indirizzo.provincia + "', " + centroVaccinale.indirizzo.cap + ")");
                dbConnection.SQLquery("INSERT INTO centrivaccinali (tipologia, nomecentro, indirizzo) SELECT '" + centroVaccinale.tipologia.toString() + "', '" + centroVaccinale.nome + "', MAX(idIndirizzo) FROM indirizzo");
                System.out.println("Centro: " + centroVaccinale.nome + " registrato con successo");
                return "OK";
            }else{
                System.out.println("Centro già esistente");
                return "Centro già presente";
            }

        }catch(SQLException e){e.printStackTrace(); return "Errore nell'inserimento, Riprovare";}
    }

    /**
     *metodo per la registrazione delle vaccinazioni del database
     * nel caso sia la prima verrà registrato anche il vaccinato, altrimenti verrà registrata solo la sua vaccinazione
     * @author Claudio
     */
    @Override
    public String registraVaccinato(UtenteVaccinato utenteVaccinato) throws RemoteException {
        try {
            ResultSet result = dbConnection.SQLquery("SELECT codicefiscale FROM vaccinati WHERE codicefiscale = '" + utenteVaccinato.codiceFiscale + "'");
            if (!result.next()) {
                dbConnection.SQLquery("INSERT INTO vaccinati VALUES ('" + utenteVaccinato.nome + "', '" + utenteVaccinato.cognome + "', '" + utenteVaccinato.codiceFiscale + "')");
            }

            //dbConnection.SQLquery("INSERT INTO Vaccinazione VALUES('" + utenteVaccinato.vaccino.toString() + "', ' ', '" + utenteVaccinato.codiceFiscale + "'), SELECT idcentrivaccinali FROM centrivaccinali WHERE nomecentro = '" + utenteVaccinato.nomeCentroVaccinale + "'");
            dbConnection.SQLquery("INSERT INTO vaccinazione (nomevaccino, datasomministrazione, codicefiscale, idcentrivaccinali) SELECT '" +  utenteVaccinato.vaccino.toString() + "', '" + utenteVaccinato.dataSomministrazione + "', '" + utenteVaccinato.codiceFiscale + "', idcentrivaccinali FROM centrivaccinali WHERE nomecentro = '" + utenteVaccinato.nomeCentroVaccinale + "'");
            return "OK";
        }catch(SQLException e){
            e.printStackTrace();
            return "Errore nell'inserimento, Riprovare";
        }
    }

    /**
     * Metodo utilizzato per recuperare tutti i centri vaccinali registrati
     * @author Claudio
     */
    @Override
    public List<CentroVaccinale> pullCentriVaccinali() throws RemoteException {

        List<CentroVaccinale> centri = new ArrayList<>();

        try {

            ResultSet result = dbConnection.SQLquery("SELECT * FROM centrivaccinali JOIN indirizzo ON centrivaccinali.indirizzo = indirizzo.idindirizzo");

            while (result.next()) {
                centri.add(new CentroVaccinale(result.getString("nomeCentro"), getIndirizzo(result) , Tipologia.Aziendale.fromString(result.getString("tipologia"))));
            }

            return centri;

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Metodo utilizzato per recuperare l'indirizzo del centro vaccinale
     * @author Claudio
     */
    private Indirizzo getIndirizzo(ResultSet result) throws SQLException {
        return new Indirizzo(  Qualificatore.Corso.fromString(result.getString("qualificatore")), result.getString("nome"), result.getInt("numeroCivico"),result.getString("comune"), result.getString("provincia"), result.getInt("cap") );
    }

    /**
     * Metodo utilizzato per recuperare tutti i vaccinati
     * @author Claudio
     */
    @Override
    public List<UtenteVaccinato> pullUtentiVaccinati() throws RemoteException {

        List<UtenteVaccinato> vaccinati = new ArrayList<>();

        try {

            ResultSet result = dbConnection.SQLquery("SELECT * FROM vaccinati NATURAL JOIN vaccinazione NATURAL JOIN centrivaccinali");

            while (result.next()) {
                vaccinati.add(new UtenteVaccinato(result.getString("nomeCentro"), result.getString("nome"), result.getString("cognome"),result.getString("codicefiscale"),result.getTimestamp("datasomministrazione").toString(), Vaccini.valueOf("Pfizer"), (short) result.getInt("idvaccinazioni")));
            }

            return vaccinati;

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
