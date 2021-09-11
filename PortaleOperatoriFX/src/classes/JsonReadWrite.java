package classes;

import ClassSerializers.StoricoSerialize;
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
 * Classe per la lettura e scrittura su file Json
 * @author Daniel Satriano
 */
public class JsonReadWrite {

    /**
     * Questo metodo si occupa di convertire un oggetto a JSON e salvarlo su un dato file
     * @param list lista di oggetti da inserire nel file (che ha funzione di DB)
     * @param pathToSaveFile il path del file
     * @throws IOException Possibile eccezzione dovuta al fatto che si sta andando a scrivere su un file
     * @see FilePaths per maggiori informazioni su cosa va inserito come path
     * @author Daniel Satriano
     */
    public static void writeToFile(ObservableList<Storico> list, FilePaths pathToSaveFile) throws IOException {
        ObservableList<StoricoSerialize> StoricoSerialized = StoricoSerialize.ListConverter(list);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Writer writer = Files.newBufferedWriter(Paths.get(pathToSaveFile.toString()));
        gson.toJson(StoricoSerialized, writer);
        writer.close();
    }
    /**
     * Questo metodo si occupa di leggere i file json da un dato file
     * @param pathToReadFrom path del file dal quale deve leggere
     * @see FilePaths per maggiori informazioni su cosa va inserito come path
     * @author Claudio Menegotto
     */

    public static ObservableList<Storico> readFromFile(FilePaths pathToReadFrom) throws IOException{

        String myFile = fileToString(pathToReadFrom);
        Gson gson = new Gson();


        ObservableList<StoricoSerialize> list = FXCollections.observableArrayList();
        StoricoSerialize[] jsonserialized_ =  gson.fromJson(myFile, new TypeToken<StoricoSerialize[]>() {}.getType());
        list.addAll(Arrays.asList(jsonserialized_));

        ObservableList<Storico> listStorico = StoricoSerialize.StoricoSer_to_Storico(list);

        return listStorico;
    }

    /**
     * Questo metodo si occupa di leggere il file stringa
     * @param pathToReadFrom path del file dal quale deve leggere
     * @see FilePaths per maggiori informazioni su cosa va inserito come path
     * @author Claudio Menegotto
     */
    public static String fileToString(FilePaths pathToReadFrom) throws IOException {
        File file = new File(pathToReadFrom.toString());
        String fileToString = "";
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null)
            fileToString = fileToString.concat(st);

        return fileToString;
    }

    /**
     * Questo metodo serve per il salvataggio su file dei dati del nuovo centro vaccinale
     * @param nuovoCentro oggetto CentroVaccinale da salvare
     * @autho ClaudioMenegotto
     */
    public static void RegistraCentroVaccinale(CentroVaccinale nuovoCentro) throws IOException {
        List<CentroVaccinale> centri = leggiCentri();
        centri.add(nuovoCentro);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Writer writer = Files.newBufferedWriter(Paths.get(FilePaths.CentriVaccinali.toString()));
        gson.toJson(centri, writer);
        writer.close();
    }

    /**
     * Questo Metodo legge la lista di centri vaccinali per accodarli a quello nuovo inserito
     * @author Claudio Menegotto
     */
    private static List<CentroVaccinale> leggiCentri() throws IOException {
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
}