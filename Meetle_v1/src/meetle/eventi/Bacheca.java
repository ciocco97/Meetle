package meetle.eventi;

import java.util.ArrayList;
import meetle.Meetle;

public class Bacheca {
    public final static String SEPARATORE_EVENTI = "\n";
    
    private Meetle meetle;    
    private ArrayList<Evento> eventi;
    

    public Bacheca(Meetle meetle) {
        this.meetle = meetle;
        eventi = new ArrayList<>();
        metodoTemporaneo();
        metodoTemporaneo();
        metodoTemporaneo();
    }

    public Bacheca(Meetle meetle, ArrayList<Evento> eventi) {
        this.meetle = meetle;
        this.eventi = eventi;
    }
    
    public void metodoTemporaneo() {
        Evento e = new PartitaDiCalcio();
        int[] indici = new int[]{Evento.I_TITOLO, Evento.I_NUM_PARTECIPANTI, Evento.I_LUOGO, PartitaDiCalcio.I_GENERE};
        String[] valori = new String[]{"partita bella", "45", "a casa", "1"};
        for(int i=0; i < indici.length; i++)
            e.setValoreDaString(indici[i], valori[i]);        
        eventi.add(e);
    }

    @Override
    public String toString() {
        return eventi.stream().map((e) -> e + SEPARATORE_EVENTI).reduce("", String::concat); 
    }   

    // Getters e Setters
    public ArrayList<Evento> getEventi() { return eventi; }
    
}
