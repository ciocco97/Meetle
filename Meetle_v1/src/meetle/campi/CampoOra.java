package meetle.campi;

import meetle.campi.tempo.Ora;

public class CampoOra extends Campo {
    
    public CampoOra(String nome, String descrizione, int ora, int minuti) {
        super(nome, descrizione);
        this.valore = new Ora(ora, minuti);
    }
    
    public CampoOra(int ora, int minuti) {
        this("Campo Ora", NO_DESCRIPTION, ora, minuti);
    }  
    
}
