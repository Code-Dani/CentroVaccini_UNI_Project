package Classes;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * classe usata per gestire in modo temporaneo gli eventi avversi nel programma;
 * questa classe non andrà a creare istanze per il database ma andrà a creare istanze di tmp da inserire in list view e parti della GUI.
 * @author De Nicola Cristian (doc)
 * @author Francesco Cavallini (codice)
 * @since 08/11/2021
 */
public class EventoAvversoTMP extends RecursiveTreeObject<EventoAvversoTMP>
{
    public Evento evento;
    public Severita severita;
    public short IDVaccinazione;
    public String nomeCognome;
    public String noteOpzionali;

    public StringProperty evento2;
    public StringProperty severita2;
    public StringProperty nomeCognome2;

    /**
     * costruttore della classe EventoAvversoTMP
     * @param _evento evento scelto tra l'enum della classe Evento (fatto in base alla scelta dell'utente).
     * @param _severita severità con cui l'utente sente quel sintomo: va da 0, molto bassa a 100, insopportabile.
     * @param _noteOpzionali note che l'utente a sua scelta può decidere di inserire come commento in più al suo malessere.
     * @param IDV ID dell'utente, usato come chiave esterna per poter legare correttamente l'evento all'utente.
     * @param NC stringa di nome+cognome per far vedere come si chiamasse l'utente che ha avuto quel malessere.
     * @author De Nicola Cristian
     * @since 08/10/2021
     */
    public EventoAvversoTMP(Evento _evento, Severita _severita, String _noteOpzionali, short IDV, String NC)
    {
        evento = _evento;
        severita = _severita;
        noteOpzionali = _noteOpzionali;
        IDVaccinazione = IDV;
        nomeCognome = NC;

        evento2 = new SimpleStringProperty(evento.toString());
        severita2 = new SimpleStringProperty(severita.name());
        nomeCognome2 = new SimpleStringProperty(nomeCognome);
    }

    /**
     * Metodo ToString della classe EventoAvversoTMP; richiamandolo ritorna le variabili presenti in questa classe
     * @return Variabili della classe EventoAvversoTMP.
     * @author De Nicola Cristian
     * @since 26/04/2021
     */
    @Override
    public String toString() {
        return "EventoAvversoTMP{" +
                "evento=" + evento +
                ", severita=" + severita +
                ", IDVaccinazione=" + IDVaccinazione +
                ", nomeCognome='" + nomeCognome + '\'' +
                ", noteOpzionali='" + noteOpzionali + '\'' +
                '}';
    }
}
