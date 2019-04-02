package meetle.eventi;

import java.io.Serializable;
import java.util.ArrayList;

public class Bacheca extends ArrayList<Evento> implements Serializable {
    public final static String SEPARATORE_EVENTI = "\n";
    
//    private transient Meetle meetle;    
//    private ArrayList<Evento> eventi;    

    public Bacheca() {
        super();
    }

    public Bacheca(ArrayList<Evento> eventi) {
//        this.eventi = eventi;
        super(eventi);
        for(int i = 0; i < 3; i++)
            metodoTemporaneo();
        
    }
    
    public void metodoTemporaneo() {
        Evento e = new PartitaDiCalcio(null);
        int[] indici = new int[]{Evento.I_TITOLO, Evento.I_NUM_PARTECIPANTI, 
            Evento.I_LUOGO, Evento.I_DATA, Evento.I_ORA, PartitaDiCalcio.I_GENERE};
        String[] valori = new String[]{"partita bella", "45", "a casa", "2018-03-31", "08:45", "1"};
        for(int i=0; i < indici.length; i++)
            e.setValoreDaString(indici[i], valori[i]);        
        add(e);
    }
    
    public boolean aggiungiEvento(Evento ev)
    {
        int ID = ev.ID;
        for (Evento e:this)
            if (e.ID == ID)
                return false;
        this.add(ev);
        return true;
    }
    public void rimuoviEvento(int ID)
    {
        this.stream().filter((ev) -> (ev.ID == ID)).forEachOrdered((ev) -> {
            this.remove(ev);
        });   
    }
    public ArrayList<Evento> getEventiByCreatoreID(String ID)
    {
        ArrayList<Evento> list = new ArrayList();
        stream().filter((ev) -> (ev.creatoreID.equals(ID))).forEachOrdered((ev) -> {
            list.add(ev);
        });
        return list;
    }
    public ArrayList<Evento> getEventiIscritti(String ID)
    {
        ArrayList <Evento> list = new ArrayList();
        this.forEach((ev) -> {
            ev.iscrittiIDs.stream().filter((st) -> (st.equals(ID))).forEachOrdered((_item) -> {
                list.add(ev);
            });
        });
        return list;
    }
    /**
     * crea una stringa decentemente stampabile contentente gli eventi in bacheca
     * @param filtroNome filtra gli eventi per nome, se "null" ritorna tutti
     * @return stringa contentente tutti gli eventi in bacheca
     */
    public String toString(String filtroNome) {        
        return stream()
                .filter(e -> e.isAperto())
                .filter((e) -> filtroNome==null ? true : (e.getNome().equals(filtroNome)))
                .map((e) -> e + "\n")
                .reduce("", String::concat);
    }

    @Override
    public String toString() {
        return toString(null);
    }    

    // Getters e Setters
//    public ArrayList<Evento> getEventi() { return eventi; }
        
}
