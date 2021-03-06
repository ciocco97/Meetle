package meetle.utenti;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import meetle.eventi.campi.CampoRange.Range;


public class Utente implements Serializable {
    private final String ID;
    private final ArrayList<Notifica> notifiche;
//    private String nomignoloPercheNicknameEraTroppoMainStream;
    private Range fasciaEta;
    private ArrayList<String> categoriePreferite; 
    
    public Utente(String ID) {
        this.ID = ID;
        notifiche = new ArrayList<>();
    }
    
    public void aggiungiNotifica(int eID, String titolo, String messaggio){
        Notifica daAggiungere = new Notifica(eID, titolo, messaggio);
        if (notifiche.contains(daAggiungere)) // se c'è già vuol dire che c'è un duplicato di ID
            aggiungiNotifica(eID, titolo, messaggio);
        else
            notifiche.add(0, daAggiungere);
    }
    
    public void rimuoviNotifica(int nID) {
        for(int i = 0; i < notifiche.size(); i++)
            if(notifiche.get(i).getID() == nID)
                { notifiche.remove(i); return; }
        System.err.println("Nnotifica NON rimossa");
    }
    
    public void segnaNotificaLetta(int nID) {
        for(int i = 0; i < notifiche.size(); i++)
            if(notifiche.get(i).getID() == nID)
                { notifiche.get(i).setVisualizzata(); return; }
        System.err.println("Notifica NON letta");
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

    public void setFasciaEta(int min, int max) { this.fasciaEta = new Range(min, max); }
    public void setCategoriePreferite(ArrayList<String> categoriePreferite) { this.categoriePreferite = categoriePreferite; }
    
    @Override
    public String toString() { return ID; }   
    
    public boolean equals(Utente u) { return ID.equals((u).ID); } 

    public String getID() { return ID; }
    public ArrayList getNotifiche() { return notifiche; }
//    public String getNomignoloPercheNicknameEraTroppoMainStream() { return nomignoloPercheNicknameEraTroppoMainStream; }
    public int[] getFasciaEtaVals() { return fasciaEta==null? null : new int[]{fasciaEta.min, fasciaEta.max}; }    
    public ArrayList<String> getCategoriePreferite() { return categoriePreferite; }
    
}
