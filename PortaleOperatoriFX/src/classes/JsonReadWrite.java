package classes;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.File;
import java.io.IOException;

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
    public static void writeToFile(ObservableList<?> list, FilePaths pathToSaveFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();   //creo l'istanza di ObjectMapper
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter()); //Instanza di DefaultPrettyPrinter
        File databaseFile = new File(pathToSaveFile.toString());
        writer.writeValue(databaseFile,list);
    }
    /**
     * Questo metodo si occupa di leggere i file json da un dato file
     * @param pathToReadFrom path del file dal quale deve leggere
     * @see FilePaths per maggiori informazioni su cosa va inserito come path
     * @author Daniel Satriano
     */
    public static ObservableList<?> readFromFile(FilePaths pathToReadFrom, Class<?> classe) throws IOException{
       ObjectMapper mapper = new ObjectMapper();
       File databaseFile = new File(pathToReadFrom.toString());
       return FXCollections.observableArrayList(mapper.readValue(databaseFile,classe));
    }
}
