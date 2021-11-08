package Classes;

import java.nio.file.Paths;

/**
 * Classe contenente gli enum con i path per raggiungere i file di salvataggio dati nel database.
 * Esistono 3 diversi file in cui vengono salvate cose diverse: CentriVaccinali.dati, Cittadini_Registrati.dati, Vaccinati_NomeCentro.dati.
 * @author Daniel Satriano
 * @since 08/10/2021
 */
public enum FilePaths
{
    CentriVaccinali{
        public String toString(){
            return System.getProperty("user.dir") + Paths.get("/../Database/CentriVaccinali.dati");
        }
    },
    CittadiniRegistrati{
        public String toString(){
            return System.getProperty("user.dir") + Paths.get("/../Database/Cittadini_Registrati.dati");
        }
    },
    VaccinatiNomeCentro{
        public String toString(){
            return System.getProperty("user.dir") + Paths.get("/../Database/Vaccinati_NomeCentroVaccinale.dati");
        }
    },
    EventiAvversi{
        public String toString(){
            return System.getProperty("user.dir") + Paths.get("/../Database/EventiAvversi.dati");
        }
    }
}
