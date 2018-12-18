package meetle.campi;

public abstract class Campo <V> {
    protected final static String NO_DESCRIPTION = "Nessuna descrizione presente"; 
    
    protected String nome, descrizione;
    protected boolean facoltativo = false;
    protected V valore;

    public Campo(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
    }
    
    public Campo(String nome, String descrizione, V valore) {
        this(nome, descrizione);
        this.valore = valore;
    }
    
    public Campo(V valore) {
        this("Campo Generico", NO_DESCRIPTION, valore);
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }
    
    public void setFacoltativo(){
        facoltativo = true;
    }   

    @Override
    public String toString() {
        return valore.toString();
    }
    
    
    
}
