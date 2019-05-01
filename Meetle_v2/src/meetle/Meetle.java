package meetle;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import meetle.eventi.Bacheca;
import meetle.eventi.Evento;
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
        
        io = new MeetleIO(this);
        
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
                    if(daSalvare)
                        salva();
                    try {
                        wait(500);
                    } catch (InterruptedException ex) { }
                }
            }
        }).start();
    }
    
    public void loginUtente (){ 
        utenteLoggatoID = JOptionPane.showInputDialog("LOGIN UTENTE");
        try {
            // salva subito gli utenti
            io.salvaUtenti();
        } catch (IOException ex) { System.err.println("ERRORE salvataggio utenti!!\n\t"+ex.getMessage()); }  
    }
    
    
    public void mostraAreaPersonale() {        
        areaPersonaleFrame.setVisible(true);
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
        } catch (IOException ex) { System.err.println("ERRORE salvataggio eventi!!\n\t"+ex.getMessage()); }
            
        daSalvare=false;
    }
    public void mandaNotifica(int eID, String uID, String messaggio) { utenti.getUtenteDaID(uID).aggiungiNotifica(eID, messaggio); }
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


