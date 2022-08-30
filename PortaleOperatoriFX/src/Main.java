import classes.DatabaseHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        System.out.println(args[0]);
        System.out.println(args[1]);
        if(!args[0].equals("localhost")){
            System.out.println("Setto gli args");
            DatabaseHelper.ADDRESS = args[0];
            DatabaseHelper.PORT = Integer.parseInt(args[1]);
        }
        launch(args);
    }
}
