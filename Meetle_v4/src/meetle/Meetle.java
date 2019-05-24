package meetle;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import meetle.eventi.Bacheca;
import meetle.gui.*;
import meetle.io.MeetleIO;
import meetle.utenti.*;

public class Meetle {
    
    private static Meetle istanza; // single ton
    
    private Bacheca bacheca;
    private Utenti utenti;
    private MeetleIO io; 
    private String utenteLoggatoID;    
    private boolean daSalvare = true; // booleana che dice se ci sono correntemente dei dati non salvati su file
    
    private BachecaFrame bachecaFrame;
    private AreaPersonaleFrame areaPersonaleFrame;  
    
    public static Meetle getIstanza() {
        if(istanza==null) 
            istanza = new Meetle();
        return istanza;
    }
    
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
        
    }  
    
    public void start() {
        loginUtente();
        bachecaFrame = new BachecaFrame();
        areaPersonaleFrame = new AreaPersonaleFrame();
        java.awt.EventQueue.invokeLater(() -> bachecaFrame.setVisible(true));
        
        new Thread(() -> {
            synchronized(this) {
                while(true) {
                    bacheca.aggiornaStati();
//                    if(daSalvare)
//                        salva();
                    bachecaFrame.aggiorna();
                    areaPersonaleFrame.aggiorna();
                    try {
                        wait(500);
                    } catch (InterruptedException ex) { }
                }
            }
        }).start();
    }
    
    public void loginUtente (){ 
        String accessoID = JOptionPane.showInputDialog("LOGIN UTENTE");
        if (accessoID == null)
            System.exit(0);
        else {
            if (utenti.getUtenteDaID(accessoID) == null) {
                utenti.add(new Utente(accessoID));
                utenteLoggatoID = utenti.getUtenteDaID(accessoID).getID();
                new ProfiloFrame().setVisible(true);
                try {
                    synchronized(this) {
                    wait();
                    }
                } catch (InterruptedException ex) {
                }
            }
            utenteLoggatoID = utenti.getUtenteDaID(accessoID).getID();
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
    
    public void setDaSalvare() { daSalvare=true; }
    
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
        } catch (IOException ex) { System.err.println("ERRORE salvataggio eventi!!\n\t"+ex.getStackTrace()); }
            
        daSalvare=false;
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
        for(Utente u: utenti) {
            // Prendo solo gli utenti che hanno tra le categorie preferite la categoria
            // di evento e non quello che è loggato al momento
            boolean interessato = u.getCategoriePreferite().contains(categoria);
            boolean loggato = u.getID().equals(utenteLoggatoID);
            if(interessato && !loggato) {
                mandaNotifica(eID, bacheca.getByID(eID).getTitolo(), u.getID(), messaggio);
            }
                
        }
    }
    
    public void mandaNotifica(int eID, String titolo, String uID, String messaggio) { utenti.getUtenteDaID(uID).aggiungiNotifica(eID, titolo, messaggio); }
    public void rimuoviNotifica(String uID, int IDnotifica) { utenti.getUtenteDaID(uID).rimuoviNotifica(IDnotifica); }
    public void setNotificaLetta(String uID, int IDnotifica) { utenti.getUtenteDaID(uID).segnaNotificaLetta(IDnotifica); }
    
    // Getters & Setters
    public Bacheca getBacheca() { setDaSalvare(); return bacheca; }    
    public Utenti getUtenti() { return utenti; }
    public String getUtenteLoggatoID() { return utenteLoggatoID; }
    public ArrayList<Notifica> getNotifiche() { return utenti.getUtenteDaID(utenteLoggatoID).getNotifiche(); }    
        
    public static void main(String[] args) throws IOException {
        
        Meetle meetle = Meetle.getIstanza();
        meetle.start();       
    }
    
}


