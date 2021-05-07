package sample;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe della window grafica "MainWindow"
 * @since 05/05/2021
 * @author Satriano Daniel
 */

public class MainWindow implements Initializable {

    // ObservableList<Storico> storici = FXCollections.observableArrayList();

    @FXML
    private JFXListView<?> LV_centri;

    @FXML
    private JFXButton BT_addCentroVacc;

    @FXML
    private JFXTreeTableView<Storico> TTV_storico;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AdjustTableTreeView();  //
    }

    /**
     * Metodo usato per settare le colonne della TableTreeView
     */
    private void AdjustTableTreeView(){
        //Colonna azione
        JFXTreeTableColumn<Storico, String> azione = new JFXTreeTableColumn<>("Azione");
        azione.setPrefWidth(200);
        azione.setReorderable(false);
        azione.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Storico, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Storico, String> param) {
                return param.getValue().getValue().azione;
            }
        });

        //Colonna Data somministrazione
        JFXTreeTableColumn<Storico, String> data = new JFXTreeTableColumn<>("Data");
        data.setPrefWidth(200);
        data.setReorderable(false);

        data.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Storico, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Storico, String> param) {
                return param.getValue().getValue().dataSomministrazione;
            }
        });

        //Colonna Ora somministrazione
        JFXTreeTableColumn<Storico, String> ora = new JFXTreeTableColumn<>("Ora");
        ora.setPrefWidth(200);
        ora.setReorderable(false);
        ora.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Storico, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Storico, String> param) {
                return param.getValue().getValue().oraSomministrazione;
            }
        });



        //SERVE PER IMPLEMENTARE LA POSSIBILITA' DI RIORDINARE I RISULTATI SULLA WINDOW FINALE
        /*
        final TreeItem<Storico> root = new RecursiveTreeItem<Storico>(storici, RecursiveTreeObject::getChildren);
        TTV_storico.setRoot(root);
        TTV_storico.setShowRoot(false);*/
        TTV_storico.getColumns().setAll(azione,data,ora);
    }
}
