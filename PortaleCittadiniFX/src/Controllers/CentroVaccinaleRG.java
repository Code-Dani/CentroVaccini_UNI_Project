package Controllers;

import classes.*;
import classes.EventoAvverso;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CentroVaccinaleRG implements Initializable {

    @FXML
    private Text txtNome;

    @FXML
    private JFXComboBox<String> ComboVaccinazione;

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

    //variabile utile all'inserimento dell'evento avverso dalla window EventoAvverso
    public static classes.EventoAvverso tempEventoDaAggiungere;

    CentroVaccinale identità = null;

    public static ObservableList<EventoAvversoTMP> eventiAvv; //lista statica usata per aggiungere eventi avversi alla list view.
    ObservableList<EventoAvversoTMP> tmp; //lista temporanea.

    /**
     * metodo che viene richiamato al caricamento della finestra per l'inserimento dei dati
     * @author Cavallini Francesco
     * @since 04/10/2021
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //carico lista utenti per joinarli con i rispettivi problemi
        List<UtenteVaccinato> listaVacc = null;
        List<EventoAvverso> eventiAvvLetti = null;

        eventiAvv = FXCollections.observableArrayList();
        tmp = FXCollections.observableArrayList();

        try
        {
            //parte RMI
            try {
                DatabaseHelper db = new DatabaseHelper();
                listaVacc  = db.ScaricaVaccinati(LoginBox.nomeCecntroVaccinale);
            } catch (RemoteException x) {
                JOptionPane.showMessageDialog(null,x.getMessage().toString());
                throw new RuntimeException(x);
            } catch (NotBoundException x) {
                JOptionPane.showMessageDialog(null,x.getMessage().toString());
                throw new RuntimeException(x);
            }//fine


            //listaVacc = JsonReadWrite.leggiVaccinati();

            for(int i=0; i<listaVacc.size();i++)
            {
                String nomeCognome = null;
                if( listaVacc.get(i).idVaccinazione == eventiAvvLetti.get(i).IDVaccinazione )
                {
                    nomeCognome = listaVacc.get(i).nome + " " + listaVacc.get(i).cognome;
                }
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

        //aggiungo un listener per rendere visibilie il bottone degli eventi avversi
        LoginBox.isLogin.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                System.out.println("changed LOGIN STATUS:" + oldValue + "->" + newValue);
                BtnEventoAvverso.setVisible(newValue);
            }
        });
    }

    //contatori utilizzati per il conteggio dei vaccini fatti ai vari utenti (usati per statistica dei grafici)
    int countAZ = 0;
    int countJej = 0;
    int countMod = 0;
    int countPft = 0;

    /**
     * metodo che viene richiamato nella home per il caricamento dei dati nella finestra
     * usato anche per gesire e caricare i dati all'interno dei grafici.
     * @param m centro vaccinale utile al riempimento dei della finestra
     * @author Cavallini Francesco
     * @author De Nicola Cristian
     * @since 18/09/2021
     */
    public void setParameters(CentroVaccinale m)
    {
        txtNome.setText(m.nome);
        txtIndirizzo.setText((m.indirizzo.toString()));
        txtTipologia.setText(m.tipologia.toString());

        identità = m;

        //sessione sul login
        try
        {
            if(LoginBox.nomeCecntroVaccinale.equals(m.nome))
                BtnEventoAvverso.setVisible(LoginBox.isLogin.getValue());
        }
        catch(Exception e) {
            //se entra qui vuol dire che non è loggato e quindi LoginBox.nomeCecntroVaccinale è impostato a null
            //quindi non faccio nulla, il bottone non deve essere messo visibile se non è loggato
        }
        //fine gestione sessione

        //inizio count dei vari vaccini tramite i contatori creati precedentemente
        try
        {
            List<UtenteVaccinato> lista;
            //parte RMI
            try {
                DatabaseHelper db = new DatabaseHelper();
                lista  = db.ScaricaVaccinati(LoginBox.nomeCecntroVaccinale);
            } catch (RemoteException x) {
                JOptionPane.showMessageDialog(null,x.getMessage().toString());
                throw new RuntimeException(x);
            } catch (NotBoundException x) {
                JOptionPane.showMessageDialog(null,x.getMessage().toString());
                throw new RuntimeException(x);
            }//fine

            //JsonReadWrite leggi = new JsonReadWrite();
            //List<UtenteVaccinato> lista = leggi.leggiVaccinati();

            for(int i = 0; i < lista.size(); i++)
            {
                if(lista.get(i).nomeCentroVaccinale.equals(m.nome))
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
            //fine contatore, dati raccolti.

            //utilizzo i dati appena raccolti per riempire il pie chart all'interno della window.
            ObservableList<PieChart.Data> PieChartData= FXCollections.observableArrayList(
                    new PieChart.Data("Astrazeneca",countAZ),
                    new PieChart.Data("Johnson & johnson",countJej),
                    new PieChart.Data("Moderna",countMod),
                    new PieChart.Data("Pfizer",countPft)
            );
            chartFasce.setData(PieChartData);
            //fine caricamento dei contatori all'interno del pie chart.


        }catch(Exception E)
        {
            System.out.println(E);
        }
        //fine manipolazione dei dati e del caricamento nei grafici.

        //inizializzazione della lista
        try{
            List<UtenteVaccinato> lista;
            //parte RMI
            try {
                DatabaseHelper db = new DatabaseHelper();
                lista  = db.ScaricaVaccinati(LoginBox.nomeCecntroVaccinale);
            } catch (RemoteException x) {
                JOptionPane.showMessageDialog(null,x.getMessage().toString());
                throw new RuntimeException(x);
            } catch (NotBoundException x) {
                JOptionPane.showMessageDialog(null,x.getMessage().toString());
                throw new RuntimeException(x);
            }//fine

            for(int i=0;i<lista.size();i++)
            {
                if(lista.get(i).nomeCentroVaccinale.equals(txtNome.getText()))
                {
                    if(lista.get(i).evento!=null)
                        eventiAvv.add(new EventoAvversoTMP(lista.get(i).evento.evento,lista.get(i).evento.severita,lista.get(i).evento.noteOpzionali,lista.get(i).evento.IDVaccinazione,lista.get(i).nome+" "+lista.get(i).cognome));
                }
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage().toString());
        }
    }

    /**
     * evento che scatena l'apertura della finiestra per il login dell'utente.
     * @author Cavallini Francesco
     * @since 23/08/2021
     */
    public void btnclickLog(javafx.scene.input.MouseEvent mouseEvent)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/FXML/Login.fxml"));

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();

            stage.setScene(scene);

            Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
            stage.setX((int)size.getWidth()/2 + 170);
            stage.setY((int)size.getHeight()/2 - 350);

            Login controller = fxmlLoader.getController();
            controller.setParameters( identità );
            stage.setTitle("Scheda Login");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    /**
     * evento che scatena l'apertura della finiestra per la registrazione dell'utente.
     * @author Cavallini Francesco
     * @since 23/08/2021
     */
    public void btnClickReg(javafx.scene.input.MouseEvent mouseEvent)
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/FXML/Registrazione.fxml"));
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

    //misure della window home e settaggio a false di una variabile booleana per la gestione della window.
    private double currentWindowX;
    private double currentWindowY;
    private boolean max_min = false;

    /**
     * Evento che gestisce la chiusura della window, il restore down/maximase, il riduci a window.
     * @author Satriano Daniel
     * @since 10/05/2021
     */
    public void window_status(javafx.scene.input.MouseEvent mouseEvent)
    {
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

    /**
     * Evento che scatena l'apertura della window EventoAvversoForm.
     * A condizione che però l'utente non abbia mai inserito nessun evento avverso;
     * Se ne avesse già aggiunto uno allora non si aprirebbe nulla.
     * @author Cavallini Francesco
     */
    public void BtnEventoAvvClick(ActionEvent actionEvent)
    {
        boolean x=false;
        /*
        for(int i=0;i<eventiAvv.size();i++)
        {
            for(int j=0;j<LoginBox.listaVaccinazioni.size();j++){
                if(eventiAvv.get(i).IDVaccinazione==LoginBox.getIdVaccinazione().get(i).idVaccinazione)
                {
                    aaaaaaaaa
                    JOptionPane.showMessageDialog(null,"Hai già aggiunto un evento avverso.");
                    x=true;
                    break;
                }
            }
        } skippo il controllo tanto lo faccio dopo
         */
        if (!x)
        {
            try
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/FXML/EventoAvversoForm.fxml"));
                Parent root;
                root = (Parent) fxmlLoader.load();
                Controllers.EventoAvverso controller = fxmlLoader.getController();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Scheda Evento Avverso");
                Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
                stage.setX((int)size.getWidth()/2 + 170);
                stage.setY((int)size.getHeight()/2 - 350);
                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);

                stage.show();
            }catch(Exception E)
            {
                E.toString();
            }
        }
    }
}
