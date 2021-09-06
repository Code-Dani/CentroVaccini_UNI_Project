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
import java.util.Arrays;
import java.util.List;

/**
 * Classe per la lettura e scrittura su file Json
 * @author Daniel Satriano
 */
public class JsonReadWrite {


    public static List<CentroVaccinale> ReadFromFile(FilePaths pathToReadFrom) throws IOException {
        //myFile va a prendere il file da cui vogliamo prendere i dati
        String myFile = fileToString(pathToReadFrom);
        Gson gson = new Gson(); //??? non mi trova la import

        List<CentroVaccinale> list = gson.fromJson(myFile, new TypeToken<List<CentroVaccinale>>() {}.getType());

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