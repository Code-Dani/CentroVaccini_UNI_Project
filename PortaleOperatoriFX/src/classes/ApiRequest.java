package classes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


/**
 * Classe per la richiesta di una API
 * @author Satriano Daniel
 * @since 10/05/2021
 */
public class ApiRequest {

    /**
     *
     * @param url url del sito a cui fare la richiesta
     * @return ritorna un JSONobject
     * @throws Exception eccezzione che si può verificare durante la richiesta GET <a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.html">per maggiori informazioni</a>
     * @author Satriano Daniel
     * @since 10/05/2021
     */
    public static JsonArray makeRequest(String url) throws Exception{
        return richiediGet(url);
    }

    /**
     *
     * @param url url del sito a cui fare la richiesta
     * @return ritorna un JSONobject
     * @throws Exception eccezzione che si può verificare durante la richiesta GET <a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.html">per maggiori informazioni</a>
     * @author Satriano Daniel
     * @since 10/05/2021
     */
    private static JsonArray richiediGet(String url) throws Exception{
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return convertJSONintoJSONobject(response.body());
    }

    /**
     * <a href="https://www.javatpoint.com/how-to-convert-string-to-json-object-in-java">Per maggiori informazioni sulla classe usata all'interno del metodo</a>
     * @param json string json ricevuta dal sito
     * @return JsonObject
     * @author Satriano Daniel
     * @author Menegotto Claudio
     *
     */
    private static JsonArray convertJSONintoJSONobject(String json){
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        return ((JsonObject)jsonObject.get("dataset")).get("dati").getAsJsonArray();
    }


    /**
     *
     * @param tmp
     * @param nodo
     * @return
     * @author Menegotto Claudio
     * @author Satriano Daniel
     */
    public static String infoGrabber(JsonObject tmp,String nodo){
        return !tmp.get(nodo).toString().contains("\"") ? tmp.get(nodo).toString() : tmp.get(nodo).toString().split("\"")[1];
    }
}