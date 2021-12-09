package classes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe per la lettura e scrittura su file Json
 *
 * @author Daniel Satriano
 * @since 03 /08/2021
 */
public class JsonReadWrite {

    /**
     * Questo metodo si occupa di leggere il file stringa
     *
     * @param pathToReadFrom path del file dal quale deve leggere
     * @return the string
     * @throws IOException the io exception
     * @see FilePaths per maggiori informazioni su cosa va inserito come path
     * @author Claudio Menegotto
     * @author Daniel Satriano
     * @since 03/08/2021
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
     *
     * @param nuovoCentro oggetto CentroVaccinale da salvare
     * @throws IOException the io exception
     * @author Claudio Menegotto
     * @since 17 /09/2021
     */
    public static void RegistraCentroVaccinale(CentroVaccinale nuovoCentro) throws IOException {
        if(nuovoCentro!= null) {
            List<CentroVaccinale> centri = leggiCentri();
            centri.add(nuovoCentro);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Writer writer = Files.newBufferedWriter(Paths.get(FilePaths.CentriVaccinali.toString()));
            gson.toJson(centri, writer);
            writer.close();
        }
    }

    /**
     * Questo Metodo legge la lista di centri vaccinali per accodarli a quello nuovo inserito
     *
     * @return the list
     * @throws IOException the io exception
     * @author Claudio Menegotto
     * @since 17 /09/2021
     */
    public static List<CentroVaccinale> leggiCentri() throws IOException {
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

    /**
     * Questo metodo serve per il salvataggio su file dei dati della nuova vaccinazione
     *
     * @param vaccinato oggetto UtenteVaccinato da salvare
     * @throws IOException the io exception
     * @author Claudio Menegotto
     * @author Daniel Satriano
     * @since 03 /08/2021
     */
    public static void registraVaccinato(UtenteVaccinato vaccinato) throws IOException {
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
     * Questo Metodo legge la lista di utentiVaccinati per accodarli a quello nuovo inserito
     *
     * @return the list
     * @throws IOException the io exception
     * @author Claudio Menegotto
     * @since 17 /09/2021
     */
    public static List<UtenteVaccinato> leggiVaccinati() throws IOException {
        List<UtenteVaccinato> utenti = new ArrayList<>();
        File file = new File(FilePaths.VaccinatiNomeCentro.toString());
        if(file.exists() && file.length()>0) {
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
}