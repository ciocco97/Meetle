package meetle.campi;

public class CampoString extends Campo {

    public CampoString(String nome, String descrizione, String valore) {
        super(nome, descrizione);
        this.valore = valore;
    }
    
    public CampoString(String valore) {
        this("Campo Stringa", NO_DESCRIPTION, valore);       
    }    
        
}
