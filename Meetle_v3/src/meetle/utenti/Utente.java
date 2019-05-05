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
    
    public void aggiungiNotifica(int eID,String titolo, String messaggio){
        Notifica daAggiungere = new Notifica(eID, titolo, messaggio, LocalDateTime.now());
        boolean OK = true;
        for(Notifica n: notifiche) {
            if(n.equals(daAggiungere)) OK = false;
        }
        if(OK) notifiche.add(daAggiungere);
        else aggiungiNotifica(eID, titolo, messaggio);
    }
    
    public void rimuoviNotifica(int nID) {
        for(int i = 0; i < notifiche.size(); i++)
            if(notifiche.get(i).getID() == nID)
                notifiche.remove(i);
        System.err.println("Nessuna notifica rimossa");
    }
    
    public void segnaNotificaLetta(int nID) {
        for(int i = 0; i < notifiche.size(); i++)
            if(notifiche.get(i).getID() == nID)
                notifiche.get(i).setVisualizzata();
        System.err.println("Nessuna notifica letta");
    }

    @Override
    public String toString() { return ID; }   
    
    public boolean equals(Utente u) { return ID.equals((u).ID); } 

    public String getID() { return ID; }     
    public ArrayList getNotifiche() { return notifiche; }
    
}
