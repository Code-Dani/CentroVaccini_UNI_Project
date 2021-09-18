package Controllers;

import Classes.*;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CentroVaccinaleRG implements Initializable {

    @FXML
    private Text txtNome;

    @FXML
    private Text txtIndirizzo;

    @FXML
    private BarChart<?, ?> chartEta;

    @FXML
    private StackedAreaChart<?, ?> ChartVaccinazioni;

    @FXML
    private PieChart chartFasce;

    @FXML
    private Text txtTipologia;

    @FXML
    private JFXButton BtnLogin;

    @FXML
    private JFXButton BtnRegistrazione;

    @FXML
    public JFXButton BtnEventoAvverso;

    @FXML
    private ImageView IMG_reduce;

    @FXML
    private ImageView IMG_restoredown;

    @FXML
    private ImageView IMG_exit;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //qui servirà caricare i dati dal json degli amiconi del backend
    }

    public Boolean IsLogin = false;

    /**
     * metodo che viene richiamato nella home per il caricamento dei dati nella finestra
     * @param m centro vaccinale utile al riempimento dei della finestra
     * @author Cavallini Francesco
     * @since 18/09/2021
     */
    int countAZ = 0;
    int countJej = 0;
    int countMod = 0;
    int countPft = 0;
    public void setParameters(CentroVaccinale m)
    {
        txtNome.setText(m.nome);
        txtIndirizzo.setText((m.indirizzo.toString()));
        txtTipologia.setText(m.tipologia.toString());

        try
        {
            JsonReadWrite leggi = new JsonReadWrite();
            List<UtenteVaccinato> lista = leggi.leggiVaccinati();

            for(int i = 0; i < lista.size(); i++) {
                if(lista.get(i).nomeCentroVaccinale.equals(m.nome) )
                {
                    if(lista.get(i).vaccino.equals(Vaccini.AstraZeneca))
                    {
                        countAZ++;
                    }
                    else if(lista.get(i).vaccino.equals(Vaccini.JeJ))
                    {
                        countJej++;
                    }
                    else if(lista.get(i).vaccino.equals(Vaccini.Moderna))
                    {
                        countMod++;
                    }
                    else if(lista.get(i).vaccino.equals(Vaccini.Pfizer))
                    {
                        countPft++;
                    }
                }
            }

        }catch(Exception E)
        {
            System.out.println(E);
        }
    }



    /**
     * evento che scatena l'apertura della finiestra per il login
     * @author Cavallini Francesco
     * @since 23/08/2021
     */
    public void btnclickLog(javafx.scene.input.MouseEvent mouseEvent) {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            //String absolutePath = System.getProperty("user.dir") + Paths.get("../FXML/CentroVaccinaleRG.fxml");

            fxmlLoader.setLocation(getClass().getResource("../FXML/Login.fxml"));
            //fxmlLoader.setController("../Controllers/CentroVaccinaleRG.java");

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);

            Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
            stage.setX((int)size.getWidth()/2 + 170);
            stage.setY((int)size.getHeight()/2 - 350);

            stage.setTitle("Scheda Login");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    /**
     * evento che scatena l'apertura della finiestra per la registrazione
     * @author Cavallini Francesco
     * @since 23/08/2021
     */
    public void btnClickReg(javafx.scene.input.MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            //String absolutePath = System.getProperty("user.dir") + Paths.get("../FXML/CentroVaccinaleRG.fxml");

            fxmlLoader.setLocation(getClass().getResource("../FXML/Registrazione.fxml"));
            //fxmlLoader.setController("../Controllers/CentroVaccinaleRG.java");

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);

            Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
            stage.setX((int)size.getWidth()/2 + 170);
            stage.setY((int)size.getHeight()/2 - 350);

            stage.setTitle("Scheda Registrazione");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    private double currentWindowX;
    private double currentWindowY;
    private boolean max_min = false;
    /**
     * Evento che gestisce la chiusura della window, il restoredown/maximase , il riduci window.
     * @param mouseEvent
     * @author Satriano Daniel
     * @since 10/05/2021
     */
    public void window_status(javafx.scene.input.MouseEvent mouseEvent) {
        Stage stage = null;
        ImageView cast = (ImageView)mouseEvent.getSource();
        stage = (Stage) IMG_reduce.getScene().getWindow();
        switch(cast.getId()){
            case "IMG_reduce":
                stage.setIconified(true);
                break;
            case "IMG_restoredown":
                Screen screen = Screen.getPrimary();
                Rectangle2D bounds = screen.getVisualBounds();
                //stage.setMaximized(!stage.isMaximized());
                max_min = !max_min;

                if(max_min == true){
                    currentWindowX = stage.getWidth();
                    currentWindowY = stage.getHeight();
                    stage.setX(bounds.getMinX());
                    stage.setY(bounds.getMinY());
                    stage.setWidth(bounds.getWidth());
                    stage.setHeight(bounds.getHeight());
                    IMG_restoredown.setImage(new javafx.scene.image.Image(getClass().getResource("/Images/lightMode/img_gp_black.png").toExternalForm()));
                }else{
                    stage.setWidth(currentWindowX);
                    stage.setHeight(currentWindowY);
                    IMG_restoredown.setImage(new Image(getClass().getResource("/Images/lightMode/img_maximise_black.png").toExternalForm()));
                    stage.centerOnScreen();
                }
                break;
            case "IMG_exit":
                stage.close();
                break;
            default:
                System.out.println("Errore nello switch delle ImageView per lo status della window");
                break;
        }
    }
}
