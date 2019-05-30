package meetle.eventi.campi;

public class CampoString extends Campo<String> {

    public CampoString(String nome, String descrizione, String stringa) {
        super(nome, descrizione, stringa);
    }
    
    public CampoString(String nome, String descrizione) {
        this(nome, descrizione, null);
    }
    
    @Override
    public void setValoreDaString(String stringa){
        this.valore = stringa;
    }
        
}
