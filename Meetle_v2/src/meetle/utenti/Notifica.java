package meetle.utenti;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Notifica implements Serializable {
    private String messaggio;
    private LocalDateTime dataora;
    private boolean visualizzata;
    private int eventoID;

    public Notifica(int eID, String messaggio, LocalDateTime dataora) {
        this.messaggio = messaggio;
        this.dataora = dataora;
        this.visualizzata = false;
        this.eventoID = eID;
    }
    
    public Notifica(int eID, String messaggio) {
        this(eID, messaggio, LocalDateTime.now());
    }
    
    public void setVisualizzata() {
        this.visualizzata = true;
    }
    
}
