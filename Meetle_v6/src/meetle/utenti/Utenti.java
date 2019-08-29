package meetle.utenti;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Utenti implements Serializable {
    
    private ArrayList<Utente> utentiList;
    
    public Utenti() { 
        utentiList = new ArrayList<>();
    }
    
    public Utenti(ArrayList<Utente> utenti) {
        utentiList = new ArrayList<>(utenti);
//        getByID("user#"+(System.nanoTime()/100%10));
    }
    
    /**
     * prende l'utente con l'ID selezionato
     * (per adesso se non lo trova ne aggiunge subito uno)
     * @param ID
     * @return utente con quell'ID
     */
    public Utente getByID(String ID) {
        for(Utente u: utentiList) //System.out.println(u);
            if(u.getID().equals(ID))
                return u; 
//        System.out.println("Utente \""+ID+"\" non presente, creazione nuovo...");
//        Utente u = new Utente(ID);
//        aggiungiUtente(u);
//        return u;
        return null;
    }
    
    public ArrayList<Utente> getUtentiPerPreferenza(String categoria) {
//        ArrayList<Utente> ritorno = new ArrayList<>();
//        for(Utente u: this) {
//            if(u.getCategoriePreferite().contains(categoria))
//                ritorno.aggiungiUtente(u);
//        }
        return (ArrayList) utentiList.stream().filter(u -> u.getCategoriePreferite().contains(categoria)).collect(Collectors.toList());
    }
    
    public boolean aggiungiUtente(Utente u) {
        if(utentiList.contains(u))
            return false;
        return utentiList.add(u);
    }
}
