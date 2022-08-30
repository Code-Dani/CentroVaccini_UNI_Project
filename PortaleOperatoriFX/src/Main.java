import classes.DatabaseHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Array;
import java.util.Arrays;

/**
 * Classe main che serve a impostare alcune scelte grafiche e avviamo la prima pagina grafica
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));
        primaryStage.setTitle("Portale Operatori Vaccinali");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/piechartCSS.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/css/TreeTableCellCSS.css").toExternalForm());
        primaryStage.setScene(scene);
        //primaryStage.setResizable(true);
        primaryStage.setFullScreenExitHint("Premi ESC per uscire dalla modalità a tutto schermo");
        primaryStage.show();
    }


    /**
     * The entry point of application.
     * Vengono modificati i parametri di DatabaseHelper in base agli args
     * @param args the input arguments
     * @author Daniel Satriano
     * @since 30/08/2022
     */
    public static void main(String[] args) {
        try {
            System.out.println(args[0]);
            System.out.println(args[1]);
            if (!args[0].equals("localhost") && !args[0].equals("")) {
                System.out.println("Setto gli args");

                DatabaseHelper.ADDRESS = args[0];
                DatabaseHelper.PORT = Integer.parseInt(args[1]);
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Null args: " + e.getMessage());
        }
        launch(args);
    }
}
