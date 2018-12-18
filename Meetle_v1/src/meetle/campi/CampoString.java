package meetle.campi;

public class CampoString extends Campo {
    private String valore;

    public CampoString(String valore) {
        this.valore = valore;
    }
    
    @Override
    public String toString() {
        return valore;
    }
}
