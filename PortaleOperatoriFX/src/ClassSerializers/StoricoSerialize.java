package ClassSerializers;

import classes.Storico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Usato per rendere la classe Storico più Serialize friendly
 * @author Daniel Satriano
 */
public class StoricoSerialize{
    public String azione;
    public String dataSomministrazione;
    public String oraSomministrazione;

    /**
     * Costruttore della classe StoricoSerialize, permette di convertire un oggetto di tipo Storico a StoricoSerialize
     * @param obj
     * @author Daniel Satriano
     */
    public StoricoSerialize(Storico obj){
        azione = obj.azione.getValue();
        dataSomministrazione = obj.azione.getValue();
        oraSomministrazione = obj.oraSomministrazione.getValue();
    }

    /**
     * Converte una ObservableList di tipo Storico a StoricoSerialize
     * @param list
     * @return ObservableList<StoricoSerialized>()
     * @author Daniel Satriano
     */
    public static ObservableList<StoricoSerialize> ListConverter(ObservableList<Storico> list){
        ObservableList<StoricoSerialize> StoricoSerialized =  FXCollections.observableArrayList();
        for(int i=0;i<list.size();i++){
            StoricoSerialized.add(new StoricoSerialize(list.get(i)));
        }
        return StoricoSerialized;
    }

    /**
     * Converte una ObservableList di tipo StoricoSerialize a Storico
     * @param list
     * @return ObservableList<Storico>()
     * @author Daniel Satriano
     */
    public static ObservableList<Storico> StoricoSer_to_Storico(ObservableList<StoricoSerialize> list){
        ObservableList<Storico> storico =  FXCollections.observableArrayList();

        for(int i=0;i<list.size();i++){
            storico.add(new Storico(list.get(i).azione,list.get(i).dataSomministrazione,list.get(i).oraSomministrazione));
        }
        return storico;
    }
}
