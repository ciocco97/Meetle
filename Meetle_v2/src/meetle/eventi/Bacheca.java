package meetle.eventi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

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
    }
    
    // aggiungiamo una partita di calcio a caso
    public void metodoTemporaneo() {
        Evento e = new PartitaDiCalcio(null);
        int[] indici = new int[]{Evento.I_TITOLO, Evento.I_NUM_PARTECIPANTI, 
            Evento.I_LUOGO, Evento.I_DATA, Evento.I_ORA, PartitaDiCalcio.I_GENERE};
        String[] valori = new String[]{"Partita bella #"+(new Random().nextInt(100)), "45", 
            "a casa", "2018-03-31", "08:45", "1"};
        for(int i=0; i < indici.length; i++)
            e.setValoreDaString(indici[i], valori[i]);        
        add(e);
    }
    
    public Evento getByID(int eID) {
        for(Evento ev: this)
            if(ev.getID() == eID)
                return ev;
        return null;
    }
    
    public ArrayList getEventiByCreatoreID(String uID) {
        return (ArrayList) stream()
                .filter((ev) -> (ev.creatoreID.equals(uID)))
                .collect(Collectors.toList());
    }
    
    public ArrayList<Evento> getEventiByIscrittoID(String uID) {
        return (ArrayList) stream().filter((ev) -> ev.isUtenteIscritto(uID))
                .collect(Collectors.toList());
    }

    /**
     * aggiunge un evento alla lista SOLO SE non c'è già
     * @param e evento da aggiungere
     * @return false se c'è già, true se viene aggiunto
     */
    @Override
    public boolean add(Evento e) {
        if (!this.stream().noneMatch((t) -> (t.ID == e.ID))) {
            System.out.println("Evento NON iserito! ID replicato");
            return false;        
        }
        System.out.println("Aggiunto evento a bacheca:\n"+e);
        return super.add(e); 
    }        
    
//    public boolean aggiungiEvento(Evento ev)
//    {
//        int ID = ev.ID;
//        for (Evento e:this)
//            if (e.ID == ID)
//                return false;
//        this.add(ev);
//        return true;
//    }
    
    public synchronized void rimuoviEventiByID(int eID) {
        remove(getByID(eID));
    }
    
    public boolean rendiApertoEvento(int eID) {
        Evento ev = getByID(eID);
        if(ev != null && ev.getIndiceStatoCorrente()==Stato.VALIDO) {
            ev.cambiaStato(Stato.APERTO);
            return true;
        }               
        return false;
    }

    @Override
    public String toString() {
        return stream()
                .map((e) -> e + "\n")
                .reduce("", String::concat);
    }    
    
//    /**
//     * crea una stringa decentemente stampabile contentente gli eventi in bacheca
//     * @param filtroNome filtra gli eventi per nome, se "null" ritorna tutti
//     * @return stringa contentente tutti gli eventi in bacheca
//     */
//    public String toString(String filtroNome) {        
//        return stream()
//                .filter(e -> e.isAperto())
//                .filter((e) -> filtroNome==null ? true : (e.getNome().equals(filtroNome)))
//                .map((e) -> e + "\n")
//                .reduce("", String::concat);
//    }
        
}
