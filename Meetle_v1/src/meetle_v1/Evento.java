package meetle_v1;

import java.util.ArrayList;
import meetle_v1.campi.*;

public abstract class Evento {
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

    public Evento(String nome, String descrizione, CampoString titolo, 
            CampoString luogo, CampoString note, CampoInt numPartecipanti, 
            CampoInt quotaIndividuale, CampoInt compresoQuota, 
            CampoData termineIscrizione, CampoData data, CampoData dataConclusiva, 
            CampoOra ora, CampoOra oraConclusiva, CampoDurata durata) {
        
        /*
        this.nome = nome;
        this.descrizione = descrizione;
        this.titolo = titolo;
        this.luogo = luogo;mpoInt compresoQuota, 
            CampoData termineIscrizione, CampoData data, CampoData dataConclusiva, 
            CampoOra ora, CampoOra oraConclusiva, CampoDurata durata) {
        
        /*
        this.nome = nome;
        this.note = note;
        this.numPartecipanti = numPartecipanti;
        this.quotaIndividuale = quotaIndividuale;
        this.compresoQuota = compresoQuota;
        this.termineIscrizione = termineIscrizione;
        this.data = data;
        this.dataConclusiva = dataConclusiva;
        this.ora = ora;
        this.oraConclusiva = oraConclusiva;
        this.durata = durata;
        */
        
        campiFissi = new Campo[NUM_CAMPI_FISSI];
        campiFissi[I_TITOLO] = titolo;
        campiFissi[I_NUM_PARTECIPANTI] = numPartecipanti;
        campiFissi[I_TERMINE_ISCRIZIONE] = termineIscrizione;
        campiFissi[I_LUOGO] = luogo;
        campiFissi[I_DATA] = data;
        campiFissi[I_ORA] = ora;
        campiFissi[I_DURATA] = durata;
        campiFissi[I_QUOTA_INDIVIDUALE] = quotaIndividuale;
        campiFissi[I_COMPRESO_QUOTA] = compresoQuota;
        campiFissi[I_DATA_CONCLUSIVA] = dataConclusiva;
        campiFissi[I_ORA_CONCLUSIVA] = oraConclusiva;
        campiFissi[I_NOTE] = note;
        
        
        this.campiAggiuntivi = new ArrayList<>();
        
        setFacoltativi();
    }

    private void setFacoltativi() {
        campiFissi[I_TITOLO].setFacoltativo();
        campiFissi[I_DURATA].setFacoltativo();
        campiFissi[I_QUOTA_INDIVIDUALE].setFacoltativo();
        campiFissi[I_DATA_CONCLUSIVA].setFacoltativo();
        campiFissi[I_ORA_CONCLUSIVA].setFacoltativo();
    } 
    
}
