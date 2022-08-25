package classes;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe che permette il salvataggio degli storici
 * @author Daniel Satriano
 * @author Claudio Menegotto
 * @since 30/04/2021
 */
public class Storico extends RecursiveTreeObject<Storico> {

    public StringProperty informazioniSomministrazioni;
    public StringProperty dataSomministrazione;

    /**
     * Costruttore della classe classes.Storico
     *
     * @param info <b>azione compiuta ad una data ora, ES: effettuato vaccino a nome cognome</b>
     * @param dataSomm <b>data della somministrazione del vaccino</b>
     * @author Daniel Satriano
     * @author Claudio Menegotto
     */
    public Storico(String info, String dataSomm){
        informazioniSomministrazioni = new SimpleStringProperty(info);
        dataSomministrazione = new SimpleStringProperty(dataSomm);
    }
}

