package meetle;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import meetle.gui.InterfacciaCMD;
import meetle.eventi.Bacheca;
import meetle.eventi.Evento;
import meetle.eventi.PartitaDiCalcio;
import meetle.eventi.campi.Campo;
import meetle.gui.BachecaFrame;
import meetle.io.MeetleIO;
import meetle.utenti.Utente;
import meetle.utenti.Utenti;

public class Meetle {
    private LocalDateTime dataoraSistema;
    
    private Bacheca bacheca;
    private Utenti utenti;
    private MeetleIO io;
    private BachecaFrame interfaccia;
    
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
        
        interfaccia = new BachecaFrame(this);
    }  
    
    public void start() {
        java.awt.EventQueue.invokeLater(() -> { interfaccia.setVisible(true); });
    }
    
    public String getDescrizioneCategorie() {
        return (new PartitaDiCalcio(null)).toDescrizioneCategoria() + "\n";
    }
        
    public String stampaBacheca(String filtroNome) { return bacheca.toString(filtroNome); }    
    public String stampaBacheca() { return stampaBacheca(null); }

    public void checkEventi() { 
//        for(Evento e: bacheca) {
//            LocalDateTime dataOraEvento = ((LocalDate)e.getCampi()[Evento.I_DATA].getValore()).atTime((LocalTime)e.getCampi()[Evento.I_ORA].getValore());
//            switch(e.getStatoCorrente()){
//                case Stato.APERTO:
//                    if(dataoraSistema.isAfter(dataOraEvento))
//                        if(e.getNumIscritti() == (Integer)e.getCampi()[Evento.I_NUM_PARTECIPANTI].getValore())
//                            e.cambiaStato(Stato.CHIUSO);
//                        else
//                            e.cambiaStato(Stato.FALLITO);
//                    break;
//                case Stato.CHIUSO:
//                    LocalDateTime dataOraFineEvento = ((LocalDate)e.getCampi()[Evento.I_DATA_CONCLUSIVA].getValore()).atTime((LocalTime)e.getCampi()[Evento.I_ORA_CONCLUSIVA].getValore());
//                    if(dataOraEvento.isAfter(dataOraFineEvento))
//                        e.cambiaStato(Stato.CONCLUSO);
//                            
//            }
//        }
    }
    
    // Getters & Setters
    public Bacheca getBacheca() { return bacheca; }    
    public Utenti getUtenti() { return utenti; }
    public ArrayList getEventiByCreatoreID(String ID){return bacheca.getEventiByCreatoreID(ID);}
    public ArrayList getEventiIscritti(String ID){return bacheca.getEventiIscritti(ID);}
    public ArrayList getNotifiche() {return utenteSessione.getNotifiche();}
    
    public void setUtente (String ID)
    {
        for (Utente u:utenti)
            if (u.getID().equals(ID)){
                this.utenteSessione = u;
                return;
            }
        Utente utente = new Utente(ID);
        utenti.add(utente);
        utenteSessione = utente;
    }
    //funzioni e attributi per aggiungere campi
    
    private Evento e = null;
    private Campo [] campi = null;
    
    public String[] istanziaEvento(String categoria) {
        if(categoria.equals(PartitaDiCalcio.NOME))
            e = new PartitaDiCalcio(null);
        campi = e.getTuttiCampi();
        String[] nomiCampiToString = new String[campi.length];
        for(int i = 0; i < campi.length; i++)
            nomiCampiToString[i] = campi[i].getNome();
        return nomiCampiToString;
    }
    
    public boolean aggiungiCampi(int indice, String valore) {
        try {
            campi[indice].setValoreDaString(valore);
            e.setValoreDaString(indice, valore);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
        
    public boolean salvaEvento() {
        if(e == null) return false;
        bacheca.add(e);
        e = null;
        campi = null;
        return true;
    }
    
    //fine funzioni per aggiungere campi
        
    
    public static void main(String[] args) throws IOException {
            
        Meetle meetle = new Meetle();
        meetle.start();
                
        for(Utente u: meetle.getUtenti())
            System.out.println(u);
        
    }
}
