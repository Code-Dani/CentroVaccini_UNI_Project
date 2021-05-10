import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));
        primaryStage.setTitle("Portale Operatori Vaccinali");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/piechartCSS.css").toExternalForm());
        primaryStage.setScene(scene);
        //primaryStage.setResizable(true);
        primaryStage.setFullScreenExitHint("Premi ESC per uscire dalla modalità a tutto schermo");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
