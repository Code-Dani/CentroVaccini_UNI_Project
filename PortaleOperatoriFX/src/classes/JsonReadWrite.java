package classes;

import com.google.gson.GsonBuilder;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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
    public static void writeToFile(ObservableList<?> list, FilePaths pathToSaveFile) throws IOException {
        write(list,pathToSaveFile);
    }
    /**
     * Metodo utilizzato per scrivere su un file
     * @param list lista di oggetti da inserire nel file (che ha funzione di DB)
     * @param pathToSaveFile il path del file
     * @throws IOException Possibile eccezzione dovuta al fatto che si sta andando a scrivere su un file
     * @see FilePaths per maggiori informazioni su cosa va inserito come path
     * @author Daniel Satriano
     */
    private static void write(ObservableList<?> list, FilePaths pathToSaveFile) throws IOException{
        Writer writer = Files.newBufferedWriter(Paths.get(pathToSaveFile.toString()), StandardOpenOption.APPEND); //creo un oggetto della classe writer da passare poi al metodo toJson della classe Gson. (per scrivere su un file)
        new GsonBuilder().setPrettyPrinting().create().toJson(list, writer);  //Converto la lista di oggetti ottenuta in input in una stringa json
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
       return read(pathToReadFrom);
    }
    /**
     * Ritorna una stringa json, All'utente è suggerito usare <br/>Arrays.asList(gson.fromJson(json, class[].class)) per convertirlo nell'appropriata lista di oggetti<br/>
     * Oppure gson.fromJson(json, class.class) per convertirlo in un oggetto della classe appropriata
     * @param pathToReadFrom path del file dal quale deve leggere
     * @see FilePaths per maggiori informazioni su cosa va inserito come path
     * @author Daniel Satriano
     */
    private static String read(FilePaths pathToReadFrom) throws IOException{
        return Files.readString(Path.of(pathToReadFrom.toString()));
    }

}
