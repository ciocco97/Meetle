package meetle;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import meetle.eventi.*;
import meetle.utenti.*;
import meetle.io.MeetleIO;
import meetle.gui.*;

public class Meetle {
    
    private Bacheca bacheca;
    private Utenti utenti;
    private MeetleIO io; 
    private String utenteLoggatoID;    
    
    private Thread aggiornatore;
    
    private BachecaFrame bachecaFrame;
    private AreaPersonaleFrame areaPersonaleFrame;  
    
    
    /**
     * costruisce gli oggetti principali di meetle (bacheca, utenti, ecc.)
     */
    public Meetle() {
        
        try {
            io = new MeetleIO(this);
        } catch (IOException e) { System.out.println("Errore istanza meetleIO...\n\t" + e.getMessage()); }
        
        try {
            // prova a caricare utenti da file
            utenti = (Utenti) (io.caricaUtenti());
        } catch (IOException | ClassNotFoundException ex) { 
            System.err.println("ERRORE lettura utenti da file!!\n\t"+ex.getMessage());
            utenti = new Utenti();
        } 

        try {
            // prova a caricare eventi da file
            bacheca = (Bacheca)(io.caricaEventi());
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
        bachecaFrame = new BachecaFrame(this);
        areaPersonaleFrame = new AreaPersonaleFrame(this);
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
        if (utenti.getByID(accessoID) == null) {
            utenti.aggiungiUtente(new Utente(accessoID));
            ProfiloFrame f = new ProfiloFrame(this);
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
            
    }
    
    public void mandaNotifica(int eID, String titolo, String uID, String messaggio) { utenti.getByID(uID).aggiungiNotifica(eID, titolo, messaggio); }
    public void rimuoviNotifica(String uID, int IDnotifica) { utenti.getByID(uID).rimuoviNotifica(IDnotifica); }
    public void setNotificaLetta(String uID, int IDnotifica) { utenti.getByID(uID).segnaNotificaLetta(IDnotifica); }
    public void mandaInvito(int eID, String uID) { utenti.getByID(uID).aggiungiInvito(eID); }
        
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
    
    // Getters & Setters
    public Bacheca getBacheca() {  return bacheca; }    
    public Utenti getUtenti() { return utenti; }
    public String getUtenteLoggatoID() { return utenteLoggatoID; }
    public ArrayList<Notifica> getNotifiche() { return utenti.getByID(utenteLoggatoID).getNotifiche(); }    
        
    public static void main(String[] args) throws IOException {
        Meetle meetle = new Meetle();
        meetle.start();    
    }
    
}


