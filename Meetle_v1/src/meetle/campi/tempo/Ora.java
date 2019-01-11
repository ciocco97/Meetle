package meetle.campi.tempo;

public class Ora {
    private int ora, minuti;

    public Ora(int ora, int minuti) {
        this.ora = ora;
        this.minuti = minuti;
    }
    
    public Ora(String ora) {
        String[] numeri = ora.split(":");
        this.ora = Integer.parseInt(numeri[0]);
        this.minuti = Integer.parseInt(numeri[1]);
    }
    
    @Override
    public String toString() {
        return ora + ":" + minuti;
    }
    
}
