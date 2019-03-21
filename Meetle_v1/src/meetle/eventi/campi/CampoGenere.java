package meetle.eventi.campi;

public class CampoGenere extends Campo {
    public static final int MASCHILE = 0, NONSANNOGUIDARE = 1, MISTO = 2, ALTRO = 3;    
    private static enum genere { maschile, femminile, misto, altro };
    
    public CampoGenere(String nome, String descrizione, int indiceGenere) {
        super(nome, descrizione, genere.values()[indiceGenere]);
    }
    
    public CampoGenere(String nome, String descrizione) {
        this(nome, descrizione, ALTRO);
    }

    /**
     * @param indiceGenere indice corrispondente dell'enum dei generi
     */
    @Override
    public void setValoreDaString(String indiceGenere) {
        this.valore = genere.values()[Integer.parseInt(indiceGenere)];
    }
    
}
