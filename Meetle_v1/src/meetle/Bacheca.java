package meetle;

import java.util.ArrayList;

public class Bacheca {
    private ArrayList<Evento> eventi;

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

    // Getters e Setters
    public ArrayList<Evento> getEventi() { return eventi; }
    
}
