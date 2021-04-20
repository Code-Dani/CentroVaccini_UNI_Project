public class EventoAvverso
{

    /**
     * definisce il tipo di EventoAvverso verificato post vaccino
     * @author Claudio
     */
    public enum evento
    {
        mal_di_testa,
        febbre,
        dolori_muscolari_e_articolari,
        infoadonopatia,
        tachicardita,
        crisi_ipertensiva
    }

    /**
     * definisce la gravità con la quale si verifica EventoAvverso post vaccino
     * @author Claudio
     */
    public enum severita
    {
        molto_bassa_1,
        bassa_2,
        fastidiosa_3,
        sopportabile_4,
        insopportabile_5
    }

    public evento avversita;

    public severita gravita;

    /**
     * per l'inserimento delle note opzionali inserite dall'utente.
     * di massimo 255 caratteri
     * @author Claudio
     */
    //da vedere se tenere stringa o array di char
    public String noteOpzionali;

    public EventoAvverso(evento _evento, severita _severita, String _noteOpzionali)
    {
        avversita = _evento;
        gravita = _severita;
        noteOpzionali = _noteOpzionali;
    }
}
