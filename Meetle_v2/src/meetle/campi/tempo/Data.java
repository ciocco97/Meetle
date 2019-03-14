package meetle.campi.tempo;

public class Data {
    private int anno, mese, giorno;

    public Data(int anno, int mese, int giorno) {
        this.anno = anno;
        this.mese = mese;
        this.giorno = giorno;
    }

    public Data(String data) {
        String[] numeri = data.split("-");
        anno = Integer.parseInt(numeri[0]);
        mese = Integer.parseInt(numeri[1]);
        giorno = Integer.parseInt(numeri[2]);
    }

    @Override
    public String toString() {
        return anno + "-" + mese + "-" + giorno;
    }
    
}
