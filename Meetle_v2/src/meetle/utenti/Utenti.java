package meetle.utenti;

import java.io.Serializable;
import java.util.ArrayList;

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
        for(Utente u: this)
            if(u.getID().equals(ID))
                return u;
        Utente u = new Utente(ID);
        add(u);
        System.out.println("Utente non presente, creato uno nuovo");
        return u;
        
    }    
    
}
