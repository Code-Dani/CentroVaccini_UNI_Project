package classes;

import interfaces.ICittadiniMetodi;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CittadiniServer extends UnicastRemoteObject implements ICittadiniMetodi {

    PostgressConnection dbConnettion;//variabile usata per fare le query //dbConnettion.SQLquery("");
    public CittadiniServer(PostgressConnection x) throws RemoteException {
        super();
        dbConnettion = x;
    }


    /**
     * Metodo utilizzato per comunicare con il server e richiedere di aggiungere un evento avverso al proprio profilo dopo
     * aver fatto il vaccino
     * @param e prende un valore compreso nell'enum "Evento"
     * @param s prende un valore compreso nell'enum "Severita"
     * @param note note opzionali dell'utente se vuole spiegare meglio il suo evento avverso
     * @param CF codice fiscale dell'utente che deve aggiungere l'evento avverso
     * @param dataVacc data in cui si è fatta la vaccinazione
     * @throws RemoteException
     * @author Cavallini Francesco
     * @since 14/08/2022
     */
    @Override
    public String AggiungiEventoAvverso(Evento e, Severita s, String note, String CF, String dataVacc) throws RemoteException {
        ResultSet result =  dbConnettion.SQLquery("SELECT idVaccinazioni FROM Vaccinazione WHERE codiceFiscale = \'"+CF+"\' AND dataSomministrazione=\'"+dataVacc+"\'");
        try{
            result.next();
            int id = result.getInt("idVaccinazioni");
            dbConnettion.SQLquery("insert into EventiAvversi(severita, evento, noteopzionali, idvaccinazioni) values(\'"+s.toString()+"\', \'"+e.toString()+"\', "+"\'"+note+"\',"+id+")");
            return "Aggiunta effetuata con successo";
        }catch (Exception E){
            return "Errore aggiunta evento avverso";
        }
    }

    /**
     * Metodo utilizzato per comunicare al server la volontà di un utente di fare un login
     * aver fatto il vaccino
     * @param email email dell'utente che vuole effettuare il login
     * @param psw password dell'utente che vuole effettuare il login
     * @param nomeCentro nome del centro vaccinale al quale si vuole fare il login
     * @throws RemoteException
     * @author Cavallini Francesco
     * @since 14/08/2022
     */
    @Override
    public List<UtenteVaccinato> Login(String email, String psw, String nomeCentro) throws RemoteException {

        try {
            List<UtenteVaccinato> vaccinati = new ArrayList();

            //System.out.println("sono entrato");

            //prendo id CENTRO
            ResultSet result = dbConnettion.SQLquery("SELECT idCentriVaccinali FROM CentriVaccinali WHERE nomeCentro = '" + nomeCentro + "'");
            result.next();
            int idCentro = result.getInt("idCentriVaccinali");

            //System.out.println("sono entrato2");

            //tentativo di correggere
            result = dbConnettion.SQLquery("SELECT codiceFiscale, nome, cognome, email, password, dataSomministrazione, nomeVaccino, idVaccinazioni, severita, evento, noteOpzionali \n" +
                            "FROM Vaccinati \n" +
                            "NATURAL FULL OUTER JOIN Vaccinazione \n" +
                            "NATURAL FULL OUTER JOIN EventiAvversi \n" +
                            "NATURAL FULL OUTER JOIN cittadiniRegistrati \n" +
                            "WHERE idCentriVaccinali = " +idCentro+ " \n" +
                            "AND email = '"+email+"'\n" +
                            "AND password = '"+psw+"' \n"
                    );

            while (result.next()) {
                String nome = result.getString("nome");
                String cognome = result.getString("cognome");
                String CF = result.getString("codiceFiscale");

                //System.out.println("sono entrato3");

                String dataVacc = result.getString("dataSomministrazione");
                Vaccini vacc = Vaccini.Moderna.fromString(result.getString("nomeVaccino"));
                short idVacc = result.getShort("idVaccinazioni");

                EventoAvverso event = null;
                if(result.getString("evento") != null){
                    String noteOPZ = result.getString("noteOpzionali");
                    Severita severita = Severita.bassa_2.fromString(result.getString("severita"));
                    Evento evento = Evento.febbre.fromString(result.getString("evento"));
                    event = new EventoAvverso(evento, severita, noteOPZ, idVacc);
                }

                vaccinati.add(new UtenteVaccinato(nomeCentro, nome, cognome, CF, dataVacc, vacc, idVacc, event));
            }

            return vaccinati;
        }catch (Exception E){
            E.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo utilizzato per comunicare al server la volontà di un utente di regitrarsi presso un centro vaccinale
     * aver fatto il vaccino
     * @param nome nome anagrafico dell'utente
     * @param cognome cognome anagrafico dell'utente
     * @param nomeCentro nome centro vaccinale al quale si vuole fare la registrazione
     * @param CF codice fiscale dell'utente che si vuole registrare
     * @param psw password dell'utente che vuole effettuare il login
     * @param mail email dell'utente che vuole effettuare la registrazione
     * @throws RemoteException
     * @author Cavallini Francesco
     * @since 14/08/2022
     */
    @Override
    public String Registrazione(String nome, String cognome, String nomeCentro, String CF, String mail, String psw) throws RemoteException, SQLException {
        try {
            ResultSet result =  dbConnettion.SQLquery("SELECT idCentriVaccinali FROM CentriVaccinali WHERE nomeCentro = '"+nomeCentro+"'");
            result.next();
            int codiceCentro = result.getInt("idCentriVaccinali");

            //System.out.println("entrato 1");

            //CONTROLLO SE è REGISTRATO IN UN ALTRO CENTRO
            result =  dbConnettion.SQLquery("SELECT * FROM CittadiniRegistrati where idCentriVaccinali = "+codiceCentro+" AND codiceFiscale = " + "'"+CF+"'");
            if(result.next()){
                return "ERRORE: Utente già registrato";
            }

            //CONTROLLO SE NOME E COGNOME INSERITI RISPONDONO ALLO STESSO CF
            result =  dbConnettion.SQLquery("SELECT nome, cognome FROM vaccinati WHERE codiceFiscale = '"+CF+"'");
            result.next();
            String nomeTMP = result.getString("nome");
            String cognomeTMP = result.getString("cognome");
            boolean checkNome = false;
            //System.out.println("entrato 2");
            if(nomeTMP.equals(nome) && cognomeTMP.equals(cognome)){
                checkNome = true;
            }
            if(!checkNome){
                return  "ERRORE: il codice fiscale inserito non corrisponde con nome e cognome";
            }
            //se ho passato entrambi i check vuol dire che posso essere iscritto
            dbConnettion.SQLquery("INSERT INTO cittadiniregistrati (email,password,codicefiscale,idcentrivaccinali) values('"+mail+"'"+", "+"'"+psw+"'"+" , "+"'"+CF+"'"+" , "+codiceCentro+")");
            //System.out.println("entrato 3");

            return "Registrazione completata";
        }catch (Exception e) {
            return "Errore: unkown";
        }
    }


    /**
     * Metodo utilizzato per scaricare dal server una lilsta di tutti i centri vaccinali
     * @throws RemoteException
     * @author Cavallini Francesco
     * @since 14/08/2022
     */
    @Override
    public List<CentroVaccinale> ScaricaCentri() throws RemoteException, SQLException {
        try {
            ResultSet result = dbConnettion.SQLquery("SELECT tipologia, nomeCentro, qualificatore, nome, numeroCivico, comune, provincia, cap FROM CentriVaccinali JOIN Indirizzo on Indirizzo.idIndirizzo = CentriVaccinali.indirizzo");
            List<CentroVaccinale> x = new ArrayList();
            while (result.next()) {
                Indirizzo ind = new Indirizzo(  Qualificatore.Corso.fromString(result.getString("qualificatore")), result.getString("nome"), result.getInt("numeroCivico") , result.getString("comune"),result.getString("provincia"),result.getInt("cap"));
                x.add(new CentroVaccinale(result.getString("nomeCentro"),ind, Tipologia.Hub.fromString(result.getString("qualificatore"))));
            }
            return x;
        }catch (Exception E){
            return null;
        }
    }

    /**
     * Metodo utilizzato per scaricare dal server una lilsta di tutti gli utenti vaccinati da uno specifico centro vaccinale
     * @param nomeCentro nome centro vaccinale dal quale voglio scaricare tutti i rispettivi vaccinati
     * @throws RemoteException
     * @author Cavallini Francesco
     * @since 14/08/2022
     */
    @Override
    public List<UtenteVaccinato> ScaricaVaccinati(String nomeCentro) throws RemoteException, SQLException {
        try {
            List<UtenteVaccinato> vaccinati = new ArrayList();

            //System.out.println("sono entrato");

            //prendo id CENTRO
            ResultSet result = dbConnettion.SQLquery("SELECT idCentriVaccinali FROM CentriVaccinali WHERE nomeCentro = '" + nomeCentro + "'");
            result.next();
            int idCentro = result.getInt("idCentriVaccinali");

            //System.out.println("sono entrato2");

            //tentativo di correggere
            result = dbConnettion.SQLquery("SELECT codiceFiscale, nome, cognome, dataSomministrazione, nomeVaccino, idVaccinazioni, severita, evento, noteOpzionali \n" +
                    "FROM Vaccinati \n" +
                    "NATURAL FULL OUTER JOIN Vaccinazione \n" +
                    "NATURAL FULL OUTER JOIN EventiAvversi \n" +
                    "WHERE idCentriVaccinali = " + idCentro);
            while (result.next()) {
                String nome = result.getString("nome");
                String cognome = result.getString("cognome");
                String CF = result.getString("codiceFiscale");

                //System.out.println("sono entrato3");

                String dataVacc = result.getString("dataSomministrazione");
                Vaccini vacc = Vaccini.Moderna.fromString(result.getString("nomeVaccino"));
                short idVacc = result.getShort("idVaccinazioni");

                EventoAvverso event = null;
                if(result.getString("evento") != null) {
                    String noteOPZ = result.getString("noteOpzionali");
                    Severita severita = Severita.bassa_2.fromString(result.getString("severita"));
                    Evento evento = Evento.febbre.fromString(result.getString("evento"));
                    event = new EventoAvverso(evento, severita, noteOPZ, idVacc);
                }

                vaccinati.add(new UtenteVaccinato(nomeCentro, nome, cognome, CF, dataVacc, vacc, idVacc, event));
            }

            return vaccinati;
        }catch (Exception E){
            E.printStackTrace();
            return null;
        }
    }


    /**
     * Metodo utilizzato per scaricare dal server una lilsta di tutti gli utenti vaccinati da uno specifico centro vaccinale
     * @param CF codice fiscale dell'utente del quale si vuole scaricare la lista di vaccinazioni
     * @throws RemoteException
     * @author Cavallini Francesco
     * @since 14/08/2022
     */
    //questa in verità non serve più. quando faccio il login posso salvarmi dentro a login box la lista di tutte le vaccinazioni, inoltre è sbagliata perchè scarica le vaccinazioni di tutti i centri
    @Override
    public List<String> ScaricaVaccinazioni(String CF) throws RemoteException, SQLException {
        ResultSet result = dbConnettion.SQLquery("SELECT nomeVaccino, dataSomministrazione  from vaccinazione WHERE codiceFiscale = \'"+CF+"\'");
        List<String> x = new ArrayList();
        while (result.next()) {
            x.add(result.getString("dataSomministrazione")+"   "+result.getString("nomeVaccino"));
        }
        return x;
    }


    public static void exec(PostgressConnection x) throws RemoteException {
        CittadiniServer obj = new CittadiniServer(x);
        Registry registro = LocateRegistry.createRegistry(7272);
        registro.rebind("CittadiniServer",obj);
    }
}
