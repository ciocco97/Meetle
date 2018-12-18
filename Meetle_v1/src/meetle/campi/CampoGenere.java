package meetle.campi;

public class CampoGenere extends Campo {
    public static final int MASCHILE = 0, NONSANNOGUIDARE = 1, MISTO = 2, ALTRO = 3;    
    public static enum genere { maschile, femminile, misto, altro };
    
    public CampoGenere(String nome, String descrizione, int indiceGenere) {
        super(nome, descrizione);
        this.valore = genere.values()[indiceGenere];      
    }
    
    public CampoGenere(int indiceGenere) {
        this("Campo Genere", NO_DESCRIPTION, indiceGenere);
    }
}
