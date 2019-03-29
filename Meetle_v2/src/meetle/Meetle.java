package meetle;

import java.io.*;
import java.time.LocalDateTime;
import meetle.gui.InterfacciaCMD;
import meetle.eventi.Bacheca;
import meetle.eventi.PartitaDiCalcio;
import meetle.io.MeetleIO;
import meetle.utenti.Utente;
import meetle.utenti.Utenti;

public class Meetle {
    private LocalDateTime dataoraSistema;
    
    private Bacheca bacheca;
    private Utenti utenti;
    private MeetleIO io;
    private InterfacciaCMD interfaccia;
    
    private Utente utenteSessione;

    public Meetle() {
        dataoraSistema = LocalDateTime.now();
        
//        bacheca = new Bacheca();
        io = new MeetleIO(this);
        
        try {
            // prova a caricare eventi da file
            bacheca = new Bacheca(io.caricaEventi());
        } catch (IOException | ClassNotFoundException ex) { 
            System.err.println("ERRORE lettura eventi da file!! Creo bacheca di default...\n\t"+ex.getMessage());
            bacheca = new Bacheca();
        } try {
            // salva subito gli eventi
            io.salvaEventi();
        } catch (IOException ex) { System.err.println("ERRORE salvataggio eventi...\n\t"+ex.getMessage()); }
        
        try {
            // prova a caricare utenti da file
            utenti = new Utenti(io.caricaUtenti());
        } catch (IOException | ClassNotFoundException ex) { 
            System.err.println("ERRORE lettura utenti da file!!\n\t"+ex.getMessage());
            utenti = new Utenti();
        } try {
            // salva subito gli utenti
            io.salvaUtenti();
        } catch (IOException ex) { System.err.println("ERRORE salvataggio utenti...\n\t"+ex.getMessage()); }        
        
        interfaccia = new InterfacciaCMD(this);
    }  
    
    public void start() {
        java.awt.EventQueue.invokeLater(() -> { interfaccia.setVisible(true); });
    }
    
    public String getDescrizioneCategorie() {
        return (new PartitaDiCalcio(null)).toDescrizioneCategoria() + "\n";
    }
        
    public String stampaBacheca(String filtroNome) { return bacheca.toString(filtroNome); }    
    public String stampaBacheca() { return stampaBacheca(null); }

    // Getters & Setters
    public Bacheca getBacheca() { return bacheca; }    
    public Utenti getUtenti() { return utenti; }
    
    
    public static void main(String[] args) throws IOException {
    
        Meetle meetle = new Meetle();
        meetle.start();
        
        for(Utente u: meetle.getUtenti())
            System.out.println(u);
        
    }
        
}
