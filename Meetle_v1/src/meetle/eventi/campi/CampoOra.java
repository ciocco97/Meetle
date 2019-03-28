package meetle.eventi.campi;

import meetle.eventi.campi.tempo.Ora;

public class CampoOra extends Campo<Ora> {
   
    public CampoOra(String nome, String descrizione, Ora ora) {
        super(nome, descrizione, ora);
    }
    
    public CampoOra(String nome, String descrizione, int ore, int minuti) {
        this(nome, descrizione, new Ora(ore, minuti));
    }
    
    public CampoOra(String nome, String descrizione) {
        this(nome, descrizione, null);
    }

    @Override
    public void setValoreDaString(String stringaOra) {
        this.valore = new Ora(stringaOra);
    }
    
}
