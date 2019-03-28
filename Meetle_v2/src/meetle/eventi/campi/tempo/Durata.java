package meetle.eventi.campi.tempo;

import java.io.Serializable;

public class Durata implements Serializable {
    private int giorni, ore, minuti;

    public Durata(int giorni, int ore, int minuti) {
        this.giorni = giorni;
        this.ore = ore;
        this.minuti = minuti;
    }
    
    public Durata(String stringaDurata){
        String[] numeri = stringaDurata.split(":");
        giorni = Integer.parseInt(numeri[0]);
        ore = Integer.parseInt(numeri[1]);
        minuti = Integer.parseInt(numeri[2]);
    }

    @Override
    public String toString() {
        return giorni + "g "+ ore + "o " + minuti + "m";
    }
    
}
