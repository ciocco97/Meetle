package meetle;

import meetle.campi.*;

public abstract class Evento {
    protected final static String NO_DESCRIPTION = "Nessuna descrizione presente";     
    
    protected String nome, descrizione;
//    protected CampoString titolo, luogo, note;
//    protected CampoInt numPartecipanti, quotaIndividuale, compresoQuota;
//    protected CampoData termineIscrizione, data, dataConclusiva;
//    protected CampoOra ora, oraConclusiva;
//    protected CampoDurata durata;
    
    public static final int NUM_CAMPI_FISSI = 12;
    public static final int I_TITOLO = 0, I_NUM_PARTECIPANTI = 1, I_TERMINE_ISCRIZIONE = 2, 
            I_LUOGO = 3, I_DATA = 4, I_ORA = 5, I_DURATA = 6, I_QUOTA_INDIVIDUALE = 7, 
            I_COMPRESO_QUOTA = 8, I_DATA_CONCLUSIVA = 9, I_ORA_CONCLUSIVA = 10, I_NOTE = 11;
    
    protected Campo[] campi;    

    /**
    // Costruttore con tutti i parametri
    public Evento(String nome, String descrizione, String titolo, 
            int numPartecipanti, Data termineIscrizione, String luogo, Data data, 
            Ora ora, Durata durata, int quotaIndividuale, int compresoQuota, 
            Data dataConclusiva, Ora oraConclusiva, String note) {
                
        campiFissi = new Campo[NUM_CAMPI_FISSI];
        campiFissi[I_TITOLO] = new CampoString(titolo);
        campiFissi[I_NUM_PARTECIPANTI] = new CampoInt(numPartecipanti);
        campiFissi[I_TERMINE_ISCRIZIONE] = new CampoData(termineIscrizione);
        campiFissi[I_LUOGO] = new CampoString(luogo);
        campiFissi[I_DATA] = new CampoData(data);
        campiFissi[I_ORA] = new CampoOra(ora);
        campiFissi[I_DURATA] = new CampoDurata(durata);
        campiFissi[I_QUOTA_INDIVIDUALE] = new CampoInt(quotaIndividuale);
        campiFissi[I_COMPRESO_QUOTA] = new CampoInt(compresoQuota);
        campiFissi[I_DATA_CONCLUSIVA] = new CampoData(dataConclusiva);
        campiFissi[I_ORA_CONCLUSIVA] = new CampoOra(oraConclusiva);
        campiFissi[I_NOTE] = new CampoString(note);
        
        setFacoltativi();
    } */
    
    public Evento() {
                
        campi = new Campo[NUM_CAMPI_FISSI];
        campi[I_TITOLO] = new CampoString();
        campi[I_NUM_PARTECIPANTI] = new CampoInt();
        campi[I_TERMINE_ISCRIZIONE] = new CampoData();
        campi[I_LUOGO] = new CampoString();
        campi[I_DATA] = new CampoData();
        campi[I_ORA] = new CampoOra();
        campi[I_DURATA] = new CampoDurata();
        campi[I_QUOTA_INDIVIDUALE] = new CampoInt();
        campi[I_COMPRESO_QUOTA] = new CampoInt();
        campi[I_DATA_CONCLUSIVA] = new CampoData();
        campi[I_ORA_CONCLUSIVA] = new CampoOra();
        campi[I_NOTE] = new CampoString();      
                        
        setFacoltativi();
    }   
    
    public void setValoreDaString(int indice, String valore){
        if(indice < NUM_CAMPI_FISSI)
            campi[indice].setValoreDaString(valore);
    }

    private void setFacoltativi() {
        campi[I_TITOLO].setFacoltativo();
        campi[I_DURATA].setFacoltativo();
        campi[I_COMPRESO_QUOTA].setFacoltativo();
        campi[I_DATA_CONCLUSIVA].setFacoltativo();
        campi[I_ORA_CONCLUSIVA].setFacoltativo();
        campi[I_NOTE].setFacoltativo();
    } 
    
    
    // Getters e Setters
    public String getNome() { return nome; }
    public String getTitolo() { return campi[I_TITOLO].toString(); }   
    public String getLuogo() { return campi[I_LUOGO].toString(); }    
    public String getData() { return campi[I_DATA].toString(); }    
    public String getOra() { return campi[I_ORA].toString(); }    
    public String getNumeroPartecipanti() { return campi[I_NUM_PARTECIPANTI].toString(); }    
    public String getTermineUltimoDIscrizione() { return campi[I_TERMINE_ISCRIZIONE].toString(); }
    
}
