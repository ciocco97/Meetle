package meetle.utenti;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Utente implements Serializable {
    private final String ID;
    private int eta;
    private ArrayList<Notifica> notifiche;
    private ArrayList<String> categoriePreferite; 
    
    public Utente(String ID) {
        this.ID = ID;
        notifiche = new ArrayList<>();
    }
    
    public boolean aggiungiNotifica(int eID, String titolo, String messaggio){
        Notifica daAggiungere = new Notifica(eID, titolo, messaggio);
        if (notifiche.contains(daAggiungere)) // se c'è già vuol dire che c'è un duplicato di ID
            return aggiungiNotifica(eID, titolo, messaggio);
        
        notifiche.add(0, daAggiungere);
        return true;
    }
    
    public boolean rimuoviNotifica(int nID) {
        for(int i = 0; i < notifiche.size(); i++)
            if(notifiche.get(i).getID() == nID) { 
                notifiche.remove(i); 
                return true; 
            }
        return false;
    }
    
    public boolean segnaNotificaLetta(int nID) {
        for(int i = 0; i < notifiche.size(); i++)
            if(notifiche.get(i).getID() == nID) { 
                notifiche.get(i).setVisualizzata(); 
                return true; 
            }
        return false;
    }
    
    public void aggiungiInvito(int eID) {
        Invito daAggiungere = new Invito(eID, "Nuovo Invito!", "Sei stato invitato a questo evento!");
        if (notifiche.contains(daAggiungere)) // se c'è già vuol dire che c'è un duplicato di ID
            aggiungiInvito(eID);
        else
            notifiche.add(0, daAggiungere);
    }
    
    public boolean haNotificheNonLette() {
        return notifiche.stream().anyMatch(n -> !n.isVisualizzata());
    }

    public void setEta(int eta) { this.eta = eta; }
    public void setCategoriePreferite(ArrayList<String> categoriePreferite) { this.categoriePreferite = categoriePreferite; }
    
//    @Override
//    public String toString() { return ID; }   

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Utente)
            return ID.equals(((Utente) obj).ID);
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getID() { return ID; }
    public int getEta() { return eta; }    
    public ArrayList<Notifica> getNotifiche() { return notifiche; }
    public ArrayList<String> getCategoriePreferite() { return categoriePreferite; }
    
}
