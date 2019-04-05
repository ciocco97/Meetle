package meetle;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import meetle.eventi.Bacheca;
import meetle.gui.*;
import meetle.io.MeetleIO;
import meetle.utenti.*;

public class Meetle {
    
    private Bacheca bacheca;
    private Utenti utenti;
    private MeetleIO io;
    private BachecaFrame interfaccia;
    private AreaPersonaleFrame personale;
    
    private Utente utenteLoggato;

    public Meetle() {
        
        io = new MeetleIO(this);
        
        try {
            // prova a caricare utenti da file
            utenti = new Utenti(io.caricaUtenti());
        } catch (IOException | ClassNotFoundException ex) { 
            System.err.println("ERRORE lettura utenti da file!!\n\t"+ex.getMessage());
            utenti = new Utenti();
        } 
//        try {
//            // salva subito gli utenti
//            io.salvaUtenti();
//        } catch (IOException ex) { System.err.println("ERRORE salvataggio utenti...\n\t"+ex.getMessage()); }   


        try {
            // prova a caricare eventi da file
            bacheca = new Bacheca(io.caricaEventi());
        } catch (IOException | ClassNotFoundException ex) { 
            System.err.println("ERRORE lettura eventi da file!! Creo bacheca di default...\n\t"+ex.getMessage());
            bacheca = new Bacheca();
        } 
        
//        salvaEventi();    
        
    }  
    
    public void start() {
        loginUtente();
        interfaccia = new BachecaFrame(this);
        personale = new AreaPersonaleFrame(this);
        java.awt.EventQueue.invokeLater(() -> { interfaccia.setVisible(true); });
    }
    
    public void areaPersonale() {
        personale.setVisible(true);
    }
    
    public void salvaEventi() {
        try {
            System.out.print("Salvataggio eventi su file... ");
            io.salvaEventi();
            System.out.println("OK!");
        } catch (IOException ex) { System.err.println("ERRORE salvataggio eventi!!\n\t"+ex.getMessage()); }
    }
    
    public void loginUtente (){ 
        String ID = JOptionPane.showInputDialog("LOGIN UTENTE");
        utenteLoggato = utenti.getUtenteDaID(ID);
        try {
            // salva subito gli utenti
            io.salvaUtenti();
        } catch (IOException ex) { System.err.println("ERRORE salvataggio utenti!!\n\t"+ex.getMessage()); }  
    }

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
    
    public void mandaNotifica(int eID, String uID, String messaggio) {
        utenti.getUtenteDaID(uID).notifica(eID, messaggio);
    }
    
    // Getters & Setters
    public Bacheca getBacheca() { return bacheca; }    
    public Utenti getUtenti() { return utenti; }
    public String getUtenteLoggatoID() { return utenteLoggato.getID(); }
    public ArrayList getEventiByCreatoreID(String ID) { return bacheca.getEventiByCreatoreID(ID); }
    public ArrayList getEventiIscritti(String ID) { return bacheca.getEventiByIscrittoID(ID); }
    public ArrayList getNotifiche() { return utenteLoggato.getNotifiche(); }
    
        
    public static void main(String[] args) throws IOException {
            
        Meetle meetle = new Meetle();
        meetle.start();       
    }
    
    
    
//    public String getDescrizioneCategorie() {
//        return (new PartitaDiCalcio(null)).toDescrizioneCategoria() + "\n";
//    }
        
//    public String stampaBacheca(String filtroNome) { return bacheca.toString(filtroNome); }    
//    public String stampaBacheca() { return stampaBacheca(null); }
    
    //funzioni e attributi per aggiungere campi
//    
//    private Evento e = null;
//    private Campo [] campi = null;
//    
//    public String[] istanziaEvento(String categoria) {
//        if(categoria.equals(PartitaDiCalcio.NOME))
//            e = new PartitaDiCalcio(null);
//        campi = e.getTuttiCampi();
//        String[] nomiCampiToString = new String[campi.length];
//        for(int i = 0; i < campi.length; i++)
//            nomiCampiToString[i] = campi[i].getNome();
//        return nomiCampiToString;
//    }
//    
//    public boolean aggiungiCampi(int indice, String valore) {
//        try {
//            campi[indice].setValoreDaString(valore);
//            e.setValoreDaString(indice, valore);
//        } catch (Exception e) {
//            return false;
//        }
//        return true;
//    }
//        
//    public boolean salvaEvento() {
//        if(e == null) return false;
//        bacheca.add(e);
//        e = null;
//        campi = null;
//        return true;
//    }
//    
    //fine funzioni per aggiungere campi
        
}
