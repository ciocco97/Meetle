package meetle.campi;

import meetle.campi.tempo.Data;

public class CampoData extends Campo<Data> {
    
//    public CampoData(String nome, String descrizione) {
//        super(nome, descrizione);
//    }
    
    public CampoData(String nome, String descrizione, Data valore) {
        super(nome, descrizione, valore);
    }
    
    public CampoData(String nome, String descrizione, int anno, int mese, int giorno) {
        this(nome, descrizione, new Data(anno, mese, giorno));
    }   
    
    public CampoData(Data data) {
        this("Campo Data", NO_DESCRIPTION, data);
    }
    
    public CampoData(int anno, int mese, int giorno) {
        this(new Data(anno, mese, giorno));
    }  
        
    public CampoData() {
        this(null);
    }

    @Override
    public void setValoreDaString(String stringa) {
        this.valore = new Data(stringa);
    }
    
    
}