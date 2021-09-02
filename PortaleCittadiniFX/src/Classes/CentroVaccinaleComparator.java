package Classes;

import java.util.Comparator;

public class CentroVaccinaleComparator  implements Comparator<CentroVaccinale> {

    public String comparatore;

    public CentroVaccinaleComparator (String comp)
    {
        comparatore = comp;
    }

    /*
    /**
     * metodo equals che fa overload dio equals e permette di paragonare sia per ordine alfabetico che per vicinanza (simulata)
     *
     * @param c1                centro vaccinale parametro
     *
     *
    @Override
    public boolean equals(Object c1)
    {
        if(comparatore.equals("alfabeto"))
        {
            if(this.nome.equals(((CentroVaccinale)c1).nome))
            {
                return true;
            }
        }

        if(comparatore.equals("vicinanza"))
        {
            if(this.indirizzo.comune.equals(((CentroVaccinale)c1).indirizzo.comune))
            {
                return true;
            }
        }

        return  false;
    } */

    @Override
    public int compare(CentroVaccinale o1, CentroVaccinale o2) {
        if(comparatore.equals("alfabeto"))
        {
            return o1.nome.compareTo(o2.nome);
        }
        else if(comparatore.equals("vicinanza"))
        {
            return o1.nome.compareTo(o2.nome);
        }

        return -1;
    }
}
