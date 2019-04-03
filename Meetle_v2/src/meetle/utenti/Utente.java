package meetle.utenti;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Utente implements Serializable {
    private final String ID;
    private ArrayList<Notifica> notifiche;

    public Utente(String ID) {
        this.ID = ID;
        notifiche = new ArrayList<>();
    }
    
    public void notifica(int eID, String messaggio){
        notifiche.add(new Notifica(eID, messaggio, LocalDateTime.now()));
    }

    @Override
    public String toString() { return ID; }   
    
    public boolean equals(Utente u) { return ID.equals((u).ID); } 

    public String getID() { return ID; }     
    public ArrayList getNotifiche() { return notifiche; }
    
}
