package Classes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe usata per la gestione della scrittura e della lettura di dati dentro il database.
 * @author Cristian De Nicola
 * @author Francesco Cavallini
 * @author Menegotto Claudio
 * @author Satriano Daniel
 * @since 10/11/2021
 */
public class JsonReadWrite
{
    /**
     * Metodo si occupa di leggere il file in formato stringa.
     * Metodo con un return di string.
     * @param pathToReadFrom path del file da convertire in stringa e leggere.
     * @see FilePaths per maggiori informazioni su cosa va inserito come path
     * @author Claudio Menegotto
     */
    public static String fileToString(FilePaths pathToReadFrom) throws IOException
    {
        File file = new File(pathToReadFrom.toString());
        String fileToString = "";
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null)
            fileToString = fileToString.concat(st);

        return fileToString;
    }

    /**
     * Metodo usato per il salvataggio, quindi registrazione nel databse di un nuovo centroVaccinale.
     * @param nuovoCentro oggetto di tipo CentroVaccinale da salvare.
     * @see CentroVaccinale per informazioni sulla classe CentroVaccinale.
     * @author ClaudioMenegotto
     */
    public static void RegistraCentroVaccinale(CentroVaccinale nuovoCentro) throws IOException
    {
        if(nuovoCentro!= null) {
            List<CentroVaccinale> centri = ReadFromFileCentroVaccinali();
            centri.add(nuovoCentro);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Writer writer = Files.newBufferedWriter(Paths.get(FilePaths.CentriVaccinali.toString()));
            gson.toJson(centri, writer);
            writer.close();
        }
    }

    /**
     * Metodo usato per il salvataggio, quindi registrazione nel databse di un nuovo utente vaccinato.
     * @param vaccinato oggetto di tipo UtenteVaccinato da salvare.
     * @see UtenteVaccinato per informazioni sulla classe UtenteVaccinato
     * @author ClaudioMenegotto
     */
    public static void registraVaccinato(UtenteVaccinato vaccinato) throws IOException
    {
        if(vaccinato!= null) {
            List<UtenteVaccinato> vaccinati = leggiVaccinati();
            vaccinati.add(vaccinato);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Writer writer = Files.newBufferedWriter(Paths.get(FilePaths.VaccinatiNomeCentro.toString()));
            gson.toJson(vaccinati, writer);
            writer.close();
        }
    }

    /**
     * Metodo usato per la registrazione sotto nome di un utente di un evento avverso avvenuto in seguito alla vaccinazione.
     * Aggiunge all'istanza già presente nel database di un utente un evento avverso subito.
     * @param vaccinati lista di tipo UtenteVaccinato da poter leggere per prendere i dati dell'utente che ha richiesto l'aggiunta dell'evento.
     * @see UtenteVaccinato per informazioni sulla classe UtenteVaccinato
     * @author De Nicola Cristian
     */
    public static void registraEventoxVaccinato(List<UtenteVaccinato> vaccinati) throws IOException
    {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Writer writer = Files.newBufferedWriter(Paths.get(FilePaths.VaccinatiNomeCentro.toString()));
            gson.toJson(vaccinati, writer);
            writer.close();
    }

    /**
     * Metodo usato per la lettura dal databse (nel filepath "VaccinatiNomeCentro") degli utenti vaccinati.
     * Usato per leggere i dati degli utenti vaccinati.
     * Questo metodo ha un return di una lista di tipo UtenteVaccinato.
     * @see UtenteVaccinato per informazioni sulla classe UtenteVaccinato.
     * @author Claudio Menegotto
     */
    public static List<UtenteVaccinato> leggiVaccinati() throws IOException
    {
        List<UtenteVaccinato> utenti = new ArrayList<>();
        File file = new File(FilePaths.VaccinatiNomeCentro.toString());
        if(file.exists() && file.length()>0)
        {
            Gson gson = new Gson();
            String fileToString = "";
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            while ((st = br.readLine()) != null)
                fileToString = fileToString.concat(st);

            utenti = gson.fromJson(fileToString, new TypeToken<List<UtenteVaccinato>>() {
            }.getType());
            return utenti;
        }
        else
            return utenti;
    }

    /**
     * Metodo usato per la lettura dal database (nel filepath "CentriVaccinali") dei centri già registrati.
     * Usato per leggere i dati dei centri già registrati.
     * Questo metodo ha un return di una lista di tipo CentroVaccinale.
     * @see FilePaths per maggiori informazioni su cosa va inserito come path.
     * @see CentroVaccinale per informazioni sulla classe CentroVaccinale.
     * @author Claudio Menegotto
     */
    public static List<CentroVaccinale> ReadFromFileCentroVaccinali() throws IOException
    {
        List<CentroVaccinale> centri = new ArrayList<>();
        File file = new File(FilePaths.CentriVaccinali.toString());
        if(file.exists() && file.length()>0)
        {
            Gson gson = new Gson();
            String fileToString = "";
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            while ((st = br.readLine()) != null)
                fileToString = fileToString.concat(st);

            centri = gson.fromJson(fileToString, new TypeToken<List<CentroVaccinale>>() {
            }.getType());
            return centri;
        }
        else
            return centri;
    }

    /**
     * Metodo usato per la lettura dal database (nel filepath "CittadiniRegistrati") dei dati degli utenti registrati.
     * Usato per leggere i dati degli utenti già registrati.
     * Questo metodo ha un return di una lista di tipo UtenteCredenziali.
     * @see UtenteCredenziali per informazioni sulla classe UtenteCredenziali.
     * @author Claudio Menegotto
     */
    public static List<UtenteCredenziali> leggiCredenziali() throws IOException
    {
        List<UtenteCredenziali> utenti = new ArrayList<>();
        File file = new File(FilePaths.CittadiniRegistrati.toString());
        if(file.exists() && file.length()>0)
        {
            Gson gson = new Gson();
            String fileToString = "";
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            while ((st = br.readLine()) != null)
                fileToString = fileToString.concat(st);

            utenti = gson.fromJson(fileToString, new TypeToken<List<UtenteCredenziali>>() {
            }.getType());
            return utenti;
        }
        else
            return utenti;
    }

    /**
     * Metodo usato per la registrazione di nuove credenziali da perte di un nuovo utente x.
     * Usato per registrare delle nuove credenziali.
     * @see UtenteCredenziali per informazioni sulla classe UtenteCredenziali.
     * @author Cristian De Nicola
     */
    public static void registraCredenziali(UtenteCredenziali u) throws IOException
    {
        List<UtenteCredenziali> utente = leggiCredenziali();
        utente.add(u);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Writer writer = Files.newBufferedWriter(Paths.get(FilePaths.CittadiniRegistrati.toString()));
        gson.toJson(utente, writer);
        writer.close();
    }

    /**
     * Metodo usato per leggere dal database gli eventi avversi dovuti alla vaccinazione che gli utenti caricano.
     * usato per leggere gli eventi avversi.
     * Questo metodo ha un return di una lista di tipo EventoAvverso.
     * @see EventoAvverso per informazioni sulla classe EventoAvverso.
     * @author Cavallini Francesco
     */
    public static List<EventoAvverso> leggiEventoAvverso() throws IOException
    {
        List<EventoAvverso> eventi = new ArrayList<>();
        File file = new File(FilePaths.EventiAvversi.toString()); //NON ESISTE QUESTO FILE PATH
        if(file.exists() && file.length()>0) {
            Gson gson = new Gson();
            String fileToString = "";
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            while ((st = br.readLine()) != null)
                fileToString = fileToString.concat(st);

            eventi = gson.fromJson(fileToString, new TypeToken<List<EventoAvverso>>() {
            }.getType());
            return eventi;
        }
        else
            return eventi;
    }
}