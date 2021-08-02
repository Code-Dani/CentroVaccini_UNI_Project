package classes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.ObservableList;
import org.hildan.fxgson.FxGson;
import org.hildan.fxgson.FxGsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

/**
 * Classe per la lettura e scrittura su file Json
 * @author Daniel Satriano
 */
public class JsonReadWrite {

    /**
     * Metodo pubblico che richiama la versione privata di esso per scrivere su un file
     * @param list lista di oggetti da inserire nel file (che ha funzione di DB)
     * @param pathToSaveFile il path del file
     * @throws IOException Possibile eccezzione dovuta al fatto che si sta andando a scrivere su un file
     * @see FilePaths per maggiori informazioni su cosa va inserito come path
     * @author Daniel Satriano
     */

    public static void writeToFile(String list, FilePaths pathToSaveFile) throws IOException,InstantiationException {
        Writer writer = Files.newBufferedWriter(Paths.get(pathToSaveFile.toString()), StandardOpenOption.APPEND); //creo un oggetto della classe writer da passare poi al metodo toJson della classe Gson. (per scrivere su un file)
        FxGson.coreBuilder().setPrettyPrinting().disableHtmlEscaping().create().toJson(list,writer);  //Converto la lista di oggetti ottenuta in input in una stringa json
        writer.close(); //termino l'oggetto writer
    }

    /**
     * Metodo pubblico che richiama la versione privata di esso per leggere da un file
     * Ritorna una stringa json, All'utente è suggerito usare <br/>Arrays.asList(gson.fromJson(json, class[].class)) per convertirlo nell'appropriata lista di oggetti<br/>
     * Oppure gson.fromJson(json, class.class) per convertirlo in un oggetto della classe appropriata
     * @param pathToReadFrom path del file dal quale deve leggere
     * @see FilePaths per maggiori informazioni su cosa va inserito come path
     * @author Daniel Satriano
     */
    public static String readFromFile(FilePaths pathToReadFrom) throws IOException{
       return Files.readString(Path.of(pathToReadFrom.toString()));

    }


}
