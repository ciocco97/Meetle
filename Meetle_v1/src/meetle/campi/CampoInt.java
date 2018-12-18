package meetle.campi;

public class CampoInt extends Campo {

    public CampoInt(String nome, String descrizione, int valore) {
        super(nome, descrizione);
        this.valore = valore;        
    }
    
    public CampoInt(int valore) {
        this("Campo Int", NO_DESCRIPTION, valore);
    }    
    
}
