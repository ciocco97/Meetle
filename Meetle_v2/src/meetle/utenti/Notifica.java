package meetle.utenti;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;

public class Notifica implements Serializable {
    private String messaggio;
    private LocalDateTime dataora;
    private boolean visualizzata;
    private int eventoID;
    private int ID;

    public Notifica(int eID, String messaggio, LocalDateTime dataora) {
        this.messaggio = messaggio;
        this.dataora = dataora;
        this.visualizzata = false;
        this.eventoID = eID;
        ID = hashCode()+(new Random()).nextInt();
    }
    
    public Notifica(int eID, String messaggio) {
        this(eID, messaggio, LocalDateTime.now());
    }
    
    public void setVisualizzata() { this.visualizzata = true; }

    public boolean equals(Notifica notifica) { return notifica.getID() == ID; }
    
    public String getMessaggio() { return messaggio; }
    public LocalDateTime getDataora() { return dataora; }
    public boolean isVisualizzata() { return visualizzata; }
    public int getEventoID() { return eventoID; } 
    public int getID() { return ID; }
    
}
