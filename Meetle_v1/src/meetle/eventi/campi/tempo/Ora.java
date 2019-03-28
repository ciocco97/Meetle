package meetle.eventi.campi.tempo;

import java.io.Serializable;

public class Ora implements Serializable {
    private int ora, minuti;

    public Ora(int ora, int minuti) {
        this.ora = ora;
        this.minuti = minuti;
    }
    
    public Ora(String stringaOra) {
        String[] numeri = stringaOra.split(":");
        this.ora = Integer.parseInt(numeri[0]);
        this.minuti = Integer.parseInt(numeri[1]);
    }
    
    @Override
    public String toString() {
        return ora + ":" + minuti;
    }
    
}
