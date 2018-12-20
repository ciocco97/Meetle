package meetle.campi;

import meetle.campi.tempo.Data;

public class CampoData extends Campo {
    
    public CampoData(String nome, String descrizione, int anno, int mese, int giorno) {
        super(nome, descrizione);
        this.valore = new Data(anno, mese, giorno);
    }
    
    public CampoData(String nome, String descrizione, Data data) {
        super(nome, descrizione);
        this.valore = data;
    }
    
    public CampoData(int anno, int mese, int giorno) {
        this("Campo Data", NO_DESCRIPTION, anno, mese, giorno);
    }
    
    public CampoData(Data data) {
        this("Campo Data", NO_DESCRIPTION, data);
    }
    
}