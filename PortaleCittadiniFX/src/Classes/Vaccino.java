package Classes;

public enum Vaccino
{
    Pfizer,
    AstraZeneca,
    Moderna,
    JeJ {
        public String toString(){
            return "J&J";
        }
    }
}

