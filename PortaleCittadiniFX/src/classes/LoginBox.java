package classes;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import javax.swing.*;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** classe statica utilizzata per il passaggio facilitato di dati e informazioni tra windows e classi.
 * @author Cavalli Francesco
 */
public class LoginBox implements Serializable
{
    static final long serialVersionUID = 6L;

    static public BooleanProperty isLogin = new SimpleBooleanProperty(false);

    static public String nome;
    static public String cognome;
    static public String nomeCecntroVaccinale;
    static public String codiceFiscale;

    static public List<UtenteVaccinato> listaVaccinazioni = new ArrayList<UtenteVaccinato>();

    /**
     * Metodo usato per la lettura dell'ID di vaccinazione dell'utente direttamente dal login.
     * @return IDvaccinazione dell'utente.
     * @author Cavallini Francesco
     */
    static public List<UtenteVaccinato> getIdVaccinazione()
    {
        return listaVaccinazioni;
    }

    /**
     * Metodo usato per la scrittura dell'ID di vaccinazione dell'utente.
     * @author Cavallini Francesco
     */
    static public void setIdVaccinazione(List<UtenteVaccinato> parameter)
    {
        listaVaccinazioni = parameter;
    }

    /**
     * Metodo usato per il controllo sul login e il salvataggio di informazioni e dati del login utili da condividere tra windows.<br/>
     * salva e setta anche lo stato della variabile isLogin.
     * @param email email con la quale l'utente sta facendo il login.
     * @param psw password dell'utente con la quale sta facendo il login.
     * @param nomeCentro nome del centro in cui l'utente sta facendo il login.
     * @author Cavallini Francesco
     */

    static public void login(String email, String psw, String nomeCentro) throws IOException
    {
        //PARTE RMI
        try {
            DatabaseHelper db = new DatabaseHelper();
            listaVaccinazioni = db.Login(email,psw,nomeCentro);
            if(listaVaccinazioni == null){
                //se il login non va a buon fine ritorno null
                JOptionPane.showMessageDialog(null, "Login fallito, \ncontrolla le credenziali e di starti loggando nel centro vaccinale corretto");
                isLogin.set(false);
            }
            else{
                nome = listaVaccinazioni.get(0).nome;
                cognome = listaVaccinazioni.get(0).cognome;
                nomeCecntroVaccinale = listaVaccinazioni.get(0).nomeCentroVaccinale;
                codiceFiscale = listaVaccinazioni.get(0).codiceFiscale;
                JOptionPane.showMessageDialog(null, "Login effettuato con successo");
                isLogin.set(true);
            }
        } catch (NotBoundException e) {
            JOptionPane.showMessageDialog(null, "Login fallito, \ncontrolla le credenziali e di starti loggando nel centro vaccinale corretto");
            isLogin.set(false);
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        /*PARTE CHE UTILIZZA I FILE
        isLogin.set(false);
        List<UtenteCredenziali> utenti = JsonReadWrite.leggiCredenziali();

        for(int i=0;i<utenti.size();i++)
        {
            //controllo nella lista utenti se ne ho uno a cui corrisponde mail e password (selection sort)
            if(utenti.get(i).indirizzoEmail.toString().equals(email) && utenti.get(i).password.toString().equals(psw))
            {
                idVaccinazione = utenti.get(i).IDVaccinazione;
                List<UtenteVaccinato> vaccinati = JsonReadWrite.leggiVaccinati();

                //ricerca nome e cognome utente (selection sort)
                for(int j=0;j<vaccinati.size();j++) {
                    if(idVaccinazione - vaccinati.get(j).getIdVaccinazione() == 0 && nomeCentro.equals(vaccinati.get(j).nomeCentroVaccinale) )
                    {
                        nome = vaccinati.get(j).nome;
                        cognome = vaccinati.get(j).cognome;
                        nomeCecntroVaccinale = nomeCentro;
                        //login avvenuto con successo.
                        JOptionPane.showMessageDialog(null, "Login effettuato con successo");
                        isLogin.set(true);
                    }
                }
            }
        }
        //login fallito.
        if(!isLogin.getValue())
        {
            JOptionPane.showMessageDialog(null, "Login fallito, \ncontrolla le credenziali e di starti loggando nel centro vaccinale corretto");
        }
         */
    }

    /**
     * Metodo usato per il logOut e la pulizia delle informazioni della sessione utente
     * @author Cavallini Francesco
     */
    static public void logOut() {
        if(isLogin.getValue())
        {
            nome = "Utente";
            isLogin.setValue(false);
            cognome = null;
            nomeCecntroVaccinale = null;
            //logOut avvenuto con successo.
            JOptionPane.showMessageDialog(null, "LogOut effettuato con successo");
        }
        else {
            JOptionPane.showMessageDialog(null, "Non sei loggato con nessun account");
        }
    }
}
