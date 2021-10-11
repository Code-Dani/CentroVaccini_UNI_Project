package Classes;

/** definisce il tipo di EventoAvverso verificato post vaccino
 * @author Menegotto Caludio
 * @author Cavalli Francesco
 * */
public enum Evento
{
    mal_di_testa{ public  String toString(){return "Mal di testa";} },
    febbre{ public  String toString(){return "Febbre";} },
    dolori_muscolari_e_articolari { public  String toString(){return "Dolori muscolari e aritcolari";} },
    infoadonopatia{ public  String toString(){return "Infoadonopatia";} },
    tachicardia{ public  String toString(){return "Tachicardia";} },
    crisi_ipertensiva{ public  String toString(){return "Crisi iper-tensiva";} },
    altro{ public  String toString(){return "... altro ...";} }
}
