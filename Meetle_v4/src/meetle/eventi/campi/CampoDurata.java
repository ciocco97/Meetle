package meetle.eventi.campi;

import java.io.Serializable;

public class CampoDurata extends Campo<CampoDurata.Durata>{
    
    static class Durata implements Serializable{
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
            return giorni + ":"+ ore + ":" + minuti;
        }
    }
    
    public CampoDurata (String nome, String descrizione, Durata durata) {
        super(nome, descrizione, durata);
    }
    
    public CampoDurata (String nome, String descrizione, int giorni, int ore, int minuti) {
        this(nome, descrizione, new Durata(giorni, ore, minuti));
    }
    
    public CampoDurata (String nome, String descrizione) {
        this(nome, descrizione, null);
    }

    @Override
    public void setValoreDaString(String stringaDurata) {
        this.valore = new Durata(stringaDurata);
    }    
    
}
