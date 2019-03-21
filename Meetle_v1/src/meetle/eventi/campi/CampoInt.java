package meetle.eventi.campi;

public class CampoInt extends Campo<Integer> {
    
    public CampoInt(String nome, String descrizione, Integer valore) {
        super(nome, descrizione, valore);
    }
    
    public CampoInt(String nome, String descrizione) {
        this(nome, descrizione, null);
    }

    @Override
    public void setValoreDaString(String stringa) {
        valore = Integer.parseInt(stringa);
    }
    
}
