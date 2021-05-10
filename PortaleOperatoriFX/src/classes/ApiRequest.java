package classes;

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
    public static JsonObject makeRequest(String url) throws Exception{
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
    private static JsonObject richiediGet(String url) throws Exception{
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        //print status code
        System.out.println(response.statusCode());
        //return response body
        return convertJSONintoJSONobject(response.body());
    }

    /**
     * <a href="https://www.javatpoint.com/how-to-convert-string-to-json-object-in-java">Per maggiori informazioni sulla classe usata all'interno del metodo</a>
     * @param json string json ricevuta dal sito
     * @return JsonObject
     * @author Satriano Daniel
     *
     */
    private static JsonObject convertJSONintoJSONobject(String json){
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        return jsonObject;
    }
}
