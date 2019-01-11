package meetle.campi;

public abstract class Campo <V> {
    protected final static String NO_DESCRIPTION = "Nessuna descrizione presente"; 
    
    protected String nome, descrizione;
    protected V valore;    
    private boolean facoltativo = false;

    public Campo(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
        // valore = null;
    }
    
    public Campo(String nome, String descrizione, V valore) {
        this(nome, descrizione);
        this.valore = valore;
    }
    
    public Campo(V valore) {
        this("Campo Generico", NO_DESCRIPTION, valore);
    }
    
    public Campo() {
        this(null);
    }

    public abstract void setValoreDaString(String stringa);

    @Override
    public String toString() { return valore.toString(); }  

    // Getters e Setters
    public String getNome() { return nome; }
    public String getDescrizione() { return descrizione; }
    public V getValore() { return valore; }   
    public boolean isFacoltativo() { return facoltativo; }     
    public void setFacoltativo(){ facoltativo = true; }    
    
}
