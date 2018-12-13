package meetle_v1.campi;

public abstract class Campo {
    protected String nome, descrizione;
    protected boolean facoltativo=false;
    
    public void setFacoltativo(){
        facoltativo=true;
    }
}
