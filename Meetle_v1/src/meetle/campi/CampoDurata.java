package meetle.campi;

import meetle.campi.tempo.Durata;

public class CampoDurata extends Campo {
    
    public CampoDurata (String nome, String descrizione, int giorni, int ore, int minuti) {
        super(nome, descrizione);
        this.valore =  new Durata(giorni, ore, minuti);
    }
    
    public CampoDurata (String nome, String descrizione, Durata durata) {
        super(nome, descrizione);
        this.valore =  durata;
    }
    
    public CampoDurata(int giorni, int ore, int minuti) {
        this("Campo Durata", NO_DESCRIPTION, giorni, ore, minuti);
    }
    
    public CampoDurata(Durata durata) {
        this("Campo Durata", NO_DESCRIPTION, durata);
    }
    
    
}
