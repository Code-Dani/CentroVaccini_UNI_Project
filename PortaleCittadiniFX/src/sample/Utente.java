package sample;

import java.util.ArrayList;

/**classe per la definizione dell'utente registrato
 * @author Satriano Daniel
 * */
public class Utente {
    public String nome;
    public String cognome;
    public String codiceFiscale;
    public String indirizzoEmail;
    private String userID;
    private String password;

    /**id vacinazione univoco su 16bit*/
    public short idVaccinazione;

    /**enum Vaccino per la definizione dei vaccini somministrabili*/
    public enum Vaccino
    {
        Pfizer,
        AstraZeneca,
        Moderna,
        JeJ{
            public String toString(){
                return "J&J";
            }
        }
    }

    public Vaccino vaccino;

    /** lista di eventi avversi dell'utente*/
    public ArrayList<EventoAvverso> eventiAvversi;

    /** costruttore*/
    public Utente (){
        eventiAvversi  = new ArrayList<EventoAvverso>();
    }

    /**aggiunge alla lista gli eventi avversi inseriti dagli utenti e vengono salvati su file*/
    public void inserisciEventiAvversi(){
        //da gestire con la parte grafica e salvataggio in file
    }
}
