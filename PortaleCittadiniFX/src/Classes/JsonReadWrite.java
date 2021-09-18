package Classes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.MediaException;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe per la lettura e scrittura su file
 * @author Cristian De Nicola
 */
public class JsonReadWrite {

    ///metodo per salvare i dati dei cittadini che fanno la registrazione su Cittadini Registrati
    public static void WriteOnFileCittadiniRegistrati(Utente u, FilePaths pathToReadFrom) throws IOException {

            List<Utente> utenti = ReadFromFileUtenti(pathToReadFrom);
            utenti.add(u);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Writer writer = Files.newBufferedWriter(Paths.get(FilePaths.CittadiniRegistrati.toString()));
            gson.toJson(utenti, writer);
            writer.close();
    }

    /**
     * Questo metodo si occupa di leggere il file stringa
     * @param pathToReadFrom path del file dal quale deve leggere
     * @see FilePaths per maggiori informazioni su cosa va inserito come path
     * @author Claudio Menegotto
     */
    public static List<CentroVaccinale> ReadFromFileCentroVaccinali(FilePaths pathToReadFrom) throws IOException {
        //myFile va a prendere il file da cui vogliamo prendere i dati
        List<CentroVaccinale> centri = new ArrayList<>();
        File file = new File(FilePaths.CentriVaccinali.toString());
        if(file.exists() && file.length()>0) {
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

    ///metodo per leggere i dati degli utenti registrati sul file Cittadini registrati (per il login)
    public static List<Utente> ReadFromFileUtenti(FilePaths pathToReadFrom) throws IOException {
        String myFile = fileToString(pathToReadFrom);
        Gson gson = new Gson();
        List<Utente> list = gson.fromJson(myFile, new TypeToken<List<Utente>>() {}.getType());

        return list;
    }

    private static String fileToString(FilePaths pathToReadFrom) throws IOException {
        File file = new File(pathToReadFrom.toString());
        String fileToString = "";
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null)
            fileToString = fileToString.concat(st);

        return fileToString;
    }
}