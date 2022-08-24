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

    private static void registraCittadino(){} //-> METODO CHE VA MESSO NELLA GESTIONE FILE

}
