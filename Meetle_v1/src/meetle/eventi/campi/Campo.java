package meetle.eventi.campi;

import java.io.Serializable;

public abstract class Campo <TipoCampo> implements Serializable {
    public final static String NO_DESCRIPTION = "Nessuna descrizione presente"; 
    
    public final static String SEPARATORE_NOME_VALORE = ": ";
    
    protected String nome, descrizione;
    protected TipoCampo valore;    
    private boolean facoltativo = false;

    Campo(String nome, String descrizione, TipoCampo valore) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.valore = valore;
    }

    public abstract void setValoreDaString(String stringa);

    @Override
    public String toString() {
        return valore == null? "" : nome +SEPARATORE_NOME_VALORE+ valore; 
    }  

    // Getters e Setters
    public String getNome() { return nome; }
    public String getDescrizione() { return descrizione; }
    public TipoCampo getValore() { return valore; }   
    public boolean isFacoltativo() { return facoltativo; }     
    public void setFacoltativo(){ facoltativo = true; }    
    
}
