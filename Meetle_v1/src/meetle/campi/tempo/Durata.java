package meetle.campi.tempo;

public class Durata {
    private int giorni, ore, minuti;

    public Durata(int giorni, int ore, int minuti) {
        this.giorni = giorni;
        this.ore = ore;
        this.minuti = minuti;
    }

    @Override
    public String toString() {
        return giorni + "g "+ ore + "o " + minuti + "m";
    }

    
}
