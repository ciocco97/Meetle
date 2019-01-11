package meetle.campi;

public class CampoGenere extends Campo {
    public static final int MASCHILE = 0, NONSANNOGUIDARE = 1, MISTO = 2, ALTRO = 3;    
    private static enum genere { maschile, femminile, misto, altro };
    
    public CampoGenere(String nome, String descrizione) {
        super(nome, descrizione);
    }
    
    public CampoGenere(String nome, String descrizione, int indiceGenere) {
        super(nome, descrizione, genere.values()[indiceGenere]);
    }
    
    public CampoGenere(int indiceGenere) {
        this("Campo Genere", NO_DESCRIPTION, indiceGenere);
    }
    
    // da sistemare
    public CampoGenere() {
        this(MASCHILE);
        this.valore = null;
    }

    @Override
    public void setValoreDaString(String stringa) {
        this.valore = genere.values()[Integer.parseInt(stringa)];
    }
    
    
}
