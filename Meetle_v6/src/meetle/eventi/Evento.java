package meetle.eventi;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import meetle.Meetle;
import meetle.eventi.campi.*;
import org.apache.commons.lang3.ArrayUtils;

public abstract class Evento implements Serializable {   
    public static final String NO_DESCRIPTION = "Nessuna descrizione presente";
    public static final String SEPARATORE_CAMPI = "\n";
    
    public static final int NUM_CAMPI_FISSI = 14;
    // Indici dei campi nell'array
    public static final int I_TITOLO = 0, I_NUM_PARTECIPANTI = 1, I_TERMINE_ISCRIZIONE = 2, 
            I_LUOGO = 3, I_DATA = 4, I_ORA = 5, I_DURATA = 6, I_QUOTA_INDIVIDUALE = 7, 
            I_COMPRESO_QUOTA = 8, I_DATA_CONCLUSIVA = 9, I_ORA_CONCLUSIVA = 10, I_NOTE = 11,
            I_TOLLERANZA_PARTECIPANTI = 12, I_DATA_RITIRO_ISCRIZIONE = 13;
    
    // Nomi dei campi (usati anche in IO)
    public static final String N_TITOLO = "Titolo", N_NUMERO_PARTECIPANTI = "N° Partecipanti", 
            N_TERMINE_ISCR = "Termine Iscrizione", N_LUOGO = "Luogo", N_DATA = "Data", N_ORA = "Ora", 
            N_DURATA = "Durata", N_QUOTA_INDIVIDUALE = "Quota individuale", 
            N_COMPRESO_QUOTA = "Compreso quota", N_DATA_CONCLUSIVA = "Data conclusiva", 
            N_ORA_CONCLUSIVA = "Ora conclusiva", N_NOTE = "Note", N_TOLLERANZA_PARTECIPANTI = "Tolleranza partecipanti",
            N_DATA_RITIRO_ISCRIZIONE = "Data ritiro iscrizione";
    
    protected String nome, descrizione, categoria;    
    protected final int ID; // identificatore univoco 
    protected Campo[] campi, campiExtra, campiSpesa;  
    protected Stato statoCorrente;
    protected ArrayList<Stato> statiPassati;  
    protected final String creatoreID;
//    protected ArrayList<String> iscrittiIDs; // non più necessario, facciamo tutto con l'hashmap
    protected HashMap<String, String> speseUtenti;
    private transient final Meetle meetle;
    
    public Evento(Meetle meetle, String creatoreID, String categoria) {                
        campi = new Campo[NUM_CAMPI_FISSI];
        campi[I_TITOLO] = new CampoString(N_TITOLO, "Titolo dell'evento");
        campi[I_NUM_PARTECIPANTI] = new CampoInt(N_NUMERO_PARTECIPANTI, "Numero massimo di partecipanti all'evento (almeno 2)");
        campi[I_TERMINE_ISCRIZIONE] = new CampoData(N_TERMINE_ISCR, "Data di scadenza per l'iscrizione");
        campi[I_LUOGO] = new CampoString(N_LUOGO, "Luogo dove si terrà l'evento");
        campi[I_DATA] = new CampoData(N_DATA, "Data di inizio dell'evento");
        campi[I_ORA] = new CampoOra(N_ORA, "Ora di inizio dell'evento (formato hh:mm)");
        campi[I_DURATA] = new CampoDurata(N_DURATA, "Durata stimata dell'evento (formato gg:hh:mm)");
        campi[I_QUOTA_INDIVIDUALE] = new CampoInt(N_QUOTA_INDIVIDUALE,  "Quota di denaro richiesta per partecipare");
        campi[I_COMPRESO_QUOTA] = new CampoInt(N_COMPRESO_QUOTA,  "Quota di denaro già compresa (?)");
        campi[I_DATA_CONCLUSIVA] = new CampoData(N_DATA_CONCLUSIVA,  "Data di fine dell'evento");
        campi[I_ORA_CONCLUSIVA] = new CampoOra(N_ORA_CONCLUSIVA, "Ora di fine dell'evento");
        campi[I_NOTE] = new CampoString(N_NOTE, "Note aggiuntive");
        campi[I_TOLLERANZA_PARTECIPANTI] = new CampoInt(N_TOLLERANZA_PARTECIPANTI, "Numero di partecipanti accettabili in più rispetto a num partecipanti (non negativo)");
        campi[I_DATA_RITIRO_ISCRIZIONE] = new CampoData(N_DATA_RITIRO_ISCRIZIONE, "Data entro cui ci si può disiscrivere da un evento");
                        
        setFacoltativi();
        
        statoCorrente = new Stato();
        statiPassati = new ArrayList<>();
        this.creatoreID = creatoreID;
        this.categoria = categoria;
//        iscrittiIDs = new ArrayList<>();
        speseUtenti = new HashMap();
        ID = hashCode()+(new Random()).nextInt();
        this.meetle = meetle;
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
     * salva lo stato corrente tra gli stati passati e lo sostituisce con uno nuovo
     * @param indiceStato indice del nuovo stato
     */    
    private void nuovoStato(int indiceStato) {
        statiPassati.add(statoCorrente);
        statoCorrente = new Stato(indiceStato);
        notificamento();
    }
    
    /**
     * si occupa di inviare le opportune notifiche
     * @param indiceStato 
     */
    private void notificamento() {
        if(meetle==null)
            return;        
        int indiceStatoCorrente = statoCorrente.getIndiceStato();
        String messaggio, messaggio2 = "Messaggio che se viene visualizzato è laffine";
        boolean bandieruccia = false;
        switch(indiceStatoCorrente) {
            case Stato.CHIUSO:
                messaggio = "Evento ufficialmente chiuso :)";
//                /**
//                 * Bisogna aggiungere gli utenti iscritti a questo evento alla 
//                 * lista degli utenti che hanno partecipato ad un evento di una data categoria
//                 * proposto dall'utente propositore dell'evento corrente :)
//                 */
//                bandieruccia = true;
                break;
            case Stato.FALLITO:
                messaggio = "Evento ufficialmente fallito :(";
                break;
            case Stato.APERTO:
                messaggio = "Hai aperto l'evento :|";
                bandieruccia = true; // Bisogna inviare la notifica a tutti quelli che sono interessati
                String nomeUtente = this.meetle.getUtenteLoggatoID();   
                messaggio2 = nomeUtente + " ha appena aperto in bacheca un evento che potrebbe interessarti *_*";
                break;
            case Stato.RITIRATO:
                messaggio = "Evento ritirato :'(";
                break;
            case Stato.CONCLUSO:
                messaggio = "Evento concluso! hope you enjoyed =D";
                break;
            default:
                messaggio = "Questo messaggio se lo vedi significa che c'è qualquadra che non cosa XD LOL !!!111!!11!1";
        }
        if(indiceStatoCorrente == Stato.VALIDO || indiceStatoCorrente == Stato.NONVALIDO) return; // così non invia le notifiche
        speseUtenti.keySet().forEach((uID) -> {
            String messaggioFinale = messaggio;
            if (indiceStatoCorrente == Stato.CHIUSO){
                int importo = calcolaImporto(uID);
                messaggioFinale = messaggio + "\n -- Importo totale dovuto: " + importo + "€"; 
            }
            this.meetle.mandaNotifica(ID, campi[I_TITOLO].getValore().toString(),uID, messaggioFinale);
        });
        this.meetle.mandaNotifica(ID, campi[I_TITOLO].getValore().toString(), getCreatoreID(), messaggio);
        
        if(bandieruccia) {
            this.meetle.notificaIlMondoTondo(ID, messaggio2);
        }        
    }
    
    /**
     * controlla che tutti i campi obbligatori siano compilati e 
     * che tutti i campi compilati siano coerenti
     * @return true se l'evento è valido 
     */
    private boolean checkValidita() {
        if (this.getProssimoCampoObbligatorioMancante()!=null) 
            return false;
        if( (getDataRitiroIscrizione().compareTo(getTermineIscrizione()) > 0) ||
                (getTermineIscrizione().compareTo(getData()) > 0) ||
                (getDataConlusiva()!=null && getData().compareTo(getDataConlusiva()) > 0) )
            return false;
        if(getNumPartecipanti() < 2 || getTolleranzaPartecipanti() < 0)
            return false;
        return true;
    }
    
    /**
     * fa cambiare automaticamente lo stato dell'evento in base alle 
     * regole imposte tra le date, il numero di iscritti, ecc.
     */
    public void aggiornaStato() {
        switch(getIndiceStatoCorrente()) {
            case Stato.NONVALIDO:
                if (checkValidita())
                    nuovoStato(Stato.VALIDO);                
                break;
            case Stato.VALIDO:
                if(!checkValidita())
                    nuovoStato(Stato.NONVALIDO);
                break;
            case Stato.APERTO:
                int numMinPartecipanti = (Integer)campi[I_NUM_PARTECIPANTI].getValore(), 
                    numMaxPartecipanti = getNumIscrittiMax();
                // Condizione 1 per passare da aperto a chiuso
                if(LocalDate.now().compareTo((LocalDate)campi[I_TERMINE_ISCRIZIONE].getValore()) > 0 &&
                        getNumIscrittiCorrente() >= numMinPartecipanti && getNumIscrittiCorrente() <= numMaxPartecipanti)
                    nuovoStato(Stato.CHIUSO);
                // Condizione 2 per passare da aperto a chiuso
                else if(LocalDate.now().compareTo((LocalDate)campi[I_TERMINE_ISCRIZIONE].getValore()) <= 0 &&
                        LocalDate.now().compareTo((LocalDate)campi[I_DATA_RITIRO_ISCRIZIONE].getValore()) > 0 &&
                        getNumIscrittiCorrente() == numMaxPartecipanti)
                    nuovoStato(Stato.CHIUSO);
                else if (LocalDate.now().compareTo((LocalDate)campi[I_TERMINE_ISCRIZIONE].getValore()) > 0) // la data attuale supera la data termine iscrizione
                    nuovoStato(Stato.FALLITO);
                break;
            case Stato.CHIUSO:
                //if (LocalDate.now().compareTo((LocalDate)campi[I_DATA_CONCLUSIVA].getValore()) > 0) // quando la data attuale supera la data di termine evento
                if (campi[I_DATA_CONCLUSIVA].getValore() != null) {
                    if (LocalDate.now().compareTo((LocalDate)campi[I_DATA_CONCLUSIVA].getValore()) > 0)
                        nuovoStato(Stato.CONCLUSO);
                } else
                    if (LocalDate.now().compareTo((LocalDate)campi[I_DATA].getValore()) > 0)
                        nuovoStato(Stato.CONCLUSO);       
                break;
        }
    }
    
    public void apriEvento() {
        if(getIndiceStatoCorrente()==Stato.VALIDO) 
            nuovoStato(Stato.APERTO);
    }
    
    public void ritiraEvento() {
        if(getIndiceStatoCorrente()==Stato.APERTO) {
            nuovoStato(Stato.RITIRATO);
            // iscrittiIDs.clear();
        }
    }
    
    
    public String getProssimoCampoObbligatorioMancante(){ //restituisce il nome del prossimo campo obbligatorio non inserito, in modo che l'utente possa saperlo
        for(Campo c: this.getTuttiCampi())
            if (!c.isFacoltativo() && c.getValore() == null)
                    return c.getNome();
        return null;
    }
//    public void chiudiEvento()
//    {
//        nuovoStato(Stato.CHIUSO);
//        String messaggio = "Evento ufficialmente chiuso!";
//        for (String utente:iscrittiIDs)
//            this.meetle.mandaNotifica(ID, campi[I_TITOLO].getValore().toString(), utente, messaggio);
//    }
    
//    public void fallisciEvento() {
//        nuovoStato(Stato.FALLITO);
//        String messaggio = "Evento ufficialmente Fallito :(";
//        for(String utente: iscrittiIDs)
//            this.meetle.mandaNotifica(ID, campi[I_TITOLO].getValore().toString(), utente, messaggio);
//    }
    
    /**
     * dice se l'utente con questo ID è iscritto
     * @param uID
     * @return 
     */
    public boolean isUtenteIscritto(String uID) { 
        return uID.equals(creatoreID) || speseUtenti.containsKey(uID); 
    }
    
    /**
     * iscrive un utente se non iscritto, altrimenti lo disiscrive 
     * @param uID id dell'utente da (dis)iscrivere
     */
    public boolean switchIscrizione(String uID, String spesa){
        if (isUtenteIscritto(uID)){
//            iscrittiIDs.remove(uID);
            if(uID.equals(creatoreID))
                return false;
            speseUtenti.remove(uID);
            return true;
        } else if (spesa != null && getNumIscrittiCorrente() < getNumIscrittiMax()) {
//            iscrittiIDs.add(uID);
            speseUtenti.put(uID, spesa);
            return true;
        }               
        return false;
    }
    
    private int calcolaImporto(String uID) {
        int importo =(int) campi[I_QUOTA_INDIVIDUALE].getValore();
        String spese = speseUtenti.get(uID);
        for (int i = 0; i<spese.length(); i++)
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
    
    

//    @Override
//    public String toString() {
//        return nome +"\n"+ Arrays.stream(getTuttiCampi())
//                .filter(c -> !c.toString().equals(""))
//                .map(c -> "\t" + c + SEPARATORE_CAMPI)
//                .reduce("", String::concat);     
//    }   
    
    // Getters e Setters
    
    public String getCategoria() {return categoria;}
    public int getID() { /*System.out.println("Evento.getID() -> ID: " + ID);*/ return ID; }
    public String getNome() { return nome; }
    public String getTitolo() { return (String) campi[I_TITOLO].getValore(); }
    public int getNumPartecipanti() { return (int) campi[I_NUM_PARTECIPANTI].getValore(); }
    public int getTolleranzaPartecipanti() { return (int) campi[I_TOLLERANZA_PARTECIPANTI].getValore(); }
    public LocalDate getTermineIscrizione() { return (LocalDate) campi[I_TERMINE_ISCRIZIONE].getValore(); }
    public LocalDate getData() { return (LocalDate) campi[I_DATA].getValore(); }
    public LocalTime getOra() { return (LocalTime) campi[I_ORA].getValore(); }
    public LocalDate getDataConlusiva() { return (LocalDate) campi[I_DATA_CONCLUSIVA].getValore(); }
    public LocalDate getDataRitiroIscrizione() { return (LocalDate) campi[I_DATA_RITIRO_ISCRIZIONE].getValore(); }
    public Campo[] getTuttiCampi() { return (Campo[]) ArrayUtils.addAll(ArrayUtils.addAll(campi, campiExtra), campiSpesa);}
    public Campo[] getCampiSpesa(){ return campiSpesa; }
    public int getIndiceStatoCorrente() { return statoCorrente.getIndiceStato(); }    
    public boolean isRitirabile() { return LocalDate.now().compareTo((LocalDate)campi[I_DATA_RITIRO_ISCRIZIONE].getValore()) <= 0 && statoCorrente.getIndiceStato() == Stato.APERTO; }
    public boolean isInvitoInviabile() { return LocalDate.now().compareTo((LocalDate) campi[I_TERMINE_ISCRIZIONE].getValore()) <= 0 && statoCorrente.getIndiceStato() == Stato.APERTO; }
    public boolean isIscrivibile() { return LocalDate.now().compareTo((LocalDate)campi[I_DATA_RITIRO_ISCRIZIONE].getValore()) <= 0; }
    public String getCreatoreID() { return creatoreID; }
    public int getNumIscrittiCorrente() { return 1+speseUtenti.size(); }
    public int getNumIscrittiMax() { return getNumPartecipanti() + getTolleranzaPartecipanti(); }
    public ArrayList<String> getIscrittiIDs() { return new ArrayList<>(speseUtenti.keySet()); }
    
}
