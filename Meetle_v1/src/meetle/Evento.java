package meetle;

import meetle.campi.*;
import meetle.campi.tempo.*;
import java.util.ArrayList;

public abstract class Evento {
    protected final static String NO_DESCRIPTION = "Nessuna descrizione presente";     
    
    protected String nome, descrizione; /*
    protected CampoString titolo, luogo, note;
    protected CampoInt numPartecipanti, quotaIndividuale, compresoQuota;
    protected CampoData termineIscrizione, data, dataConclusiva;
    protected CampoOra ora, oraConclusiva;
    protected CampoDurata durata; */
    
    private static final int NUM_CAMPI_FISSI = 12;
    public static final int I_TITOLO = 0, I_NUM_PARTECIPANTI = 1, I_TERMINE_ISCRIZIONE = 2, 
            I_LUOGO = 3, I_DATA = 4, I_ORA = 5, I_DURATA = 6, I_QUOTA_INDIVIDUALE = 7, 
            I_COMPRESO_QUOTA = 8, I_DATA_CONCLUSIVA = 9, I_ORA_CONCLUSIVA = 10, I_NOTE = 11;
    protected Campo[] campiFissi;
    
    protected ArrayList<Campo> campiAggiuntivi;

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
        
        
        this.campiAggiuntivi = new ArrayList<>();
        
        setFacoltativi();
    }
    
    public Evento() {
        this("", NO_DESCRIPTION, "Senza Titolo", 0, null, "", null, null, null, 0, 0, null, null, "");      
    }
    

    private void setFacoltativi() {
        campiFissi[I_TITOLO].setFacoltativo();
        campiFissi[I_DURATA].setFacoltativo();
        campiFissi[I_QUOTA_INDIVIDUALE].setFacoltativo();
        campiFissi[I_DATA_CONCLUSIVA].setFacoltativo();
        campiFissi[I_ORA_CONCLUSIVA].setFacoltativo();
    } 
    
    public String getTitolo() { return campiFissi[I_TITOLO].toString(); }
    
    public String getNome() { return nome; }
    
    public String getLuogo() { return campiFissi[I_LUOGO].toString(); }
    
    public String getData() { return campiFissi[I_DATA].toString(); }
    
    public String getOra() { return campiFissi[I_ORA].toString(); }
    
    public String getNumeroPartecipanti() { return campiFissi[I_NUM_PARTECIPANTI].toString(); }
    
    public String getTermineUltimoDIscrizione() { return campiFissi[I_TERMINE_ISCRIZIONE].toString(); }
    
}
