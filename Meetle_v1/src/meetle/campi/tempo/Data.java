package meetle.campi.tempo;

public class Data {
    private int anno, mese, giorno;

    public Data(int anno, int mese, int giorno) {
        this.anno = anno;
        this.mese = mese;
        this.giorno = giorno;
    }

    @Override
    public String toString() {
        return anno + "-" + mese + "-" + giorno;
    }
    
    
    
}
