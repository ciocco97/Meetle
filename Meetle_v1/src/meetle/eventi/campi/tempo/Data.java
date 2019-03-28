package meetle.eventi.campi.tempo;

import java.io.Serializable;

public class Data implements Serializable {
    private int anno, mese, giorno;

    public Data(int anno, int mese, int giorno) {
        this.anno = anno;
        this.mese = mese;
        this.giorno = giorno;
    }

    public Data(String stringaData) {
        String[] numeri = stringaData.split("-");
        anno = Integer.parseInt(numeri[0]);
        mese = Integer.parseInt(numeri[1]);
        giorno = Integer.parseInt(numeri[2]);
    }

    @Override
    public String toString() {
        return anno + "-" + mese + "-" + giorno;
    }
    
}
