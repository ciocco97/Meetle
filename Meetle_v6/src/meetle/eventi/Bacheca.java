package meetle.eventi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;
import meetle.eventi.stati.Stato;

public class Bacheca implements Serializable {

    private ArrayList<Evento> eventiList;
    
//    private transient Meetle meetle;    

    public Bacheca() {
        eventiList = new ArrayList<>();
    }

    public Bacheca(ArrayList<Evento> eventi) {
        eventiList = new ArrayList<>(eventi);
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
//        aggiungiEvento(e);
//    }
    
    public Evento getByID(int eID) {
        for(Evento ev: eventiList)
            if(ev.getID() == eID)
                return ev;
        return null;
    }
    
    public ArrayList<Evento> getEventiByCreatoreID(String uID) {
        return (ArrayList) eventiList.stream()
                .filter((ev) -> (ev.creatoreID.equals(uID)))
                .collect(Collectors.toList());
    }
    
    public ArrayList<Evento> getEventiByIscrittoID(String uID) {
        return (ArrayList) eventiList.stream().filter((ev) -> ev.isUtenteIscritto(uID))
                .collect(Collectors.toList());
    }
    
    public ArrayList<Evento> getEventi() {
        return eventiList;
    }

    /**
     * aggiunge un evento alla lista SOLO SE non c'è già
     * @param e evento da aggiungere
     * @return false se c'è già, true se viene aggiunto
     */
    public boolean aggiungiEvento(Evento e) {
        if (eventiList.stream().anyMatch((t) -> (t.ID == e.ID))) {
//            System.err.println("Evento NON iserito! ID replicato");
            return false;        
        }
//        System.out.println("Aggiunto evento a bacheca:\n"+e);
        return eventiList.add(e); 
    }        
    
    public synchronized boolean rimuoviByID(int eID) {
        return eventiList.remove(getByID(eID));
//            System.err.println("Nessun evento rimosso");
    }
    
    public void aggiornaStati() {
        eventiList.stream().forEach(e -> e.aggiornaStato());
    }
    
    /**
     * cerca tutti gli utenti che sono hanno già partecipato ad un evento 
     * della stessa categoria e con lo stesso utente creatore
     * @param eID l'id dell'evento a cui si vuole invitare
     * @return un arraylist di stringhe, gli userID degli utenti trovati
     */
    public ArrayList<String> utentiInvitabili(int eID) {
        ArrayList<String> ritorno = new ArrayList<>();
        Evento ev = getByID(eID);
        eventiList.stream().filter(e -> e.getCreatoreID().equals(ev.getCreatoreID()))
                .filter(e -> e.getIndiceStatoCorrente() == Stato.CONCLUSO)
                .filter(e -> e.getCategoria().equals(ev.getCategoria()))
                .forEach(e -> {
                    e.getIscrittiIDs().stream().forEach(iID -> {
                        if(!ritorno.contains(iID))
                            ritorno.add(iID);
                    });
                });
        return ritorno;
    }

//    @Override
//    public String toString() {
//        return eventiList.stream()
//                .map((e) -> e + "\n")
//                .reduce("", String::concat);
//    }    
    
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
