package meetle.eventi.campi;

import meetle.eventi.campi.tempo.Data;

public class CampoData extends Campo<Data> {
    
    public CampoData(String nome, String descrizione, Data valore) {
        super(nome, descrizione, valore);
    }
    
    public CampoData(String nome, String descrizione, int anno, int mese, int giorno) {
        this(nome, descrizione, new Data(anno, mese, giorno));
    }   
    
    public CampoData(String nome, String descrizione) {
        this(nome, descrizione, null);
    }

    @Override
    public void setValoreDaString(String stringa) {
        this.valore = new Data(stringa);
    }
    
    
}