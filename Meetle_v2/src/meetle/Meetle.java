package meetle;

import java.io.*;
import meetle.gui.InterfacciaCMD;
import meetle.eventi.Bacheca;
import meetle.eventi.Evento;
import meetle.eventi.PartitaDiCalcio;
import meetle.eventi.campi.Campo;
import meetle.io.MeetleIO;

public class Meetle {
    private InterfacciaCMD interfaccia;
    private Bacheca bacheca;
    private MeetleIO io;

    public Meetle() {
        interfaccia = new InterfacciaCMD(this);
//        bacheca = new Bacheca(this);
        io = new MeetleIO(this);
        try {
            // prova a caricare eventi da file
            bacheca = new Bacheca(this, io.caricaEventi());
        } catch (IOException | ClassNotFoundException ex) { 
            System.err.println("ERRORE lettura eventi da file! Creo bacheca di default...\n\t"+ex.getMessage());
            bacheca = new Bacheca(this);
        }
        try {
            io.salvaEventi();
        } catch (IOException ex) { }
    }  
    
    public void start() {
        java.awt.EventQueue.invokeLater(() -> { interfaccia.setVisible(true); });
    }
    
    public String getDescrizioneCategorie() {
        return (new PartitaDiCalcio()).toDescrizioneCategoria() + "\n";
    }
        
    public String stampaBacheca(String filtroNome) { return bacheca.toString(filtroNome); }    
    public String stampaBacheca() { return stampaBacheca(null); }

    // Getters & Setters
    public Bacheca getBacheca() { return bacheca; }    
    
    
    public static void main(String[] args) throws IOException {
    
        Meetle meetle = new Meetle();
        meetle.start();
        
    }
    
    //funzioni e attributi per aggiungere campi
    
    private Evento e = null;
    private Campo [] campi = null;
    
    public String[] istanziaEvento(String categoria) {
        if(categoria.equals(PartitaDiCalcio.NOME))
            e = new PartitaDiCalcio();
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
        bacheca.getEventi().add(e);
        e = null;
        campi = null;
        return true;
    }
    
    //fine funzioni per aggiungere campi
        
}
