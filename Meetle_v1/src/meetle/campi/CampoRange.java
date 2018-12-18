package meetle.campi;

public class CampoRange extends Campo {
       
    private int min, max;
    
    public CampoRange(String nome, String descrizione, int min, int max) {
        super(nome, descrizione);
        this.min = min;
        this.max = max;
    }
    
    public CampoRange(int min, int max) {
        this("Campo Range", NO_DESCRIPTION, min, max);
    }

    @Override
    public String toString() {
        return min + "-" + max;
    }
    
    
    
}
