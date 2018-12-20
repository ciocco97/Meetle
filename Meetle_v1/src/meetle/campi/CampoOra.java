package meetle.campi;

import meetle.campi.tempo.Ora;

public class CampoOra extends Campo {
    
    public CampoOra(String nome, String descrizione, int ora, int minuti) {
        super(nome, descrizione);
        this.valore = new Ora(ora, minuti);
    }
    
    public CampoOra(String nome, String descrizione, Ora ora) {
        super(nome, descrizione);
        this.valore = ora;
    }
    
    public CampoOra(int ora, int minuti) {
        this("Campo Ora", NO_DESCRIPTION, ora, minuti);
    }
    
    public CampoOra(Ora ora) {
        this("Campo Ora", NO_DESCRIPTION, ora);
    }
    
}
