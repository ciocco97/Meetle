package meetle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bacheca {
    private ArrayList<Evento> eventi;
    
    private final String SEPARATORE = "§";

    public Bacheca() {
        eventi = new ArrayList<>();
        metodoTemporaneo();
    }
    
    public void metodoTemporaneo() {
        Evento e = new PartitaDiCalcio();
        int[] indici = new int[]{Evento.I_TITOLO, Evento.I_NUM_PARTECIPANTI, Evento.I_LUOGO};
        String[] valori = new String[]{"partita bella", "45", "a casa"};
        for(int i=0; i < indici.length; i++)
            e.setValoreDaString(indici[i], valori[i]);        
        eventi.add(e);
    }
    
    public void caricaEventi() {
        String path = "Data/eventi.txt";
        
        File file = new File(path);
        FileReader fr;
        
        if (file.exists()) { System.out.println("Il file esiste"); }
        else {
            System.err.println("Il file non esiste");
        }
        
        try {
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while(true) {
                String line = br.readLine();
                if(line == null) break;
                String[] pezzi = line.split(SEPARATORE);
                String prefisso = pezzi[0];
                Evento e = null;
                
                switch (prefisso) {
                    case "Partita di Calcio":
                        e = new PartitaDiCalcio();
                        for(int i = 1; i < pezzi.length; i++) {
                            if(!pezzi[i].equalsIgnoreCase("null"))
                                e.setValoreDaString(i - 1, pezzi[i]);
                        }
                    break;
                }
                if(e != null){
                    System.out.println(e.toEncript(SEPARATORE));
                    eventi.add(e);
                }
                
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Errore nella lettura del file usando fr");
        } catch (IOException ex) {
            System.err.println("Errore nell'uso di br");
        }
        
        
    }
    
    public void salvaEventi() {
        String path = "Data/eventi.txt";
        File file = new File(path);
        FileWriter fw;
        
        if (file.exists()) { System.out.println("Il file esiste"); }
        else {
            try { file.createNewFile(); System.out.println("Il file è stato creato"); } 
            catch (IOException ex) { System.err.println("Errore nella creazione di file"); }
        }
        
        try {
            fw = new FileWriter(file);
        
            for(Evento e: eventi) {
                fw.write(e.toEncript(SEPARATORE));
                fw.flush();
                System.out.println(e.toEncript(SEPARATORE)); //Inutile al cazzo -> solo per vedere cosa esce senza aprire il file
            }
            
            fw.close();
        
        } catch (IOException ex) {
            System.err.println("Errore nella creazione del FileWriter");
        }
    }

    // Getters e Setters
    public ArrayList<Evento> getEventi() { return eventi; }
    
}
