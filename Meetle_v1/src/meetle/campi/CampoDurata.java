package meetle.campi;

import meetle.campi.tempo.Durata;

public class CampoDurata extends Campo<Durata> {
    
    public CampoDurata (String nome, String descrizione) {
        super(nome, descrizione);
    }
    
    public CampoDurata (String nome, String descrizione, Durata valore) {
        super(nome, descrizione, valore);
    }
    
    public CampoDurata (String nome, String descrizione, int giorni, int ore, int minuti) {
        this(nome, descrizione, new Durata(giorni, ore, minuti));
    }
    
    public CampoDurata(Durata durata) {
        this("Campo Durata", NO_DESCRIPTION, durata);
    }
    
    public CampoDurata(int giorni, int ore, int minuti) {
        this(new Durata(giorni, ore, minuti));
    }
    
    public CampoDurata() {
        this(null);
    }

    @Override
    public void setValoreDaString(String stringa) {
        this.valore = new Durata(stringa);
    }
    
    
    
    
}
