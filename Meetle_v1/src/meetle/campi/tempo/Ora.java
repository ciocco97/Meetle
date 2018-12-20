package meetle.campi.tempo;

public class Ora {
    private int ora, minuti;

    public Ora(int ora, int minuti) {
        this.ora = ora;
        this.minuti = minuti;
    }
    
    @Override
    public String toString() {
        return ora + ":" + minuti;
    }

    
}
