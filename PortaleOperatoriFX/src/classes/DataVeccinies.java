package classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;


/**
 * classe dedicata alla home per la lettura in tempo reale della situazione italiana sulla vaccinazione
 * @author Menegotto Claudio
 */
public class DataVeccinies {

    public int abitanti;
    public String dataUpdate;
    public int somministrazioniTotali;
    public int vaccinazioniCompletate;
    public int dosiGiornaliere;


    public DataVeccinies(){
        updateData();
    }

    public void updateData(){
        //update dei dati delle vaccinazioni
        String response = request();
    }

    public String request() {
        URL url = null;
        try {
            url = new URL("https://lab24.ilsole24ore.com/_json/vaccini/dati-mondo.json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            StringBuilder content;

            try (BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                content = new StringBuilder();
                while ((line = input.readLine()) != null) {
                    // Append each line of the response and separate them
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            } finally {
                connection.disconnect();
            }

            return content.toString();


        } catch (MalformedURLException e) {
            return null;
        } catch (ProtocolException e) {
            return null;
        } catch (IOException e) {
           return null;
        }
    }
}
