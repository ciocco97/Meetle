package meetle.eventi;

import meetle.utenti.Osservatore;
import meetle.eventi.stati.StatoNonValido;
import meetle.eventi.stati.Stato;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import meetle.Meetle;
import meetle.eventi.campi.*;
import meetle.gui.testo.Dizionario;
import meetle.utenti.Utente;
import org.apache.commons.lang3.ArrayUtils;

public abstract class Evento implements Serializable, Osservabile {   
    public static final String NO_DESCRIPTION = "Nessuna descrizione presente";
    public static final String SEPARATORE_CAMPI = "\n";
    
    public static final int NUM_CAMPI_FISSI = 14;
    // Indici dei campi nell'array
    public static final int I_TITOLO = 0, I_NUM_PARTECIPANTI = 1, I_TERMINE_ISCRIZIONE = 2, 
            I_LUOGO = 3, I_DATA = 4, I_ORA = 5, I_DURATA = 6, I_QUOTA_INDIVIDUALE = 7, 
            I_COMPRESO_QUOTA = 8, I_DATA_CONCLUSIVA = 9, I_ORA_CONCLUSIVA = 10, I_NOTE = 11,
            I_TOLLERANZA_PARTECIPANTI = 12, I_DATA_RITIRO_ISCRIZIONE = 13;
    
    protected String nome, descrizione, categoria;    
    protected final int ID; // identificatore univoco 
    protected Campo[] campi, campiExtra, campiSpesa;  
    protected Stato statoCorrente;
    protected List<Stato> statiPassati;
    protected final String creatoreID;
//    protected ArrayList<String> iscrittiIDs; // non più necessario, facciamo tutto con l'hashmap
    protected HashMap<String, String> iscritti;
    protected List<Osservatore> osservatori;
//    private transient final Meetle meetle;
    
    public Evento(Utente tenteCreatore, String categoria) {                
        campi = new Campo[NUM_CAMPI_FISSI];
        campi[I_TITOLO] = new CampoString(Dizionario.get(Dizionario.NOME_TITOLO), Dizionario.get(Dizionario.DESC_TITOLO));
        campi[I_NUM_PARTECIPANTI] = new CampoInt(Dizionario.get(Dizionario.NOME_NUM_PARTECIPANTI), Dizionario.get(Dizionario.DESC_NUM_PARTECIPANTI));
        campi[I_TERMINE_ISCRIZIONE] = new CampoData(Dizionario.get(Dizionario.NOME_TERMINE_ISCRIZIONE), Dizionario.get(Dizionario.DESC_TERMINE_ISCRIZIONE));
        campi[I_LUOGO] = new CampoString(Dizionario.get(Dizionario.NOME_LUOGO), Dizionario.get(Dizionario.DESC_LUOGO));
        campi[I_DATA] = new CampoData(Dizionario.get(Dizionario.NOME_DATA), Dizionario.get(Dizionario.DESC_DATA));
        campi[I_ORA] = new CampoOra(Dizionario.get(Dizionario.NOME_ORA), Dizionario.get(Dizionario.DESC_ORA));
        campi[I_DURATA] = new CampoDurata(Dizionario.get(Dizionario.NOME_DURATA), Dizionario.get(Dizionario.NOME_DURATA));
        campi[I_QUOTA_INDIVIDUALE] = new CampoInt(Dizionario.get(Dizionario.NOME_QUOTA_INDIVIDUALE),  Dizionario.get(Dizionario.DESC_QUOTA_INDIVIDUALE));
        campi[I_COMPRESO_QUOTA] = new CampoInt(Dizionario.get(Dizionario.NOME_COMPRESO_QUOTA),  Dizionario.get(Dizionario.DESC_COMPRESO_QUOTA));
        campi[I_DATA_CONCLUSIVA] = new CampoData(Dizionario.get(Dizionario.NOME_DATA_CONCLUSIVA),  Dizionario.get(Dizionario.DESC_DATA_CONCLUSIVA));
        campi[I_ORA_CONCLUSIVA] = new CampoOra(Dizionario.get(Dizionario.NOME_ORA_CONCLUSIVA), Dizionario.get(Dizionario.DESC_ORA_CONCLUSIVA));
        campi[I_NOTE] = new CampoString(Dizionario.get(Dizionario.NOME_NOTE), Dizionario.get(Dizionario.DESC_NOTE));
        campi[I_TOLLERANZA_PARTECIPANTI] = new CampoInt(Dizionario.get(Dizionario.NOME_TOLLERANZA_PARTECIPANTI), Dizionario.get(Dizionario.DESC_TOLLERANZA_PARTECIPANTI));
        campi[I_DATA_RITIRO_ISCRIZIONE] = new CampoData(Dizionario.get(Dizionario.NOME_DATA_RITIRO_ISCRIZIONE), Dizionario.get(Dizionario.DESC_DATA_RITIRO_ISCRIZIONE));
                        
        setFacoltativi();
        
//        statoCorrente = new StatoDeprecato();
//        statiPassati = new ArrayList<>();
        statoCorrente = new StatoNonValido(this);
        statiPassati = new ArrayList<>();
        this.creatoreID = tenteCreatore==null?"null":tenteCreatore.getUserID();
        this.categoria = categoria;
//        iscrittiIDs = new ArrayList<>();
        iscritti = new HashMap();
        osservatori = new ArrayList<>();
        registra(tenteCreatore);
        ID = /*hashCode()+*/(new Random()).nextInt();
    }  
    
    /**
     * imposta i campi che devono essere facoltativi come tali
     */
    private void setFacoltativi() {
        //campi[I_TITOLO].setFacoltativo();
        /*
        Riteniamo il titolo un parametro obbligatorio in quanto fondamentale
        poichè è il parametro principale per il riconoscimento immediato di un 
        evento, il che migliora molto l'usabilità dell'applicazione. 
        Un evento senza titolo sarebbe molto difficile da distinguere da un 
        altro simile ad esso.
        */
        campi[I_DURATA].setFacoltativo();
        campi[I_COMPRESO_QUOTA].setFacoltativo();
        campi[I_DATA_CONCLUSIVA].setFacoltativo();
        campi[I_ORA_CONCLUSIVA].setFacoltativo();
        campi[I_NOTE].setFacoltativo();        
    }  
    
    /**
     * imposta il valore di un campo
     * @param indice l'indice del campo da impostare
     * @param valore il valore da usare
     * @return true se il valore viene impostato
     */
    public boolean setValoreDaString(int indice, String valore){
        if (indice >= 0) {
            if (indice < campi.length)
                campi[indice].setValoreDaString(valore);
            else if (indice - campi.length < campiExtra.length)
                campiExtra[indice - campi.length].setValoreDaString(valore);
            else if (indice - campi.length - campiExtra.length < campiSpesa.length) 
                campiSpesa[indice - campi.length - campiExtra.length].setValoreDaString(valore);
            else 
                return false;
        } else 
            return false;
        return true;
//        if(indice < NUM_CAMPI_FISSI)
//            campi[indice].setValoreDaString(valore);
//        else
//            campiExtra[indice-NUM_CAMPI_FISSI].setValoreDaString(valore);
    } 
    
    /**
     * controlla che tutti i campi obbligatori siano compilati e 
     * che tutti i campi compilati siano coerenti
     * @return true se l'evento è valido 
     */
    public boolean checkValidita() {
        if (this.getNomeProxCampoObbligMancante()!=null) 
            return false;
        if( (getDataRitiroIscrizione().compareTo(getTermineIscrizione()) > 0) ||
                (getTermineIscrizione().compareTo(getData()) > 0) ||
                (getDataConlusiva()!=null && getData().compareTo(getDataConlusiva()) > 0) )
            return false;
        if(getNumIscrittiMax() < 2 || getTolleranzaPartecipanti() < 0)
            return false;
        return true;
    }
    
    /**
     * fa cambiare automaticamente lo stato dell'evento in base alle 
     * regole imposte tra le date, il numero di iscritti, ecc.
     */
    public void aggiornaStato() {
        statoCorrente.aggiornaStato();
    }
    
    public boolean apriEvento() {
        return statoCorrente.apriEvento();
    }
    
    public boolean ritiraEvento() {
        return statoCorrente.ritiraEvento();
    }
    
    /**
     * salva lo stato corrente tra gli stati passati e lo sostituisce con uno nuovo
     * @param nuovoStato nuovo stato
     */        
    public void nuovoStato(Stato nuovoStato) {
        statiPassati.add(statoCorrente);
        statoCorrente = nuovoStato;
        notifica();
//        if(getIndiceStatoCorrente() == Stato.APERTO)
//            messaggio2 = nomeUtente + " ha appena aperto in bacheca un evento che potrebbe interessarti *_*";
//            this.meetle.notificaIlMondoTondo(ID, messaggio2);
    }

    @Override
    public final boolean registra(Osservatore tore) {
        if(!osservatori.contains(tore))
            return osservatori.add(tore);
        return false;
    }

    @Override
    public final boolean deregistra(Osservatore tore) {
        return osservatori.remove(tore);
    }

    @Override
    public void notifica() {
        osservatori.forEach(tore -> { if(tore!=null) tore.aggiorna(this); });
    }
    
//    /**
//     * iscrive un utente se non iscritto, altrimenti lo disiscrive 
//     * @param uID id dell'utente da (dis)iscrivere
//     * @param spesa
//     * @return 
//     */
//    public boolean switchIscrizione(String uID, String spesa){
//        System.out.println("NO\n");
//        if (isUtenteIscritto(uID)){
////            iscrittiIDs.remove(uID);
//            if(uID.equals(creatoreID))
//                return false;
//            iscritti.remove(uID);
//            return true;
//        } else if (spesa != null && getNumIscrittiCorrente() < getNumIscrittiMax()) {
////            iscrittiIDs.add(uID);
//            iscritti.put(uID, spesa);
//            return true;
//        }               
//        return false;
//    }
    
    public boolean iscrivi(Utente tente, String codificaSpese) {
        if (getCreatoreID().equals(tente.getUserID()) || isUtenteIscritto(tente.getUserID()) || 
                codificaSpese==null || !isIscrivibile()) 
            return false; 
        iscritti.put(tente.getUserID(), codificaSpese);
        registra(tente);
        return true;
    }
    
    public boolean disiscrivi(Utente tente) {
        if (getCreatoreID().equals(tente.getUserID()) || !isUtenteIscritto(tente.getUserID()) || !isRitirabile())
            return false;
        iscritti.remove(tente.getUserID());
        deregistra(tente);
        return true;
    }
    
    public int calcolaImporto(String uID) {
        int importo = (int) campi[I_QUOTA_INDIVIDUALE].getValore();
        String spese = iscritti.get(uID);
        if(creatoreID.equals(uID))
            spese = "ttttttttttttttttt";
        for (int i = 0; i<campiSpesa.length; i++)
            importo += spese.charAt(i) == 't' ? (int) campiSpesa[i].getValore() : 0;
        return importo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Evento)
            return ID == ((Evento) obj).ID; 
        else
            return super.equals(obj); 
    }
    
/*
    @Override
    public String toString() {
        return nome +"\n"+ Arrays.stream(getTuttiCampi())
                .filter(c -> !c.toString().equals(""))
                .map(c -> "\t" + c + SEPARATORE_CAMPI)
                .reduce("", String::concat);     
    }   
*/
    
    // Getters e Setters    

    public int getID() { /*System.out.println("Evento.getID() -> ID: " + ID);*/ return ID; }
    public String getTitolo() { return (String) campi[I_TITOLO].getValore(); }
    public String getCreatoreID() { return creatoreID; }
    public String getCategoria() { return categoria; }
    public Campo[] getTuttiCampi() { return (Campo[]) ArrayUtils.addAll(ArrayUtils.addAll(campi, campiExtra), campiSpesa);}
    public Campo[] getCampiSpesa(){ return campiSpesa; }
    public String getNomeProxCampoObbligMancante(){ //restituisce il nome del prossimo campo obbligatorio non inserito, in modo che l'utente possa saperlo
        for(Campo c: this.getTuttiCampi())
            if (!c.isFacoltativo() && c.getValore() == null)
                    return c.getNome();
        return null;
    }
    public ArrayList<String> getIscrittiIDs() { return new ArrayList<>(iscritti.keySet()); }
    public int getNumIscrittiMin() { return (int) campi[I_NUM_PARTECIPANTI].getValore(); }
    public int getTolleranzaPartecipanti() { return (int) campi[I_TOLLERANZA_PARTECIPANTI].getValore(); }
    public int getNumIscrittiMax() { return getNumIscrittiMin() + getTolleranzaPartecipanti(); }
    public int getNumIscrittiCorrente() { return 1+iscritti.size(); }
    public boolean isUtenteIscritto(String uID) {  return /*uID.equals(creatoreID) ||*/ iscritti.containsKey(uID); }
    public LocalDate getTermineIscrizione() { return (LocalDate) campi[I_TERMINE_ISCRIZIONE].getValore(); }
    public LocalDate getDataRitiroIscrizione() { return (LocalDate) campi[I_DATA_RITIRO_ISCRIZIONE].getValore(); }
    public LocalDate getData() { return (LocalDate) campi[I_DATA].getValore(); }
    public LocalDate getDataConlusiva() { return (LocalDate) campi[I_DATA_CONCLUSIVA].getValore(); }
    public LocalTime getOra() { return (LocalTime) campi[I_ORA].getValore(); }
    public int getIndiceStatoCorrente() { return statoCorrente.getIndiceStato(); }   
    public boolean isIscrivibile() { return getIndiceStatoCorrente() == Stato.APERTO && getNumIscrittiCorrente() < getNumIscrittiMax() && LocalDate.now().compareTo(getTermineIscrizione()) <= 0; }
    public boolean isRitirabile() { return getIndiceStatoCorrente() == Stato.APERTO && LocalDate.now().compareTo(getDataRitiroIscrizione()) <= 0;}
//    public boolean isInvitoInviabile() { return LocalDate.now().compareTo((LocalDate) campi[I_TERMINE_ISCRIZIONE].getValore()) <= 0 && getIndiceStatoCorrente()== Stato.APERTO; }
    
}
