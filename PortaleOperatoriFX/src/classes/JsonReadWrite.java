package classes;

import ClassSerializers.StoricoSerialize;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

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
     * @author Daniel Satriano
     */

    public static ObservableList<Storico> readFromFile(FilePaths pathToReadFrom) throws IOException{
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get(pathToReadFrom.toString()));
        ObservableList<StoricoSerialize> list = new Gson().fromJson(reader,new TypeToken<ObservableList<StoricoSerialize>>() {}.getType());
        ObservableList<Storico> l_storico = StoricoSerialize.StoricoSer_to_Storico(list);
        reader.close();
        return l_storico;
    }
}
