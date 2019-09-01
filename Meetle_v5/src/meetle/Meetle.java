package meetle;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import meetle.eventi.*;
import meetle.utenti.*;
import meetle.io.MeetleIO;
import meetle.gui.*;

public class Meetle {
    
    private static Meetle istanza; // single ton
    
    private Bacheca bacheca;
    private Utenti utenti;
    private MeetleIO io; 
    private String utenteLoggatoID;    
    private boolean daSalvare = true; // booleana che dice se ci sono correntemente dei dati non salvati su file
    
    private Thread aggiornatore;
    
    private BachecaFrame bachecaFrame;
    private AreaPersonaleFrame areaPersonaleFrame;  
    
    public static Meetle getIstanza() {
        if(istanza==null) 
            istanza = new Meetle();
        return istanza;
    }
    
    /**
     * costruisce gli oggetti principali di meetle (bacheca, utenti, ecc.)
     */
    private Meetle() {
        
        try {
            io = new MeetleIO();
        } catch (IOException e) { System.out.println("Errore istanza meetleIO...\n\t" + e.getMessage()); }
        
        try {
            // prova a caricare utenti da file
            utenti = new Utenti(io.caricaUtenti());
        } catch (IOException | ClassNotFoundException ex) { 
            System.err.println("ERRORE lettura utenti da file!!\n\t"+ex.getMessage());
            utenti = new Utenti();
        } 

        try {
            // prova a caricare eventi da file
            bacheca = new Bacheca(io.caricaEventi());
        } catch (IOException | ClassNotFoundException ex) { 
            System.err.println("ERRORE lettura eventi da file!! Creo bacheca di default...\n\t"+ex.getMessage());
            bacheca = new Bacheca();
        }  
        
        aggiornatore = new Thread(() -> {
            synchronized(this) {
                while(true) {
                    bacheca.aggiornaStati();
//                    if(daSalvare)
//                        salva();
                    bachecaFrame.aggiorna();
                    areaPersonaleFrame.aggiorna();
                    try {  wait(500); } catch (InterruptedException ex) { }
                }
            }
        });
        
    }  
    
    /**
     * lancia effettivamente il programma (con login, finestre della GUI, threads, ecc.)
     */
    public void start() {
        loginUtente();
        bachecaFrame = new BachecaFrame();
        areaPersonaleFrame = new AreaPersonaleFrame();
        java.awt.EventQueue.invokeLater(() -> bachecaFrame.setVisible(true));
        aggiornatore.start();
    }
    
    /**
     * chiede di eseguire il login via input (se non viene eseguito chiude tutto)
     * e se l'utente è nuovo apre la finestra di configurazione del profilo
     */
    public void loginUtente (){ 
        String accessoID = JOptionPane.showInputDialog("Insersci il tuo nome utente");
        if (accessoID == null)
            System.exit(0);
        
        utenteLoggatoID = accessoID;
        if (utenti.getUtenteDaID(accessoID) == null) {
            utenti.add(new Utente(accessoID));
            ProfiloFrame f = new ProfiloFrame();
            f.setDefaultCloseOperation(ProfiloFrame.DO_NOTHING_ON_CLOSE);
            f.setVisible(true);
            try { synchronized(this) { wait(); } } catch (InterruptedException ex) { }
        }
        
        try {
            // salva subito gli utenti
            io.salvaUtenti();
        } catch (IOException ex) { System.err.println("ERRORE salvataggio utenti!!\n\t"+ex.getMessage()); }  
    }
    
    
    public void mostraAreaPersonale() {
        bachecaFrame.setVisible(false);
        areaPersonaleFrame.inizializza();
        areaPersonaleFrame.setVisible(true);
    }
    
    public void mostraBacheca() {
        areaPersonaleFrame.setVisible(false);
        bachecaFrame.inizializza();
        bachecaFrame.setVisible(true);
        
    }
    
//    public void setDaSalvare() { daSalvare=true; }
    
    /**
     * salva sia la bacheca che gli utenti su file
     */
    public void salva() {
        try {
            System.out.print("Salvataggio utenti su file... ");
            io.salvaUtenti();
            System.out.println("OK!");
        } catch (IOException ex) { System.err.println("ERRORE salvataggio utenti!!\n\t"+ex.getMessage()); }
        
        try {
            System.out.print("Salvataggio eventi su file... ");
            io.salvaEventi();
            System.out.println("OK!");
        } catch (IOException ex) { System.err.println("ERRORE salvataggio eventi!!\n\t"+ex.getMessage()); }
            
        daSalvare=false;
    }
    
    /**
     * cerca tutti gli utenti che sono hanno già partecipato ad un evento 
     * della stessa categoria e con lo stesso utente creatore
     * @return un arraylist di stringhe, gli userID degli utenti trovati
     */
    public ArrayList<String> utentiInvitabili(int eID) {
        Evento ev = bacheca.getByID(eID);
        ArrayList<String> ritorno = new ArrayList<>();
        bacheca.stream().filter(e -> e.getCreatoreID().equals(utenteLoggatoID))
                .filter(e -> e.getIndiceStatoCorrente() == Stato.CONCLUSO)
                .filter(e -> e.getCategoria().equals(ev.getCategoria()))
                .forEach(e -> {
                    e.getIscrittiIDs().stream().forEach(iID -> {
                        if(!ritorno.contains(iID))
                            ritorno.add(iID);
                    });
                });
        return ritorno;
    }
    
    /**
     * Funzione che notifica tutti gli utenti che sono interessati alla categoria
     * di eID: viene spedito loro messaggio
     * Unico utente che non viene notificato è il creatore dell'evento, nonché
     * colui che sta usando il programma
     * @param eID
     * @param messaggio 
     */
    public void notificaIlMondoTondo(int eID, String messaggio) {
        String categoria = bacheca.getByID(eID).getCategoria();
        for(Utente u: utenti.getUtentiPerPreferenza(categoria)) {
            if(!u.getID().equals(utenteLoggatoID))
                mandaNotifica(eID, bacheca.getByID(eID).getTitolo(), u.getID(), messaggio);
        }
    }
    
    public void mandaNotifica(int eID, String titolo, String uID, String messaggio) { utenti.getUtenteDaID(uID).aggiungiNotifica(eID, titolo, messaggio); }
    public void rimuoviNotifica(String uID, int IDnotifica) { utenti.getUtenteDaID(uID).rimuoviNotifica(IDnotifica); }
    public void setNotificaLetta(String uID, int IDnotifica) { utenti.getUtenteDaID(uID).segnaNotificaLetta(IDnotifica); }
    public void mandaInvito(int eID, String uID) { utenti.getUtenteDaID(uID).aggiungiInvito(eID); }
    
    // Getters & Setters
    public Bacheca getBacheca() {  return bacheca; }    
    public Utenti getUtenti() { return utenti; }
    public String getUtenteLoggatoID() { return utenteLoggatoID; }
        
    public static void main(String[] args) throws IOException {
        Meetle meetle = Meetle.getIstanza();
        meetle.start();       
    }
    
}


