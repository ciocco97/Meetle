package meetle.campi;

import meetle.campi.tempo.Ora;

public class CampoOra extends Campo<Ora> {
    
//    public CampoOra(String nome, String descrizione) {
//        super(nome, descrizione);
//    }
    
    public CampoOra(String nome, String descrizione, Ora ora) {
        super(nome, descrizione, ora);
    }
    
    public CampoOra(String nome, String descrizione, int ore, int minuti) {
        super(nome, descrizione, new Ora(ore, minuti));
    }
    
    public CampoOra(Ora ora) {
        this("Campo Ora", NO_DESCRIPTION, ora);
    }
    
    public CampoOra(int ore, int minuti) {
        this(new Ora(ore, minuti));
    }
    
    public CampoOra() {
        this(null);
    }

    @Override
    public void setValoreDaString(String stringa) {
        this.valore = new Ora(stringa);
    }
    
}
