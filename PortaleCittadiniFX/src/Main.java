import Classes.CentroVaccinale;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*Parent root = FXMLLoader.load(getClass().getResource("/FXML/Home.fxml"));
        primaryStage.setTitle("Portale Cittadini");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();*/

        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Home.fxml"));
        primaryStage.setTitle("Portale Cittadini");

        primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.setFullScreenExitHint("Premi ESC per uscire dalla modalità a tutto schermo");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);


    }

    /**
     * Metodo per cercare centro vaccinale
     * @param nomeCentro
     * @return item,null
     */
    public static CentroVaccinale cercaCentroVaccinale(String nomeCentro){
        //CentroVaccinale item = new CentroVaccinale();  //<- l'item è attualmente inizializzato solo per non throware errori
        //Richiama il metodo per prendersi la lista aggiornata dei centroVaccinali

        //effettua un operazione di ricerca all'interno della lista
        if(true) //-> quindi trova il centro indicato
            return null;
        else
            return null;

    }

    /**
     * Metodo per cercare centro vaccinale
     * @param comune
     * @param tipologia
     * @return item, null
     */
    public static CentroVaccinale cercaCentroVaccinale(String comune, String tipologia){
        // CentroVaccinale item = new CentroVaccinale(); //<- l'item è attualmente inizializzato solo per non throware errori

        //Richiama il metodo per prendersi la lista aggiornata dei centroVaccinali -> ci sarà da fare un controllo per vedere se l'operazione è andata a buon fine

        //effettua un operazione di ricerca all'interno della lista
        if(true) //-> quindi trova il centro indicato
            return null;
        else
            return null;

    }

    private static void visualizzaCentroVaccinale(CentroVaccinale centro){     //<- QUESTO METODO è UN METODO GRAFICO, VA FATTO NELLA GRAFICA

    }

    private static void registraCittadino(){} //-> METODO CHE VA MESSO NELLA GESTIONE FILE

}
