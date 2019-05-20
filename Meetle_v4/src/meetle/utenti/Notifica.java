package meetle.utenti;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;

public class Notifica implements Serializable {
    private final int ID, eventoID;
    private final String titolo, messaggio;
    private final LocalDateTime dataora;
    private boolean visualizzata;

    public Notifica(int eID, String titolo, String messaggio, LocalDateTime dataora) {
        this.messaggio = messaggio;
        this.titolo = titolo;
        this.dataora = dataora;
        this.visualizzata = false;
        this.eventoID = eID;
        ID = hashCode()+(new Random()).nextInt();
    }
    
    public Notifica(int eID, String titolo, String messaggio) {
        this(eID, titolo, messaggio, LocalDateTime.now());
    }
    
    public void setVisualizzata() { this.visualizzata = true; }

    public boolean equals(Notifica notifica) { return notifica.getID() == ID; }
    
    public String getTitolo() { return titolo; }
    public String getMessaggio() { return messaggio; }
    public LocalDateTime getDataora() { return dataora; }
    public boolean isVisualizzata() { return visualizzata; }
    public int getEventoID() { return eventoID; } 
    public int getID() { return ID; }
    
}
