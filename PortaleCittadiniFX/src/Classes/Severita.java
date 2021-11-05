package Classes;

/**
 *definisce la gravità con la quale si verifica EventoAvverso post vaccino
 *@author Menegotto Caludio
 *@author Cavalli Francesco
 */
public enum Severita
{
    molto_bassa_1{ public  String toString(){return "Molto Bassa (1)";} },
    bassa_2{ public  String toString(){return "Bassa (2)";} },
    fastidiosa_3{ public  String toString(){return "Fastidiosa (3)";} },
    sopportabile_4{ public  String toString(){return "Sopportabile (4)";} },
    insopportabile_5{ public  String toString(){return "Insopportabile (5)";} }
}

///ATTENZIONE
///IL MODO IN CUI ABBIAMO GESTITO IL CONTROLLO DELLA SEVERITA' E' DIVERSO DA QUESTO ENUM!!