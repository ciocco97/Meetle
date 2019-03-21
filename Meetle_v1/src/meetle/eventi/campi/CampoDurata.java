package meetle.eventi.campi;

import meetle.eventi.campi.tempo.Durata;

public class CampoDurata extends Campo<Durata> {
    
    public CampoDurata (String nome, String descrizione, Durata valore) {
        super(nome, descrizione, valore);
    }
    
    public CampoDurata (String nome, String descrizione, int giorni, int ore, int minuti) {
        this(nome, descrizione, new Durata(giorni, ore, minuti));
    }
    
    public CampoDurata (String nome, String descrizione) {
        this(nome, descrizione, null);
    }

    @Override
    public void setValoreDaString(String stringa) {
        this.valore = new Durata(stringa);
    }    
    
}
