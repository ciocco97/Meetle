package meetle.utenti;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Utenti extends ArrayList<Utente> implements Serializable {
    
    public Utenti() { super(); }
    
    public Utenti(ArrayList<Utente> utenti) {
        super(utenti);
//        getUtenteDaID("user#"+(System.nanoTime()/100%10));
    }
    
    /**
     * prende l'utente con l'ID selezionato
     * (per adesso se non lo trova ne aggiunge subito uno)
     * @param ID
     * @return utente con quell'ID
     */
    public Utente getUtenteDaID(String ID) {
        for(Utente u: this) //System.out.println(u);
            if(u.getID().equals(ID))
                return u; 
//        System.out.println("Utente \""+ID+"\" non presente, creazione nuovo...");
//        Utente u = new Utente(ID);
//        add(u);
//        return u;
        return null;
    }
    
    public ArrayList<Utente> getUtentiPerPreferenza(String categoria) {
//        ArrayList<Utente> ritorno = new ArrayList<>();
//        for(Utente u: this) {
//            if(u.getCategoriePreferite().contains(categoria))
//                ritorno.add(u);
//        }
        return (ArrayList) stream().filter(u -> u.getCategoriePreferite().contains(categoria)).collect(Collectors.toList());
    }
    
}
