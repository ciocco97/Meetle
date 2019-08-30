package meetle.utenti;

import java.io.Serializable;
import java.util.ArrayList;
import meetle.eventi.Evento;
import meetle.eventi.Osservabile;
import meetle.eventi.stati.Stato;

public class Utente implements Serializable, Osservatore {
    private final String userID;
    private int eta;
    private ArrayList<Notifica> notifiche;
    private ArrayList<String> categoriePreferite; 
    
    public Utente(String ID) {
        this.userID = ID;
        notifiche = new ArrayList<>();
    }
    
    public boolean aggiungiNotifica(int eID, String titolo, String messaggio){
        Notifica daAggiungere = new Notifica(eID, titolo, messaggio);
        if (notifiche.contains(daAggiungere)) // se c'è già vuol dire che c'è un duplicato di userID
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
        if (notifiche.contains(daAggiungere)) // se c'è già vuol dire che c'è un duplicato di userID
            aggiungiInvito(eID);
        else
            notifiche.add(0, daAggiungere);
    }

    @Override
    public void aggiorna(Osservabile bile) {
        System.out.println(userID + ": notificato");
        Evento evento = (Evento) bile;
        String mex = "non dovresti vedere questo messaggio";
        switch(evento.getIndiceStatoCorrente()) {
            case Stato.NONVALIDO:
            case Stato.VALIDO:
                return;
            case Stato.APERTO:
                mex = "L'evento è ora aperto alle iscrizioni";
                break;
            case Stato.CHIUSO:
                mex = "Le iscrizioni sono chiuse: l'evento è confermato!\nIl tuo importo dovuto è di €";
                mex += evento.calcolaImporto(userID);
                break;
            case Stato.CONCLUSO:
                mex = "L'evento si è concluso";
                break;
            case Stato.FALLITO:
                mex = "L'evento è fallito, purtroppo :( Non ha raggiunto in tempo il minimo di partecipanti";
                break;
            case Stato.RITIRATO:
                mex = "L'evento è stato ritirato dal propositore";
                break;
        }
        aggiungiNotifica(evento.getID(), "Aggiornamento Stato: "+evento.getTitolo(), mex);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Utente)
            return userID.equals(((Utente) obj).userID);
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean haNotificheNonLette() {
        return notifiche.stream().anyMatch(n -> !n.isVisualizzata());
    }
    
    public String getUserID() { return userID; }
    public int getEta() { return eta; }    
    public ArrayList<Notifica> getNotifiche() { return notifiche; }
    public ArrayList<String> getCategoriePreferite() { return categoriePreferite; }
    public void setEta(int eta) { this.eta = eta; }
    public void setCategoriePreferite(ArrayList<String> categoriePreferite) { this.categoriePreferite = categoriePreferite; }
    
}
