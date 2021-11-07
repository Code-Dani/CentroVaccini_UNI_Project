package Classes;

import Controllers.CentroVaccinaleRG;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

/** classe statica utile al passagio di informazioni relativa al login tra finestre
 * @author Cavalli Francesco
 * */
public class LoginBox {
    static public BooleanProperty isLogin = new SimpleBooleanProperty(false);
    static private Short idVaccinazione;
    static public String nome;
    static public String cognome;

    static public String nomeCecntroVaccinale; //questo non so se serve ma nel caso lo salvo

    /**
     * permette la lettura dell'id di vaccinazione
     * @author Cavallini Francesco
     */
    static public Short getIdVaccinazione()
    {
        return idVaccinazione;
    }

    /**
     * permette la lettura della stringa nome+cognome
     * @author De Nicola Cristian
     */
    static public String getNomeCognome(){return nome+cognome;}

    /**
     * permette la scrittura dell'id di vaccinazione
     * @author Cavallini Francesco
     */
    static public void setIdVaccinazione(Short parameter)
    {
        idVaccinazione = parameter;
    }

    /**
     * metodo statico che permette di fare il login e di conseguenza salvare lo stasto di isLogin e le altre informazioni necessarie da condividere tra finestre
     * @author Cavallini Francesco
     */
    static public void login(String email, String psw, String nomeCentro) throws IOException {
        isLogin.set(false);
        List<UtenteCredenziali> utenti = JsonReadWrite.leggiCredenziali();

        for(int i=0;i<utenti.size();i++)
        {
            //controllo nella lista utenti se ne ho uno a cui corrsiponde mail e pass (selection sort)
            if(utenti.get(i).indirizzoEmail.toString().equals(email) && utenti.get(i).password.toString().equals(psw))
            {
                idVaccinazione = utenti.get(i).IDVaccinazione;

                List<UtenteVaccinato> vaccinati = JsonReadWrite.leggiVaccinati();
                //ricerca nome e cognome utente (selection sort)
                for(int j=0;j<vaccinati.size();j++) {
                    if(idVaccinazione - vaccinati.get(j).getIdVaccinazione() == 0 && nomeCentro.equals(vaccinati.get(j).nomeCentroVaccinale) ) //modo semplice per controllare che i due valori siano uguali
                    {
                        nome = vaccinati.get(j).nome;
                        cognome = vaccinati.get(j).cognome;
                        nomeCecntroVaccinale = nomeCentro;
                        JOptionPane.showMessageDialog(null, "Login effettuato con successo");
                        isLogin.set(true);
                    }
                }
            }
        }
        //stampo all'utente un messaggio di login fallito
        if(!isLogin.getValue())
        {
            JOptionPane.showMessageDialog(null, "Login fallito, \ncontrolla le credenziali e di starti loggando nel centro vaccinale corretto");
        }
    }

}
