package Classes;

/**
 * classe usata per gestire e creare un nuovo evento avverso.
 * viene richiamata quando l'utente, vaccinato, vuole inserire nel database del centro vaccinale sintomi o malori dovuti al vaccino.
 * @author Menegotto Caludio
 * @author De Nicola Cristian
 * @since 08/11/2021
 */
public class EventoAvverso
{
    public Evento evento;
    public Severita severita;
    public short IDVaccinazione;
    public String noteOpzionali;

    /**
     * costruttore della classe Evento avverso, usa
     * @param _evento evento scelto tra l'enum della classe Evento (fatto in base alla scelta dell'utente).
     * @param _severita severità con cui l'utente sente quel sintomo: va da 0, molto bassa a 100, insopportabile.
     * @param _noteOpzionali note che l'utente a sua scelta può decidere di inserire come commento in più al suo malessere.
     * @param IDV ID dell'utente, usato come chiave esterna per poter legare correttamente l'evento all'utente.
     * @author De Nicola Cristian
     * @since 08/11/2021
     */
    public EventoAvverso(Evento _evento, Severita _severita, String _noteOpzionali, short IDV)
    {
        evento = _evento;
        severita = _severita;
        noteOpzionali = _noteOpzionali;
        IDVaccinazione = IDV;
    }
}