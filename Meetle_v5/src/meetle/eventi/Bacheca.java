package meetle.eventi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class Bacheca implements Serializable {
    public final static String SEPARATORE_EVENTI = "\n";
    
    private ArrayList<Evento> bacheca;
    
//    private transient Meetle meetle;    
//    private ArrayList<Evento> eventi;    

    public Bacheca() {
        bacheca = new ArrayList<>();
    }

    public Bacheca(ArrayList<Evento> eventi) {
//        this.eventi = eventi;
        bacheca = new ArrayList<>(eventi);
    }
    
//    // aggiungiamo una partita di calcio a caso
//    public void metodoTemporaneo(String ID) {
//        Evento e = new PartitaDiCalcio(ID);
//        int[] indici = new int[]{Evento.I_TITOLO, Evento.I_NUM_PARTECIPANTI, Evento.I_TERMINE_ISCRIZIONE,
//            Evento.I_LUOGO, Evento.I_DATA, Evento.I_ORA, PartitaDiCalcio.I_QUOTA_INDIVIDUALE, 
//            Evento.I_TOLLERANZA_PARTECIPANTI, Evento.I_DATA_RITIRO_ISCRIZIONE,
//            PartitaDiCalcio.I_GENERE, PartitaDiCalcio.I_FASCIA_ETA };
//        String[] valori = new String[]{"Partita bella #"+(new Random().nextInt(100)), "3", "2019-05-30",
//            "a casa", "2019-06-01", "08:45", "20", "2", "2019-05-29", "femminile", "8-15"};
//        for(int i=0; i < indici.length; i++)
//            e.setValoreDaString(indici[i], valori[i]);   
//        add(e);
//    }
    
    public Evento getByID(int eID) {
        for(Evento ev: bacheca)
            if(ev.getID() == eID)
                return ev;
        return null;
    }
    
    public ArrayList<Evento> getEventiByCreatoreID(String uID) {
        return (ArrayList) bacheca.stream()
                .filter((ev) -> (ev.creatoreID.equals(uID)))
                .collect(Collectors.toList());
    }
    
    public ArrayList<Evento> getEventiByIscrittoID(String uID) {
        return (ArrayList) bacheca.stream().filter((ev) -> ev.isUtenteIscritto(uID))
                .collect(Collectors.toList());
    }

    /**
     * aggiunge un evento alla lista SOLO SE non c'è già
     * @param e evento da aggiungere
     * @return false se c'è già, true se viene aggiunto
     */
    public boolean add(Evento e) {
        if (!bacheca.stream().noneMatch((t) -> (t.ID == e.ID))) {
            System.err.println("Evento NON iserito! ID replicato");
            return false;        
        }
        System.out.println("Aggiunto evento a bacheca:\n"+e);
        return bacheca.add(e); 
    }        
    
    public synchronized void rimuoviByID(int eID) {
        if(!bacheca.remove(getByID(eID)))
            System.err.println("Nessun evento rimosso");
    }
    
    public void aggiornaStati() {
        bacheca.stream().forEach(e -> e.aggiornaStato());
    }

    @Override
    public String toString() {
        return bacheca.stream()
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
