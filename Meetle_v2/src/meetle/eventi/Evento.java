package meetle.eventi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;
import meetle.Meetle;
import meetle.eventi.campi.*;
import org.apache.commons.lang3.ArrayUtils;

public abstract class Evento implements Serializable {
    public static final String NO_DESCRIPTION = "Nessuna descrizione presente";
    // Nomi dei campi (usati anche in IO)
    public static final String N_TITOLO = "Titolo", N_NUMERO_PARTECIPANTI = "N° Partecipanti", 
            N_TERMINE_ISCR = "Termine Iscrizione", N_LUOGO = "Luogo", N_DATA = "Data", N_ORA = "Ora", 
            N_DURATA = "Durata", N_QUOTA_INDIVIDUALE = "Quota individuale", 
            N_COMPRESO_QUOTA = "Compreso quota", N_DATA_CONCLUSIVA = "Data conclusiva", 
            N_ORA_CONCLUSIVA = "Ora conclusiva", N_NOTE = "Note";
    public static final String SEPARATORE_CAMPI = "\n";
    
    public static final int NUM_CAMPI_FISSI = 12;
    // Indici dei campi nell'array
    public static final int I_TITOLO = 0, I_NUM_PARTECIPANTI = 1, I_TERMINE_ISCRIZIONE = 2, 
            I_LUOGO = 3, I_DATA = 4, I_ORA = 5, I_DURATA = 6, I_QUOTA_INDIVIDUALE = 7, 
            I_COMPRESO_QUOTA = 8, I_DATA_CONCLUSIVA = 9, I_ORA_CONCLUSIVA = 10, I_NOTE = 11;
    
    protected String nome, descrizione;    
    protected final int ID; // identificatore univoco 
    protected Campo[] campi, campiExtra;  
    protected Stato statoCorrente;
    protected ArrayList<Stato> statiPassati;  
    protected final String creatoreID;
    protected ArrayList<String> iscrittiIDs;
    
    public Evento(String creatoreID) {                
        campi = new Campo[NUM_CAMPI_FISSI];
        campi[I_TITOLO] = new CampoString(N_TITOLO, "Titolo dell'evento");
        campi[I_NUM_PARTECIPANTI] = new CampoInt(N_NUMERO_PARTECIPANTI, "Numero massimo di partecipanti all'evento");
        campi[I_TERMINE_ISCRIZIONE] = new CampoData(N_TERMINE_ISCR, "Data di scadenza per l'iscrizione");
        campi[I_LUOGO] = new CampoString(N_LUOGO, "Luogo dove si terrà l'evento");
        campi[I_DATA] = new CampoData(N_DATA, "Data di inizio dell'evento");
        campi[I_ORA] = new CampoOra(N_ORA, "Ora di inizio dell'evento");
        campi[I_DURATA] = new CampoDurata(N_DURATA, "Durata stimata dell'evento");
        campi[I_QUOTA_INDIVIDUALE] = new CampoInt(N_QUOTA_INDIVIDUALE,  "Quota di denaro richiesta per partecipare");
        campi[I_COMPRESO_QUOTA] = new CampoInt(N_COMPRESO_QUOTA,  "Quota di denaro già compresa (?)");
        campi[I_DATA_CONCLUSIVA] = new CampoData(N_DATA_CONCLUSIVA,  "Data di fine dell'evento");
        campi[I_ORA_CONCLUSIVA] = new CampoOra(N_ORA_CONCLUSIVA, "Ora di fine dell'evento");
        campi[I_NOTE] = new CampoString(N_NOTE, "Note aggiuntive");      
                        
        setFacoltativi();
        
        statoCorrente = new Stato();
        statiPassati = new ArrayList<>();
        this.creatoreID = creatoreID;
        iscrittiIDs = new ArrayList<>();
        ID = hashCode()+(new Random()).nextInt();
    }  
    
    private void setFacoltativi() {
        campi[I_TITOLO].setFacoltativo();
        campi[I_DURATA].setFacoltativo();
        campi[I_COMPRESO_QUOTA].setFacoltativo();
        campi[I_DATA_CONCLUSIVA].setFacoltativo();
        campi[I_ORA_CONCLUSIVA].setFacoltativo();
        campi[I_NOTE].setFacoltativo();        
    }  
    
    public void setValoreDaString(int indice, String valore){
        if(indice < NUM_CAMPI_FISSI)
            campi[indice].setValoreDaString(valore);
        else
            campiExtra[indice-NUM_CAMPI_FISSI].setValoreDaString(valore);
    }
    
//    public void setTuttiValori(String[] valori) {
//        for (int i = 0; i<campi.length; i++)
//            campi[i].setValoreDaString(valori[i]);
//    }    
    
    /**
     * salva lo stato corrente tra gli stati passati e lo sostituisce con uno nuovo
     * @param nuovoIndiceStato 
     */
    private void nuovoStato(int nuovoIndiceStato){
        statiPassati.add(statoCorrente);
        statoCorrente = new Stato(nuovoIndiceStato);
    }
    
    /**
     * fa cambiare lo stato dell'evento 
     */
    public void checkStato() {
        switch(getIndiceStatoCorrente()) {
            case Stato.NONVALIDO:
                boolean ok = true;
                for(Campo c: getTuttiCampi())
                    if(!c.isFacoltativo() && c.getValore()==null) 
                        ok = false;
                if (ok)
                    nuovoStato(Stato.VALIDO); 
                break;
            case Stato.VALIDO:
                for(Campo c: getTuttiCampi())
                    if(!c.isFacoltativo() && c.getValore()==null) {
                        nuovoStato(Stato.NONVALIDO);
                        break;
                    }
                break;
            case Stato.APERTO:
                if(getNumIscritti() >= (int)campi[I_NUM_PARTECIPANTI].getValore())
                    nuovoStato(Stato.CHIUSO);
                else if (false) // la data attuale supera la data termine iscrizione
                    nuovoStato(Stato.FALLITO);
                break;
            case Stato.CONCLUSO:
                if (false) // quando la data attuale supera la data di termine evento
                    nuovoStato(Stato.CONCLUSO);
                break;
        }
    }
    
    public void apriEvento() {
        if(getIndiceStatoCorrente()==Stato.VALIDO)
            nuovoStato(Stato.APERTO);            
    }
    
    
    /**
     * dice se l'utente con questo ID è iscritto
     * @param uID
     * @return 
     */
    public boolean isUtenteIscritto(String uID) { 
        return uID.equals(creatoreID) || iscrittiIDs.contains(uID); 
    }
    
    public void switchIscrizione(String uID){
        if (isUtenteIscritto(uID))
            iscrittiIDs.remove(uID); 
        else 
            iscrittiIDs.add(uID);
    }

    @Override
    public String toString() {
        return nome +"\n"+ Stream.concat(Arrays.stream(campi), Arrays.stream(campiExtra))
                .filter(c -> !c.toString().equals(""))
                .map(c -> "\t" + c + SEPARATORE_CAMPI)
                .reduce("", String::concat);     
    }   
    
    
    // Getters e Setters
    
    public int getID() { return ID; }
    public String getNome() { return nome; }
    public Campo[] getTuttiCampi() { return (Campo[]) ArrayUtils.addAll(campi, campiExtra); }
    public int getIndiceStatoCorrente() { return statoCorrente.getIndiceStato(); }    
    public String getCreatoreID(){ return creatoreID; }
    public int getNumIscritti() { return 1+iscrittiIDs.size(); }
    
        
//    public void setTitolo(String titolo) { campi[I_TITOLO].setValoreDaString(titolo); }
//    public void setDurata(String valore){campi[I_DURATA].setValoreDaString(valore);}
//    public void setCompresoQuota(String valore){campi[I_COMPRESO_QUOTA].setValoreDaString(valore);}
//    public void setDataConclusiva(String valore){campi[I_DATA_CONCLUSIVA].setValoreDaString(valore);}
    
}
