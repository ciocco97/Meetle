package meetle.eventi;

import java.io.Serializable;
import java.util.ArrayList;
import meetle.Meetle;

public class Bacheca implements Serializable {
    public final static String SEPARATORE_EVENTI = "\n";
    
    private transient Meetle meetle;    
    private ArrayList<Evento> eventi;
    

    public Bacheca(Meetle meetle) {
        this.meetle = meetle;
        eventi = new ArrayList<>();
        metodoTemporaneo();
        metodoTemporaneo();
    }

    public Bacheca(Meetle meetle, ArrayList<Evento> eventi) {
        this.meetle = meetle;
        this.eventi = eventi;
    }
    
    public void metodoTemporaneo() {
        Evento e = new PartitaDiCalcio();
        int[] indici = new int[]{Evento.I_TITOLO, Evento.I_NUM_PARTECIPANTI, 
            Evento.I_LUOGO, Evento.I_DATA, Evento.I_ORA, PartitaDiCalcio.I_GENERE};
        String[] valori = new String[]{"partita bella", "45", "a casa", "2018-03-31", "8:45", "1"};
        for(int i=0; i < indici.length; i++)
            e.setValoreDaString(indici[i], valori[i]);        
        eventi.add(e);
    }
    
    /**
     * crea una stringa decentemente stampabile contentente gli eventi in bacheca
     * @param filtroNome filtra gli eventi per nome, se "null" ritorna tutti
     * @return stringa contentente tutti gli eventi in bacheca
     */
    public String toString(String filtroNome) {        
        return eventi.stream()
                .filter((e) -> filtroNome==null ? true : (e.getNome().equals(filtroNome)))
                .map((e) -> e + "\n")
                .reduce("", String::concat);
    }

    @Override
    public String toString() {
        return toString(null);
    }    

    // Getters e Setters
    public ArrayList<Evento> getEventi() { return eventi; }
        
}
