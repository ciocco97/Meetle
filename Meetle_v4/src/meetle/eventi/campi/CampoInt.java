package meetle.eventi.campi;

public class CampoInt extends Campo<Integer> {
    
    public CampoInt(String nome, String descrizione, Integer intero) {
        super(nome, descrizione, intero);
    }
    
    public CampoInt(String nome, String descrizione) {
        this(nome, descrizione, null);
    }

    @Override
    public void setValoreDaString(String stringaIntero) {
        valore = Integer.parseInt(stringaIntero);
    }
    
}
