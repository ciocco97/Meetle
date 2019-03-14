package meetle.campi;

public class CampoString extends Campo<String> {

//    public CampoString(String nome, String descrizione) {
//        super(nome, descrizione);
//    }
    
    public CampoString(String nome, String descrizione, String valore) {
        super(nome, descrizione, valore);
    }
    
    public CampoString(String valore) {
        this("Campo Stringa", NO_DESCRIPTION, valore);       
    }    
    
    public CampoString(){
        this(null);
    }
    
    @Override
    public void setValoreDaString(String stringa){
        this.valore = stringa;
    }
        
}
