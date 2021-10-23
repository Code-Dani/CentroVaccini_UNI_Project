package Controllers;

import Classes.*;
import Classes.EventoAvverso;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import javax.security.auth.callback.CallbackHandler;
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
    public JFXTreeTableView<EventoAvversoTMP> LWEventiAvversi;

    @FXML
    private BarChart<?, ?> chartEta;

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


    /**
     * metodo che viene richiamato al caricamento della finestra per l'inserimento dei dati
     * @author Cavallini Francesco
     * @since 04/10/2021
     */
    ObservableList<EventoAvversoTMP> eventiAvv;
    ObservableList<EventoAvversoTMP> tmp;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //carico lista utenti per joinarli con i rispettivi problemi
        List<UtenteVaccinato> listaVacc = null;
        List<EventoAvverso> eventiAvvLetti = null;

        eventiAvv = FXCollections.observableArrayList();
        tmp = FXCollections.observableArrayList();

        try
        {
            listaVacc = JsonReadWrite.leggiVaccinati();
            eventiAvvLetti = JsonReadWrite.leggiEventoAvverso();

            for(int i=0; i<listaVacc.size();i++)
            {
                String nomeCognome = null;
                if( listaVacc.get(i).IDVaccinazione == eventiAvvLetti.get(i).IDVaccinazione )
                {
                    nomeCognome = listaVacc.get(i).nome + " " + listaVacc.get(i).cognome;
                }
                // Evento _evento, Severita _severita, String _noteOpzionali, short IDV, String NC
                eventiAvv.add(new EventoAvversoTMP(eventiAvvLetti.get(i).evento, eventiAvvLetti.get(i).severita, eventiAvvLetti.get(i).noteOpzionali, eventiAvvLetti.get(i).IDVaccinazione, nomeCognome));
            }
        }catch(Exception E)
        {
            System.out.println(E);
        }

        //creo oggetti che rappresentano colonna tabella
        JFXTreeTableColumn<EventoAvversoTMP,String> committente = new JFXTreeTableColumn<>("Committente");
        committente.setPrefWidth(100);
        committente.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<EventoAvversoTMP, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<EventoAvversoTMP, String> eventoAvversoTMPStringCellDataFeatures) {
                return eventoAvversoTMPStringCellDataFeatures.getValue().getValue().nomeCognome2;
            }
        });

        JFXTreeTableColumn<EventoAvversoTMP,String> evento = new JFXTreeTableColumn<>("Evento");
        evento.setPrefWidth(50);
        evento.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<EventoAvversoTMP, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<EventoAvversoTMP, String> eventoAvversoTMPStringCellDataFeatures) {
                return eventoAvversoTMPStringCellDataFeatures.getValue().getValue().evento2;
            }
        });

        JFXTreeTableColumn<EventoAvversoTMP,String> severita = new JFXTreeTableColumn<>("Severità");
        severita.setPrefWidth(80);
        severita.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<EventoAvversoTMP, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<EventoAvversoTMP, String> eventoAvversoTMPStringCellDataFeatures) {
                return eventoAvversoTMPStringCellDataFeatures.getValue().getValue().severita2;
            }
        });
        
        tmp = eventiAvv;

        final TreeItem<EventoAvversoTMP> root = new RecursiveTreeItem<EventoAvversoTMP>(eventiAvv, RecursiveTreeObject::getChildren);
        LWEventiAvversi.getColumns().setAll(committente, evento, severita);
        LWEventiAvversi.setRoot(root);
        LWEventiAvversi.setShowRoot(false);

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
            List<UtenteVaccinato> lista = leggi.leggiVaccinati(); //il classes.nome classe l'ho messo solo perchè a un certo punto aveva iniziato a dare fastidio a caso

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

            //System.out.println("contatori: " + countAZ + " "+ countJej + " "+ countMod + " "+ countPft);

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
            Parent root;
            root = (Parent) fxmlLoader.load();
            Registrazione controller = fxmlLoader.getController();

            controller.inizializza(txtNome.getText()); //passaggio del nome del centro vaccinale

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Scheda Registrazione");
            Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
            stage.setX((int)size.getWidth()/2 + 170);
            stage.setY((int)size.getHeight()/2 - 350);
            stage.setScene(scene);
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
