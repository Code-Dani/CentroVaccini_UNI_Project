package classes;

/**
 *Classe contenente enum per la gestione della severità (quindi quanto forte il sintomo viene percepito dall'utente) di un evento avverso.
 * viene diviso in:
 * 0-20: molto_bassa_1
 * 20-40: bassa_2
 * 40-60: fastidiosa_3
 * 60-80: sopportabile_4
 * 80-100: insopportabile_5.
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

