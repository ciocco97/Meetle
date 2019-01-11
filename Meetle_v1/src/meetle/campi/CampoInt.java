package meetle.campi;

public class CampoInt extends Campo<Integer> {
    
    public CampoInt(String nome, String descrizione) {
        super(nome, descrizione);
    }
    
    public CampoInt(String nome, String descrizione, Integer valore) {
        super(nome, descrizione, valore);
    }
    
    public CampoInt(Integer valore) {
        this("Campo Intero", NO_DESCRIPTION, valore);
    }
    
    public CampoInt() {
        this(null);
    }

    @Override
    public void setValoreDaString(String stringa) {
        valore = Integer.parseInt(stringa);
    }
    
}
